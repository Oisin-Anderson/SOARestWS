package ie.ait.soacondget;

import ie.ait.soacondget.PlayAccount;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public enum PlayDao {
    
    instance;
    
    private Connection con = null;
    
    private PlayDao(){
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
    
    
    public PlayAccount getPlayer(String playerID){
        
        PlayAccount player = new PlayAccount();
        try{
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM players where id = " + playerID + ";");
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                player.setId(rs.getInt("id"));
                player.setPname(rs.getString("pname"));
                player.setGoals(rs.getInt("goals"));
                player.setTimestamp(rs.getString("TIMESTAMP"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return player;
    }
    
    
    public List<PlayAccount> getAllPlayers(){
        
        List<PlayAccount> players = new ArrayList<PlayAccount>();
        try{
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM players");
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String pname = rs.getString("pname");
                int goals = rs.getInt("goals");
                String timestamp = rs.getString("TIMESTAMP");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return players;
    }
    
    
}
