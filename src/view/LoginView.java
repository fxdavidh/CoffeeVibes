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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.EmployeeDAO;
import controller.PositionDAO;


public class LoginView extends JFrame implements ActionListener {
	
	JButton adminBtn, baristaBtn, hrBtn, managerBtn;
	private PositionDAO pd = new PositionDAO();
	private EmployeeDAO ed = new EmployeeDAO();
	
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
	
	private boolean login(String name) {
		int ID = pd.getPositionId(name);
		JOptionPane login = new JOptionPane();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.add(new JLabel("Username: "));
		JTextField username = new JTextField();
		panel.add(username);
		panel.add(new JLabel("Password: "));
		JTextField password = new JTextField();
		panel.add(password);
		int choice = JOptionPane.showConfirmDialog(null,panel, 
	               "Login as " + name, JOptionPane.OK_CANCEL_OPTION);
		if(choice == JOptionPane.OK_OPTION) {
			JOptionPane warning = new JOptionPane();
			if(username.getText().isEmpty()) {
				warning.showMessageDialog(null,"Username Cannot be Empty!!");
				login(name);
				return false;
			}else {
				if(password.getText().isEmpty()) {
					warning.showMessageDialog(null,"Password Cannot be Empty!!");
					login(name);
					return false;
				}else {	
					if(ed.validateLogin(ID,username.getText(),password.getText()) == false) {
						warning.showMessageDialog(null, "Username or Password is incorrect");
						login(name);
						return false;
					}else {
						warning.showMessageDialog(null,"Login as "+ name +" Success");
						return true;
					}								
				}
			}
		}else {
			return false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(adminBtn)){
			if(login("Admin")) {
				setVisible(false);
				new AdminView();
			}
		} 
		else if (e.getSource().equals(baristaBtn)){
			if(login("Barista")) {
				setVisible(false);
				new BaristaView();
			}
		}
		else if (e.getSource().equals(hrBtn)){
			if(login("HR")) {
				setVisible(false);
				new HRView();
			}
		}
		else if (e.getSource().equals(managerBtn)){
			if(login("Manager")) {
				setVisible(false);
				new ManagerView();
			}
		}
	}
}
