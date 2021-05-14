package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import adminView.AdminView;
import hrView.HRView;

public class LoginView extends JFrame implements ActionListener {
	
	JButton adminBtn, baristaBtn, hrBtn, managerBtn;
	
	public LoginView() {
		initFrame();
	}
	
	private void initFrame() {
		// TODO Auto-generated method stub
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		initHeader();
		initButton();
		
		setVisible(true);
		setLocation(200, 200);
		setResizable(false);
	}
	
	private void initHeader() {
		// TODO Auto-generated method stub
		JLabel title = new JLabel("Please Login to Your Role");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font(null, Font.BOLD, 18));
		add(title, BorderLayout.NORTH);
	}
	
	private void initButton() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		
		adminBtn = new JButton("Admin");
		baristaBtn = new JButton("Barista");
		hrBtn = new JButton("Human Resource");
		managerBtn = new JButton("Manager");
		
		adminBtn.addActionListener(this);
		baristaBtn.addActionListener(this);
		hrBtn.addActionListener(this);
		managerBtn.addActionListener(this);
		
		panel.add(adminBtn);
		panel.add(baristaBtn);
		panel.add(hrBtn);
		panel.add(managerBtn);
		
		add(panel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(adminBtn)){
			setVisible(false);
			new AdminView();
		} 
		else if (e.getSource().equals(baristaBtn)){
			setVisible(false);
			new BaristaView();
		}
		else if (e.getSource().equals(hrBtn)){
			setVisible(false);
			new HRView();
		}
		else if (e.getSource().equals(managerBtn)){
			setVisible(false);
			new ManagerView();
		}
	}
}
