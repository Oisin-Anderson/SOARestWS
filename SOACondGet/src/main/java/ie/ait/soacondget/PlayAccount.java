package ie.ait.soacondget;

import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="player")
@XmlType(propOrder = {"id", "pname", "goals", "TIMESTAMP"})
public class PlayAccount {
    private int id;
    private String pname;
    private int goals;
    private Timestamp timestamp;
    
    public PlayAccount (int id, String pname, int goals, Timestamp timestamp) {
        this.id = id;
        this.pname = pname;
        this.goals = goals;
        this.timestamp = timestamp;
    }
    
    public PlayAccount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PlayAccount{" + "id=" + id + ", pname=" + pname + ", goals=" + goals + ", timestamp=" + timestamp + '}';
    }
    

    
    
}
