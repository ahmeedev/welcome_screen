package welcome;

import javax.swing.*;
import java.awt.*;
import java.io.RandomAccessFile;
public class Welcome extends JFrame{  
    
    // Deme fonts
    Font font=new Font("serif",Font.BOLD,14);
   
    JPanel main;
    JPanel external;
    JPanel panel1;
    
    static Color currentColor;
    static boolean isRandomOn=false;
    JMenu color;
    static JMenuItem[] colorItems=new JMenuItem[5]; // for rgb colors
    String colors[]={"Red","Blue","Green","Random","Specific"};
    
    static JMenu pattern;
    static JMenuItem patterns[]=new JMenuItem[3];
    String patternsName[]={"Pattern_1","Pattern_2","Stop_Pattern"};
    
    JMenu lightingPanel;
    JMenuItem custom;
    JMenuItem resetPanel;
    static JMenuBar bar=new JMenuBar();;
    
    public Welcome() {
//        lightPanels();/
  
     setSize(850,600);
     setLocationByPlatform(true);
     Image image=Toolkit.getDefaultToolkit().getImage("");
     setIconImage(image);
     
     
     // setting layouts for frame
     lightingLengths();
     lighting();
     layouts();
     
     pattern=new JMenu("Patterns");
     for (int i = 0; i < 3; i++) {
       patterns[i]=new JMenuItem();
       patterns[i].setText(patternsName[i]);
       if(i==2)
           pattern.addSeparator();
       
       pattern.add(patterns[i]);
       patterns[i].addActionListener(new Choice());
     }
       pattern.remove(3);

     color=new JMenu("Colors");
     
     for (int i = 0; i < 5; i++) {
      colorItems[i]=new JMenuItem();
      colorItems[i].setText(colors[i]);
      if(i==4)
      color.addSeparator();
      color.add(colorItems[i]);
      colorItems[i].addActionListener(new ColorSelection());
        }
  
     lightingPanel=new JMenu("Lighting_Panel");
     custom=new JMenuItem("Customized");
//     custom.addActionListener(new CustomPanelsActions());
     resetPanel=new JMenuItem("Reset");
     custom.addActionListener(new Choice());
     resetPanel.addActionListener(new Choice());
     
     bar.add(color);
     bar.add(pattern);
     
     // Panels menu
     lightingPanel.add(custom);
     lightingPanel.addSeparator();
     lightingPanel.add(resetPanel);
     bar.add(lightingPanel);
     
     
     setJMenuBar(bar);
     
     setLocationRelativeTo(null);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     setVisible(true);

     statingPatternRecognizing();
//      ColorPatterns selection=new ColorPatterns();
    }


    JButton login;
    JButton exit;
    void layouts(){
      
        external=new JPanel();
        external.setLayout(new GridLayout(1,1));
        external.setBorder(BorderFactory.createTitledBorder("Access Point!"));
        
        external.add(new Login_Panel()); // add here the custom panel

  
        main.add(external,BorderLayout.CENTER);
        add(main);
        
    }
    
    
    JPanel panele;
    JPanel panelw;
    JPanel paneln;
    JPanel panels;
    
    static JPanel panele1;
    static JPanel panele2;
    static JPanel panelw1;
    static JPanel panelw2;
    static JPanel paneln1;
    static JPanel paneln2;
    static JPanel panels1;
    static JPanel panels2;
    
    static JPanel north[]; static int northLength=1;  
    static JPanel east[];  static int eastLength=1;
    static JPanel south[]; static int southLength=1;
    static JPanel west[];  static int westLength=1;
    
    void lightingLengths(){
        
        try {
        
         RandomAccessFile file=new RandomAccessFile("lightingLengths.txt","r");
         northLength=file.readInt();
         eastLength=file.readInt();
         southLength=file.readInt();
         westLength=file.readInt();

         
         file.close();
   
        } catch (Exception e) {
//           JOptionPane.showMessageDialog(null,"Error!, File is missing","Attention!!",JOptionPane.ERROR_MESSAGE);
            northLength=1;
            eastLength=1;
            southLength=1;
            westLength=1;
        }
        
        north=new JPanel[northLength];
        east=new JPanel[eastLength];
        south=new JPanel[southLength];
        west=new JPanel[westLength];
    }
    
    
    void lighting(){
    
    main=new JPanel();
    main.setLayout(new BorderLayout());

    paneln=new JPanel();
    paneln.setLayout(new GridLayout(1,northLength));
        for (int i = 0; i < northLength; i++) {
            north[i]=new JPanel();
            paneln.add(north[i]);
        }

    panele=new JPanel();
    panele.setLayout(new GridLayout(eastLength,1));
        for (int i = 0; i < eastLength; i++) {
            east[i]=new JPanel();
            panele.add(east[i]);
        }
    

    panels=new JPanel();
    panels.setLayout(new GridLayout(1,southLength));
        for (int i = 0; i < southLength; i++) {
            south[i]=new JPanel();
            panels.add(south[i]);
        }

    panelw=new JPanel();
    panelw.setLayout(new GridLayout(westLength,1));
        for (int i = 0; i < westLength; i++) {
            west[i]=new JPanel();
            panelw.add(west[i]);
        }
    
    main.add(panele,BorderLayout.EAST);
    main.add(panelw,BorderLayout.WEST);
    main.add(paneln,BorderLayout.NORTH);
    main.add(panels,BorderLayout.SOUTH);
    }
    
    
    static int startingPattern=0;
 public void statingPatternRecognizing(){
     
     try {
         
         RandomAccessFile file=new RandomAccessFile("startingPattern.txt","r");
         startingPattern=file.readInt();
         file.close();
         
     } catch (Exception e) {
        // JOptionPane.showMessageDialog(null,"Error!, File is missing","Attention!!",JOptionPane.ERROR_MESSAGE);
     startingPattern=0;
     }
     
     if(startingPattern==0)
                patterns[0].doClick();
     else if(startingPattern==1)
                patterns[1].doClick();
     
 }  
    
    public static void main(String[] args) {
        new Welcome();
    }
    
}