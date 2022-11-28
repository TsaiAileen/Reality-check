package realitycheck.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comment {

	@Id
    private Integer id ;
	
	private Integer videoId;
	private Integer userId;
	private String description ;
	private Integer upVotes;
	private Integer downVotes;
		
	public Comment (Integer id, Integer videoId, Integer userId, String description)
	{
		this.id = id;
		this.videoId = videoId;
		this.userId = userId;
		this.description = description;
		this.upVotes = 0;
		this.downVotes = 0;
	}

	public Comment() {}

	public Integer getId() {
        return id;
    }

	public Integer getVideoId() {
		return videoId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
    public String getDescription() {
        return description;
    }
        
    public void setUpVotes(Integer upVotes) {
    	this.upVotes = upVotes;
    }
    
    public void setDownVotes(Integer downVotes) {
    	this.downVotes = downVotes;
    }
    
    public Integer getVotesDifference() {
    	return upVotes - downVotes;
    }
    
}
