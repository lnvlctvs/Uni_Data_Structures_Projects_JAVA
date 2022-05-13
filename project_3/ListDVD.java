import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
//@authors Togantzi M. & Kalergis Ch.
public class ListDVD extends JFrame implements ActionListener, MouseListener {
	private JButton but1;
	private JButton but2;
	private JButton but3;
	private JList list;
	private JLabel label;
	private ImageIcon imIco1;
	private DefaultListModel listModel;
	private static ArrayList<Item> products_list = new ArrayList<Item>();
	private JTextArea resultArea = new JTextArea("DVD details: \n Please select a DVD and \n press \"View DVD Details\"",10,20);


    //An example of JList with a DefaultListModel that we build up at runtime.

	public ListDVD() {
		setTitle("DVD Collection");
		drawFrame();
		but1.addActionListener(this);
		but2.addActionListener(this);
		but3.addActionListener(this);
		list.addMouseListener(this);
		setVisible(true);
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == but1){
			int i = list.getSelectedIndex();
			if (i != -1) {
				listModel.remove(i);
				products_list.remove(i);
			}
		}
		else if (e.getSource() == but2) {
			String title = (String)JOptionPane.showInputDialog(this, "Please type the title of DVD");
			String director = (String)JOptionPane.showInputDialog(this, "Please type the name of the director");
			String movieStar = (String)JOptionPane.showInputDialog(this, "Please type the movieStar name ");
			String playingTime = (String)JOptionPane.showInputDialog(this, "Please type the playingTime");
			String price = (String)JOptionPane.showInputDialog(this, "Please type the price of the dvd");
			float pr = (new Float(price)).floatValue();
			int pltm = Integer.parseInt(playingTime);
			DVD dvd = new DVD(title, director, movieStar, pltm, pr);
			// Add new DVD in to ArrayList
			products_list.add(dvd);

			// add to list the new title of the DVD
			listModel.addElement(dvd.getTitle());
		}
		else  if (e.getSource() == but3) {
			int i = list.getSelectedIndex();
			String outputText = products_list.get(i).getContents();
			resultArea.setText(outputText);
   		}
	}//actionPerformed

	public void mouseClicked(MouseEvent event) {
		int i = list.getSelectedIndex();
		if (list.getSelectedIndex() != -1) {
			String pathimage = products_list.get(i).getImagePath();
			if (pathimage != null) {
				label.setIcon(new ImageIcon(pathimage));
			}
			else {
					label.setIcon(new ImageIcon());
			}
		}
	}
	public void mouseExited(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mousePressed(MouseEvent event){}


	private void drawFrame() {
		setBounds(300, 300, 350, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		but1 = new JButton("Remove a DVD");
		but2 = new JButton("Add a DVD");
		but3 = new JButton("View DVD Details");
	 	listModel = new DefaultListModel();
		for (Item it : products_list)
		{
			listModel.addElement(it.getTitle());
		}
		list = new JList(listModel);
		list.setSelectedIndex(0);
		label = new JLabel();
		label.setIcon(new ImageIcon(products_list.get(0).getImagePath()));
		Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
		JPanel paneButton = new JPanel();
		paneButton.setLayout(new FlowLayout());
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(150, 100));
		paneButton.add(but1);
		paneButton.add(but2);
		paneButton.add(but3);
		resultArea.setFont(new Font("Serif", Font.ITALIC, 18));
		resultArea.setForeground(Color.BLUE);
		resultArea.setEditable(false);
		paneButton.add(resultArea);
		cp.add(paneButton, BorderLayout.LINE_START);
		cp.add(listScroller, BorderLayout.CENTER);
		cp.add(label, BorderLayout.LINE_END);
		pack();
	}//drawFrame

		public static void main(String[] args) {
		Item it1 = new DVD("Match Point ","woody allen","Jonathan Rhys Meyers",90,(float)35.70);
		Item it2 = new DVD("Anna Karenina ","Joe Wright","Keira Christina Knightley ",115,(float)34.68);
		Item it3 = new DVD("Les Miserables ","Ton Hooper","Anne Hathaway",135,(float)33.90);
		Item it4 = new DVD("birds ","woody spoken","Antoin Meyers",123,(float)45.70);
		Item it5 = new DVD("the road ","Alan Dallas","Jonathan hart",110,(float)36.95);
		Item it6 = new DVD("La chamade ","Pierre Gillette","Catherine Deneuve",103,(float)38.40);
		Item it7 = new DVD("The Hangover","Todd Phillips","Zach Galifianakis",120,(float)56.90);
		it1.setImagePath("Images/1.jpg");
		it2.setImagePath("Images/2.jpg");
		it3.setImagePath("Images/3.jpg");
		it4.setImagePath("Images/4.jpg");
		it5.setImagePath("Images/5.jpg");
		it6.setImagePath("Images/6.jpg");
		it7.setImagePath("Images/7.jpg");
		products_list.add(it1);
		products_list.add(it2);
		products_list.add(it3);
		products_list.add(it4);
		products_list.add(it5);
		products_list.add(it6);
		products_list.add(it7);
		ListDVD frame = new ListDVD();

	}
}