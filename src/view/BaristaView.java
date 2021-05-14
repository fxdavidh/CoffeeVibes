package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class BaristaView extends JFrame{
	
	JFrame fr;
	JPanel pn;
	JLabel lb;
	JButton btnco; 
	JButton btnadd; 
	JButton btnvw; 
	JButton btnrv; 
	JTable tbl;
	
	public void components() {
		pn = new JPanel();
		lb = new JLabel("Order Cart");
		btnco = new JButton("Checkout");
		btnadd = new JButton("Add");
		btnvw = new JButton("View");
		btnrv = new JButton("Remove");
		tbl = new JTable();
	}
	
	private void initFrame() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(500,500);
		setVisible(true);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		init();
	}
	
	public void init() {
		components();  
		btnco.setBounds(190,400,100, 40);
		lb.setBounds(110,-10,300,100);
		lb.setFont(new Font(lb.getFont().getName(), Font.PLAIN, 50));
		tbl.setBounds(0, 80,300,300);
		btnadd.setBounds(340,120,100,50);
		btnvw.setBounds(340,200,100,50);
		btnrv.setBounds(340,280,100,50);
		add(btnadd);
		add(btnvw);
		add(btnrv);
		add(tbl);
		add(lb);
		add(btnco);
	
	}
	
	public void add() {
		JOptionPane addOP = new JOptionPane();
		String name = addOP.showInputDialog("Enter Product ID : ");
		
	}
	
	public BaristaView() {
		initFrame();
	}
}
