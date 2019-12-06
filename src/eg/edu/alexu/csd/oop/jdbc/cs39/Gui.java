package eg.edu.alexu.csd.oop.jdbc.cs39;
 
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
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
 
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import eg.edu.alexu.csd.oop.db.cs39.IDataBase;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
 
public class Gui {
 
    private JFrame frame;
    private JTextField input;
    private JScrollPane scrollPane;
    private JTable table;
    static   Logger logger = Logger.getLogger(Gui.class.getName());
    Connection connection;
 
//  IDataBase db = new IDataBase();
//  Vector<Vector<Vector>> tables ;
    JTable[] arr ;
    public DefaultTableModel model ;
	private IDataBase DbManager = IDataBase.getUniqueInstance();
    public DefaultTableModel model2 ;
    int counterLog=0 ;
    int flag=0;
    private JLabel label;
    private JButton path;
    public JTextArea textArea = new JTextArea();
//  private JList list;
 
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
    public void pathInit() {
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);
        System.out.println(f.getSelectedFile());
        Driver driver = new DriverImp();
        Properties info = new Properties();
        File dbDir = new File(""+f.getSelectedFile());
        info.put("path", dbDir.getAbsoluteFile());
        try {
             connection = (Connection) driver.connect("jdbc:xmldb://localhost", info);
             logger.setLevel(Level.INFO);
             logger.info("connection to database "+f.getSelectedFile() +" succeeded");
             textArea.setBackground(UIManager.getColor("Button.background"));
             String pattern = "dd MMMMM yyyy HH:mm:ss";
             SimpleDateFormat simpleDateFormat =
                     new SimpleDateFormat(pattern);
 
             String date = simpleDateFormat.format(new Date());
    DbManager.SetPath(""+f.getSelectedFile());
             textArea.append("\n"+date+" connection to database "+f.getSelectedFile() +" succeeded");
             
              
           
             
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
   
 
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        textArea.setEditable(false);
        frame = new JFrame();
     
       
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(0, 0, screen.width, screen.height - 35);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        frame.getContentPane().setLayout(gridBagLayout);
        JPanel panel = new JPanel();
        JScrollPane scrollPane1 = new JScrollPane( textArea );
       
        panel.add( scrollPane1 );
        JButton process = new JButton("process");
        SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
         try {  
 
                // This block configure the logger with handler and formatter  
            FileHandler fh = new FileHandler("MyLogFile_"
                        + format.format(Calendar.getInstance().getTime()) + ".log");
           
                logger.addHandler(fh);
   
                SimpleFormatter formatter = new SimpleFormatter();  
                fh.setFormatter(formatter);  
 
                // the following statement is used to log any messages  
 
            } catch (SecurityException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }
 
        path = new JButton("change path");
        path.setPreferredSize(new Dimension(150, 23));
        path.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               
                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                f.showSaveDialog(null);
                System.out.println(f.getSelectedFile());
                Driver driver = new DriverImp();
                Properties info = new Properties();
                File dbDir = new File(""+f.getSelectedFile());
                info.put("path", dbDir.getAbsoluteFile());
                try {
                     connection = (Connection) driver.connect("jdbc:xmldb://localhost", info);
                     logger.setLevel(Level.INFO);
                     
                     logger.info("connection to database "+f.getSelectedFile() +" succeeded");
                     String pattern = "dd MMMMM yyyy HH:mm:ss";
                     SimpleDateFormat simpleDateFormat =
                             new SimpleDateFormat(pattern);
 
                     String date = simpleDateFormat.format(new Date());
                     DbManager.SetPath(""+f.getSelectedFile());
                     
                     textArea.append("\n"+date+" connection to database "+f.getSelectedFile() +" succeeded");
                     
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        pathInit();
       
        GridBagConstraints gbc_path = new GridBagConstraints();
        gbc_path.gridwidth = 5;
        gbc_path.insets = new Insets(0, 0, 5, 5);
        gbc_path.gridx = 8;
        gbc_path.gridy = 1;
        frame.getContentPane().add(path, gbc_path);
       
       
        label = new JLabel("log");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 0;
        gbc_label.gridy = 3;
        frame.getContentPane().add(label, gbc_label);
        scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridheight = 8;
        gbc_scrollPane.gridwidth = 12;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 2;
        gbc_scrollPane.gridy = 4;
       
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
         
         try{StatementImp stmt = (StatementImp) connection.createStatement();
         frame.addWindowListener(new WindowAdapter() {
 			@Override
 			public void windowClosed(WindowEvent e) {
 				//System.out.println("2flna");
 			}
 			@Override
 			public void windowClosing(WindowEvent e) {
 				//System.out.println("bn2fel");
 				try {
 					DbManager.save();
 				} catch (Exception e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
 			}
 		});
        process.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ResultSetImp rs = null ;
                
                try {
                   
                  stmt.execute(input.getText());
                 logger.setLevel(Level.INFO);
                   logger.info(input.getText());
                     String pattern = "dd MMMMM yyyy HH:mm:ss";
                     SimpleDateFormat simpleDateFormat =
                             new SimpleDateFormat(pattern);
 
                     String date = simpleDateFormat.format(new Date());
                   
                   textArea.append("\n"+date+" "+input.getText());
                   
 
//                 logText.add(input.getText());
//                 list.add(new JLabel(input.getText()));
                 
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if(input.getText().trim().split("\\s+")[0].equalsIgnoreCase("select")) {
               try {
				rs=	(ResultSetImp) stmt.executeQuery(input.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
                }
                
                if(rs!=null&&rs.items.length>0) {
               
                      Vector<String>     names1=  rs.Names;
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
                        for(int i=0;i<rs.items.length;i++) {
                           
                                model.addRow(rs.items[i]);
                           
                        }
                   
                       input.setText("success :)");
                   
                }
//              if (parser.checkInput(input.getText()) == 8) {
//                  Vector<String> columns= partitions.CreateTable(input.getText());
//                  table = new JTable();
//                  flag=1;
//                  scrollPane.setViewportView(table);
//                  model = (DefaultTableModel) table.getModel();
//                  comboBox.addItem(columns.lastElement());
//                  counter = comboBox.getItemCount();
//                  comboBox.setSelectedIndex(counter-1);
//                 
//                  for(int i=0;i<columns.size()-1;i++) {
//                      model.addColumn(columns.elementAt(i));
//                     
//                  }
////                    arr[0]=table;
////                System.out.println( table.getColumnModel().getColumnCount());
////                    System.out.println(tables.size());
//              }else if (parser.checkInput(input.getText())==3) {
//                  partitions.DropDatabase(input.getText());
//                  if(label.getText().compareToIgnoreCase(partitions.getDropDataBaseName())==0) {
//                      label.setText("");
//                     
//                      counter=1;
//                  JTable  table2 = new JTable();
//                  scrollPane.setViewportView(null);
////                        scrollPane.setViewportView(table2);
//                      model = (DefaultTableModel) table2.getModel();
//                      input.setText("success :)");
//                      comboBox.removeAllItems();
//                  }
//              }
//              else if (parser.checkInput(input.getText())==4) {
//                  partitions.DropTable(input.getText());
//          for(int i=0;i<comboBox.getItemCount();i++){
//              if(partitions.getTablename().compareToIgnoreCase((String) comboBox.getItemAt(i))==0) {
////                    comboBox.remove(i);
//                  comboBox.removeItemAt(i);
//              }
//          }
//              }
//              else if(parser.checkInput(input.getText())==2) {
//                  comboBox.removeAllItems();
//                  counter=1;
//                  table = new JTable();
//                  scrollPane.setViewportView(table);
// 
//                  String name = partitions.CreateDatabase(input.getText()) ;
//                  label.setText(name);
//                  input.setText("success :)");
//              }
//              else if (parser.checkInput(input.getText())==12||parser.checkInput(input.getText())==7||parser.checkInput(input.getText())==5||parser.checkInput(input.getText())==6||parser.checkInput(input.getText())==11) {
//                       try {
//                         System.out.println("sh215o");
//                    Vector<String>     names1=  db.getNames((String) comboBox.getSelectedItem());
//                      Object[][] table1 =  db.executeQuery("SELECT * FROM "+comboBox.getSelectedItem());
//                      table = new JTable();
//                      scrollPane.setViewportView(table);
//                      DefaultTableModel tableModel = new DefaultTableModel() {
//
//                          @Override
//                          public boolean isCellEditable(int row, int column) {
//                             //all cells false
//                             return false;
//                          }
//                      };
//                      table.setModel(tableModel);
//                       model = (DefaultTableModel) table.getModel();
//         
//                      for(int j=0;j<names1.size();j++) {
//                          model.addColumn(names1.get(j));
//                         
//                      }
//                      for(int i=0;i<table1.length;i++) {
//                         
//                              model.addRow(table1[i]);
//                         
//                      }
//                  } catch (SQLException e1) {
//                      // TODO Auto-generated catch block
//                      e1.printStackTrace();
//                  }
//                     input.setText("success :)");
//              }
//              else if (parser.checkInput(input.getText())==1) {
//                    try {
//                      Partitions p = new Partitions();
//                      p.SelectTable(input.getText());
//                 Vector<String>    names1=  db.getNames(p.getTablename());
//                      Object[][] table1 =  db.executeQuery("SELECT * FROM "+p.getTablename());
//                      comboBox.setSelectedItem(p.getTablename());
//                      counter = comboBox.getSelectedIndex()+1;
//                      table = new JTable();
//                      scrollPane.setViewportView(table);
//                      DefaultTableModel tableModel = new DefaultTableModel() {
//
//                          @Override
//                          public boolean isCellEditable(int row, int column) {
//                             //all cells false
//                             return false;
//                          }
//                      };
//                      table.setModel(tableModel);
//                       model = (DefaultTableModel) table.getModel();
//         
//                      for(int j=0;j<names1.size();j++) {
//                          model.addColumn(names1.get(j));
//                         
//                      }
//                      for(int i=0;i<table1.length;i++) {
//                         
//                              model.addRow(table1[i]);
//                         
//                      }
//                  } catch (SQLException e1) {
//                      // TODO Auto-generated catch block
//                      e1.printStackTrace();
//                  }
//                     input.setText("success :)");
//              }
//              else if (parser.checkInput(input.getText())==9) {
//                    try {
//                      Partitions p = new Partitions();
//                      p.Select(input.getText());
//                 Vector<String>    names1=  db.getNames(p.getTablename());
//                      Object[][] table1 =  db.executeQuery(input.getText());
//                      comboBox.setSelectedItem(p.getTablename());
//                      counter = comboBox.getSelectedIndex()+1;
//                      table = new JTable();
//                      scrollPane.setViewportView(table);
//                      DefaultTableModel tableModel = new DefaultTableModel() {
//
//                          @Override
//                          public boolean isCellEditable(int row, int column) {
//                             //all cells false
//                             return false;
//                          }
//                      };
//                      table.setModel(tableModel);
//                       model = (DefaultTableModel) table.getModel();
//         
//                      for(int j=0;j<names1.size();j++) {
//                          model.addColumn(names1.get(j));
//                         
//                      }
//                      for(int i=0;i<table1.length;i++) {
//                         
//                              model.addRow(table1[i]);
//                         
//                      }
//                  } catch (SQLException e1) {
//                      // TODO Auto-generated catch block
//                      e1.printStackTrace();
//                  }
//                     input.setText("success :)");
//              }
//              else if (parser.checkInput(input.getText())==10) {
//                    try {
//                      Partitions p = new Partitions();
//                      p.selecttwocolumnscondition(input.getText());
//                      Object[][] table1 =  db.executeQuery(input.getText());
//                      comboBox.setSelectedItem(p.getTablename());
//                      counter = comboBox.getSelectedIndex()+1;
//                      table = new JTable();
//                      scrollPane.setViewportView(table);
//                      DefaultTableModel tableModel = new DefaultTableModel() {
//
//                          @Override
//                          public boolean isCellEditable(int row, int column) {
//                             //all cells false
//                             return false;
//                          }
//                      };
//                      table.setModel(tableModel);
//                       model = (DefaultTableModel) table.getModel();
//         
//                      model.addColumn(p.getselectconditioncloumn1());
//                      for(int i=0;i<table1.length;i++) {
//                         
//                              model.addRow(table1[i]);
//                         
//                      }
//                  } catch (SQLException e1) {
//                      // TODO Auto-generated catch block
//                      e1.printStackTrace();
//                  }
//                     input.setText("success :)");
//              }
//              else if (parser.checkInput(input.getText()) != 0) {
//                  input.setText("success :)");
//              } else {
//                  input.setText("wrong input");
//              }
//
            }
        });
        
    }
        catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
//      process.addActionListener(new ActionListener() {
//          public void actionPerformed(ActionEvent e) {
//          }
//      });
 
        input = new JTextField();
        GridBagConstraints gbc_input = new GridBagConstraints();
        gbc_input.gridwidth = 8;
        gbc_input.insets = new Insets(0, 0, 5, 5);
        gbc_input.fill = GridBagConstraints.HORIZONTAL;
        gbc_input.gridx = 0;
        gbc_input.gridy = 2;
        frame.getContentPane().add(input, gbc_input);
        input.setColumns(10);
        GridBagConstraints gbc_process = new GridBagConstraints();
        gbc_process.insets = new Insets(0, 0, 5, 5);
        gbc_process.gridx = 10;
        gbc_process.gridy = 2;
        frame.getContentPane().add(process, gbc_process);
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.insets = new Insets(0, 0, 5, 5);
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 0;
        gbc_textArea.gridy = 6;
        frame.getContentPane().add(textArea, gbc_textArea);
       
 
//      addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//              int confirmed = JOptionPane.showConfirmDialog(null,
//                  "Are you sure you want to exit the program?", "Exit Program Message Box",
//                  JOptionPane.YES_NO_OPTION);
//
//              if (confirmed == JOptionPane.YES_OPTION) {
//                dispose();
//              }
//            }
//          });
    }
 
 
        }