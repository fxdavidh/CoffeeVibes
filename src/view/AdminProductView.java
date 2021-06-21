package view;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.sun.javafx.scene.paint.GradientUtils.Point;

import controller.ProductDAO;
import model.Product;
import model.cartItem;

public class AdminProductView extends JFrame {

	private JFrame fr;
	private JPanel pn;
	private JLabel lb;
	private JButton btnadd; 
	private JButton btnup; 
	private JButton btndl; 
	private JButton btnbc; 
	private JButton btnvw; 
	private JTable tbl;
	private JScrollPane jsp = new JScrollPane();
	private DefaultTableModel dtm; 
	
	private ProductDAO productDAO = new ProductDAO();
	
	private Vector<Product> products = new Vector<Product>();
	private Vector<String> header = new Vector<>();
	
	private void components() {
		pn = new JPanel();
		lb = new JLabel("Product List");
		btnadd = new JButton("Add");
		btnup = new JButton("Update");
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
		products = productDAO.getAllProduct();
		dtm = new DefaultTableModel(header,0);
		for(Product i : products) {
			Vector<Object> product = new Vector<>();
			product.add(i.getId());
			product.add(i.getName());
			product.add(i.getDescription());
			product.add(i.getPrice());
			product.add(i.getStock());
			dtm.addRow(product);
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
		header.add("Name");
		header.add("Description");
		header.add("Price");
		header.add("Stock");
		lb.setBounds(150,10,300,100);
		lb.setFont(new Font(lb.getFont().getName(), Font.PLAIN, 30));
		lb.setForeground(Color.WHITE);
		tbl.setColumnSelectionAllowed(true);
		tbl.setRowSelectionAllowed(true);
		btnvw.setBounds(70,375,100,50);
		btnup.setBounds(190,375,100,50);
		btndl.setBounds(310,375,100,50);
		btnadd.setBounds(365,60,100,50);
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
		add(btnup);
		add(btndl);
		add(btnbc);
		add(btnvw);
		add(tbl);
		add(lb);
		refreshList();
	}

	private void add(String name, String desc, String price, String stock) {
		JPanel panel = new JPanel();
		TextField nameTxt, descTxt, priceTxt, stockTxt;
		nameTxt = new TextField();
		descTxt = new TextField();
		priceTxt = new TextField();
		stockTxt = new TextField();
		
		panel.setLayout(new GridLayout(0,1));
		
		panel.add(new JLabel("Name : "));
		panel.add(nameTxt);
		panel.add(new JLabel("Description : "));
		panel.add(descTxt);
		panel.add(new JLabel("Price : "));
		panel.add(priceTxt);
		panel.add(new JLabel("Stock : "));
		panel.add(stockTxt);
		nameTxt.setText(name);
		descTxt.setText(desc);
		priceTxt.setText(price);
		stockTxt.setText(stock);
		
		int choice = JOptionPane.showConfirmDialog(null,panel, 
	               "Add new product to list", JOptionPane.OK_CANCEL_OPTION);
		JOptionPane warning = new JOptionPane();
		if (choice == JOptionPane.OK_OPTION) {
			if(nameTxt.getText().isEmpty()) {
				warning.showMessageDialog(null, "Name Cannot Be Empty");
				add(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
			}else {
				if(descTxt.getText().isEmpty()) {
					warning.showMessageDialog(null, "Description Cannot Be Empty");
					add(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
				}else {
					if(priceTxt.getText().isEmpty()){
						warning.showMessageDialog(null, "Price Cannot Be Empty");
						add(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
					}
					else if (!priceTxt.getText().toString().matches("[-+]?\\d*\\.?\\d+")) {
						warning.showMessageDialog(null, "Price Must Be Numeric");
						add(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
					}
					else if (Integer.parseInt(priceTxt.getText().toString()) < 1) {
						warning.showMessageDialog(null, "Price Must Be More Than 1");
						add(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
					}else {
						if(stockTxt.getText().isEmpty()){
							warning.showMessageDialog(null, "Stock Cannot Be Empty");
							add(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
						}
						else if (!stockTxt.getText().toString().matches("[+]?\\d*\\.?\\d+")) {
							warning.showMessageDialog(null, "Stock Must Be a Positive Numeric");
							add(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
						}
						else {
							productDAO.insertProduct(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
							refreshList();
						}
					}
				}
			}
		}
	}
	
	private void update(String name, String desc, String price, String stock) {

		int index = -1;
		index = tbl.getSelectedRow();
		
		if(index == -1) {
			JOptionPane.showMessageDialog(null, "Please select a product to UPDATE");
			refreshList();
		} else {
			String id = tbl.getValueAt(index, 0).toString();
			
			JPanel panel = new JPanel();
			TextField nameTxt, descTxt, priceTxt, stockTxt;
			nameTxt = new TextField();
			descTxt = new TextField();
			priceTxt = new TextField();
			stockTxt = new TextField();
			
			panel.setLayout(new GridLayout(0,1));
			
			panel.add(new JLabel("Name : "));
			panel.add(nameTxt);
			panel.add(new JLabel("Description : "));
			panel.add(descTxt);
			panel.add(new JLabel("Price : "));
			panel.add(priceTxt);
			panel.add(new JLabel("Stock : "));
			panel.add(stockTxt);
			nameTxt.setText(tbl.getValueAt(index, 1).toString());
			descTxt.setText(tbl.getValueAt(index, 2).toString());
			priceTxt.setText(tbl.getValueAt(index, 3).toString());
			stockTxt.setText(tbl.getValueAt(index, 4).toString());
			
			int choice = JOptionPane.showConfirmDialog(null,panel, 
		               "Update a product to list", JOptionPane.OK_CANCEL_OPTION);
			JOptionPane warning = new JOptionPane();
			
			if (choice == JOptionPane.OK_OPTION) {
				if(nameTxt.getText().isEmpty()) {
					warning.showMessageDialog(null, "Name Cannot Be Empty");
					update(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
				}else {
					if(descTxt.getText().isEmpty()) {
						warning.showMessageDialog(null, "Description Cannot Be Empty");
						update(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
					}else {
						if(priceTxt.getText().isEmpty()){
							warning.showMessageDialog(null, "Price Cannot Be Empty");
							update(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
						}
						else if (!priceTxt.getText().toString().matches("[-+]?\\d*\\.?\\d+")) {
							warning.showMessageDialog(null, "Price Must Be Numeric");
							update(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
						}
						else if (Integer.parseInt(priceTxt.getText().toString()) < 1) {
							warning.showMessageDialog(null, "Price Must Be More Than 1");
							update(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
						}else {
							if(stockTxt.getText().isEmpty()){
								warning.showMessageDialog(null, "Stock Cannot Be Empty");
								update(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
							}
							else if (!stockTxt.getText().toString().matches("[+]?\\d*\\.?\\d+")) {
								warning.showMessageDialog(null, "Stock Must Be a Positive Numeric");
								update(nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
							}
							else {
								productDAO.updateProduct(id, nameTxt.getText(), descTxt.getText(), priceTxt.getText(), stockTxt.getText());
								refreshList();
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
			JOptionPane.showMessageDialog(null, "Please select a product to DELETE");
			refreshList();
		}
		else {
			String id = tbl.getValueAt(index, 0).toString();

			JPanel panel = new JPanel();
			JLabel warning;
			
			String name = tbl.getValueAt(index, 1).toString();
			String message = "Are you sure you want to DELETE " + name +"?";
			warning = new JLabel(message);
			panel.add(warning);
			int choice = JOptionPane.showConfirmDialog(null, panel, "Confirm Delete", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				productDAO.deleteProduct(id);
				refreshList();
			}
		}
	}
	
	private void view() {
		int index = -1;
		index = tbl.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(null, "Please select a product to VIEW");
			refreshList();
		}
		else {
           JOptionPane view = new JOptionPane();
           view.showMessageDialog(null,"Product ID: " + tbl.getValueAt(index, 0).toString() + "\n" +
									   "Product Name: " + tbl.getValueAt(index, 1).toString() + "\n" +
									   "Product Description: " + tbl.getValueAt(index, 2).toString() + "\n" +
								       "Product Price: " + tbl.getValueAt(index, 3).toString() + "\n" + 
									   "Product Stock: " + tbl.getValueAt(index, 4).toString() + "\n");
		}
	}
	
	public AdminProductView() {
		initFrame();
	}
}
