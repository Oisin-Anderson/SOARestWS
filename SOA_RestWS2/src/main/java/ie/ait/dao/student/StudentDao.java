package ie.ait.dao.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public enum StudentDao {
    
    instance;
    
    private Connection con = null;
    
    private StudentDao(){
        try{
            System.out.println("Loading DB Driver....");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("DB Driver Loaded");
            
            con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/sample",
                    "billy",
                    "billy");
            
        }catch (ClassNotFoundException ex){
            System.err.println("\nClassNotFoundException");
            ex.printStackTrace();
        }catch (SQLException ex){
            System.err.println("\nSQLException");
            ex.printStackTrace();
        }
    }
    
    
    public List<Student> getStudents(){
        
        List<Student> students = new ArrayList<Student>();
        try{
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM student");
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String course = rs.getString("course");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return students;
    }
    
    
}
