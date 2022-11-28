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

public class InjectYTComment extends JDialog {

	private YouTubeController youTubeController;
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public InjectYTComment(YouTubeController youTubeController) {
		setResizable(false);
		
		this.youTubeController = youTubeController;

		setBounds(100, 100, 200, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JButton VideoIsActive = new JButton("Video is active [Event]");
		VideoIsActive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				youTubeController.VideoIsActiveEvent();
			}
		});
		JButton CommentRemoved = new JButton("Comment removed");
		CommentRemoved.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				youTubeController.CommentRemoved();;
			}
		});
		JButton MonitorVideos = new JButton("Monitor videos");
		MonitorVideos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Boolean videoStateChanged = youTubeController.MonitorVideos();
    			System.out.println("Has the video state changed? " + videoStateChanged);
			}
		});

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(VideoIsActive, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addComponent(CommentRemoved, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addComponent(MonitorVideos, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(VideoIsActive)
					.addGap(18)
					.addComponent(CommentRemoved)
					.addGap(18)
					.addComponent(MonitorVideos)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cancelButton.setActionCommand("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						InjectYTComment.super.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}		
	}

	public void showDialog() {
		    setVisible(true);
	}
}
