package electricity;
import javax.swing.*;

//import java.awt.*;

public class Main extends JFrame {
    Main()  // object in the main method
    {
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/pa.png"));    // loads from memory
        JLabel stick = new JLabel(img);
        add(stick);
        setSize(500,500);
        setLocation(500,170);
        setVisible(true);

        try
        {
            Thread.sleep(3000);
            setVisible(false);
            new Login();
        }
        catch(Exception e)
        {
            System.out.println();
        }

    }


    public static void main(String[] args) {
        new Main(); // object
    }
}