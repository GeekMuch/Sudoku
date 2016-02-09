import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


	
public class SudokuGUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JButton btn1 = new JButton("Choose File");
	static JButton btn2 = new JButton("START");
	JButton btn3 = new JButton("Save file");
	JTextField filename = new JTextField();
	static JTextField dir = new JTextField();
	public static Button area1[][];

	
	
	int returnval;		
	
    public SudokuGUI() {
		
		
		super("Sudoku Solver v0.9");
	
		JPanel f1 = new JPanel();

        f1.setLayout( new GridLayout(9,9));
   
        add(f1, BorderLayout.CENTER);
       
        // buttons for the lulz :D
        
        area1 = new Button[9][9];
        // Create an empty view
        for( int i = 0; i < 9; i++ )
           for( int j = 0; j < 9; j++ )
           {
        	  area1[i][j]  = new Button() ;
        	  f1.add(area1[i][j]) ;
              
           }
         
        
        f1.setVisible(true);
			
	    // buttons
        final JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ButtonPanel.setLayout(new BorderLayout());
        ButtonPanel.add(btn1);
        ButtonPanel.add(btn2);
        ButtonPanel.add(btn3);
       
         
        Border LineBorder  = new LineBorder(Color.lightGray);
        ButtonPanel.setBorder(LineBorder);
        
        BoxLayout horizontal = new BoxLayout(ButtonPanel, BoxLayout.X_AXIS);
        ButtonPanel.setLayout(horizontal);
        
        FlowLayout flow = new FlowLayout();
        ButtonPanel.setLayout(flow);
        
        add(ButtonPanel, BorderLayout.SOUTH);
        setVisible(true);
        
        btn1.addActionListener(new openfile()); 
        btn2.addActionListener(new startSolve()); 
        btn3.addActionListener(new saveFile()); 
    }
   public static class openfile implements ActionListener{

	public static String path;
	public static String infield = new String();

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btn1) {
			 JFileChooser c = new JFileChooser();
			 int returnVal = c.showOpenDialog(null);
			 if(returnVal == JFileChooser.APPROVE_OPTION){ 
			 File file = c.getSelectedFile();
			 dir.setText(c.getSelectedFile().getAbsolutePath().toString());
			 path = file.toString();
	 	 	 Field field = new Field();
			 String s = openfile.path;
			 field.fromFile(s);
			 System.out.println(field);
			 infield = field.toString();
			 NewFieldView();
			 
			 
			}
			 
		} 
	}
 }
   public static class startSolve implements ActionListener{

	public static String donefield = new String();
	   
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn2) {
			Field field = new Field();
			field.fromFile(openfile.path);
			try {
				Sudoku.solve(field, 0, 0);
				
			} catch (SolvedException e1) {
				System.out.println("***SOLVED***");
				SolvedFieldView();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			}
		}
   }
		
    public class saveFile implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn3) {
			PrintWriter out = null;
			try {
				out = new PrintWriter(new FileWriter("SolvedSudoku.txt"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			out.print(Sudoku.solved2); 
			System.out.println("****SAVED in current directory****");
			out.close();
			}	
		}
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    
    



	public static void NewFieldView() {
		for( int i = 0; i < 9; i++ )
	         for( int j = 0; j < 9; j++ )
	            if( Field.inputmodel[i][j] != 0 )
	               area1[i][j].setLabel( String.valueOf(Field.model[i][j]) ) ;
	            else
	            	area1[i][j].setLabel( "" ) ;
	   }
	public static void SolvedFieldView() {
		for( int i = 0; i < 9; i++){
	         for( int j = 0; j < 9; j++){
	        	 if( Field.model[i][j] != 0 ){
	               area1[i][j].setLabel( String.valueOf(Field.model[i][j]) ) ;
	        	 }
	        	 else {
		            	area1[i][j].setLabel( "" ) ;
	        	 }
	         }
		}
	   }
	
	
}// end of SUdokuGUI
   
