package electricity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class database
{
    Connection conn;
    Statement statement;
    database()
    {
        // connectivity is  done . java project is connected with database  and query will be run wit the help of statement
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "mp7032@binit");
            statement = conn.createStatement();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
