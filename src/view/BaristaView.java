package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
import controller.TransactionHeaderDAO;
import controller.VoucherDAO;
import jdk.nashorn.internal.scripts.JO;
import model.Product;
import model.TransactionDetailModel;
import model.Voucher;
import model.cartItem;


public class BaristaView extends JFrame{
	
	JFrame fr;
	JPanel pn;
	JPanel panel;
	JLabel lb;
	JTextField ID;
	JButton btnco; 
	JButton btnadd; 
	JButton btnvw; 
	JButton btnrv; 
	JTable tbl;
	JLabel total;
	JOptionPane addOP;
	JButton check;
	JScrollPane jsp;
	DefaultTableModel dtm; 
	private ArrayList<cartItem> cart = new ArrayList<>();
	private Vector<String> header = new Vector<>();
	private int totalprice = 0;
	private int tempprice = 0;
	
	private void components() {
		panel = new JPanel();
		pn = new JPanel();
		lb = new JLabel("Order Cart");
		btnco = new JButton("Checkout");
		btnadd = new JButton("Add");
		btnvw = new JButton("View");
		btnrv = new JButton("Remove");
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
	
	private void refreshCart() {
		int count = 1;
		remove(jsp);
		dtm = new DefaultTableModel(header,0);
		for(cartItem i : cart) {
			Vector<Object> product = new Vector<>();
			product.add(count);
			product.add(i.getProduct().getName());
			product.add(i.getQuantity());
			product.add(i.getProduct().getPrice());
			dtm.addRow(product);
			count++;
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
	
	private void addtoCart(Product product,int quantity) {
		int index = -1;
		int count = 0;
		for(cartItem i : cart) {
			if(i.getProduct().getId() == product.getId()) {
				index = count;
				break;
			}
			count++;
		}
		if(index != -1) {
			cart.get(index).setQuantity(cart.get(index).getQuantity() + quantity);
		}else {
			cart.add(new cartItem(product, quantity));
		}
	}
	
	private void removeFromCart(int index,int quantity) {
		if(cart.get(index).getQuantity() == quantity) {
			cart.remove(index);
		}else {
			cart.get(index).setQuantity(cart.get(index).getQuantity() - quantity);
		}
		refreshCart();
	}
	
	private void init() {
		components(); 
		totalprice = 0;
		tempprice = 0;
		jsp.setBounds(0, 80,300,300);
		refreshCart();
		header.add("No. ");
		header.add("Name");
		header.add("Quantity");
		header.add("Price");
		btnco.setBounds(190,400,100, 40);
		lb.setBounds(110,-10,300,100);
		lb.setFont(new Font(lb.getFont().getName(), Font.PLAIN, 50));
		tbl.setColumnSelectionAllowed(true);
		tbl.setRowSelectionAllowed(true);
		btnadd.setBounds(340,120,100,50);
		btnvw.setBounds(340,200,100,50);
		btnrv.setBounds(340,280,100,50);
		btnadd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add();
			}
		});
		btnvw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tbl.getSelectedRow() == -1) {
					JOptionPane warning = new JOptionPane();
					warning.showMessageDialog(null,"Select an item first");
					return;
				}
				view();
			}
		});
		btnrv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tbl.getSelectedRow() == -1) {
					JOptionPane warning = new JOptionPane();
					warning.showMessageDialog(null,"Select an item first");
					return;
				}
				remove();
			}
		});
		btnco.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkout();
			}
		});
		add(btnadd);
		add(btnvw);
		add(btnrv);
		add(tbl);
		add(lb);
		add(btnco);
	}
	
	private void view() {
		JOptionPane view = new JOptionPane();
		int index = tbl.getSelectedRow();
		Product product = cart.get(index).getProduct();
		view.showMessageDialog(null,"Product ID:" + product.getId() + "\n" +
									"Product Name:" + product.getName() + "\n" +
									"Product Description:" + product.getDescription() + "\n" +
									"Product Price:" + product.getPrice() + "\n" + 
									"Product Stock:" + product.getStock() + "\n");
	}
	
	private void add() {
		addOP = new JOptionPane();
		Vector<Product> products = new Vector<>();
		ProductDAO pd = new ProductDAO();
		products = pd.getAllProduct();
		ArrayList<String>names = new ArrayList<>();
		names.add("Select an item");
		int size = products.size();
		for(int i=0;i<size;i++) {
			names.add(products.get(i).getName());
		}
		Object[] namesfix = names.toArray();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.add(new JLabel("Product ID: "));
		JComboBox combo = new JComboBox(namesfix);
		panel.add(combo);
		panel.add(new JLabel("Quantity: "));
		JTextField qty = new JTextField();
		panel.add(qty);
		int choice = JOptionPane.showConfirmDialog(null,panel, 
	               "Add product to cart", JOptionPane.OK_CANCEL_OPTION);
		JOptionPane warning = new JOptionPane();
		if(choice == JOptionPane.OK_OPTION) {
			int index = combo.getSelectedIndex()-1;
			if(index == -1){
				warning.showMessageDialog(null,"Please select an item");
				add();
			}else {
				int stock = products.get(index).getStock();
				if(qty.getText().isEmpty()) {
					warning.showMessageDialog(null,"Please input quantity");
					add();
				}else {
					int quantity = Integer.parseInt(qty.getText());
					if(quantity > stock) {
						warning.showMessageDialog(null,"Stock is not enough\nCurrent stock : " + stock);
						add();
					}else {
						addtoCart(products.get(index),quantity);
						refreshCart();
					}
				}
			}
		}
	}
	
	private void remove() {
		int index = tbl.getSelectedRow();
		JOptionPane confirm = new JOptionPane();
		int qnow = cart.get(index).getQuantity();
		String quantity = "";
		do{
			quantity = confirm.showInputDialog(null, "Quantity :");
			if(Integer.parseInt(quantity) > qnow) {
				confirm.showMessageDialog(null,"Quantity exceeded item's quantity in cart");				
			}
		}while(Integer.parseInt(quantity) > qnow);
		int choice = confirm.showConfirmDialog(null,"Are you sure to remove this item(s)?\n" + cart.get(index).getProduct().getName() + " : " + quantity,"Confirmation",JOptionPane.YES_NO_OPTION);
		if(choice == JOptionPane.YES_OPTION) {
			removeFromCart(index, Integer.parseInt(quantity));
		}
	}
	
	private boolean validateVoucher(String ID) {
		VoucherDAO vd = new VoucherDAO();
		Voucher voucher = vd.getVoucher(Integer.parseInt(ID));
		JOptionPane result = new JOptionPane();
		if(voucher == null) {
			result.showMessageDialog(null, "Voucher not found");
			return false;
		}else {
			if(voucher.getStatus() == "N") {
				result.showMessageDialog(null, "Voucher is no longer valid");
				return false;
			}else {
				tempprice = 0;
				result.showMessageDialog(null, "Voucher found!\nYou get "+voucher.getDiscount()+"% discount");
				tempprice = totalprice - (totalprice*voucher.getDiscount()/100);
				return true;
			}
		}
	}
	
	private void checkout() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.add(new JLabel("Voucher ID: "));
		ID = new JTextField();
		panel.add(ID);
		check = new JButton("Check Voucher");
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = ID.getText();
				if(!text.isEmpty()) {
					boolean exist = validateVoucher(text);
					if(exist == true) {
						total.setText("Total Price: " + tempprice);
					}else {
						total.setText("Total Price: " + totalprice);
					}
				}else{
					JOptionPane warning = new JOptionPane();
					warning.showMessageDialog(null, "Input Voucher ID first");
				}
			}
		});
		panel.add(check);
		int size = cart.size();
		for(int i=0;i<size;i++) {
			totalprice = cart.get(i).getProduct().getPrice() * cart.get(i).getQuantity();
		}
		total = new JLabel("Total Price: " + totalprice);
		panel.add(total);
		int choice = JOptionPane.showConfirmDialog(null,panel, 
	               total.getText() + "\nCheckout Now?", JOptionPane.OK_CANCEL_OPTION);
		if(choice == JOptionPane.OK_OPTION) {
			JOptionPane warning = new JOptionPane();
			if(totalprice == 0) {
				warning.showMessageDialog(null, "No item in cart");
				checkout();
			}else {
				TransactionHeaderDAO thd = new TransactionHeaderDAO();
				int nextAI = thd.getNextAI();
				thd.insertHeader(Integer.parseInt(ID.getText()));
				int cartSize = cart.size();
				for(int i=0;i<cartSize;i++) {
					TransactionDetailModel tdm = new TransactionDetailModel();
					tdm.insert(nextAI,cart.get(i).getProduct().getId(),cart.get(i).getQuantity());
				}
				setVisible(false);
				new LoginView();
			}
		}
	}
	
	
	public BaristaView() {
		initFrame();
	}
}
