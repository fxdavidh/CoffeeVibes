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

import controller.EmployeeDAO;
import controller.PositionDAO;
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
	private EmployeeDAO ed = new EmployeeDAO();
	private PositionDAO pd = new PositionDAO();
	
	private void components() {
		title = new JLabel("Employee List");
		tbl = new JTable();
	}
	
	private void init() {
		components();
		header.add("No. ");
		header.add("ID");
		header.add("Position");
		header.add("Name");
		header.add("Salary");
		header.add("Status");
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
		int count = 1;
		remove(jsp);
		employees = new ArrayList<Employee>(ed.getAllEmployee());
		dtm = new DefaultTableModel(header,0);
		for(Employee i : employees) {
			Vector<Object> employee= new Vector<>();
			employee.add(count);
			employee.add(i.getId());
			employee.add(pd.getPositionName(i.getPositionId()));
			employee.add(i.getName());
			employee.add(i.getSalary());
			employee.add(i.getStatus());
			dtm.addRow(employee);
			count++;
		}
		tbl = new JTable(dtm){
	         public boolean editCellAt(int row, int column, java.util.EventObject e) {
	             return false;
	          }
	    };
	    jsp = new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(15,120,450,250);
		getContentPane().add(jsp);
	}
	
	public ManagerEmployeeView() {
		initFrame();
	}
}
