package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.TransactionDetail;

public class ManagerEmployeeView extends JFrame{
	
	private JLabel title;
	private JLabel TransactionID;
	private JButton button1;
	private JButton button2;
	private JScrollPane jsp = new JScrollPane();
	private JTable tbl;
	private DefaultTableModel dtm; 
	private ArrayList<Employee> employees = new ArrayList<>();
	private Vector<String> header = new Vector<>();
	
	private void components() {
		title = new JLabel("Employee List");
		tbl = new JTable();
	}
	
	private void init() {
		components();
		header.add("No. ");
		header.add("ID");
		header.add("Voucher ID");
		header.add("Total Price");
		title.setBounds(120,0,300,100);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
		add(title);
		tbl.setColumnSelectionAllowed(true);
		tbl.setRowSelectionAllowed(true);
		add(tbl);
		refreshList();
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
	
	private void refreshList() {
		
	}
	
	public ManagerEmployeeView() {
		initFrame();
	}
}
