package realitycheck.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import realitycheck.service.YouTubeController;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class VoteChannel extends JDialog {

	private YouTubeController youTubeController;
	private final JPanel contentPanel = new JPanel();

	public VoteChannel(YouTubeController youTubeController) {
		setResizable(false);
		this.youTubeController = youTubeController;
		setBounds(100, 100, 450, 286);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		JList channelList = new JList(youTubeController.GetChannelList());
		channelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(channelList);

		JLabel lblDownVotes = new JLabel("DownVotes");		
		JLabel lblUpVotesCount = new JLabel("0");
		lblUpVotesCount.setHorizontalAlignment(SwingConstants.RIGHT);		
		JLabel lbDownVotesCount = new JLabel("0");
		lbDownVotesCount.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lbUpVotes = new JLabel("UpVotes");		
		JButton upVoteButton = new JButton("UpVote");		
		upVoteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                youTubeController.ChangeVotes(1, channelList.getSelectedIndex());
                int[] VoteCount = youTubeController.GetVoteCount(channelList.getSelectedIndex());
                lblUpVotesCount.setText(VoteCount[0] + "");
			}
		});
		JButton downVotesButton = new JButton("DownVote");		
		downVotesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                youTubeController.ChangeVotes(2, channelList.getSelectedIndex());
                int[] VoteCount = youTubeController.GetVoteCount(channelList.getSelectedIndex());
                lbDownVotesCount.setText(VoteCount[1] + "");
			}
		});
        channelList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!channelList.getValueIsAdjusting()) {
                    int[] VoteCount = youTubeController.GetVoteCount(channelList.getSelectedIndex());
                    lblUpVotesCount.setText(VoteCount[0] + "");
                    lbDownVotesCount.setText(VoteCount[1] + "");
                }
            }
        });
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(upVoteButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(downVotesButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(154)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lbDownVotesCount, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblUpVotesCount, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDownVotes)
								.addComponent(lbUpVotes, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
							.addGap(48)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(upVoteButton)
						.addComponent(lblUpVotesCount)
						.addComponent(lbUpVotes))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(downVotesButton)
						.addComponent(lbDownVotesCount)
						.addComponent(lblDownVotes))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						VoteChannel.super.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
    public void showDialog() {setVisible(true);}
}
