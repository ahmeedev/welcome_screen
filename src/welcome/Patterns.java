
package welcome;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.RandomAccessFile;
import static welcome.Welcome.*;

public class Patterns {
static boolean endOfPattern=false;

    public Patterns() {
        
        
    }
    
    public static void main(String[] args) {
    }
    
    static void startingPatternRecognize(int hold){
        
        try{
         
         RandomAccessFile file=new RandomAccessFile("startingPattern.txt","rw");
         file.writeInt(hold);
         file.close();
         
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null,"Error!, File is missing","Attention!!",JOptionPane.ERROR_MESSAGE);
     }
     
    }

} 

   class startingPatternRun implements ActionListener{

        public void actionPerformed(ActionEvent ae) {
            
            
        }
   }

 class Pattern1 extends Thread{

        Thread th;
        static boolean flagForPattern1=true;
        static boolean pattern1Running=false;
//        static int northCount=Welcome.northLength-1;
        static int northCount=0;
        static int eastCount=0;
        static int holdSouth=Welcome.southLength-1;
        static int southCount=holdSouth;
        static int holdWest=Welcome.westLength-1;
        static int westCount=holdWest;
        public Pattern1() {
            th=new Thread(this,"Thread_For_Pattern1");
//            th.start();
        }
        
 
     public void run(){
            pattern1Running=true;
            int speed=1000;
            //setting for rerunning
            Patterns.startingPatternRecognize(0);
            while(flagForPattern1){
             try {
            while(true){ 
                Thread.sleep(speed);
                if(northCount!=Welcome.northLength)
            {north[northCount].setBackground(Welcome.currentColor);
             northCount++;
            }
            
            if(northCount==Welcome.northLength)
                    break;

            }  // north loop
            
            while(true){
                
                Thread.sleep(speed);
                if(eastCount!=Welcome.eastLength)
            {east[eastCount].setBackground(Welcome.currentColor);
             eastCount++;
            }
            
            if(eastCount==Welcome.eastLength)
                    break;
            

            }  // east loop
            
            while(true){
                
                Thread.sleep(speed);
                if(southCount!=-1)
            {south[southCount].setBackground(Welcome.currentColor);
             southCount--;
            }
            
            if(southCount==-1)
                    break;
            

            }  // south loop
            
            
            while(true){
                
                Thread.sleep(speed);
                if(westCount!=-1)
            {west[westCount].setBackground(Welcome.currentColor);
             westCount--;
            }
            
            if(westCount==-1)
                    break;
            

            }  // west loop
            
            if(pattern1Running){
                
                northCount=0;
                eastCount=0;
                southCount=holdSouth;
                westCount=holdWest;
                
            }
            if(Welcome.isRandomOn)
                Welcome.colorItems[3].doClick();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error in Threading","Attention Cheif!!",JOptionPane.ERROR_MESSAGE);
            }
            
            }  // end of loop
             
            pattern1Running=false;
        }
    }   // pattern1



class Pattern2 extends Thread{

        Thread th;
        static boolean flagForPattern2=true;
        static boolean pattern2Running=false;
        static int northCount=0;
        static int eastCount=0;
        static int holdSouth=Welcome.southLength-1;
        static int southCount=holdSouth;
        static int holdWest=Welcome.westLength-1;
        static int westCount=holdWest;
        public Pattern2() {
            th=new Thread(this,"Thread_For_Pattern2");

        }
        
 
     public void run(){
         
            Color color=Color.LIGHT_GRAY;
            int speed=100;
            //setting for rerunning
            Patterns.startingPatternRecognize(1);
            pattern2Running=true;
      while (flagForPattern2) {
               try{  
             while(true){
                
                Thread.sleep(speed);
                if(northCount!=Welcome.northLength)
            {north[northCount].setBackground(Welcome.currentColor);
             
             Thread.sleep(speed);
             north[northCount].setBackground(color);
             northCount++;
            }
            
            if(northCount==Welcome.northLength)
                    break;

            }  // north loop
           
   
              while(true){
                
                Thread.sleep(speed);
                if(eastCount!=Welcome.eastLength)
            {east[eastCount].setBackground(Welcome.currentColor);
             Thread.sleep(speed);
             east[eastCount].setBackground(color);
             eastCount++;
            }
            
            if(eastCount==Welcome.eastLength)
                    break;
            
            }  // east loop
             
             while(true){
                
                Thread.sleep(speed);
                if(southCount!=-1)
            {south[southCount].setBackground(Welcome.currentColor);
             Thread.sleep(speed);
             south[southCount].setBackground(color);
             southCount--;
            }
            
            if(southCount==-1)
                    break;
            
            }  // south loop
             
            while(true){
                
                Thread.sleep(speed);
                if(westCount!=-1)
            {west[westCount].setBackground(Welcome.currentColor);
            Thread.sleep(speed);
            west[westCount].setBackground(color);
             westCount--;
            }
            
            if(westCount==-1)
                    break ;
            
            }  // west loop
             
            if(pattern2Running){
                
                northCount=0;
                eastCount=0;
                southCount=holdSouth;
                westCount=holdWest;
                
            }
            
            if(Welcome.isRandomOn)
                Welcome.colorItems[3].doClick();
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error in Threading (Pattern_2)","Attention Cheif!!",JOptionPane.ERROR_MESSAGE);
             } 
              
            
        }
      
               pattern2Running=false;
     }
    }   // pattern2




class Choice implements ActionListener{
Pattern1 pattern1;
Pattern2 pattern2;
static boolean pattern1Control=true;
static boolean pattern2Control=true;
    public Choice(){
       clearPatternEffect(); 
    }

    public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()==patterns[0])
                {  
                   if(!Pattern1.pattern1Running && !Pattern2.pattern2Running) {
                   clearPatternEffect();    
                   if(pattern1Control){
                   pattern1=new Pattern1(); 
                   pattern1.start();
                   checkExitButtonPattern1();
                   pattern1Control=false;
                   }
                   
                   }
                   else
                   { int value=JOptionPane.showConfirmDialog(null,"A Pattern is already Running"
                                + "\nDo you want to stop it? ","Attention!",JOptionPane.WARNING_MESSAGE);
                    if(value==JOptionPane.OK_OPTION)
                        patterns[2].doClick();
                        }
                }
            else if(ae.getSource()==patterns[1])
                {
                   
                   if(!Pattern2.pattern2Running && ! Pattern1.pattern1Running) {
                   clearPatternEffect(); 
                   if(pattern2Control){ 
                   pattern2=new Pattern2(); 
                   pattern2.start();
                   checkExitButtonPattern2();
                   pattern2Control=false;
                   }
                   
                   }
                   else
                   {
                       int value=JOptionPane.showConfirmDialog(null,"A Pattern is already Running"
                                + "\nDo you want to stop it? ","Attention!",JOptionPane.WARNING_MESSAGE);
                    if(value==JOptionPane.OK_OPTION)
                        patterns[2].doClick();
                   }
                }
            
           
            else if(ae.getSource()==patterns[2]){
               
               if(Pattern1.flagForPattern1 || Pattern2.flagForPattern2){  
               Pattern1.flagForPattern1=false;
               pattern1=null;
               Pattern2.flagForPattern2=false;
               pattern2=null;
//               clearPatternEffe?ct();
               
            }
               }

            else if(ae.getActionCommand().equalsIgnoreCase("Customized")){
                new CustomLightingPanel();
             }
            else if (ae.getActionCommand().equalsIgnoreCase("Reset")){
                
            try {
        
             RandomAccessFile file=new RandomAccessFile("lightingLengths.txt","rw");
             file.writeInt(1);
             file.writeInt(1);
             file.writeInt(1);
             file.writeInt(1);

             file.close();
             JOptionPane.showMessageDialog(null,"Lighting are Reset","Information!",JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) { }
                              
            }
            
            
            } // end of actionPerformed
    
    
    static int northCount=Welcome.northLength-1;
    static int eastCount=Welcome.eastLength-1;
    static int southCount=Welcome.southLength-1;
    static int westCount=Welcome.westLength-1;
    void clearPatternEffect(){
        
        Pattern1.flagForPattern1=true;
        pattern1Control=true;
        Pattern2.flagForPattern2=true;
        pattern2Control=true;
        Color color=Color.lightGray;
        while(true){
            if(northCount!=-1)
            {north[northCount].setBackground(color);
             northCount--;
            }
            if(eastCount!=-1)
            {east[eastCount].setBackground(color);
             eastCount--;
            }
            if(southCount!=-1)
            {south[southCount].setBackground(color);
             southCount--;
            }
            if(westCount!=-1)
            {west[westCount].setBackground(color);
             westCount--;
            }  
            
            if(northCount==-1 && eastCount==-1 && southCount==-1 && westCount==-1)
                break;
        }

        
    }

    
         void checkExitButtonPattern1(){
         if(pattern1.isAlive()){
         pattern.add(patterns[2],3);
         
      }
       
}
         void checkExitButtonPattern2(){
         if(pattern2.isAlive()){
         pattern.add(patterns[2],3);
         
      }
       
}
   
    }


