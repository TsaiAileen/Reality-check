package realitycheck.model.monitoring;

import java.util.Random;

public enum VideoStatus 
{
	videoAvailable,
	videoIsHidden,
	videoUnavailable;
	
	public static VideoStatus getRandom()
	{
		Random rand = new Random();
		float chance = rand.nextFloat();
		VideoStatus newStatus;

		if(chance < 0.5) {			// 50% chance to be available
			newStatus = VideoStatus.videoAvailable;
		}
		else if (chance < 0.75) {	// 25% chance to be hidden
			newStatus = VideoStatus.videoIsHidden;			
		}
		else {						// 25% chance to be unavailable			
			newStatus = VideoStatus.videoUnavailable;
		}		
		
		return newStatus;
	}
}
