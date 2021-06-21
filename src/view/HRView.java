package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeDAO;
import controller.PositionDAO;
import controller.ProductDAO;
import model.Employee;
import controller.PositionDAO;
import controller.ProductDAO;
import model.Employee;
import model.Position;
import model.Product;

public class HRView extends JFrame {

	private JFrame fr;
	private JPanel pn;
	private JLabel lb;
	private JButton btnadd; 
	private JButton btnup; 
	private JButton btndl; 
	private JButton btnpos;
	private JButton btnbc;
	private JTable tbl;
	private JScrollPane jsp = new JScrollPane();
	private DefaultTableModel dtm; 
	
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	private PositionDAO positionDAO = new PositionDAO();
	
	private Vector<Employee> employees = new Vector<Employee>();
	private Vector<Position> positions = new Vector<Position>();
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
		btnbc = new JButton("Back");
		tbl = new JTable();
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
		remove(jsp);
		employees = employeeDAO.getAllEmployee();
		positions = positionDAO.getAllPosition();
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
		tbl.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
		            if (me.getClickCount() == 2) {
		               view();
		            }
		         }
			});
		jsp = new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(15,120,450,250);
		getContentPane().add(jsp);
	}
	
	private void init() {
		components(); 
		header.add("ID ");
		header.add("PositionID");
		header.add("Name");
		header.add("Status");
		header.add("Salary");
		header.add("Username");
		header.add("Password");
		lb.setBounds(120,0,300,100);
		lb.setFont(new Font(lb.getFont().getName(), Font.PLAIN, 30));
		lb.setForeground(Color.WHITE);
		tbl.setColumnSelectionAllowed(true);
		tbl.setRowSelectionAllowed(true);
		btnpos.setBounds(345,60,120,50);
		btnadd.setBounds(70,375,100,50);
		btnup.setBounds(190,375,100,50);
		btndl.setBounds(310,375,100,50);

		btnpos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addPosition("");
			}
		});

		btnadd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addEmployee("", "", "", "", "", "");
			}
		});
		btnup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				update("", "", "", "", "", "");
			}
		});
		btndl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				delete();
			}
		});
		btnbc.setBounds(0,0,100,50);
		btnbc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new LoginView();
			}
		});
		add(btnpos);
		add(btnadd);
		add(btnup);
		add(btndl);
		add(btnbc);
		add(tbl);
		add(lb);
		refreshList();
	}
	
	private void addPosition(String name) {
		JPanel panel = new JPanel();
		TextField nameTxt;
		nameTxt = new TextField();
		
		panel.setLayout(new GridLayout(0,1));
		
		panel.add(new JLabel("Position Name : "));
		panel.add(nameTxt);
		nameTxt.setText(name);
		
		int choice = JOptionPane.showConfirmDialog(null,panel, 
	               "Add new Position to list", JOptionPane.OK_CANCEL_OPTION);
		JOptionPane warning = new JOptionPane();
		if (choice == JOptionPane.OK_OPTION) {
			if(nameTxt.getText().isEmpty()) {
				warning.showMessageDialog(null, "Position Name Cannot Be Empty");
				addPosition(nameTxt.getText());
			}else {
				for(Position i : positions) {
					if(i.getName().equalsIgnoreCase(nameTxt.getText())) {
						warning.showMessageDialog(null, "This Position Has Been Registered");
						addPosition(nameTxt.getText());
					}
				}
				positionDAO.insertPosition(nameTxt.getText());
				refreshList();
			}
		}
	}
	
	private void addEmployee(String positionId, String name, String status, String salary, String username, String password) {

		JPanel panel = new JPanel();
		TextField nameTxt, salaryTxt, usernameTxt, passwordTxt;
		
		ArrayList<String> positionID = new ArrayList<String>();
		positions = positionDAO.getAllPosition();
		
		positionID.add("Select an ID");
		int size = positions.size();
		for(int i=0; i<size; i++) {
			positionID.add(positions.get(i).getName());
		}
		Object[] positionFix = positionID.toArray();
		JComboBox cmbPosition = new JComboBox(positionFix);
		
		nameTxt = new TextField();
		salaryTxt = new TextField();
		usernameTxt = new TextField();
		passwordTxt = new TextField();
		
		panel.setLayout(new GridLayout(0,1));
		
		panel.add(new JLabel("Position ID : "));
		panel.add(cmbPosition);
		panel.add(new JLabel("Name : "));
		panel.add(nameTxt);
		panel.add(new JLabel("Salary : "));
		panel.add(salaryTxt);
		panel.add(new JLabel("Username : "));
		panel.add(usernameTxt);
		panel.add(new JLabel("Password : "));
		panel.add(passwordTxt);
		
		cmbPosition.setSelectedItem(positionId);
		nameTxt.setText(name);
		salaryTxt.setText(salary);
		usernameTxt.setText(username);
		passwordTxt.setText(password);
		
		int choice = JOptionPane.showConfirmDialog(null,panel, 
	               "Add new Employee", JOptionPane.OK_CANCEL_OPTION);
		JOptionPane warning = new JOptionPane();
		
		if (choice == JOptionPane.OK_OPTION) {
			int index = cmbPosition.getSelectedIndex()-1;
			if (index == -1) {
				warning.showMessageDialog(null,"Please select an ID");
				addEmployee(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
							"", salaryTxt.getText(), usernameTxt.getText(), 
							passwordTxt.getText());
			}else {
				if (nameTxt.getText().isEmpty()) {
					warning.showMessageDialog(null, "Name Cannot Be Empty");
					addEmployee(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
							"", salaryTxt.getText(), usernameTxt.getText(), 
							passwordTxt.getText());
				}else {
					if (salaryTxt.getText().isEmpty()) {
						warning.showMessageDialog(null, "Salary Cannot Be Empty");
						addEmployee(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
								"", salaryTxt.getText(), usernameTxt.getText(), 
								passwordTxt.getText());
					}
					else if (!salaryTxt.getText().toString().matches("[-+]?\\d*\\.?\\d+")) {
						warning.showMessageDialog(null, "Salary Must Be Numeric");
						addEmployee(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
								"", salaryTxt.getText(), usernameTxt.getText(), 
								passwordTxt.getText());			
					}
					else if (Integer.parseInt(salaryTxt.getText().toString()) < 1) {
						warning.showMessageDialog(null, "Salary Must Be More Than 1");
						addEmployee(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
								"", salaryTxt.getText(), usernameTxt.getText(), 
								passwordTxt.getText());				
					}else {
						if (usernameTxt.getText().isEmpty()) {
							warning.showMessageDialog(null, "Username Cannot Be Empty");
							addEmployee(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
									"", salaryTxt.getText(), usernameTxt.getText(), 
									passwordTxt.getText());
						}else {
							if (passwordTxt.getText().isEmpty()) {
								warning.showMessageDialog(null, "Password Cannot Be Empty");
								addEmployee(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
										"", salaryTxt.getText(), usernameTxt.getText(), 
										passwordTxt.getText());
							}else {
								employeeDAO.insertEmployee(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), "A", salaryTxt.getText(), usernameTxt.getText(), passwordTxt.getText());
								refreshList();
							}
						}
					}
				}
			}
		}
	}
	
	private void update(String positionId, String name, String status, String salary, String username, String password) {
		int index = -1;
		index = tbl.getSelectedRow();
		
		if(index == -1) {
			JOptionPane.showMessageDialog(null, "Please select an Employee to UPDATE");
			refreshList();
		} else {
			JPanel panel = new JPanel();
			TextField nameTxt, salaryTxt, usernameTxt, passwordTxt;
			String id = tbl.getValueAt(index, 0).toString();
			
			ArrayList<String> positionID = new ArrayList<String>();
			positions = positionDAO.getAllPosition();
			
			positionID.add("Select an ID");
			int size = positions.size();
			for(int i=0; i<size; i++) {
				positionID.add(positions.get(i).getName());
			}
			Object[] positionFix = positionID.toArray();
			JComboBox cmbPosition = new JComboBox(positionFix);
			
			ArrayList<String> statusID = new ArrayList<String>();
			statusID.add("Active");
			statusID.add("Fired");
			Object[] statusFix = statusID.toArray();
			JComboBox cmbStatus = new JComboBox(statusFix);
			
			nameTxt = new TextField();
			salaryTxt = new TextField();
			usernameTxt = new TextField();
			passwordTxt = new TextField();
			
			panel.setLayout(new GridLayout(0,1));
			
			panel.add(new JLabel("Position ID : "));
			panel.add(cmbPosition);
			panel.add(new JLabel("Name : "));
			panel.add(nameTxt);
			panel.add(new JLabel("Status : "));
			panel.add(cmbStatus);
			panel.add(new JLabel("Salary : "));
			panel.add(salaryTxt);
			panel.add(new JLabel("Username : "));
			panel.add(usernameTxt);
			panel.add(new JLabel("Password : "));
			panel.add(passwordTxt);
			
			for(int i=0; i<positions.size(); i++) {
				if (Integer.parseInt(tbl.getValueAt(index, 1).toString()) == positions.get(i).getId()) {
					positionId = positions.get(i).getName();
				}
			}
			
			cmbPosition.setSelectedItem(positionId);
			nameTxt.setText(tbl.getValueAt(index, 2).toString());
			cmbStatus.setSelectedItem(tbl.getValueAt(index, 3).toString());
			salaryTxt.setText(tbl.getValueAt(index, 4).toString());
			usernameTxt.setText(tbl.getValueAt(index, 5).toString());
			passwordTxt.setText(tbl.getValueAt(index, 6).toString());
			
			int choice = JOptionPane.showConfirmDialog(null,panel, 
		               "Add new Employee", JOptionPane.OK_CANCEL_OPTION);
			JOptionPane warning = new JOptionPane();
			
			if (choice == JOptionPane.OK_OPTION) {
				int select = cmbPosition.getSelectedIndex()-1;
				if (select == -1) {
					warning.showMessageDialog(null,"Please select an ID");
					update(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
								"", salaryTxt.getText(), usernameTxt.getText(), 
								passwordTxt.getText());
				}else {
					if (nameTxt.getText().isEmpty()) {
						warning.showMessageDialog(null, "Name Cannot Be Empty");
						update(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
								"", salaryTxt.getText(), usernameTxt.getText(), 
								passwordTxt.getText());
					}else {
						if (salaryTxt.getText().isEmpty()) {
							warning.showMessageDialog(null, "Salary Cannot Be Empty");
							update(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
									"", salaryTxt.getText(), usernameTxt.getText(), 
									passwordTxt.getText());
						}
						else if (!salaryTxt.getText().toString().matches("[-+]?\\d*\\.?\\d+")) {
							warning.showMessageDialog(null, "Salary Must Be Numeric");
							update(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
									"", salaryTxt.getText(), usernameTxt.getText(), 
									passwordTxt.getText());				
						}
						else if (Integer.parseInt(salaryTxt.getText().toString()) < 1) {
							warning.showMessageDialog(null, "Salary Must Be More Than 1");
							update(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
									"", salaryTxt.getText(), usernameTxt.getText(), 
									passwordTxt.getText());				
						}else {
							if (usernameTxt.getText().isEmpty()) {
								warning.showMessageDialog(null, "Username Cannot Be Empty");
								update(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
										"", salaryTxt.getText(), usernameTxt.getText(), 
										passwordTxt.getText());
							}else {
								if (passwordTxt.getText().isEmpty()) {
									warning.showMessageDialog(null, "Password Cannot Be Empty");
									update(cmbPosition.getSelectedItem().toString(), nameTxt.getText(), 
											"", salaryTxt.getText(), usernameTxt.getText(), 
											passwordTxt.getText());
								}else {
									employeeDAO.updateEmployee(id, cmbPosition.getSelectedItem().toString(), nameTxt.getText(), cmbStatus.getSelectedItem().toString(), salaryTxt.getText(), usernameTxt.getText(), passwordTxt.getText());
									refreshList();
								}
							}
						}
					}
				}
			}
		}
	}
	
	private void delete() {
		int index = -1;
		index = tbl.getSelectedRow();
		
		if(index == -1) {
			JOptionPane.showMessageDialog(null, "Please select an Employee to DELETE");
			refreshList();
		}
		else {
			String id = tbl.getValueAt(index, 0).toString();

			JPanel panel = new JPanel();
			JLabel warning;
			
			String name = tbl.getValueAt(index, 2).toString();
			String message = "Are you sure you want to DELETE " + name +"?";
			warning = new JLabel(message);
			panel.add(warning);
			int choice = JOptionPane.showConfirmDialog(null, panel, "Confirm Delete", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				employeeDAO.deleteEmployee(id);
				refreshList();
			}
		}
	}

	private void view() {
		int index = -1;
		index = tbl.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(null, "Please select an Employee to VIEW");
			refreshList();
		}
		else {
           JOptionPane view = new JOptionPane();
           view.showMessageDialog(null,"Employee ID : " + tbl.getValueAt(index, 0).toString() + "\n" +
									   "Employee Position ID : " + tbl.getValueAt(index, 1).toString() + "\n" +
									   "Employee Name : " + tbl.getValueAt(index, 2).toString() + "\n" +
									   "Employee Status : " + tbl.getValueAt(index, 3).toString() + "\n" +
									   "Employee Salary : " + tbl.getValueAt(index, 4).toString() + "\n" +
									   "Employee Username : " + tbl.getValueAt(index, 5).toString() + "\n" +
									   "Employee Password : " + tbl.getValueAt(index, 6).toString());
		}
	}
}
