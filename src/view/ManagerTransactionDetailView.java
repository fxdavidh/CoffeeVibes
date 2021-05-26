package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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
	private JButton button1;
	private JButton button2;
	private JScrollPane jsp = new JScrollPane();
	private JTable tbl;
	private DefaultTableModel dtm; 
	private ArrayList<TransactionDetail> transactions = new ArrayList<>();
	private Vector<String> header = new Vector<>();
	private TransactionDetailDAO td = new TransactionDetailDAO();
	private ProductDAO pd = new ProductDAO();
	
	private void components(int index) {
		title = new JLabel("Transactions Detail List");
		TransactionID = new JLabel("Transaction ID: " + Integer.toString(index));
		tbl = new JTable();
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
	               Product product = pd.getProduct(transactions.get(row).getProductId());
	               JOptionPane view = new JOptionPane();
	               view.showMessageDialog(null,"Product ID:" + product.getId() + "\n" +
											   "Product Name:" + product.getName() + "\n" +
											   "Product Description:" + product.getDescription() + "\n" +
										       "Product Price:" + product.getPrice() + "\n" + 
											   "Product Stock:" + product.getStock() + "\n");
	            }
	         }
		});
		jsp = new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(15,120,450,250);
		getContentPane().add(jsp);
	}
	
	private void init(int index) {
		components(index);
		header.add("No. ");
		header.add("Product ID");
		header.add("Quantity");
		title.setBounds(80,0,400,100);
		title.setFont(new Font(title.getFont().getName(), Font.BOLD, 30));
		add(title);
		TransactionID.setBounds(155,50,300,100);
		TransactionID.setFont(new Font(title.getFont().getName(), Font.BOLD, 20));
		add(TransactionID);
		tbl.setColumnSelectionAllowed(true);
		tbl.setRowSelectionAllowed(true);
		add(tbl);
		refreshList(index);
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
}
	
	public ManagerTransactionDetailView(int index) {
		initFrame();
		init(index);
	}
}
