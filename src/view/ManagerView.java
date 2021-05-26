package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ManagerView extends JFrame{

	private JLabel title;
	private JButton button1;
	private JButton button2;
	
	private void components() {
		title = new JLabel("Welcome Manager");
		button1 = new JButton("Transactions");
		button2 = new JButton("Employees");
	}
	
	private void init() {
		components();
		title.setBounds(120,0,300,100);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
		add(title);
		button1.setBounds(140,150,200,50);
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ManagerTransactionView();
			}
		});
		add(button1);
		button2.setBounds(140,250,200,50);
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerEmployeeView();
			}
		});
		add(button2);
	}
	
	private void initFrame() {
			setLocationRelativeTo(null);
			setDefaultCloseOperation(this.EXIT_ON_CLOSE);
			setLayout(null);
			setSize(500,500);
			this.getContentPane().setBackground(Color.white);
			setVisible(true);
			setResizable(false);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			init();
	}
	
	public ManagerView() {
		initFrame();
	}

}
