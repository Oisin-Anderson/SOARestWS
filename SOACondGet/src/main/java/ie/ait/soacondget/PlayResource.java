package ie.ait.soacondget;

import java.io.IOException;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


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

    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void postBook(
            @FormParam("pname") String pname,
            @FormParam("goals") int goals,
            @FormParam("timestamp") String timestamp,
            @Context HttpServletResponse servletResponse) throws IOException {

        PlayAccount player = new PlayAccount();

        int id = PlayDao.instance.getNextId();
        player.setId(id);
        player.setPname(pname);
        player.setGoals(goals);
        player.setTimestamp(timestamp);
        PlayDao.instance.create(player);
        servletResponse.sendRedirect("../index.html");
    }
    
    
    @DELETE
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deleteAllStudents(
            @Context HttpServletResponse servletResponse) throws IOException {

        PlayDao.instance.deleteAll();
        return Response.status(200).entity("Operation Successful").build();
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

    
    @PUT
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{playerID}")
    public void putBook(@PathParam("playerID") String id,
            @FormParam("pname") String pname,
            @FormParam("goals") int goals,
            @FormParam("timestamp") String timestamp,
            @Context HttpServletResponse servletResponse) throws IOException {

        PlayAccount player = new PlayAccount();
        player.setId(Integer.parseInt(id));
        player.setPname(pname);
        player.setGoals(goals);
        player.setTimestamp(timestamp);
        PlayDao.instance.create(player);
        servletResponse.sendRedirect("../index.html");
    }

    
    @DELETE
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{playerID}")
    public Response deleteBook(@PathParam("playerID") String id,
            @Context HttpServletResponse servletResponse) throws IOException {

        PlayDao.instance.delete(Integer.parseInt(id));
        return Response.status(200).entity("Operation Successful").build();
    }
    
}
