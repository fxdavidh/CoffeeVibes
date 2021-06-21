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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ProductDAO;
import controller.VoucherDAO;
import model.Product;
import model.Voucher;

public class AdminVoucherView extends JFrame {
	
	private JFrame fr;
	private JPanel pn;
	private JLabel lb;
	private JButton btnadd; 
	private JButton btndl; 
	private JButton btnbc; 
	private JButton btnvw; 
	private JTable tbl;
	private JScrollPane jsp = new JScrollPane();
	private DefaultTableModel dtm; 

	private VoucherDAO voucherDAO = new VoucherDAO();
	
	private Vector<Voucher> vouchers = new Vector<Voucher>();
	private Vector<String> header = new Vector<>();
	
	private void components() {
		pn = new JPanel();
		lb = new JLabel("Voucher List");
		btnadd = new JButton("Add");
		btndl = new JButton("Delete");
		btnbc = new JButton("Back");
		btnvw = new JButton("View");
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
		vouchers = voucherDAO.getAllVoucher();
		dtm = new DefaultTableModel(header,0);
		for(Voucher i : vouchers) {
			Vector<Object> voucher = new Vector<>();
			voucher.add(i.getId());
			voucher.add(i.getDiscount());
			voucher.add(i.getStatus());
			dtm.addRow(voucher);
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
		header.add("Discount");
		header.add("Status");
		lb.setBounds(120,0,300,100);
		lb.setFont(new Font(lb.getFont().getName(), Font.PLAIN, 30));
		lb.setForeground(Color.WHITE);
		tbl.setColumnSelectionAllowed(true);
		tbl.setRowSelectionAllowed(true);
		btnvw.setBounds(120,375,100,50);
		btndl.setBounds(240,375,100,50);
		btnadd.setBounds(365,60,100,50);
		btnadd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add("");
			}
		});
		btndl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				delete();
			}
		});
		btnvw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				view();
			}
		});
		btnbc.setBounds(0,0,100,50);
		btnbc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new AdminView();
			}
		});
		add(btnadd);
		add(btndl);
		add(btnbc);
		add(btnvw);
		add(tbl);
		add(lb);
		refreshList();
	}

	private void add(String discount) {
		JPanel panel = new JPanel();
		TextField discountTxt, statusTxt;
		discountTxt = new TextField();
		statusTxt = new TextField();

		panel.add(new JLabel("Discount : "));
		panel.add(discountTxt);
		panel.add(new JLabel("Status : "));
		panel.add(statusTxt);

		discountTxt.setText(discount);
		statusTxt.setText("Active");
		
		panel.setLayout(new GridLayout(0,1));
		
		int choice = JOptionPane.showConfirmDialog(null,panel, 
	               "Add new product to list", JOptionPane.OK_CANCEL_OPTION);
		JOptionPane warning = new JOptionPane();
		if (choice == JOptionPane.OK_OPTION) {
			if(discountTxt.getText().isEmpty()) {
				warning.showMessageDialog(null, "Discount Amount Cannot Be Empty");
				add(discountTxt.getText());
			} 
			else if (!discountTxt.getText().toString().matches("[-+]?\\d*\\.?\\d+")) {
				warning.showMessageDialog(null, "Discount Amount Must Be Numeric");
				add(discountTxt.getText());
			}
			else if (Integer.parseInt(discountTxt.getText().toString()) < 1 || Integer.parseInt(discountTxt.getText().toString()) > 100) {
				warning.showMessageDialog(null, "Discount Amount Must Be Between 1 and 100");
				add(discountTxt.getText());
			} else {
				voucherDAO.insertVoucher(discountTxt.getText(), statusTxt.getText());
				refreshList();
			}
		}
	}
	
	private void delete() {
		int index = -1;
		index = tbl.getSelectedRow();
		
		if(index == -1) {
			JOptionPane.showMessageDialog(null, "Please select a Voucher to DELETE");
			refreshList();
		}
		else {
			String id = tbl.getValueAt(index, 0).toString();

			JPanel panel = new JPanel();
			JLabel warning;
			
			String message = "Are you sure you want to DELETE " + id +"?";
			warning = new JLabel(message);
			panel.add(warning);
			int choice = JOptionPane.showConfirmDialog(null, panel, "Confirm Delete", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				voucherDAO.deleteVoucher(id);
				refreshList();
			}
		}
	}
	
	private void view() {
		int index = -1;
		index = tbl.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(null, "Please select a Voucher to VIEW");
			refreshList();
		}
		else {
           JOptionPane view = new JOptionPane();
           view.showMessageDialog(null,"Product ID: " + tbl.getValueAt(index, 0).toString() + "\n" +
									   "Product Discount: " + tbl.getValueAt(index, 1).toString() + "\n" +
									   "Product Status: " + tbl.getValueAt(index, 2).toString());
		}
	}

	public AdminVoucherView() {
		initFrame();
	}

}
