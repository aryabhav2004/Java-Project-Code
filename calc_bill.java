package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class calc_bill extends JFrame implements ActionListener
{
    JLabel calc_bill,meter_no,name,name_txt,address,address_txt,unit,month;
    JTextField unit1;
    Choice meter_ch,Month1;
    Button submit,cancel;
    calc_bill()
    {
        super("Calculate Bill");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.YELLOW);
        add(panel);

        calc_bill = new JLabel("Calculate Bill");
        calc_bill.setBounds(100,10,200,20);
        calc_bill.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(calc_bill);

        meter_no = new JLabel("Meter Number");
        meter_no.setBounds(50,90,100,20);
        meter_no.setFont(new Font("Arial",Font.PLAIN,14));
        panel.add(meter_no);

        meter_ch = new Choice();
        try
        {
            database d = new database();
            ResultSet resultSet = d.statement.executeQuery("select * from newCustomer");  // good for retreival
            while(resultSet.next()) {
                meter_ch.add(resultSet.getString("meter"));
            }
        }catch(Exception d)
        {
            d.printStackTrace();
        }
        meter_ch.setBounds(160,90,150,20);
        panel.add(meter_ch);


        name = new JLabel("Name");
        name.setBounds(50,140,200,20);
        name.setFont(new Font("Arial",Font.PLAIN,14));
        panel.add(name);

        name_txt = new JLabel("");
        name_txt.setBounds(160,140,150,20);
        panel.add(name_txt);

        address = new JLabel("Address");
        address.setBounds(50,190,200,20);
        address.setFont(new Font("Arial",Font.PLAIN,14));
        panel.add(address);

        address_txt = new JLabel("");
        address_txt.setBounds(160,190,150,20);
        panel.add(address_txt);

        meter_ch.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try
                {
                    database i = new database();
                    ResultSet resultSet1 = i.statement.executeQuery("select * from newCustomer where meter = '"+meter_ch.getSelectedItem()+"'");
                    while(resultSet1.next())
                    {
                        name_txt.setText(resultSet1.getString("name"));
                        address_txt.setText(resultSet1.getString("address"));
                    }
                }catch(Exception f)
                {
                    f.printStackTrace();
                }
            }
        });

        unit = new JLabel("Unit");
        unit.setBounds(50,240,200,20);
        unit.setFont(new Font("Arial",Font.PLAIN,14));
        panel.add(unit);

        unit1 = new JTextField();
        unit1.setBounds(160,240,150,20);
        panel.add(unit1);

        month = new JLabel("Month");
        month.setBounds(50,290,100,20);
        month.setFont(new Font("Arial",Font.PLAIN,14));
        panel.add(month);

        Month1 = new Choice();
        Month1.add("January");
        Month1.add("February");
        Month1.add("March");
        Month1.add("April");
        Month1.add("May");
        Month1.add("June");
        Month1.add("July");
        Month1.add("August");
        Month1.add("September");
        Month1.add("October");
        Month1.add("November");
        Month1.add("December");
        Month1.setBounds(160,290,150,20);
        panel.add(Month1);

        submit = new Button("Submit");
        submit.setBounds(65,370,100,20);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new Button("Cancel");
        cancel.setBounds(195,370,100,20);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);


        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/bill.png"));
        Image img1 = img.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(img1);
        JLabel img3 = new JLabel(img2);
        add(img3,"East");

        setSize(700,500);
        setLocation(420,150);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit)
        {
            String smeter = meter_ch.getSelectedItem();
            String smonth = Month1.getSelectedItem();
            String sunit  = unit1.getText();

            int total_bill = 0;
            int units = Integer.parseInt(sunit);

            String query = "select * from  tax";
            try
            {
                database m = new database();
                ResultSet resultSet = m.statement.executeQuery(query);
                while(resultSet.next())
                {
                    total_bill+= units * Integer.parseInt(resultSet.getString("cost_unit"));
                    total_bill+= Integer.parseInt(resultSet.getString("meter_r"));
                    total_bill+= Integer.parseInt(resultSet.getString("service"));
                    total_bill+= Integer.parseInt(resultSet.getString("service_tax"));
                    total_bill+= Integer.parseInt(resultSet.getString("add_tax"));
                }
            }
            catch(Exception b)
            {
                b.printStackTrace();
            }
            String query2 = "insert into bill values('"+smeter+"','"+smonth+"','"+sunit+"','"+total_bill+"','Not Paid')";
            try
            {
                database h = new database();
                h.statement.executeUpdate(query2);
                JOptionPane.showMessageDialog(null,"Customer Bill updated Successfully");
                setVisible(false);

            }catch(Exception q)
            {
                q.printStackTrace();
            }
         }

        else
        {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new calc_bill();
    }
}
