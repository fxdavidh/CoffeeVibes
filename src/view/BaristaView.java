package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.ProductDAO;
import model.Product;


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
		btnadd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add();
			}
		});
		add(btnadd);
		add(btnvw);
		add(btnrv);
		add(tbl);
		add(lb);
		add(btnco);
	
	}
	
	public void add() {
		JOptionPane addOP = new JOptionPane();
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
		String product = (String) addOP.showInputDialog(null,"Choose product","Add to cart", JOptionPane.DEFAULT_OPTION,null,namesfix,namesfix[0]);
		if(product == "Select an item") {
			JOptionPane warning = new JOptionPane();
			warning.showMessageDialog(null,"Please select an item");;
			add();
		}
	}
	
	public BaristaView() {
		initFrame();
	}
}
