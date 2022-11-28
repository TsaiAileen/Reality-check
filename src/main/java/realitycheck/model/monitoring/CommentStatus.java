package realitycheck.model.monitoring;

import java.util.Random;

public enum CommentStatus
{
	commentAvailable,
	commentDeleted,
	commentDownvoted,
	commentsDisabled;
	
	public static CommentStatus getRandom()
	{
		Random rand = new Random();
		float chance = rand.nextFloat();
		CommentStatus newStatus;

		if(chance < 0.7) {			// 40% chance to be available
			newStatus = CommentStatus.commentAvailable;
		}
		else if (chance < 0.8) {	// 20% chance to be deleted
			newStatus = CommentStatus.commentDeleted;		
		}
		else if (chance < 0.9) {	// 20% chance to be downVoted
			newStatus = CommentStatus.commentDownvoted;		
		}
		else {						// 20% chance to be disabled
			newStatus = CommentStatus.commentsDisabled;
		}		
		
		return newStatus;
	}
}
