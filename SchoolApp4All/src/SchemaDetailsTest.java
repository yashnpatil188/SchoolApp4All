import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
public class SchemaDetailsTest {
   public static void main(String args[]) throws Exception {
     String databaseName = "school";
     String userName = "root";
     String password = "maauli123";
     String mySQLPort = "3306";
     String hostUrl = "127.0.0.1";
     // Setup the connection with the DB
     Class.forName("com.mysql.jdbc.Driver");
     Connection conn = DriverManager.getConnection("jdbc:mysql://" + hostUrl
         + ":" + mySQLPort, userName, password);
     // --- LISTING DATABASE SCHEMA NAMES ---
     ResultSet resultSet = conn.getMetaData().getCatalogs();
     ResultSet resultSetColumn = conn.getMetaData().getCatalogs();
     /*while (resultSet.next()) {
       System.out.println("Schema Name = " + resultSet.getString("TABLE_CAT"));
     }
     resultSet.close();*/
     
     // --- LISTING DATABASE TABLE NAMES ---
     String[] types = { "TABLE" };
     resultSet = conn.getMetaData().getTables(databaseName, null, "%", types);
     String tableName = "";
     while (resultSet.next()) {
       tableName = resultSet.getString(3);
       System.out.println("Table Name = " + tableName);
       
    // --- LISTING DATABASE COLUMN NAMES ---
       DatabaseMetaData meta = conn.getMetaData();
       resultSetColumn = meta.getColumns(databaseName, null, tableName, "%");
       while (resultSetColumn.next()) {
      	 System.out.println("Column Name of table " + tableName + " = " + resultSetColumn.getString(4));
       }
     }
     resultSet.close();
     
     /*// --- LISTING DATABASE COLUMN NAMES ---
     DatabaseMetaData meta = conn.getMetaData();
     resultSet = meta.getColumns(databaseName, null, tableName, "%");
     while (resultSet.next()) {
    	 System.out.println("Column Name of table " + tableName + " = " + resultSet.getString(4));
     }*/
   }
}