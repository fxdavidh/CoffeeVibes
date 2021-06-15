package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeDAO;
import controller.PositionDAO;
import model.Employee;
import model.Product;
import model.TransactionDetail;

public class ManagerEmployeeView extends JFrame{
	
	private JLabel title;
	private JLabel TransactionID;
	private JButton btnvw;
	private JButton btnbc;
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
		btnvw = new JButton("View");
		btnbc = new JButton("Back");
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
		title.setForeground(Color.WHITE);
		add(title);
		tbl.setColumnSelectionAllowed(true);
		tbl.setRowSelectionAllowed(true);
		add(tbl);
		btnvw.setBounds(185,375,100,50);
		btnvw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tbl.getSelectedRow() == -1) {
					JOptionPane warning = new JOptionPane();
					warning.showMessageDialog(null,"Select a employee first \nor double click the desired row");
					return;
				}
				view(tbl.getSelectedRow());
			}
		});
		btnbc.setBounds(0,0,100,50);
		btnbc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new ManagerView();
			}
		});
		add(btnvw);
		add(btnbc);
		refreshList();
	}
	
	private void view(int row) {
		JOptionPane view = new JOptionPane();
		Employee employee = employees.get(row);
		String status = "";
		if(employee.getStatus() == "Active") {
			status = "ACTIVE";
		}else if(employee.getStatus() == "Fired") {
			status = "FIRED";
		}
		String pos = pd.getPositionName(employee.getPositionId());
		view.showMessageDialog(null,"Employee ID: " + employee.getId() + "\n" +
									"Employee Name: " + employee.getName() + "\n" +
									"Employee Salary: " + employee.getSalary() + "\n" +
									"Employee Position: " + pos + "\n" +
									"Employee Status: " + status +  "\n"); 
	}
	
	private void initFrame() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(500,500);
		this.getContentPane().setBackground(Color.BLACK);
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
	 	tbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow();
	               view(row);
	            }
	         }
		});
	    jsp = new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(15,120,450,250);
		getContentPane().add(jsp);
	}
	
	public ManagerEmployeeView() {
		initFrame();
	}
}
