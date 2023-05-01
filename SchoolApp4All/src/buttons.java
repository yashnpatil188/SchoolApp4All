import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 

public class buttons extends JPanel{
    public static void main (String args []) {
    
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BULLS EYE");
        JPanel panel = new JPanel();
        JButton button1 = new JButton("click me");
        JButton button2 = new JButton("second button");
        
        
        panel.add(button1);
        panel.add(button2);
        
        frame.add(panel);
        
        button1.addActionListener( new Action1());
        button2.addActionListener(new Action2());
    }
    static class Action1 implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JFrame Frame1 = new JFrame();
            JPanel panel1 = new JPanel();
            JLabel label1 = new JLabel("OM NAMA SHIVAYA");
            Frame1.setVisible(true);
            Frame1.setSize(300,300);
            Frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel1.add(label1);
            Frame1.add(panel1);
        }
    
    }
        
    static class Action2 implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JPanel panel2 = new JPanel();
            
            JLabel label2 = new JLabel(" NARAYANAYA GANAPATHI ");
            GridBagConstraints c = new GridBagConstraints();
            c.gridx=0;
            c.gridy=0;
            panel2.add(label2,c);
            panel2.setSize(50,50);
            
        }
    }
    
}