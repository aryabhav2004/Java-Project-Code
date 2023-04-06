package electricity;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener
{
    JTextField usertxt,userpass;
    Choice loginnchoice;
    JButton login,signup,cancel;

    Login()
    {
        super("Login"); // for title
        getContentPane().setBackground(Color.white );

        JLabel username  =  new JLabel("UserName");
        add(username);
        username.setBounds(300,60,100,20);

        usertxt = new JTextField();
        usertxt.setBounds(380,60 , 150,20);
        add(usertxt);
/////////////////////////////////////////////////////////////////
        JLabel password = new JLabel("Password");
        add(password);
        password.setBounds(300,110,100,20);

        userpass = new JTextField();
        userpass.setBounds(380,110 , 150,20);
        add(userpass);
/////////////////////////////////////////////
        JLabel loginn = new JLabel("Login In As");
        add(loginn);
        loginn.setBounds(300,160,80,20);

        loginnchoice = new Choice();
        loginnchoice.add("Admin");
        loginnchoice.add("Customer");
        loginnchoice.setBounds(380,160,150,20);
        add(loginnchoice);
/////////////////////////////////////////////////////////////
        login = new JButton("Login");
        login.setBounds(320,200,90,20);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(450,200,90,20);
        cancel.addActionListener(this);
        add(cancel);

        signup = new JButton("SignUp");
        signup.setBounds(385,230,90,20);
        signup.addActionListener(this);
        add(signup);
/////////////////////////////////////////////////////////////////////
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("images/final.png"));
        JLabel proflabel = new JLabel(img1);
        proflabel.setBounds(0,5,250,250);
        add(proflabel);

        setSize(600,300);
        setLocation(500,270);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == login)
        {
            String susername  = usertxt.getText();
            String spass = userpass.getText();
            String schoice = loginnchoice.getSelectedItem();

            try
            {
                database h = new database();
                String query = "select * from SignUp where username = '"+susername+"' and pass =  '"+spass+"' and user = '"+schoice+"'" ;
                ResultSet resultSet = h.statement.executeQuery(query);

                if(resultSet.next()) {
                    String meter5 = resultSet.getString("meter_no");
                    setVisible(false);
                    new display(schoice,meter5);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Invalid Login");
                }
            }

            catch(Exception f)
            {
                f.printStackTrace();
            }

        }
        else if(e.getSource() == cancel)
        {
            setVisible(false);
        }
        else
        {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}


