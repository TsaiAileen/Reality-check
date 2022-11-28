package realitycheck.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Controller;
import realitycheck.model.*;
import realitycheck.repo.*;
import realitycheck.model.monitoring.*;

@Controller
public class YouTubeController {
	
    private UserRepo users ;
    private VideoRepo videos ;
    private CommentRepo comments ;
	private ChannelRepo channels ;
        
	// Create an instance of the YouTubeAPI class to simulate YouTube interaction
	private YouTubeAPI youTubeSim = new YouTubeAPI();
	private EmailService emailService = new EmailService();
	private final Integer commentVotesThreshold = 100;
	
	public YouTubeController(UserRepo users, VideoRepo videos, CommentRepo comments, ChannelRepo channels)
	{
		this.comments = comments;
		this.users = users;
		this.videos = videos;
		this.channels = channels;
	}
	
	public Boolean MonitorVideos() 
	{
		VideoInfo videoInfo;
		Boolean commentsStatusChanged = false;

		System.out.println("\n---------------------- Starting Monitor Videos Case -------------------------------------------------------\n");
		
		for (Video video: videos.findAll())
		{
			videoInfo = youTubeSim.CheckVideoAvailability(video);
			System.out.println("- Checking video availability for video: " + video.getVideoName()
					+ "\n    video status: " + videoInfo.videoStatus
					+ "\n    comment status: " + videoInfo.commentStatus);
	        if(videoInfo.videoStatus == VideoStatus.videoAvailable && !(videoInfo.commentStatus == CommentStatus.commentsDisabled))
			{
	        	List<Comment> videoComments = GetCommentsFromVideo(video);
	        	if(videoComments.size() == 0)
	        	{
	    			System.out.println("No comments associated to this video, deleting video: " + video.getVideoName());
	        		videos.delete(video);
	        		continue;
	        	}
	        	
	        	// Picking a random comment to continue with the case
	    		Random rand = new Random();
	    		Comment currentComment = videoComments.get(rand.nextInt(videoComments.size()));

				if(currentComment != null && (videoInfo.commentStatus == CommentStatus.commentDeleted || videoInfo.commentStatus == CommentStatus.commentDownvoted))
				{
	    			System.out.println("Comment deleted or downvoted, removing from video: " + video.getVideoName()
	    				+ ", Comment id: " + currentComment.getId());
	    			comments.delete(currentComment);
					commentsStatusChanged = true;
				}
			}
	        else
	        {
    			System.out.println("--> Video is hidden or comments are disabled, deleting video: " + video.getVideoName());
        		videos.delete(video);	        	
	        }
		}
		return commentsStatusChanged;
	}
	
	public void VideoIsActiveEvent()
	{		
		List<Video> allVideos = new ArrayList<Video>();		

		System.out.println("\n---------------------- Starting New Video Active Case -------------------------------------------------------\n");

        System.out.println("There are " + videos.count() + " videos in the repo.");

        videos.findAll().forEach(allVideos::add);

		if(allVideos.size() == 0)
		{
			// No more videos to call
			return;
		}
		
		Random rand = new Random();
		Video video = allVideos.get(rand.nextInt(allVideos.size()));

        System.out.println("Video: " + video.getVideoName() + " , was the video selected to inject comments.");
		
		VideoInfo videoInfo = youTubeSim.CheckVideoAvailability(video);
		
        System.out.println("Video availability check returned with values:"
        		+ "\n  - Status: "+ videoInfo.videoStatus
        		+ "\n  - Comment status:" + videoInfo.commentStatus);
		
		// End the process if the video is not available or the comments are disabled
		if(videoInfo.videoStatus != VideoStatus.videoAvailable || videoInfo.commentStatus == CommentStatus.commentsDisabled)
		{
	        System.out.println("Deleting video from the Database: "
	        		+ video.getVideoName());
			videos.delete(video);
			return;
		}		
		
		// Get most UpVoted comment
		Comment commentToInject = GetNextCommentToInject(video);

		if (commentToInject == null) {
	        System.out.println("No comments left to inject now. Finishing the process.");
			return;			
		}
		
        System.out.println("Trying to get credentials from userID: " 
        + commentToInject.getUserId());

        // Injecting next comment
        InjectNextComment(video, commentToInject);
	}
		
	public void CommentRemoved() {

		System.out.println("\n---------------------- Starting Comment Removed Case -------------------------------------------------------\n");
		// Get a random comment		
		List<Comment> allComments = new ArrayList<Comment>();		
        comments.findAll().forEach(allComments::add);
		Random rand = new Random();
		Comment currentComment = allComments.get(rand.nextInt(allComments.size()));

        System.out.println("There are " + comments.count() + " comments in the repo."
        		+ "\nThe comment removed was: " + currentComment.getDescription());

		Comment nextCommentToInjext = GetNextCommentToInject(currentComment);
		
		Optional<Video> video = videos.findById(currentComment.getVideoId());
		
		// Double checking if the video exist, anyway the DB has on delete cascade on the videos
		// so is very unlikely to get a comment without a video associated to it.
		if(video.get() == null)
		{
	        System.out.println("The video associated to that comment was deleted, removing comment");
	        comments.delete(nextCommentToInjext);
	        return;
		}
		
		InjectNextComment(video.get(), nextCommentToInjext);
	}
	
	private Comment GetNextCommentToInject(Comment comment)
	{
		Video video = videos.findById(comment.getVideoId()).get();
		if (video == null)
		{
	        System.out.println("The video associated to the comment was removed, deleting video: "
	        		+ video.getVideoName());
			videos.delete(video);
			return null;
		}
		return GetNextCommentToInject(video);
	}
	
	private Comment GetNextCommentToInject(Video video)
	{
		Comment nextCommentToInject = null;
		List<Comment> associatedComments = GetCommentsFromVideo(video);
		if (associatedComments.size() == 0)
		{
			// Comments exhausted
	        System.out.println("Comments associated to the video exhausted, deleting video: "
	        		+ video.getVideoName());
			videos.delete(video);
			return null;
		}
		else
		{
	        for(Comment comment: associatedComments) {
	        	if(comment.getVotesDifference() > commentVotesThreshold) {
	        		nextCommentToInject = comment;
	        		break;
	        	}
	        }
			
	        if(nextCommentToInject == null) {
				System.out.println("There are no comments left with enough upvotes");
				return null;
	        }
	        
			System.out.println("Comment to inject: "
	        		+ nextCommentToInject.getDescription() + ", with votes threshold: "
					+ nextCommentToInject.getVotesDifference());
		}
		return nextCommentToInject;
	}
	
	private void InjectNextComment(Video video, Comment comment) {
        // Get User Credentials
		Optional<User> user = users.findById(comment.getUserId());
		if (user.get() == null)
		{
			// User associated to the video not found
	        System.out.println("User associated to the comment not found.");
			return;
		}
		
		if(youTubeSim.LoginToUserAccount(user.get()))
		{
	        System.out.println("Logged in to the user account: " + user.get().getName());
			youTubeSim.InjectComment(video, comment);
			youTubeSim.LogOutFromUserAccount(user);
		}
		else
		{
			emailService.SendAuthRequest(user.get());
		}
	}

	public String[] GetChannelList() {
		List<Channel> allChannels = new ArrayList<Channel>();
		channels.findAll().forEach(allChannels::add);
		String[] channelNames = new String[(int)channels.count()];
		for (int i = 0; i < channels.count(); i++) {
			channelNames[i] = allChannels.get(i).getChannelURL();
		}
		return channelNames;
	}

	public int[] GetVoteCount(int channelId) {
		List<Channel> allChannels = new ArrayList<Channel>();
		channels.findAll().forEach(allChannels::add);
		int upvotes = allChannels.get(channelId).upVotes;
		int downvotes = allChannels.get(channelId).downVotes;
		int[] voteCount = new int[2];
		voteCount[0] = upvotes;
		voteCount[1] = downvotes;
		return voteCount;
	}


	public void ChangeVotes(int flag, int channelId) {
		List<Channel> allChannels = new ArrayList<Channel>();
		channels.findAll().forEach(allChannels::add);
		if (flag == 1) {
			allChannels.get(channelId).setUpVotes();
			Channel newChannel = allChannels.get(channelId);
			channels.save(newChannel);
		}
		else {
			allChannels.get(channelId).setDownVotes();
			Channel newChannel = allChannels.get(channelId);
			channels.save(newChannel);
		}
	}

	public String[] GetCommentList() {
		List<Video> allVideos = new ArrayList<Video>();
		List<Comment> allComments = new ArrayList<Comment>();
		videos.findAll().forEach(allVideos::add);
		comments.findAll().forEach(allComments::add);
		String[] commentDescriptions = new String[(int)comments.count()];
		for (int i = 0; i < comments.count(); i++) {
			int videoId = allComments.get(i).getVideoId();
			String fullName = "(" + videos.findById(videoId).get().getVideoName() + ") " + allComments.get(i).getDescription();
			commentDescriptions[i] = fullName;
		}
		return commentDescriptions;
	}

	public void MakeReport(int commentId) {
		Random rand = new Random();
		int expertId = rand.nextInt(1005-1000+1) + 1000;
		List<User> allUsers = new ArrayList<User>();
		List<Video> allVideos = new ArrayList<Video>();
		List<Comment> allComments = new ArrayList<Comment>();
		users.findAll().forEach(allUsers::add);
		videos.findAll().forEach(allVideos::add);
		comments.findAll().forEach(allComments::add);
		String reportMessage;
		String commentDescription = allComments.get(commentId).getDescription();
		String videoName = videos.findById(allComments.get(commentId).getVideoId()).get().getVideoName();
		emailService.SendCommentReportEmail(users.findById(expertId).get(), commentDescription, videoName);
	}

	public void NominateChannel(Integer id,Integer userId, String channelURL, String description, ChannelCategory category) {
        Channel newChannel = new Channel(id, userId, channelURL, description, category);
        channels.save(newChannel);
	}
	
	private List<Comment> GetCommentsFromVideo(Video video)
	{
		return comments.findByVideoId(video.getId());
	}
	
}
