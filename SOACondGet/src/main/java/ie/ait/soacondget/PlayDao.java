package ie.ait.soacondget;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public enum PlayDao {
    
    instance;
    
    private Connection con = null;
    
    private PlayDao(){
        try{
            System.out.println("Loading DB Driver....");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("DB Driver Loaded");
            
            con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/soacondget",
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
    
    
    public PlayAccount getPlayer(int playerID){
        
        PlayAccount player = null;
        try{
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM players where id = ?");
            pstmt.setString(1, Integer.toString(playerID));
            
            ResultSet rs = pstmt.executeQuery();
            
            if (!rs.next()){
                return null;
            }
            
            int id = rs.getInt("id");
            String pname = rs.getString("pname");
            int goals = rs.getInt("goals");
            String timestamp = rs.getString("TIMESTAMP");

            player = new PlayAccount(id, pname, goals, timestamp);
                
        } catch (SQLException ex) {
            System.err.println("\nSQLException");
            ex.printStackTrace();
        }
        
        System.out.println(player);
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
                
                players.add(new PlayAccount(id, pname, goals, timestamp));
            }
        } catch (SQLException ex) {
            System.err.println("\nSQLException");
            ex.printStackTrace();
        }
        
        System.out.println(players);
        
        return players;
    }

    public void create(PlayAccount player) {
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO players VALUES(?,?,?,?)");
            pstmt.setInt(1, player.getId());
            pstmt.setString(2, player.getPname());
            pstmt.setInt(3, player.getGoals());
            pstmt.setString(4, player.getTimestamp());

            int count = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("\nSQLException");
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM players WHERE ID = ?");
            pstmt.setInt(1, id);
            

            int count = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("\nSQLException");
            ex.printStackTrace();
        }
    }
    
    public void deleteAll() {
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE * FROM players");
            
            int count = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("\nSQLException");
            ex.printStackTrace();
        }
    }

    public int getNextId() {

        int maxId = 0;

        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT max(id) FROM players");

            ResultSet rs = pstmt.executeQuery();

            rs.next();

            maxId = rs.getInt(1);

        } catch (SQLException ex) {
            System.err.println("\nSQLException");
            ex.printStackTrace();
        }

        return maxId + 1;
    }
    
    
}
