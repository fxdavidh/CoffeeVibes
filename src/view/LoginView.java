package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class LoginView extends JFrame implements ActionListener {
	
	JButton adminBtn, baristaBtn, hrBtn, managerBtn;
	
	public LoginView() {
		initFrame();
	}
	
	private void initFrame() {
		// TODO Auto-generated method stub
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);
		initHeader();
		initButton();
		setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);
	}
	
	private void initHeader() {
		JLabel title = new JLabel("Coffee Vibes");
		title.setBounds(150,0,300,100);
		title.setFont(new Font(title.getFont().getName(), Font.BOLD, 30));
		add(title);
	}
	
	private void initButton() {		
		adminBtn = new JButton("Admin");
		baristaBtn = new JButton("Barista");
		hrBtn = new JButton("Human Resource");
		managerBtn = new JButton("Manager");
		
		adminBtn.addActionListener(this);
		adminBtn.setBounds(160,110,150,50);
		baristaBtn.addActionListener(this);
		baristaBtn.setBounds(160,190,150,50);
		hrBtn.addActionListener(this);
		hrBtn.setBounds(160,270,150,50);
		managerBtn.addActionListener(this);
		managerBtn.setBounds(160,350,150,50);
		
		add(adminBtn);
		add(baristaBtn);
		add(hrBtn);
		add(managerBtn);
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
