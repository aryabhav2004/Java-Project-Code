package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class display extends JFrame implements ActionListener
{
    String atype,meter_p;
    display(String atype,String meter_p)
    {
        super("Main Window");
        this.meter_p = meter_p;
        this.atype = atype;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/ebs1.png"));
        Image image = img.getImage().getScaledInstance(1530,830, Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(image);
        JLabel img3 = new JLabel(img2);
        add(img3);
//-------------------------------------------------------------//
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu); // main menu

        JMenu menu1 = new JMenu("Menu");
        menu1.setFont(new Font("serif", Font.PLAIN,15));


        JMenuItem menu2 = new JMenuItem("New Customer");
        menu2.setFont(new Font("monospaced", Font.PLAIN,14));
        menu2.addActionListener(this);
        menu1.add(menu2);

        JMenuItem customer_details = new JMenuItem("Customer Details");
        customer_details.setFont(new Font("monospaced",Font.PLAIN,14));
        customer_details.addActionListener(this);
        menu1.add(customer_details);

        JMenuItem dep = new JMenuItem("Deposit Details");
        dep.setFont(new Font("monospaced",Font.PLAIN,14));
        dep.addActionListener(this);
        menu1.add(dep);

        JMenuItem bill = new JMenuItem("Calculate Bill");
        bill.setFont(new Font("monospaced",Font.PLAIN,14));
        bill.addActionListener(this);
        menu1.add(bill);

        JMenu info = new JMenu("Information");
        info.setFont(new Font("serif", Font.PLAIN,15));


        JMenuItem update = new JMenuItem("Update Information");
        update.setFont(new Font("monospaced",Font.PLAIN,14));
        update.addActionListener(this);
        info.add(update);

        JMenuItem view = new JMenuItem("View Information");
        view.setFont(new Font("monospaced",Font.PLAIN,14));
        view.addActionListener(this);
        info.add(view);

        JMenu user = new JMenu("User");
        user.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem pay_bill = new JMenuItem("Pay Bill");
        pay_bill.setFont(new Font("monospaced",Font.PLAIN,14));
        pay_bill.addActionListener(this);
        user.add(pay_bill);

        JMenuItem bill_details = new JMenuItem("Bill Details");
        bill_details.setFont(new Font("monospaced",Font.PLAIN,14));
        bill_details.addActionListener(this);
        user.add(bill_details);

        JMenu Bill2 = new JMenu("Bill");
        bill.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem generate = new JMenuItem("Generate bill");
        generate.setFont(new Font("monospaced",Font.PLAIN,14));
        generate.addActionListener(this);
        Bill2.add(generate);

        JMenu exit = new JMenu("Exit");
        exit.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem exit1 = new JMenuItem("Exit");
        exit1.setFont(new Font("monospaced",Font.PLAIN,14));
        exit1.addActionListener(this);
        exit.add(exit1);


        if(atype.equals("Admin"))
        {
            menu.add(menu1);
        }
        else{
            menu.add(Bill2);
            menu.add(user);
            menu.add(info);

        }
        menu.add(exit);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String mss = e.getActionCommand();
        if(mss.equals("New Customer"))
        {
            new customer();
        }
        else if(mss.equals("Customer Details"))
        {
            new details();
        }
        else if(mss.equals("Deposit Details"))
        {
            new deposit();
        }
        else if(mss.equals("Calculate Bill"))
        {
            new calc_bill();
        }
        else if(mss.equals("View Information"))
        {
            new view(meter_p);
        }
        else if(mss.equals("Update Information"))
        {
            new update(meter_p);
        }
        else if(mss.equals("Bill Details"))
        {
            new bill_details(meter_p);
        }
        else if(mss.equals("Exit"))
        {
            setVisible(false);
            new Login();
        }
        else if(mss.equals("Pay Bill"))
        {
            new pay_bill(meter_p);
        }
        else if(mss.equals("Generate bill"))
        {
            new final1(meter_p);
        }
    }

    public static void main(String[] args) {

        new display("","");
    }
}
