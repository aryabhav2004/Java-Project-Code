package electricity;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener       // action listerner for clicking stuff
{

    JLabel create_acc,acc,id,username,name,pass,meter;
    JTextField id1,username1,name1,pass1,meter1;
    JButton create , back;
    Choice create1;
    Signup()
    {
        super("Sign Up Page");

        setSize(600,350);
        setLocation(500,200);

        create_acc = new JLabel("Create-Account");
        create_acc.setBounds(4,4,120,20);
        add(create_acc);

        acc = new JLabel("Create Account As");
        acc.setBounds(25,30,120,20);
        add(acc);

        meter = new JLabel("Meter No");
        meter.setBounds(25,75,120,20);
        meter.setVisible(false);
        add(meter);

        id = new JLabel("Employer ID");
        id.setBounds(25,75,100,20);
        id.setVisible(true);
        add(id);

        username = new JLabel("UserName");
        username.setBounds(25,120,100,20);
        add(username);

        name = new JLabel("Name");
        name.setBounds(25,165,100,20);
        add(name);

        pass= new JLabel("Password");
        pass.setBounds(25,210,100,20);
        add(pass);
/////////////////////////////////////////////////////////

        id1 = new JTextField();
        id1.setBounds(160,75,120,20);
        id1.setVisible(true);
        add(id1);

        username1 = new JTextField();
        username1.setBounds(160,120,120,20);
        add(username1);

        meter1 = new JTextField();
        meter1.setBounds(160,75,120,20);
        meter1.setVisible(false);
        add(meter1);

        name1= new JTextField("");
        name1.setBounds(160,165,120,20);
        add(name1);

        meter1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {  // when meter no is put
                try
                {
                    database db = new database();
                    ResultSet resultSet = db.statement.executeQuery("select * from SignUp where meter_no = '"+meter1.getText()+"'");
                    if(resultSet.next())
                    {
                        name1.setText(resultSet.getString("name"));
                    }
                }
                catch (Exception E)
                {
                    E.printStackTrace();
                }
            }
        });
        pass1= new JTextField();
        pass1.setBounds(160,210,120,20);
        add(pass1);
//////////////////////////////////////////////////////////
        create1 = new Choice();
        create1.add("Admin");
        create1.add("Customer");
        create1.setBounds(160,30,120,20);
        add(create1);


        create1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String option = create1.getSelectedItem();
                if(option.equals("Customer"))
                {
                    id.setVisible(false);
                    name1.setEditable(false);
                    id1.setVisible(false);
                    meter.setVisible(true);
                    meter1.setVisible(true);

                }
                else
                {
                    id.setVisible(true);
                    id1.setVisible(true);
                    meter.setVisible(false);
                    meter1.setVisible(false);

                }
            }
        });
/////////////////////////////////////////////
        create = new JButton("Create");
        create.setBounds(40,260,100,20);
        create.addActionListener(this);  // this will move to action perfrom if we click
        add(create);

        back = new JButton("Back");
        back.setBounds(170,260,100,20);
        back.addActionListener(this);
        add(back);

//------------------------------------------------------------------------//
        ImageIcon img2 = new ImageIcon(ClassLoader.getSystemResource("images/signup.png"));
        JLabel sign_up = new JLabel(img2);
        sign_up.setBounds(200,15,500,300);
        add(sign_up);
//---------------------------------------------------------------------//

        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {  // which button will be clicked will store in e
        if(e.getSource()==create)
        {
            String schoice  =  create1.getSelectedItem();
            String suname   =  username1.getText();
            String sname    =  name1.getText();
            String spass    =  pass1.getText();
            String smeter   =  meter1.getText();
            try
            {
                database c = new database();
                String query = null;
                if(create1.equals("Admin")) {
                    query = "insert into SignUp value('" + smeter + "','" + suname + "','" + sname + "','" + spass + "','" + schoice + "')";
                }
                else {
                    query = "update SignUp set username = '"+suname+"' , pass = '"+spass+"' , user= '"+schoice+"'where meter_no = '"+smeter+"'";
                //   whenever meter no is there there the username and password will be set
                }
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Account created Successfully");
                setVisible(false);
                new Login();
            }catch(Exception d)
            {
                d.printStackTrace();
            }
        }
        else if (e.getSource() == back)
        {
            setVisible(false);
            new Login();
        }
    }
    public static void main(String[] args) {
        new Signup();
    }
}
