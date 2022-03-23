package ie.ait.soacondget;

import ie.ait.soacondget.PlayAccount;
import ie.ait.soacondget.PlayDao;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("players")
public class PlayResource {
   
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PlayAccount> getAllPlayers() {
        return PlayDao.instance.getAllPlayers();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{playerID}")
    public PlayAccount getPlayer(@PathParam("playerID") String playerID) {
        return PlayDao.instance.getPlayer(playerID);
    }
    
}
