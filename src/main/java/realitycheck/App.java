package realitycheck;

import realitycheck.model.*;
import realitycheck.model.monitoring.ChannelCategory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import realitycheck.gui.CaseSelector;
import realitycheck.repo.*;
import realitycheck.service.YouTubeController;

@SpringBootApplication
public class App implements InitializingBean {
	
    @Autowired
    private UserRepo users ;
    @Autowired
    private ChannelRepo channels;
    @Autowired
    private VideoRepo videos ;
    @Autowired
    private CommentRepo comments ;
    
    private YouTubeController youTubeController;

    private void setupUsers() {

        System.out.println("Setting up users") ;

        User user ;
        String email;
        String name;
//
        for (int i = 0; i < 100; i++) {
            name = "Bob" + i;
            email = "bob" + i + "@gmail.com";
            user = new User(i, name, email);
            users.save(user);
        }
    }

    private void setupExperts() {

        System.out.println("Setting up experts");

        Expert expert;
        String email;
        String name;

        for (int i = 1000; i < 1005; i++) {
            name = "David" + i;
            email = "david" + i + "@gmail.com";
            expert = new Expert(i, name, email);
            users.save(expert);
        }


    }

    private void setupModerator() {

        System.out.println("Setting up moderators");
    }

    private void setupChannels() {

        System.out.println("Setting up channels") ;

        Channel channel ;
        channel = new Channel(130, 1, "youtu.be/c/FakeScienceChannel", "False theories", ChannelCategory.science);
        channels.save(channel);
        channel = new Channel(131, 1, "youtu.be/c/WeH4t3U", "Hate speech", ChannelCategory.social);
        channel.upVotes = 10;
        channel.downVotes = 20;
        channels.save(channel);        
        channel = new Channel(132, 3, "youtu.be/c/TheDonutsExtremists", "Easy tomislead", ChannelCategory.religion);
        channels.save(channel);        
        channel = new Channel(133, 4, "youtu.be/c/YourHomeworkEZ", "Wrong information", ChannelCategory.education);
        channels.save(channel);        
        channel = new Channel(134, 4, "youtu.be/c/SquareEarthers", "False theories", ChannelCategory.science);
        channels.save(channel);        
        channel = new Channel(140, 5, "youtu.be/c/EZTechHacks", "Easy to mislead", ChannelCategory.technology);
        channels.save(channel);        
        channel = new Channel(141, 5, "youtu.be/c/TheSunIsFadingOut", "Conspiracy theories", ChannelCategory.climate);
        channels.save(channel);        
        channel = new Channel(142, 10, "youtu.be/c/UnitedAgainstBob", "Hate speech", ChannelCategory.social);
        channels.save(channel);        
        channel = new Channel(143, 8, "youtu.be/c/LiarPresident", "False accusations", ChannelCategory.politics);
        channels.save(channel);        
        channel = new Channel(144, 7, "youtu.be/c/LatestTechReviews", "Easy to mislead", ChannelCategory.technology);
        channels.save(channel);        
    }
    private void setupVideos() {

        System.out.println("Setting up videos") ;

        Video video;
        video = new Video(130, 130, "youtu.be/watch?v=gijeigfhuw" ,"Global warming the biggest lie");
        videos.save(video);
        video = new Video(131, 130, "youtu.be/watch?v=gsdnfuhans" ,"North Pole is not real");
        videos.save(video);
        video = new Video(132, 130, "youtu.be/watch?v=gfdgerrrs" ,"Climate conference analysis");
        videos.save(video);
        video = new Video(133, 130, "youtu.be/watch?v=vcxfdsasda" ,"Weather does not exist");
        videos.save(video);

        video = new Video(140, 140, "youtu.be/watch?v=asdrfasdwe" ,"Global warming the biggest lie");
        videos.save(video);
        video = new Video(141, 140, "youtu.be/watch?v=casdFDFSf" ,"North Pole is not real");
        videos.save(video);
        video = new Video(142, 140, "youtu.be/watch?v=gsgryvedhdf" ,"Climate conference analysis");
        videos.save(video);
        video = new Video(143, 140, "youtu.be/watch?v=hdfdyfhwsff" ,"Weather does not exist");
        videos.save(video);
    }

    private void setupComments() {

        System.out.println("Setting up comments") ;

        Comment comment ;
        comment = new Comment(1,130, 1,"This video leads to misinformation");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);
        comment = new Comment(2,130, 30,"Take this video down please");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);
        comment = new Comment(3,130, 10,"We are trying to take down fake videos");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);

        comment = new Comment(4,131, 4,"This video leads to misinformation");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);
        comment = new Comment(5,131, 20,"Take this video down please");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);
        comment = new Comment(6,131, 18,"We are trying to take down fake videos");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);
        
        comment = new Comment(7,132, 7,"This video leads to misinformation");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);
        comment = new Comment(8,132, 11,"Take this video down please");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);
        comment = new Comment(9,132, 13,"We are trying to take down fake videos");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);

        comment = new Comment(10,140, 15,"This video leads to misinformation");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);
        comment = new Comment(11,140, 5,"Take this video down please");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);
        comment = new Comment(12,140, 32,"We are trying to take down fake videos");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);

        comment = new Comment(13,141, 40,"This video leads to misinformation");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);
        comment = new Comment(14,141, 64,"Take this video down please");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);
        comment = new Comment(15,141, 75,"We are trying to take down fake videos");
        comment.setUpVotes(getRandomNumber(50,200));
        comment.setDownVotes(getRandomNumber(0,100));
        comments.save(comment);
    }
    
    private int getRandomNumber(Integer min, Integer max)
    {
    	return (int) ((Math.random() * (max - min)) + min);
    }

    public static void main(String args[]) {
    	
    	SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
        builder.headless(false).run(args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        setupUsers();
        setupExperts();
        setupChannels();
        setupVideos();
        setupComments();
        
        // Setting up YouTubeController variables
        this.youTubeController = new YouTubeController(this.users, this.videos, this.comments, this.channels);
                
        CaseSelector gui = new CaseSelector(youTubeController);  //(this.students) ;
        gui.pack();
        gui.setVisible(true);
    }
}

