package realitycheck.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Video {

	@Id
	private Integer id ;
	
	private Integer channelId;
	private String videoURL ;
	private String videoName ;
	private Integer upVotes ;
	private Integer downVotes ;
	
	public Video (Integer id, Integer channelId, String videoURL, String videoName)
	{
		this.id = id;
		this.channelId = channelId;
		this.videoURL = videoURL;
		this.videoName = videoName;
		this.upVotes = 0;
		this.downVotes = 0;
	}

	public Video() {}

	public Integer getId() {
        return id;
    }

    public String getVideoURL() {
        return videoURL;
    }
    
    public String getVideoName() {
    	return videoName;
    }
    
    public Integer getVotesDifference() {
    	return upVotes - downVotes;
    }
}
