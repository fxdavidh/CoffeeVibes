package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeDAO;
import controller.ProductDAO;
import model.Employee;
import model.Product;

public class HRView extends JFrame {

	JFrame fr;
	JPanel pn;
	JLabel lb;
	JButton btnadd; 
	JButton btnup; 
	JButton btndl; 
	JButton btnpos;
	JTable tbl;
	JScrollPane jsp;
	DefaultTableModel dtm; 
	
	EmployeeDAO employeeDAO = new EmployeeDAO();
	
	private Vector<Employee> employees = new Vector<Employee>();
	private Vector<String> header = new Vector<>();
	
	public HRView() {
		initFrame();
	}
	
	private void components() {
		pn = new JPanel();
		lb = new JLabel("Employee List");
		btnadd = new JButton("Add");
		btnup = new JButton("Update");
		btndl = new JButton("Delete");
		btnpos = new JButton("New Position");
		tbl = new JTable();
		jsp = new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
	
	private void refreshList() {
		remove(jsp);
		employees = employeeDAO.ge
		dtm = new DefaultTableModel(header,0);
		for(Employee i : employees) {
			Vector<Object> employee = new Vector<>();
			employee.add(i.getId());
			employee.add(i.getPositionId());
			employee.add(i.getName());
			employee.add(i.getStatus());
			employee.add(i.getSalary());
			employee.add(i.getUsername());
			employee.add(i.getPassword());
			dtm.addRow(employee);
		}
		tbl = new JTable(dtm){
	         public boolean editCellAt(int row, int column, java.util.EventObject e) {
	             return false;
	          }
	       };
		jsp = new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(0, 80,300,300);
		getContentPane().add(jsp);
	}
	
	private void init() {
		components(); 
		jsp.setBounds(0, 80,300,300);
		header.add("ID ");
		header.add("PositionID");
		header.add("Name");
		header.add("Status");
		header.add("Salary");
		header.add("Username");
		header.add("Password");
		lb.setBounds(110,-10,300,100);
		lb.setFont(new Font(lb.getFont().getName(), Font.PLAIN, 50));
		tbl.setColumnSelectionAllowed(true);
		tbl.setRowSelectionAllowed(true);
		btnpos.setBounds(340,40,100,50);
		btnadd.setBounds(340,120,100,50);
		btnup.setBounds(340,200,100,50);
		btndl.setBounds(340,280,100,50);
		btnadd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add("", "", "", "");
			}
		});
		btnup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				update("", "", "", "");
			}
		});
		btndl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				delete();
			}
		});
		add(btnpos);
		add(btnadd);
		add(btnup);
		add(btndl);
		add(tbl);
		add(lb);
		refreshList();
	}

}
