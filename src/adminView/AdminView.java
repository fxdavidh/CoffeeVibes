package adminView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminView extends JFrame implements ActionListener {
	
	JButton insertBtn, viewBtn, updateBtn, deleteBtn;
	
	public AdminView() {
		// TODO Auto-generated constructor stub
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
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);
	}
	
	private void initHeader() {
		// TODO Auto-generated method stub
		JLabel title = new JLabel("Welcome Back Admin");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font(null, Font.BOLD, 18));
		add(title, BorderLayout.NORTH);
	}
	
	private void initButton() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		
		insertBtn = new JButton("Insert Product");
		viewBtn = new JButton("View Product");
		updateBtn = new JButton("Update Product");
		deleteBtn = new JButton("Delete Product");
		
		viewBtn.addActionListener(this);
		viewBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		
		panel.add(viewBtn);
		panel.add(viewBtn);
		panel.add(updateBtn);
		panel.add(deleteBtn);
		
		add(panel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(viewBtn)){
			setVisible(false);
			new ProductGetMenu();
		} 
		else if (e.getSource().equals(insertBtn)){
			setVisible(false);
			new ProductInsertMenu();
		}
		else if (e.getSource().equals(updateBtn)){
			setVisible(false);
			new ProductUpdateMenu();
		}
		else if (e.getSource().equals(deleteBtn)){
			setVisible(false);
			new ProductDeleteMenu();
		}
	}
}
