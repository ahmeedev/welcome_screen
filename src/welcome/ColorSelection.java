package welcome;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import static welcome.Welcome.*;

public class ColorSelection implements ActionListener {
    static Color colorSelected=Color.BLUE;
    
    Timer currentColorSelectionTimer=new Timer(20,new CurrentColorSelection());   // checking the custom color continuosly

    public ColorSelection() {
        
             currentColorSelectionTimer.start(); // color selection start
    }

    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==colorItems[0])
        {
            Welcome.isRandomOn=false;
            colorSelected=Color.RED;
        }
        else if(e.getSource()==colorItems[1])
        {
            Welcome.isRandomOn=false;
            colorSelected=Color.BLUE;
        }
        else if(e.getSource()==colorItems[2])
        {
            Welcome.isRandomOn=false;
            colorSelected=Color.GREEN;
        }
        else if(e.getSource()==colorItems[3])
        {
            Welcome.isRandomOn=true;
            colorSelected=random();
        }
        
    }
       
  public static Color random(){
      
    Random rand=new Random();
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    Color randomColor = new Color(r, g, b);
    return randomColor;
    
  }
  
}

 class CurrentColorSelection implements ActionListener{
 
        public void actionPerformed(ActionEvent ae) {
          
            Welcome.currentColor=ColorSelection.colorSelected;
          
        }
    }