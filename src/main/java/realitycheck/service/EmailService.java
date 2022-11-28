package realitycheck.service;

import realitycheck.model.User;

public class EmailService {

	public void SendAuthRequest(User user)
	{
		String authMessage = "The logging attempt to the user \"" + user.getName() 
			+ "\" has failed, sending an auth email request to: \"" + user.getEmail();
		System.out.println(authMessage);
	}
	
	public void SendChannelNominationEmail(User user)
	{
		// Class to send an email to the experts to verify a channel
	}

	public void SendCommentReportEmail(User user, String comment, String videoName)
	{
		// Class to send an email to the experts to verify a video
		String reportMessage = "The comment \"" + comment + "\" about video \"" + videoName + "\" is reported to " + user.getName() + "(" + user.getEmail() + ").";
		System.out.println(reportMessage);
	}
}
