package realitycheck.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import realitycheck.service.YouTubeController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CaseSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton CaseInjectComment;
	private JButton CaseChannelNomination;
	private JButton CaseVoteChannel;
	private JButton CaseReportComment;

	private YouTubeController youTubeController;
	
	/**
	 * Create the dialog.
	 */
	public CaseSelector(YouTubeController youTubeController) {
		setResizable(false);
		
		this.youTubeController = youTubeController;
		
		setBounds(100, 100, 229, 246);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.EAST);
		
		CaseInjectComment = new JButton("Inject most voted comment");
		CaseInjectComment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SelectCaseGUI(1);
			}
		});
		CaseInjectComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		CaseChannelNomination = new JButton("Channel nomination");
		CaseChannelNomination.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SelectCaseGUI(2);				
			}
		});
		
		CaseVoteChannel = new JButton("Vote existing channel");
		CaseVoteChannel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SelectCaseGUI(3);
			}
		});
		
		CaseReportComment = new JButton("Report Comments");
		CaseReportComment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SelectCaseGUI(4);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(CaseInjectComment, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(CaseChannelNomination, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(CaseVoteChannel, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(CaseReportComment, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(CaseInjectComment)
					.addGap(18)
					.addComponent(CaseChannelNomination)
					.addGap(18)
					.addComponent(CaseVoteChannel)
					.addGap(18)
					.addComponent(CaseReportComment)
					.addGap(17))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			/*{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}*/
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.exit(0);
					}
				});
				buttonPane.add(cancelButton);
			}
		}		
	}	
	
	private void SelectCaseGUI(int caseStudy) {
		
        System.out.println("Selecting Case Study: " + caseStudy);

		switch(caseStudy)
		{
		case 1:
			InjectYTComment injectCommentGUI = new InjectYTComment(youTubeController);
			injectCommentGUI.showDialog();
			break;
		case 2:
			NominateChannel nominateGUI = new NominateChannel(youTubeController);
			nominateGUI.showDialog();
			break;
		case 3:
			VoteChannel voteChannelGUI = new VoteChannel(youTubeController);
			voteChannelGUI.showDialog();
			break;
		case 4:
			ReportComments reportCommentsGUI = new ReportComments(youTubeController);
			reportCommentsGUI.showDialog();
			break;
		}
	}
}
