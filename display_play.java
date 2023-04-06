package electricity;
import javax.swing.*;
import java.awt.*;

public class display_play extends JFrame
{
    display_play()
    {
        JLabel n = new JLabel("Paid Successfully");
        n.setBounds(160,200,500,100);
        n.setFont(new Font("monospaced",Font.BOLD,40));
        add(n);

        setSize(700,700);
        setLocation(500,100);
        getContentPane().setBackground(Color.YELLOW);
        setLayout(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new display_play();
    }
}
