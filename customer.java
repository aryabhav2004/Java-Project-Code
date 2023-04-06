// first we made frame then add panel on the frame , add labels and buttons on panel
package electricity;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
public class customer extends JFrame implements ActionListener
{
    JLabel new_customer,meter_txt,new_Customer,meter_no,address,city,state,email,phone_no;
    JTextField customer_field,address_txt,city_txt,state_txt,email_txt,phone_txt;
    JButton next,cancel1;
    customer()
    {
        super("New Customer");
        setSize(700,500);
        setLocation(500,200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.YELLOW);
        add(panel);

        new_customer = new JLabel("New Customer");
        new_customer.setBounds(180,10,200,20);
        new_customer.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(new_customer);

        new_Customer = new JLabel("New Customer");
        new_Customer.setBounds(50,80,100,20);
        panel.add(new_Customer);

        customer_field = new JTextField();
        customer_field.setBounds(180,80,140,20);
        panel.add(customer_field);

        meter_no = new JLabel("Meter No");
        meter_no.setBounds(50,130,100,20);
        panel.add(meter_no);

        meter_txt = new JLabel("");
        meter_txt.setBounds(180,130,140,20);
        panel.add(meter_txt);

        Random r = new Random();
        Long number  = r.nextLong() % 1000000;
        meter_txt.setText("" + Math.abs(number));

        address = new JLabel("Address");
        address.setBounds(50,180,100,20);
        panel.add(address);

        address_txt = new JTextField();
        address_txt.setBounds(180,180,140,20);
        panel.add(address_txt);

        city = new JLabel("City");
        city.setBounds(50,230,100,20);
        panel.add(city);

        city_txt = new JTextField();
        city_txt.setBounds(180,230,140,20);
        panel.add(city_txt);

        state = new JLabel("State");
        state.setBounds(50,280,100,20);
        panel.add(state);

        state_txt = new JTextField();
        state_txt.setBounds(180,280,140,20);
        panel.add(state_txt);

        email = new JLabel("Email");
        email.setBounds(50,330,100,20);
        panel.add(email);

        email_txt = new JTextField();
        email_txt.setBounds(180,330,140,20);
        panel.add(email_txt);

        phone_no = new JLabel("Phone no");
        phone_no.setBounds(50,380,100,20);
        panel.add(phone_no);

        phone_txt = new JTextField();
        phone_txt.setBounds(180,380,140,20);
        panel.add(phone_txt);

        next = new JButton("Next");
        next.setBounds(70 , 420 , 100 , 20);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        panel.add(next);

        cancel1 = new JButton("Cancel");
        cancel1.setBounds(200 , 420 , 100 , 20);
        cancel1.setBackground(Color.BLACK);
        cancel1.setForeground(Color.WHITE);
        cancel1.addActionListener(this);
        panel.add(cancel1);

        setLayout(new BorderLayout());
        add(panel , "Center");  // this will add the panel in the center


        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("images/new.png"));
        Image img2 = img1.getImage().getScaledInstance(220,200, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel img4 = new JLabel(img3);
        add(img4,"West");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next)
        {
            String scustomer = customer_field.getText();
            String smeter = meter_txt.getText();
            String saddress = address_txt.getText();
            String scity = city_txt.getText();
            String sstate = state_txt.getText();
            String semail = email_txt.getText();
            String sphone  = phone_txt.getText();

            // two query will run
            // 1 - all data to be in new customer
            //2  - meter no and name in sign up

            String query_customer = "insert into newCustomer values('"+scustomer+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+semail+"','"+sphone+"')";
            String query_signUp  = "insert into SignUp values('"+smeter+"','','"+scustomer+"','','')";

            try
            {
                database c = new database();
                c.statement.executeUpdate(query_customer);
                c.statement.executeUpdate(query_signUp);

                JOptionPane.showMessageDialog(null ,"Customer Details Added Succesfully");
                setVisible(false);
                new meter(smeter);

            }catch (Exception d)
            {
                d.printStackTrace();
            }
        }
        else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new customer();

    }
}
