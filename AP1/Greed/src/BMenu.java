import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BMenu extends JFrame implements MouseListener{
	JButton button1 , button2 , button3;
	JLabel label1 , label2;
	JPanel panel;
	BMenu(){
		//icons and background
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("pic3_4.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(1410 , 500, Image.SCALE_SMOOTH);
		ImageIcon myIcon = new ImageIcon(dimg);
		BufferedImage img2 = null;
		try {
		    img2 = ImageIO.read(new File("M.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg2 = img2;
		
		//panel
		
		panel = new JPanel();
		panel.setBounds(0,0,500,500);
		panel.setOpaque(true);
		panel.setBackground(Color.black);
		panel.setLayout(null);
		//title
		label1 = new JLabel();
		label1.setText("Greed");
		label1.setForeground(Color.yellow);
		label1.setFont(new Font(null, Font.BOLD,30));
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setBounds(150 , 50 , 200 , 80);
		label1.setOpaque(true);
		label1.setBackground(Color.black);
		label1.setBorder(BorderFactory.createLineBorder(Color.yellow, 4, true));
		//background
		label2 = new JLabel();
		label2.setBounds(0 , 0 , 500 , 500);
		label2.setOpaque(true);
		label2.setBackground(Color.blue);
		label2.setIcon(myIcon);
		//-----new game button
		button1 = new JButton();
		button1.setBounds(200,160,100,25);
		button1.setBorder(BorderFactory.createLineBorder(Color.yellow, 1, true));
		button1.setFocusable(false);
		button1.setText("New Game");
		button1.setFont(new Font(null,Font.BOLD,10));
		button1.setBackground(Color.black);
		button1.setForeground(Color.yellow);
		button1.addMouseListener(this);
		//--resume button
		button2 = new JButton();
		button2.setBounds(200,205,100,25);
		button2.setBorder(BorderFactory.createLineBorder(Color.yellow, 1, true));
		button2.setFocusable(false);
		button2.setText("Resume Game");
		button2.setFont(new Font(null,Font.BOLD,10));
		button2.setBackground(Color.black);
		button2.setForeground(Color.yellow);
		button2.addMouseListener(this);
		//--scoreboard button
		button3 = new JButton();
		button3.setBounds(200,250,100,25);
		button3.setBorder(BorderFactory.createLineBorder(Color.yellow, 1, true));
		button3.setFocusable(false);
		button3.setText("Scoreboard");
		button3.setFont(new Font(null,Font.BOLD,10));
		button3.setBackground(Color.black);
		button3.setForeground(Color.yellow);
		button3.addMouseListener(this);
		//-----menu frame

		this.setIconImage(dimg2);
		this.setTitle("Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(500,500);
		this.setResizable(false);
		this.setBackground(Color.black);
		this.add(panel);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(label1);
		panel.add(label2);
		this.setVisible(true);
	}
	public boolean isNumber(String num) {
		if(num.length() == 0) {
			return false;
		}
		for(int i = 0; i < num.length(); i++) {
			if(num.charAt(i) == '1') {
				
			}
			else if (num.charAt(i) == '2') {
				
			}
			else if (num.charAt(i) == '3') {
				
			}
			else if (num.charAt(i) == '4') {
				
			}
			else if (num.charAt(i) == '5') {
	
			}
			else if (num.charAt(i) == '6') {
	
			}
			else if (num.charAt(i) == '7') {
	
			}
			else if (num.charAt(i) == '8') {
	
			}
			else if (num.charAt(i) == '9') {
				
			}
			else if (num.charAt(i) == '0') {

			}
			else {
				return false;
			}
		}
		return true;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button1) {
			String ans = JOptionPane.showInputDialog(null,"Please enter game size : ","Game size",JOptionPane.PLAIN_MESSAGE);
			if(ans != null && isNumber(ans)) {
				Game newGame = new Game(Integer.parseInt(ans),false);
			}
		}
		if(e.getSource() == button2) {
			Game newGame = new Game(1,true);
		}
		if(e.getSource() == button3) {
			ScoreBoard newScore = new ScoreBoard();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button1) {
			button1.setBackground(Color.lightGray);
			button1.setForeground(Color.red);
		}
		if(e.getSource() == button2) {
			button2.setBackground(Color.lightGray);
			button2.setForeground(Color.red);
		}
		if(e.getSource() == button3) {
			button3.setBackground(Color.lightGray);
			button3.setForeground(Color.red);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button1) {
			button1.setBackground(Color.black);
			button1.setForeground(Color.yellow);
		}
		if(e.getSource() == button2) {
			button2.setBackground(Color.black);
			button2.setForeground(Color.yellow);
		}
		if(e.getSource() == button3) {
			button3.setBackground(Color.black);
			button3.setForeground(Color.yellow);
		}
	}
}
