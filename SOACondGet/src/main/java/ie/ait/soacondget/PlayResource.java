package ie.ait.soacondget;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("players")
public class PlayResource {
   
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<PlayAccount> getAllPlayers() {
        System.out.println("GetPlayers");
        return PlayDao.instance.getAllPlayers();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{playerID}")
    public PlayAccount getPlayer(@PathParam("playerID") String playerID) {
        System.out.println("GetPlayer");
        return PlayDao.instance.getPlayer(Integer.parseInt(playerID));
    }
    
}
