package ie.ait.dao.bank;

import ie.ait.dao.bank.BankAccount;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public enum BankDao {
    
    instance;
    
    private Connection con = null;
    
    private BankDao(){
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
    
    
    public BankAccount getAccount(String AccountNumber){
        
        BankAccount account = new BankAccount();
        try{
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM accounts where account_number = " + AccountNumber + ";");
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                account.setBCode(rs.getString("branch_code"));
                account.setAcNumber(rs.getString("account_number"));
                account.setCustName(rs.getString("cust_name"));
                account.setCustAddress(rs.getString("cust_address"));
                account.setCustRating(rs.getInt("cust_rating"));
                account.setBalance(rs.getFloat("balance"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return account;
    }
    
    
    public List<BankAccount> getAllAccounts(){
        
        List<BankAccount> accounts = new ArrayList<BankAccount>();
        try{
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM ACCOUNTS");
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                String branch_code = rs.getString("branch_code");
                String account_number = rs.getString("account_number");
                String cust_name = rs.getString("cust_name");
                String cust_address = rs.getString("cust_address");
                int cust_rating = rs.getInt("cust_rating");
                float balance = rs.getFloat("balance");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return accounts;
    }
    
    
}
