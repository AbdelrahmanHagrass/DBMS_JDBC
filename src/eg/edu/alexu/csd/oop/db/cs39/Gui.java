package eg.edu.alexu.csd.oop.db.cs39;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Gui {

	private JFrame frame;
	private JTextField input;
	private JScrollPane scrollPane;
	private JTable table;
	Partitions partitions = new Partitions();
	IDataBase db = new IDataBase();
//	Vector<Vector<Vector>> tables ;
	JTable[] arr ;
	private JComboBox comboBox;
	public DefaultTableModel model ;
	int counter=1 ;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, screen.width, screen.height - 35);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);
		Parser parser = new Parser();

		JButton process = new JButton("process");
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==comboBox) {
					System.out.println(comboBox.getSelectedIndex());
				}
			}
		});
		
		label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		frame.getContentPane().add(label, gbc_label);
//		comboBox.addActionListener((ActionListener) this);
//		public void actionPerformed(ActionEvent e) {
//			if(e.getSource()==comboBox) {
//				System.out.println("asadas");
//			}
//		}
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 2;
		frame.getContentPane().add(comboBox, gbc_comboBox);
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 8;
		gbc_scrollPane.gridwidth = 12;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 2;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		scrollPane.setViewportView(table);
		DefaultTableModel tableModel = new DefaultTableModel() {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
 		table.setModel(tableModel);
		 model = (DefaultTableModel) table.getModel();
		
		process.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("a7a");
				try {
					db.QueryManagement(input.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				if (parser.checkInput(input.getText()) == 8) {
//					Vector<String> columns= partitions.CreateTable(input.getText());
//					table = new JTable();
//					scrollPane.setViewportView(table);
//					model = (DefaultTableModel) table.getModel();
//					comboBox.addItem(columns.lastElement());
//					comboBox.setSelectedIndex(counter-1);
//					counter++;
//					for(int i=0;i<columns.size()-1;i++) {
//						model.addColumn(columns.elementAt(i));
//						
//					}
////					arr[0]=table;
////				System.out.println(	table.getColumnModel().getColumnCount());
////					System.out.println(tables.size());
//				}
//				else if(parser.checkInput(input.getText())==2) {
//					comboBox.removeAllItems();
//					table = new JTable();
//					scrollPane.setViewportView(table);
////					string name1 = partitions.
//					String name = partitions.CreateDatabase(input.getText()) ;
//					label.setText(name);
//					input.setText("success :)");
//				}
//				else if (parser.checkInput(input.getText())==7||parser.checkInput(input.getText())==5||parser.checkInput(input.getText())==6||parser.checkInput(input.getText())==1) {
//                       //Object[][] table =  db.executeQuery(input.getText());
//					   input.setText("success :)");
//				}
//				else if (parser.checkInput(input.getText()) != 0) {
//					input.setText("success :)");
//				} else {
//					input.setText("wrong input");
//				}

			}
		});
		process.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		input = new JTextField();
		GridBagConstraints gbc_input = new GridBagConstraints();
		gbc_input.gridwidth = 8;
		gbc_input.insets = new Insets(0, 0, 5, 5);
		gbc_input.fill = GridBagConstraints.HORIZONTAL;
		gbc_input.gridx = 0;
		gbc_input.gridy = 0;
		frame.getContentPane().add(input, gbc_input);
		input.setColumns(10);
		GridBagConstraints gbc_process = new GridBagConstraints();
		gbc_process.insets = new Insets(0, 0, 5, 5);
		gbc_process.gridx = 10;
		gbc_process.gridy = 0;
		frame.getContentPane().add(process, gbc_process);

		
	}

}