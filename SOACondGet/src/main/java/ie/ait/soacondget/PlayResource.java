package ie.ait.soacondget;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("players")
public class PlayResource {
   
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllPlayers() {
        System.out.println("GetPlayers");
        return Response.status(200).entity(PlayDao.instance.getAllPlayers()).build();
    }
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{playerID}")
    public Response getPlayer(@PathParam("playerID") String playerID) {
        System.out.println("GetPlayer");
        return Response.status(200).entity(PlayDao.instance.getPlayer(playerID)).build();
    }

    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postPlayer(
            @FormParam("pname") String pname,
            @FormParam("goals") String goal,
            @Context HttpServletResponse servletResponse) throws IOException {
        
        System.out.println(pname + " ," + goal);
        int goals = Integer.parseInt(goal);
        PlayAccount player = new PlayAccount();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        int id = PlayDao.instance.getNextId();
        player.setId(id);
        player.setPname(pname);
        player.setGoals(goals);
        player.setTimestamp(timestamp);
        PlayDao.instance.create(player);
        
        return Response.status(200).build();
    }
    
    
    @DELETE
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deleteAllPlayers(
            @Context HttpServletResponse servletResponse) throws IOException {

        PlayDao.instance.deleteAll();
        return Response.status(200).entity("Players have all been Deleted.").build();
    }
    

    @HEAD
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response doHead() {
        return Response
                .noContent()
                .type(MediaType.APPLICATION_XML)
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    
    
    @OPTIONS
    public Response doOptions() {
        Set<String> api = new TreeSet<>();
        api.add("GET");
        api.add("POST");
        api.add("DELETE");
        api.add("HEAD");

        return Response
                .noContent()
                .allow(api)
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("put")
    public Response putPlayer(
            @FormParam("eid") String id,
            @FormParam("epname") String pname,
            @FormParam("egoals") String goal,
            @Context HttpServletResponse servletResponse) throws IOException {
        
        int goals = Integer.parseInt(goal);
        PlayAccount check = PlayDao.instance.getPlayer(id);
        
        if(check != null){
            PlayAccount player = new PlayAccount();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            player.setId(Integer.parseInt(id));
            player.setPname(pname);
            player.setGoals(goals);
            player.setTimestamp(timestamp);
            PlayDao.instance.update(player);
            return Response.status(200).build();
        }
        
        return Response.status(304).build();
    }

    
    @DELETE
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{playerID}")
    public Response deletePlayer(@PathParam("playerID") String id,
            @Context HttpServletResponse servletResponse) throws IOException {

        PlayDao.instance.delete(Integer.parseInt(id));
        return Response.status(200).entity("Selected Player has been Deleted.").build();
    }
    
}
