import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Game extends JFrame{
	JTextPane board;
	JScrollPane Board;
	StyledDocument doc;
	String text;
	JLabel label1;
	public static JLabel label2;
	Action upA, downA,leftA,rightA,ulA,urA,dlA,drA;
	int X,Y,score,Size;
	long startingTime , endingTime;
	
	public Game(int size,boolean saved) {
		//------
		startingTime = System.currentTimeMillis();
		endingTime = 0;
		score = 0;
		Size = size;
		upA = new UpA();
		downA = new DownA();
		leftA = new LeftA();
		rightA = new RightA();
		ulA = new ULA();
		urA = new URA();
		dlA = new DLA();
		drA = new DRA();
		//---
		label2 = new JLabel("Time : 00:00:00");
		Clock.start();
		
		label2.setLayout(null);
		label2.setOpaque(true);
		label2.setBackground(Color.white);
		label2.setHorizontalAlignment(SwingConstants.LEFT);
		//----icons
		
		BufferedImage img3 = null;
		try {
		    img3 = ImageIO.read(new File("G.jfif"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg3 = img3;
		// score and %
		label1 = new JLabel();
		
		label1.setLayout(null);
		label1.setOpaque(true);
		label1.setBackground(Color.white);
		label1.setHorizontalAlignment(SwingConstants.LEFT);
		//---- text gereration
		text = "";
		if(!saved) {
			for(int i = 0 ; i < size ; i++) {
				for(int j = 0; j < size * 3 ; j++) {
					if(i == (size / 2) && j == (size * 3 / 2) ){
						text += "#";
						X = j;
						Y = i;
					}
					else {
						Random rand = new Random();
						text += Integer.toString(rand.nextInt(9) + 1);
					}
					
				}
				if(i != size-1) {
					text += "\n";
				}
			}
		}
		else {
			text = loadGame();
			for(int i = 0;i < text.length();i++) {
				if(text.charAt(i) == '\n') {
					Size++;
				}
				if(text.charAt(i) == '0') {
					score++;
				}
				
			}
			for(int i = 0;i < text.length();i++) {
				if(text.charAt(i) == '#') {
					Y = i/(Size*3+1);
	        		X = (i - Y*(Size*3+1))%(Size*3+1);
	        		
				}
			}
		}
		String pers = (Double.toString((double)(score*100)/(Size*Size*3 -1)));
		for(int i = 0;i < pers.length() ; i++) {
			if(pers.charAt(i) == '.') {
				pers = pers.substring(0,i+2);
				break;
			}
		}
		label1.setText("Score : " + Integer.toString(score) + "         "+ pers + "%");
		//-----game board
		doc =new DefaultStyledDocument();
		board = new JTextPane(doc);
		setText(text);
		board.setEditable(false);
		board.setBackground(Color.black);
		JPanel nowarp = new JPanel(new BorderLayout());
		nowarp.add(board);
		Board = new JScrollPane(nowarp);
		
		//------game frame
		this.setVisible(true);
		this.setTitle("Greed");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setResizable(true);
		this.setIconImage(dimg3);
		this.getContentPane().setBackground(Color.white);
		this.add(label2);
		this.add(label1);
		this.add(Board);
		this.pack();	
		board.setHighlighter(null);
		board.grabFocus();
		
		board.getInputMap().put(KeyStroke.getKeyStroke('w'),"up");
		board.getActionMap().put("up", upA);
		board.getInputMap().put(KeyStroke.getKeyStroke('s'),"down");
		board.getActionMap().put("down", downA);
		board.getInputMap().put(KeyStroke.getKeyStroke('d'),"right");
		board.getActionMap().put("right", rightA);
		board.getInputMap().put(KeyStroke.getKeyStroke('a'),"left");
		board.getActionMap().put("left", leftA);
		board.getInputMap().put(KeyStroke.getKeyStroke('q'),"lua");
		board.getActionMap().put("lua", ulA);
		board.getInputMap().put(KeyStroke.getKeyStroke('e'),"ru");
		board.getActionMap().put("ru", urA);
		board.getInputMap().put(KeyStroke.getKeyStroke('z'),"ld");
		board.getActionMap().put("ld", dlA);
		board.getInputMap().put(KeyStroke.getKeyStroke('c'),"dr");
		board.getActionMap().put("dr", drA);
	}
	public void setText(String T) {
		board.setText(T);
		SimpleAttributeSet set = new SimpleAttributeSet();
		int fontSize = 18;
        for(int i = 0; i < board.getDocument().getLength(); i++) {
        	StyleConstants.setBackground(set, Color.black);
        	if(T.charAt(i) != '\n' && T.charAt(i) != '0') {
        		int yy = i/(Size*3+1);
        		int xx = (i - yy*(Size*3+1))%(Size*3+1);
        		if(marker(xx, yy)) {
        			StyleConstants.setBackground(set,Color.darkGray);
        		}
        	}
        	if(T.charAt(i) == '1') {
        		StyleConstants.setForeground(set, Color.red);
        		StyleConstants.setFontSize(set, fontSize);
        		StyleConstants.setBold(set, false);
        		//StyleConstants.setBackground(set, Color.black);
        		doc.setCharacterAttributes(i, 1, set, true);
			}
			else if (T.charAt(i) == '2') {
				StyleConstants.setForeground(set, Color.blue);
				StyleConstants.setFontSize(set, fontSize);
				StyleConstants.setBold(set, false);
				//StyleConstants.setBackground(set, Color.black);
				doc.setCharacterAttributes(i, 1, set, true);
			}
			else if (T.charAt(i) == '3') {
				StyleConstants.setForeground(set, Color.yellow);
				StyleConstants.setFontSize(set, fontSize);
				StyleConstants.setBold(set, false);
				//StyleConstants.setBackground(set, Color.black);
				doc.setCharacterAttributes(i, 1, set, true);
			}
			else if (T.charAt(i) == '4') {
				StyleConstants.setForeground(set, Color.cyan);
				StyleConstants.setFontSize(set, fontSize);
				StyleConstants.setBold(set, false);
				//StyleConstants.setBackground(set, Color.black);
				doc.setCharacterAttributes(i, 1, set, true);
			}
			else if (T.charAt(i) == '5') {
				StyleConstants.setForeground(set, Color.green);
				StyleConstants.setFontSize(set, fontSize);
				StyleConstants.setBold(set, false);
				//StyleConstants.setBackground(set, Color.black);
				doc.setCharacterAttributes(i, 1, set, true);
			}
			else if (T.charAt(i) == '6') {
				StyleConstants.setForeground(set, Color.gray);
				StyleConstants.setFontSize(set, fontSize);
				StyleConstants.setBold(set, false);
				//StyleConstants.setBackground(set, Color.black);
				doc.setCharacterAttributes(i, 1, set, true);
			}
			else if (T.charAt(i) == '7') {
				StyleConstants.setForeground(set, Color.orange);
				StyleConstants.setFontSize(set, fontSize);
				StyleConstants.setBold(set, false);
				//StyleConstants.setBackground(set, Color.black);
				doc.setCharacterAttributes(i, 1, set, true);
			}
			else if (T.charAt(i) == '8') {
				StyleConstants.setForeground(set, Color.pink);
				StyleConstants.setFontSize(set, fontSize);
				StyleConstants.setBold(set, false);
			//	StyleConstants.setBackground(set, Color.black);
				doc.setCharacterAttributes(i, 1, set, true);
			}
			else if (T.charAt(i) == '9') {
				StyleConstants.setForeground(set, Color.magenta);
				StyleConstants.setFontSize(set, fontSize);
				StyleConstants.setBold(set, false);
			//	StyleConstants.setBackground(set, Color.black);
				doc.setCharacterAttributes(i, 1, set, true);
			}
			else if (T.charAt(i) == '#') {
				StyleConstants.setForeground(set, Color.black);
				StyleConstants.setFontSize(set, fontSize);
				StyleConstants.setBold(set, true);
				StyleConstants.setBackground(set, Color.white);
				doc.setCharacterAttributes(i, 1, set, true);
			}
			else if (T.charAt(i) == '0') {
				StyleConstants.setForeground(set, Color.black);
				StyleConstants.setFontSize(set, fontSize);
				StyleConstants.setBold(set, false);
				StyleConstants.setBackground(set, Color.black);
				doc.setCharacterAttributes(i, 1, set, true);
			}
        }
	}
	public class UpA extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// w
			String[] textS = text.split("\n");
			if(Y-1 >= 0) {
				if(textS[Y -1 ].charAt(X) != '0') {
					int digit = Integer.parseInt(""+textS[Y-1].charAt(X));
					if(Y-digit >= 0) {
						
						for(int i = Y;i >= Y-digit;i--) {
							char[] ch = textS[i].toCharArray();
							if(ch[X] != '0') {
								ch[X] = '0';
								score++;
							}
							textS[i] = String.valueOf(ch);
						}
						score--;
						Y -= digit;
						char[] ch = textS[Y].toCharArray();
						ch[X] = '#';
						textS[Y] = String.valueOf(ch);
						text = "";
						for(int i = 0;i<textS.length;i++) {
							text += textS[i];
							if(i != textS.length -1) {
								text+="\n";
							}
						}
						setText(text);
					}
				}
			}
			checkIsTheGameFinished(X, Y);
		}
	}
	public class DownA extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//s
			String[] textS = text.split("\n");
			if(Y+1 < textS.length) {
				if(textS[Y +1 ].charAt(X) != '0') {
					int digit = Integer.parseInt(""+textS[Y+1].charAt(X));
					if(Y+digit < textS.length) {
						
						for(int i = Y;i <= Y+digit;i++) {
							char[] ch = textS[i].toCharArray();
							if(ch[X] != '0') {
								ch[X] = '0';
								score++;
							}
							textS[i] = String.valueOf(ch);
						}
						score--;
						Y += digit;
						char[] ch = textS[Y].toCharArray();
						ch[X] = '#';
						textS[Y] = String.valueOf(ch);
						text = "";
						for(int i = 0;i<textS.length;i++) {
							text += textS[i];
							if(i != textS.length -1) {
								text+="\n";
							}
						}
						setText(text);
						
					}
				}
			}
			checkIsTheGameFinished(X, Y);

		}
	
	}
	public class LeftA extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//a
			String[] textS = text.split("\n");
			if(X-1 >= 0 ) {
				if(textS[Y].charAt(X-1) != '0') {
					int digit = Integer.parseInt(""+textS[Y].charAt(X-1));
					if(X-digit >= 0) {
						char[] ch = textS[Y].toCharArray();
						for(int i = X;i >= X-digit;i--) {
							if(ch[i] != '0') {
								ch[i] = '0';
								score++;
							}
						}
						score--;
						X -= digit;
						ch[X] = '#';
						textS[Y] = String.valueOf(ch);
						text = "";
						for(int i = 0;i<textS.length;i++) {
							text += textS[i];
							if(i != textS.length -1) {
								text+="\n";
							}
						}
						setText(text);
						
					}
				}
			}
			checkIsTheGameFinished(X, Y);

		}
	}
	public class RightA extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			//d
			String[] textS = text.split("\n");
			if(X+1 < textS[Y].length()) {
				if(textS[Y].charAt(X+1) != '0') {
					int digit = Integer.parseInt(""+textS[Y].charAt(X+1));
					if(X+digit < textS[Y].length()) {
						char[] ch = textS[Y].toCharArray();
						for(int i = X;i <= X+digit;i++) {
							if(ch[i] != '0') {
								ch[i] = '0';
								score++;
							}
						}
						score--;
						X += digit;
						ch[X] = '#';
						textS[Y] = String.valueOf(ch);
						text = "";
						for(int i = 0;i<textS.length;i++) {
							text += textS[i];
							if(i != textS.length -1) {
								text+="\n";
							}
						}
						setText(text);
						
					}
				}
			}
			checkIsTheGameFinished(X, Y);

		}
	
	}
	public class ULA extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// q
			String[] textS = text.split("\n");
			if(X-1 >= 0 && Y-1 >= 0) {
				if(textS[Y-1].charAt(X-1) != '0') {
					int digit = Integer.parseInt(""+textS[Y-1].charAt(X-1));
					if(X-digit >= 0 && Y-digit >= 0) {
						for(int i = Y;i >= Y-digit;i--) {
							char[] ch = textS[i].toCharArray();
							if(ch[X - (Y-i)] != '0') {
								ch[X - (Y-i)] = '0';
								score++;
							}
							textS[i] = String.valueOf(ch);
						}
						score--;
						X -= digit;
						Y -= digit;
						char[] ch = textS[Y].toCharArray();
						ch[X] = '#';
						textS[Y] = String.valueOf(ch);
						text = "";
						for(int i = 0;i<textS.length;i++) {
							text += textS[i];
							if(i != textS.length -1) {
								text+="\n";
							}
						}
						setText(text);
						
					}
				}
			}
			checkIsTheGameFinished(X, Y);

		}
	
	}
	public class URA extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			//e
			String[] textS = text.split("\n");
			if(X+1 < textS[Y].length() && Y-1 >= 0) {
				if(textS[Y-1].charAt(X+1) != '0') {
					int digit = Integer.parseInt(""+textS[Y-1].charAt(X+1));
					if(X+digit < textS[Y].length() && Y-digit >= 0) {
						for(int i = Y;i >= Y-digit;i--) {
							char[] ch = textS[i].toCharArray();
							if(ch[X + (Y-i)] != '0') {
								ch[X + (Y-i)] = '0';
								score++;
							}
							textS[i] = String.valueOf(ch);
						}
						score--;
						X += digit;
						Y -= digit;
						char[] ch = textS[Y].toCharArray();
						ch[X] = '#';
						textS[Y] = String.valueOf(ch);
						text = "";
						for(int i = 0;i<textS.length;i++) {
							text += textS[i];
							if(i != textS.length -1) {
								text+="\n";
							}
						}
						setText(text);
						
					}
				}
			}
			checkIsTheGameFinished(X, Y);
		}
	
	}
	public class DLA extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//z
			String[] textS = text.split("\n");
			if(X-1 >= 0 && Y+1 < textS.length) {
				if(textS[Y+1].charAt(X-1) != '0') {
					int digit = Integer.parseInt(""+textS[Y+1].charAt(X-1));
					if(X-digit >=0 && Y+digit < textS.length) {
						for(int i = Y;i <= Y+digit;i++) {
							char[] ch = textS[i].toCharArray();
							if(ch[X - (i-Y)] != '0') {
								ch[X - (i-Y)] = '0';
								score++;
							}
							textS[i] = String.valueOf(ch);
						}
						score--;
						X -= digit;
						Y += digit;
						char[] ch = textS[Y].toCharArray();
						ch[X] = '#';
						textS[Y] = String.valueOf(ch);
						text = "";
						for(int i = 0;i<textS.length;i++) {
							text += textS[i];
							if(i != textS.length -1) {
								text+="\n";
							}
						}
						setText(text);
						
					}
				}
			}
			checkIsTheGameFinished(X, Y);

		}
	
	}
	public class DRA extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// c
			String[] textS = text.split("\n");
			if(X+1 < textS[Y].length() && Y+1 < textS.length) {
				if(textS[Y+1].charAt(X+1) != '0') {
					int digit = Integer.parseInt(""+textS[Y+1].charAt(X+1));
					if(X+digit < textS[Y].length() && Y+digit < textS.length) {
						for(int i = Y;i <= Y+digit;i++) {
							char[] ch = textS[i].toCharArray();
							if(ch[X + (i-Y)] != '0') {
								ch[X + (i-Y)] = '0';
								score++;
							}
							textS[i] = String.valueOf(ch);
						}
						score--;
						X += digit;
						Y += digit;
						char[] ch = textS[Y].toCharArray();
						ch[X] = '#';
						textS[Y] = String.valueOf(ch);
						text = "";
						for(int i = 0;i<textS.length;i++) {
							text += textS[i];
							if(i != textS.length -1) {
								text+="\n";
							}
						}
						setText(text);
						
					}
				}
			}
			checkIsTheGameFinished(X, Y);

		}
	
	}
	public boolean marker(int xPosition , int yPosition) {
		String[] textS = text.split("\n");
		int up = (Y-1 >=0 && Y - Integer.parseInt( "" + textS[Y-1].charAt(X)) >= 0) ? Integer.parseInt( "" + textS[Y-1].charAt(X)) : 0;
		int down = (Y+1 < textS.length && Y + Integer.parseInt( "" + textS[Y+1].charAt(X)) < textS.length) ? Integer.parseInt("" + textS[Y+1].charAt(X)) : 0;
		int left = (X-1 >= 0 && X - Integer.parseInt( "" + textS[Y].charAt(X-1)) >= 0)?Integer.parseInt(""+ textS[Y].charAt(X-1)):0;
		int right = (X+1 < textS[Y].length() && X + Integer.parseInt( "" + textS[Y].charAt(X+1)) < textS[Y].length())?Integer.parseInt(""+ textS[Y].charAt(X+1)):0;
		
		int upleft = (Y-1 >=0 && X-1 >= 0 && Y - Integer.parseInt( "" + textS[Y-1].charAt(X-1)) >= 0 && X - Integer.parseInt( "" + textS[Y-1].charAt(X-1)) >= 0)?Integer.parseInt(""+ textS[Y-1].charAt(X-1)):0;
		int upright = (Y-1 >=0 && X+1 < textS[Y].length() && Y - Integer.parseInt( "" + textS[Y-1].charAt(X+1)) >= 0 && X + Integer.parseInt( "" + textS[Y-1].charAt(X+1)) < textS[Y-1].length())?Integer.parseInt(""+ textS[Y-1].charAt(X+1)):0;
		int downleft = (Y+1 < textS.length && X-1 >= 0 && Y + Integer.parseInt( "" + textS[Y+1].charAt(X-1)) < textS.length && X - Integer.parseInt( "" + textS[Y+1].charAt(X-1)) >= 0)?Integer.parseInt(""+ textS[Y+1].charAt(X-1)):0;
		int downright = (Y+1 < textS.length && X+1 < textS[Y].length() && X + Integer.parseInt( "" + textS[Y+1].charAt(X+1)) < textS[Y+1].length() && Y + Integer.parseInt( "" + textS[Y+1].charAt(X+1)) < textS.length)?Integer.parseInt(""+ textS[Y+1].charAt(X+1)):0;
		if(Math.abs(Y-yPosition) == 0) {
			if(X > xPosition) {
				if(X-xPosition <= left) {
					return true;
				}
			}
			else if(X < xPosition) {
				if(xPosition - X <= right) {
					return true;
				}
			}
		}
		if(Math.abs(X-xPosition) == 0) {
			if(Y > yPosition) {
				if(Y-yPosition <= up) {
					return true;
				}
			}
			else if(Y < yPosition) {
				if(yPosition - Y <= down) {
					return true;
				}
			}
		}
		if(Math.abs(Y-yPosition) == Math.abs(X-xPosition)) {
			if( Y > yPosition && X > xPosition) {
				if(Y-yPosition <= upleft) {
					return true;
				}
			}
			if( Y < yPosition && X > xPosition) {
				if(yPosition - Y <= downleft) {
					return true;
				}
			}
			if( Y > yPosition && X < xPosition) {
				if(Y-yPosition <= upright) {
					return true;
				}
			}
			if( Y < yPosition && X < xPosition) {
				if(yPosition - Y <= downright) {
					return true;
				}
			}
		}
		return false;
	}
	public void checkIsTheGameFinished(int XPosition , int YPosition) {
		int moves = 8;
		String[] textS = text.split("\n");
		if(XPosition - 1 >= 0) {
			int digit = Integer.parseInt(""+ textS[YPosition].charAt(XPosition - 1));
			if(digit == 0 || XPosition - digit < 0) {
				moves--;
			}
		}
		else {
			moves--;
		}
		if(XPosition + 1 < textS[YPosition].length()) {
			int digit = Integer.parseInt(""+ textS[YPosition].charAt(XPosition + 1));
			if(digit == 0 || XPosition + digit >= textS[YPosition].length()) {
				moves--;
			}
		}
		else {
			moves--;
		}
		if(YPosition - 1 >= 0) {
			int digit = Integer.parseInt(""+ textS[YPosition - 1].charAt(XPosition));
			if(digit == 0 || YPosition - digit < 0) {
				moves--;
			}
		}
		else {
			moves--;
		}
		if(YPosition + 1 < textS.length) {
			int digit = Integer.parseInt(""+ textS[YPosition + 1].charAt(XPosition));
			if(digit == 0 || YPosition + digit >= textS.length) {
				moves--;
			}
		}
		else {
			moves--;
		}
		if(YPosition - 1 >= 0 && XPosition - 1 >= 0) {
			int digit = Integer.parseInt(""+ textS[YPosition - 1].charAt(XPosition - 1));
			if(digit == 0 || YPosition - digit < 0 || XPosition - digit < 0) {
				moves--;
			}
		}
		else {
			moves--;
		}
		if(YPosition - 1 >= 0 && XPosition + 1 < textS[YPosition].length()) {
			int digit = Integer.parseInt(""+ textS[YPosition - 1].charAt(XPosition + 1));
			if(digit == 0 || YPosition - digit < 0 || XPosition + digit >= textS[YPosition].length()) {
				moves--;
			}
		}
		else {
			moves--;
		}
		if(YPosition + 1 < textS.length && XPosition - 1 >= 0) {
			int digit = Integer.parseInt(""+ textS[YPosition + 1].charAt(XPosition - 1));
			if(digit == 0 || YPosition + digit >= textS.length || XPosition - digit < 0) {
				moves--;
			}
		}
		else {
			moves--;
		}
		if(YPosition + 1 < textS.length && XPosition + 1 < textS[YPosition].length()) {
			int digit = Integer.parseInt(""+ textS[YPosition + 1].charAt(XPosition + 1));
			if(digit == 0 || YPosition + digit >= textS.length || XPosition + digit >= textS[YPosition].length()) {
				moves--;
			}
		}
		else {
			moves--;
		}
		String pers = (Double.toString((double)(score*100)/(Size*Size*3 -1)));
		for(int i = 0;i < pers.length() ; i++) {
			if(pers.charAt(i) == '.') {
				pers = pers.substring(0,i+2);
				break;
			}
		}
		//score update
		label1.setText("Score : " + Integer.toString(score) + "         "+ pers + "%");
		//---
		if(moves == 0) {
			endingTime = System.currentTimeMillis();
			String name = JOptionPane.showInputDialog(null,"Your final score was : " + score +"\nplease enter your name to save your record","Game Over",JOptionPane.PLAIN_MESSAGE);
			if(name != null && !name.equals("")) {
				//nothing for now
			}
			else {
				name = "Nameless";
			}
			saveScore(name, score);
			Clock.stop();
			System.gc();
			this.dispose();
		}
		//save the game
		saveGame(text);

	}
	public void saveGame(String boardText) {
		try {
			File myFile = new File("lastsave.txt");
			if(myFile.createNewFile()) {
				
			}
			endingTime = System.currentTimeMillis();
			long time = (endingTime - startingTime);
			FileWriter myWriter = new FileWriter(myFile);
			myWriter.write(Long.toString(time)+"\n"+boardText);
			myWriter.close();
		} 
		catch (IOException ex) {
			this.dispose();
		}
	}
	public String loadGame() {
		String ans = "";
		try {
			File myFile = new File("lastsave.txt");
			if(!myFile.exists()) {
				ans= "1#1";
				this.dispose();
			}
			Scanner fReader = new Scanner(myFile);
			boolean temp = true;
			while (fReader.hasNextLine()) {
				String data = fReader.nextLine();
				if(temp) {
					startingTime -= Long.parseLong(data) ;
					temp = false;
				}else {
					ans += (data + '\n');
				}
			}
			ans = ans.substring(0,ans.length() - 1);
			fReader.close();
			
		} 
		catch (IOException ex) {
			this.dispose();
		}
		return ans;
	}
	public void saveScore(String uName,int uScore) {
		double time = (double)(endingTime - startingTime)/1000;
		try {
			File myFile = new File("scoreboard.txt");
			if(myFile.createNewFile()) {
				
			}
			FileWriter myWriter = new FileWriter(myFile,true);
			myWriter.write(uName + " : " + Integer.toString(uScore) +" ( "+ Double.toString(time) + "s )\n");
			myWriter.close();
		} 
		catch (IOException ex) {
			this.dispose();
		}
	}
}
