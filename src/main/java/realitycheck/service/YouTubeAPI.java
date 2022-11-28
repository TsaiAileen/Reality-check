package realitycheck.service;

import realitycheck.model.*;
import java.util.Optional;
import java.util.Random;

import realitycheck.model.User;
import realitycheck.model.monitoring.CommentStatus;
import realitycheck.model.monitoring.VideoInfo;
import realitycheck.model.monitoring.VideoStatus;

public class YouTubeAPI {
		
	public VideoInfo CheckVideoAvailability (Video video)
	{
		VideoInfo videoInfo = new VideoInfo();
		videoInfo.videoStatus = VideoStatus.getRandom(); 		// Get a random value of the video status
		videoInfo.commentStatus = CommentStatus.getRandom(); 	// Get a random value of the comment status
		return videoInfo;
	}
	
	public Boolean LoginToUserAccount(User user)
	{
		// creating Random object
		Random rand = new Random(); 
		Boolean isLoggedIn = rand.nextFloat() < 0.85; // Giving a 85% chance to be true
        System.out.println("Logging in to user's " + user.getName() + " YouTube's account: " +  isLoggedIn);
		return isLoggedIn;
	}
	
	public Boolean InjectComment(Video video, Comment comment)
	{
		// Injects the most UpVoted comment associated to a video
        System.out.println("Injecting comment : " + comment.getDescription() 
        	+ ", to video: " + video.getVideoName());
		return true;
	}
	
	public Boolean LogOutFromUserAccount(Optional<User> user)
	{
        System.out.println("Logging out from user's YouTube account: " + user.get().getName());
		return true;
	}
}
