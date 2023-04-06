package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class meter extends JFrame implements ActionListener
{
    JLabel heading,meter_no,type,code,bill_type,info,meter1,meter1_ch;
    Choice meter_ch,type_ch,code_ch,bill_type_ch;
    JButton submit;
    String meterNo;
    meter(String meterNo)
    {
        this.meterNo = meterNo;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.yellow);
        add(panel);

        heading = new JLabel("Meter Information");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahome",Font.BOLD,20));
        panel.add(heading);

        meter1 = new JLabel("Meter Number");
        meter1.setBounds(50,50,200,20);
        panel.add(meter1);

        meter1_ch = new JLabel(meterNo);
        meter1_ch.setBounds(180,50,200,20);
        panel.add(meter1_ch);

        meter_no = new JLabel("Meter Number");
        meter_no.setBounds(50,90,100,20);
        panel.add(meter_no);

        meter_ch = new Choice();
        meter_ch.setBounds(180,90,150,20);
        meter_ch.add("Outside");
        meter_ch.add("Inside");
        panel.add(meter_ch);

        type = new JLabel("Meter Type");
        type.setBounds(50,130,100,20);
        panel.add(type);

        code = new JLabel("Phase code");
        code.setBounds(50,170,100,20);
        panel.add(code);

        type_ch = new Choice();
        type_ch.add("Electric meter");
        type_ch.add("Solar meter");
        type_ch.add("Smart Meter");
        type_ch.setBounds(180,130,150,20);
        panel.add(type_ch);

        code_ch = new Choice();
        code_ch.add("011");
        code_ch.add("012");
        code_ch.add("021");
        code_ch.add("022");
        code_ch.add("031");
        code_ch.add("032");
        code_ch.add("041");
        code_ch.add("042");
        code_ch.add("051");
        code_ch.add("052");
        code_ch.add("061");
        code_ch.add("062");
        code_ch.add("071");
        code_ch.add("072");
        code_ch.add("081");
        code_ch.add("082");
        code_ch.add("091");
        code_ch.add("092");
        code_ch.add("099");
        code_ch.setBounds(180,170,150,20);
        panel.add(code_ch);

        bill_type = new JLabel("Bill Type");
        bill_type.setBounds(50,210,100,20);
        panel.add(bill_type);

        bill_type_ch = new Choice();
        bill_type_ch.add("Normal");
        bill_type_ch.add("Industrial");
        bill_type_ch.setBounds(180,210,150,20);
        panel.add(bill_type_ch);

        info = new JLabel("30 Days Billing will be there..");
        info.setBounds(50,250,150,20);
        panel.add(info);

        submit = new JButton("Submit");
        submit.setBounds(170,300,100,20);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        setLayout(new BorderLayout());
        add(panel , "Center");

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/meter1.png"));
        Image img1 = img.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(img1);
        JLabel img3 = new JLabel(img2);
        add(img3,"East");

        setSize(700,500);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit)
        {
            String smeterno = meterNo;
            String smeter_no = meter_ch.getSelectedItem();
            String smetertype = type_ch.getSelectedItem();
            String sphasecode = code_ch.getSelectedItem();
            String sbill =  bill_type_ch.getSelectedItem();

            String query_meter_info = "insert into meter_info values('"+smeterno+"','"+smeter_no+"','"+smetertype+"','"+sphasecode+"','"+sbill+"')";
            try
            {
                database b = new database();
                b.statement.executeUpdate(query_meter_info);

                JOptionPane.showMessageDialog(null,"Meter Information submitted successfully");
                setVisible(false);

            }catch(Exception D)
            {
                D.printStackTrace();
            }
        }
        else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new meter("");
    }
}
