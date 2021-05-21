package welcome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.RandomAccessFile;
import javax.swing.event.*;

public class CustomLightingPanel {


    private JFrame frameLightPanels;
    JPanel lightMain;
    JSpinner north;
    JSpinner south;
    JSpinner east;
    JSpinner west;

    // for giving the label values
    private JLabel labelNorth;
    private JLabel labelSouth;
    private JLabel labelEast;
    private JLabel labelWest;
    JButton save;

    static int holdNorthLength = 1;
    static int holdEastLength = 1;
    static int holdSouthLength = 1;
    static int holdWestLength = 1;

    Font font = new Font("gill sans", Font.BOLD, 14);

    public CustomLightingPanel() {

        frameLightPanels = new JFrame("Set Custom Panels Length");
        frameLightPanels.setSize(480, 300);
//     Image image=Toolkit.getDefaultToolkit().getImage("");
//     setIconImage(image);  
        lightMain = new JPanel();
        lightMain.setLayout(new BorderLayout());
        initialValues();
        SpinnerModel modelNorth = new SpinnerNumberModel(holdNorthLength, 1, 20, 1);
        SpinnerModel modelEast = new SpinnerNumberModel(holdEastLength, 1, 20, 1);
        SpinnerModel modelSouth = new SpinnerNumberModel(holdSouthLength, 1, 20, 1);
        SpinnerModel modelWest = new SpinnerNumberModel(holdWestLength, 1, 20, 1);

        JPanel northPanel = new JPanel();
        north = new JSpinner(modelNorth);
        north.setFont(font);
        north.setPreferredSize(new Dimension(100, 40));
        north.addChangeListener(new Setting());
        northPanel.add(north);
        lightMain.add(northPanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        south = new JSpinner(modelSouth);
        south.setFont(font);
        south.setPreferredSize(new Dimension(100, 40));
        south.addChangeListener(new Setting());
        southPanel.add(south);
        lightMain.add(southPanel, BorderLayout.SOUTH);

        JPanel eastPanel = new JPanel();
        east = new JSpinner(modelEast);
        east.setFont(font);
        east.setPreferredSize(new Dimension(50, 35));
        eastPanel.setLayout(new BorderLayout());
        east.addChangeListener(new Setting());
        eastPanel.add(east);
//     eastPanel.add(new Label(" "));
        lightMain.add(eastPanel, BorderLayout.EAST);

        JPanel westPanel = new JPanel();
        west = new JSpinner(modelWest);
        west.setFont(font);
        west.setPreferredSize(new Dimension(50, 35));
        westPanel.setLayout(new BorderLayout());
        west.addChangeListener(new Setting());
        westPanel.add(west);
        lightMain.add(westPanel, BorderLayout.WEST);

        //=================================================//
        JPanel middle = new JPanel();
        JPanel forButton = new JPanel();
        forButton.setLayout(null);

        labelNorth = new JLabel("North's Lights ", SwingConstants.CENTER);
        labelSouth = new JLabel("South's Lights ", SwingConstants.CENTER);
        labelEast = new JLabel("East's Lights:-", SwingConstants.CENTER);
        labelWest = new JLabel("-:West's Lights", SwingConstants.CENTER);

        labelNorth.setFont(font);
        labelSouth.setFont(font);
        labelEast.setFont(font);
        labelWest.setFont(font);
        ImageIcon icon = new ImageIcon("loading.png");
        save = new JButton("Change");
        save.setFont(font);
        save.setIcon(icon);
        save.addActionListener(new Setting());
//     save.setPreferredSize(new Dimension(90,50));
        save.setBounds(22, 45, 135, 50);
        forButton.add(save);

        middle.setLayout(new BorderLayout());
        middle.add(labelNorth, BorderLayout.NORTH);
        middle.add(labelSouth, BorderLayout.SOUTH);
        middle.add(labelEast, BorderLayout.EAST);
        middle.add(labelWest, BorderLayout.WEST);
        middle.add(forButton);
        lightMain.add(middle, BorderLayout.CENTER);
        //=================================================//

        frameLightPanels.add(lightMain);
        frameLightPanels.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameLightPanels.setResizable(false);
        frameLightPanels.setVisible(true);

    }

    void initialValues() {

        try {

            RandomAccessFile file = new RandomAccessFile("lightingLengths.txt", "rw");
            holdNorthLength = file.readInt();
            holdEastLength = file.readInt();
            holdSouthLength = file.readInt();
            holdWestLength = file.readInt();

            file.close();

        } catch (Exception e) {
//           JOptionPane.showMessageDialog(null,"Error!, File is missing","Attention!!",JOptionPane.ERROR_MESSAGE);
            holdNorthLength = 1;
            holdEastLength = 1;
            holdSouthLength = 1;
            holdWestLength = 1;
        }

    }

    class Setting implements ChangeListener, ActionListener {

        public void stateChanged(ChangeEvent ce) {
            if (ce.getSource() == north) {
                holdNorthLength = (int) north.getValue();
            } else if (ce.getSource() == east) {
                holdEastLength = (int) east.getValue();
            } else if (ce.getSource() == south) {
                holdSouthLength = (int) south.getValue();
            } else if (ce.getSource() == west) {
                holdWestLength = (int) west.getValue();
            }

        }

        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == save) {
                try {

                    RandomAccessFile file = new RandomAccessFile("lightingLengths.txt", "rw");
                    file.writeInt(holdNorthLength);
                    file.writeInt(holdEastLength);
                    file.writeInt(holdSouthLength);
                    file.writeInt(holdWestLength);

                    file.close();
                    JOptionPane.showMessageDialog(null, "Lighting_Panels Customized", "Done!", JOptionPane.INFORMATION_MESSAGE);
                    getFrameLightPanels().dispose();
                    
                    
                       int value=JOptionPane.showConfirmDialog(null,"Are you want to restart "
                               + "to see the change??","Attention!",JOptionPane.WARNING_MESSAGE);
                    if(value==JOptionPane.OK_OPTION)
                    {
                        Runtime.getRuntime().exec("java -jar Welcome.jar");
                        System.exit(0);
                    }
                   
                } catch (Exception e) {

                }
            }

        }

    }

    public static void main(String[] args) {
        new CustomLightingPanel();
    }

    /**
     * @return the frameLightPanels
     */
    public JFrame getFrameLightPanels() {
        return frameLightPanels;
    }

}
