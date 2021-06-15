package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.scene.layout.Background;

public class LoadingView extends JFrame{
	
	private void initTitle() {
		JLabel title = new JLabel("Coffee Vibes");
		title.setBounds(100,20,300,100);
		title.setFont(new Font(title.getFont().getName(), Font.BOLD, 30));
		title.setForeground(Color.WHITE);
		ImageIcon imgicon = new ImageIcon(new ImageIcon(getClass().getResource("../assets/cup.png")).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)); 
		title.setIcon(imgicon);
		add(title);
		JLabel text2 = new JLabel("Home of delicious coffees");
		text2.setForeground(Color.WHITE);
		text2.setBounds(188,45,300,100);
		add(text2);
	}
	
	private void initFrame() {
		// TODO Auto-generated method stub
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setLayout(null);
		setVisible(true);
		this.getContentPane().setBackground(Color.BLACK);
		initTitle();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);
	}
	
	public LoadingView() {
		initFrame();
		try {
			Thread.sleep(2000);
			setVisible(false);
			new LoginView();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
