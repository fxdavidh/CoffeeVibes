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

import controller.ProductDAO;
import controller.TransactionDetailDAO;
import model.Product;
import model.TransactionDetail;
import model.TransactionHeader;

public class ManagerTransactionDetailView extends JFrame{
	private JLabel title;
	private JLabel TransactionID;
	private JButton btnvw;
	private JButton btnbc;
	private JScrollPane jsp = new JScrollPane();
	private JTable tbl;
	private DefaultTableModel dtm; 
	private ArrayList<TransactionDetail> transactions = new ArrayList<>();
	private Vector<String> header = new Vector<>();
	private TransactionDetailDAO td = new TransactionDetailDAO();
	private ProductDAO pd = new ProductDAO();
	
	private void components(int index) {
		title = new JLabel("Transactions Detail");
		TransactionID = new JLabel("Transaction ID: " + Integer.toString(index));
		tbl = new JTable();
		btnvw = new JButton("View");
		btnbc = new JButton("Back");
	}
	
	private void refreshList(int index) {
		int count = 1;
		remove(jsp);
		transactions = td.getTransactionDetail(index);
		dtm = new DefaultTableModel(header,0);
		for(TransactionDetail i : transactions) {
			Vector<Object> transaction = new Vector<>();
			transaction.add(count);
			transaction.add(i.getProductId());
			transaction.add(i.getQty());
			dtm.addRow(transaction);
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
	
	private void view(int row) {
		   Product product = pd.getProduct(transactions.get(row).getProductId());
           JOptionPane view = new JOptionPane();
           view.showMessageDialog(null,"Product ID:" + product.getId() + "\n" +
									   "Product Name:" + product.getName() + "\n" +
									   "Product Description:" + product.getDescription() + "\n" +
								       "Product Price:" + product.getPrice() + "\n" + 
									   "Product Stock:" + product.getStock() + "\n");
	}
	
	private void init(int index) {
		components(index);
		header.add("No. ");
		header.add("Product ID");
		header.add("Quantity");
		title.setBounds(120,0,400,100);
		title.setFont(new Font(title.getFont().getName(), Font.BOLD, 25));
		title.setForeground(Color.WHITE);
		add(title);
		TransactionID.setBounds(155,50,300,100);
		TransactionID.setFont(new Font(title.getFont().getName(), Font.BOLD, 20));
		TransactionID.setForeground(Color.WHITE);
		add(TransactionID);
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
					warning.showMessageDialog(null,"Select a transaction first \nor double click the desired row");
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
				new ManagerTransactionView();
			}
		});
		add(btnvw);
		add(btnbc);
		refreshList(index);
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
}
	
	public ManagerTransactionDetailView(int index) {
		initFrame();
		init(index);
	}
}
