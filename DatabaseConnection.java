/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agricultureproject;
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Dead
 */
public class DatabaseConnection {
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ResultSetMetaData rsmd = null;
    
    public Connection getConnection()
    {
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/irrigation_db","root","");

            if(conn == null)
            { 
                JOptionPane.showMessageDialog(null,"Failed to connect."); 
            } 
                return conn;
        }
        catch(Exception e){ 
            System.out.println(e);
            return null;
        } 

    }
    
    
    
    public boolean insertData(String tableName,HashMap hm)
    {
        try 
        {
            StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
            StringBuilder placeholders = new StringBuilder();

            for (Iterator<String> iter = hm.keySet().iterator(); iter.hasNext();) {
                sql.append(iter.next());
                placeholders.append("?");

                if (iter.hasNext()) {
                    sql.append(",");
                    placeholders.append(",");
                }
            }

            sql.append(") VALUES (").append(placeholders).append(")");
            System.out.println(sql);
            getConnection();
            pst = conn.prepareStatement(sql.toString());
            int i = 0;

            for (Object value : hm.values()) 
            {
                pst.setObject(++i, value);
                System.out.println(value);
            }
            System.out.println(pst);
            int affectedRows = pst.executeUpdate();
            return true;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
// Get a set of the entries
//      Set set = hm.entrySet();
//      
//      // Get an iterator
//      Iterator i = set.iterator();
//      
//      // Display elements
//      while(i.hasNext()) {
//         Map.Entry me = (Map.Entry)i.next();
//         System.out.print(me.getKey() + ": ");
//         System.out.println(me.getValue());
//      }
//      System.out.println();
    }
    
    public int maxId(String tableName, String columnName)
    {
        try
        {
            int id;
            getConnection();
            System.out.println(tableName);
            String query =  "SELECT MAX(" + columnName + ") id from " +tableName ;
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            if(rs.next())
            {
                id = Integer.parseInt(rs.getString("id"));
                return id;
            }
            else
            {
                return 0;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

    public HashMap<Integer,HashMap> getAllInformation(String tableName, String condition)
    {
        //ArrayList<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        HashMap<Integer,HashMap> mainTable = new HashMap<Integer,HashMap>();
        int columnNumber = 0;
        String columnName = "";
        String columnValue = "";
        
        try
        {
            getConnection();

            String query =  "SELECT * FROM " + tableName;
            if(!condition.trim().equals(""))
            {
                query = query.concat(condition);
            }
            System.out.println(query);
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            int j=0;
            while(rs.next())
            {
                HashMap subTable = new HashMap(j);
                //subTable.clear();
                rsmd = rs.getMetaData();
                columnNumber = rsmd.getColumnCount();
                
                for(int i=1; i<=columnNumber;i++)
                {
                    columnName = rsmd.getColumnName(i);
                    columnValue = rs.getString(rsmd.getColumnName(i));
                    subTable.put(columnName, columnValue);
                }
                //rows.add(subTable);
                //System.out.println(subTable);
                mainTable.put(++j, subTable);
                //System.out.println(rows);
            }
            //System.out.println(rows);
            //System.out.println(subTable);
            //System.out.println(mainTable);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        finally
        {
            return mainTable;
        }  
    }
}
