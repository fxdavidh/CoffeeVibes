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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.TransactionDetailDAO;
import controller.TransactionHeaderDAO;
import model.TransactionHeader;
import model.cartItem;

public class ManagerTransactionView extends JFrame{
	private JLabel title;
	private JButton button1;
	private JButton button2;
	private JScrollPane jsp = new JScrollPane();
	private JTable tbl;
	private DefaultTableModel dtm; 
	private ArrayList<TransactionHeader> transactions = new ArrayList<>();
	private Vector<String> header = new Vector<>();
	private TransactionDetailDAO td = new TransactionDetailDAO();
	private TransactionHeaderDAO th = new TransactionHeaderDAO();
	private void components() {
		title = new JLabel("Transactions List");
		tbl = new JTable();
	}
	
	private void refreshList() {
		int count = 1;
		remove(jsp);
		transactions = th.getTransactionHeaders();
		dtm = new DefaultTableModel(header,0);
		for(TransactionHeader i : transactions) {
			Vector<Object> transaction = new Vector<>();
			transaction.add(count);
			transaction.add(i.getId());
			transaction.add(i.getVoucherId());
			transaction.add(td.getTotalPrice(i.getId()));
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
	               new ManagerTransactionDetailView(transactions.get(row).getId());
	            }
	         }
		});
		jsp = new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(15,100,450,250);
		getContentPane().add(jsp);
	}
	
	private void init() {
		components();
		header.add("No. ");
		header.add("ID");
		header.add("Voucher ID");
		header.add("Total Price");
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
	
	public ManagerTransactionView() {
		initFrame();
	}
}
