package realitycheck.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import realitycheck.model.monitoring.ChannelCategory;

@Entity
public class Channel {

	@Id
    private Integer id ;
	private Integer userId;
	private String channelURL ;
	private String description;
	private String category;
	public Integer upVotes;
	public Integer downVotes;
	private Boolean isActive;
	private Boolean isVerified;

	public Channel() {}
		
	public Channel (Integer id, Integer userId, String channelURL, String description ,ChannelCategory category)
	{
		this.id = id;
		this.userId = userId;
		this.channelURL = channelURL;
		this.description = description;
		this.category = category.toString();
		// Set default values
		this.upVotes = 0;
		this.downVotes = 0;
		this.isVerified = false;
		this.isActive = false;
	}

	public Integer getId() {
        return id;
    }

	public String getDescription() {return description; }

    public String getChannelURL() {
        return channelURL;
    }
    
    public ChannelCategory getCategory()
    {
    	return ChannelCategory.valueOf(category);
    }

    public Boolean isInActiveQueue()
    {
    	return isActive;
    }

	public void setUpVotes() { upVotes++; }

	public void setDownVotes() { downVotes++; }
    
    public void VerifyChannel(boolean verification)
    {
    	this.isVerified = verification;
    }    
}
