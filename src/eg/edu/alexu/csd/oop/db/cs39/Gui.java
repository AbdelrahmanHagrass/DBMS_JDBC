package eg.edu.alexu.csd.oop.db.cs39;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.Toolkit;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Gui {

	private JFrame frame;
	private JTextField input;
	private JScrollPane scrollPane;
	private JTable table;
	Partitions partitions = new Partitions();
//	IDataBase db = new IDataBase();
	IDataBase db = IDataBase.getUniqueInstance();
//	Vector<Vector<Vector>> tables ;
	JTable[] arr ;
	private JComboBox comboBox;
	public DefaultTableModel model ;

	public DefaultTableModel model2 ;
	int counter=1 ;
	int flag=0;
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

			private void addWindowListener(WindowAdapter windowAdapter) {
				// TODO Auto-generated method stub
				
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
		try {
			db.load();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				//System.out.println("2flna");
			}
			@Override
			public void windowClosing(WindowEvent e) {
				//System.out.println("bn2fel");
				try {
					db.save();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println("22fel");
			}
		});
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
//		try {
//			db.load();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		try {
			db.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (Map.Entry<String, DB> entry : db.m.entrySet()) {
//			
//			System.out.println(entry.getValue().getDatabaseName());
//			System.out.println(entry.getValue().Tables.size());
//		}
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==comboBox) {
					
					 try {
		                    Vector<String>	 names1=  db.getNames((String) comboBox.getSelectedItem());
		                    counter=comboBox.getSelectedIndex()+1;
								Object[][] table1 =  db.executeQuery("SELECT * FROM "+comboBox.getSelectedItem());
								
								table = new JTable();
								scrollPane.setViewportView(table);
								DefaultTableModel tableModel = new DefaultTableModel() {

								    @Override
								    public boolean isCellEditable(int row, int column) {
								       //all cells false
								       return false;
								    }
								};
						 		table.setModel(tableModel);
								 model2 = (DefaultTableModel) table.getModel();
					
								for(int j=0;j<names1.size();j++) {
									model2.addColumn(names1.get(j));
									
								}
							
								for(int i=0;i<table1.length;i++) {
									
										model2.addRow(table1[i]);
									
								}
						
					 
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					 
					
					
                				
			}}
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
//				System.out.println("a7123123a");
				try {
					db.QueryManagement(input.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (parser.checkInput(input.getText()) == 8) {
					Vector<String> columns= partitions.CreateTable(input.getText());
					table = new JTable();
					flag=1;
					scrollPane.setViewportView(table);
					model = (DefaultTableModel) table.getModel();
					comboBox.addItem(columns.lastElement());
					counter = comboBox.getItemCount();
					comboBox.setSelectedIndex(counter-1);
					
					for(int i=0;i<columns.size()-1;i++) {
						model.addColumn(columns.elementAt(i));
						
					}
//					arr[0]=table;
//				System.out.println(	table.getColumnModel().getColumnCount());
//					System.out.println(tables.size());
				}else if (parser.checkInput(input.getText())==3) {
					partitions.DropDatabase(input.getText());
					if(label.getText().compareToIgnoreCase(partitions.getDropDataBaseName())==0) {
						label.setText("");
						
						counter=1;
					JTable	table2 = new JTable();
					scrollPane.setViewportView(null);
//						scrollPane.setViewportView(table2);
						model = (DefaultTableModel) table2.getModel();
						input.setText("success :)");
						comboBox.removeAllItems();
					}
				}
				else if (parser.checkInput(input.getText())==4) {
					partitions.DropTable(input.getText());
			for(int i=0;i<comboBox.getItemCount();i++){
				if(partitions.getTablename().compareToIgnoreCase((String) comboBox.getItemAt(i))==0) {
//					comboBox.remove(i);
					comboBox.removeItemAt(i);
				}
			}
				}
				else if(parser.checkInput(input.getText())==2) {
					comboBox.removeAllItems();
					counter=1;
					table = new JTable();
					scrollPane.setViewportView(table);
	
					String name = partitions.CreateDatabase(input.getText()) ;
					label.setText(name);
					input.setText("success :)");
				}
				else if (parser.checkInput(input.getText())==12||parser.checkInput(input.getText())==7||parser.checkInput(input.getText())==5||parser.checkInput(input.getText())==6||parser.checkInput(input.getText())==11) {
                       try {
                    	   System.out.println("sh215o");
                    	   
                    	   Vector<String>	 names1=  db.getNames(db.p.getTablename()); 
   						Object[][] table1 =  db.executeQuery("SELECT * FROM "+db.p.getTablename());
//                    Vector<String>	 names1=  db.getNames((String) comboBox.getSelectedItem());
//						Object[][] table1 =  db.executeQuery("SELECT * FROM "+comboBox.getSelectedItem());
						table = new JTable();
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
			
						for(int j=0;j<names1.size();j++) {
							model.addColumn(names1.get(j));
							
						}
						for(int i=0;i<table1.length;i++) {
							
								model.addRow(table1[i]);
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					   input.setText("success :)");
				}
				else if (parser.checkInput(input.getText())==1) {
                    try {
                    	Partitions p = new Partitions();
                    	p.SelectTable(input.getText());
                 Vector<String>	 names1=  db.getNames(p.getTablename());
						Object[][] table1 =  db.executeQuery("SELECT * FROM "+p.getTablename());
						comboBox.setSelectedItem(p.getTablename());
						counter = comboBox.getSelectedIndex()+1;
						table = new JTable();
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
			
						for(int j=0;j<names1.size();j++) {
							model.addColumn(names1.get(j));
							
						}
						for(int i=0;i<table1.length;i++) {
							
								model.addRow(table1[i]);
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					   input.setText("success :)");
				}
				else if (parser.checkInput(input.getText())==9) {
                    try {
                    	Partitions p = new Partitions();
                    	p.Select(input.getText());
                 Vector<String>	 names1=  db.getNames(p.getTablename());
						Object[][] table1 =  db.executeQuery(input.getText());
						comboBox.setSelectedItem(p.getTablename());
						counter = comboBox.getSelectedIndex()+1;
						table = new JTable();
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
			
						for(int j=0;j<names1.size();j++) {
							model.addColumn(names1.get(j));
							
						}
						for(int i=0;i<table1.length;i++) {
							
								model.addRow(table1[i]);
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					   input.setText("success :)");
				}
				else if (parser.checkInput(input.getText())==10) {
                    try {
                    	Partitions p = new Partitions();
                    	p.selecttwocolumnscondition(input.getText());
						Object[][] table1 =  db.executeQuery(input.getText());
						comboBox.setSelectedItem(p.getTablename());
						counter = comboBox.getSelectedIndex()+1;
						table = new JTable();
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
			
						model.addColumn(p.getselectconditioncloumn1());
						for(int i=0;i<table1.length;i++) {
							
								model.addRow(table1[i]);
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					   input.setText("success :)");
				}
				else if (parser.checkInput(input.getText()) != 0) {
					input.setText("success :)");
				} else {
					input.setText("wrong input");
				}

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
		

//		addWindowListener(new WindowAdapter() {
//			  public void windowClosing(WindowEvent e) {
//			    int confirmed = JOptionPane.showConfirmDialog(null, 
//			        "Are you sure you want to exit the program?", "Exit Program Message Box",
//			        JOptionPane.YES_NO_OPTION);
//
//			    if (confirmed == JOptionPane.YES_OPTION) {
//			      dispose();
//			    }
//			  }
//			});
	}



}