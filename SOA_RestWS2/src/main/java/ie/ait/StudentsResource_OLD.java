package ie.ait;

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


@Path("students")
public class StudentsResource_OLD {
   
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Student> getStudents() {
        return StudentDao_OLD.instance.getStudents();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{studentId}")
    public Student getStudent(@PathParam("studentId") String id) {
        return StudentDao_OLD.instance.getStudent(Integer.parseInt(id));
    }
    
    @POST
    @Produces({MediaType.TEXT_HTML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void postStudent(@FormParam("id") int id,
            @FormParam("name") String name,
            @FormParam("address") String address,
            @FormParam("course") String course,
            @Context HttpServletResponse servletResponse) throws IOException {
                   Student student = new Student();
                   student.setId(StudentDao_OLD.instance.getNextId());
                   student.setCourse(course);
                   student.setName(name);
                   student.setAddress(address);
                   StudentDao_OLD.instance.create(student);
                   servletResponse.sendRedirect("../../index.html");
            }
    
    @PUT
    @Produces({MediaType.TEXT_HTML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{studentId}")
    public void putStudent(
            @PathParam("studentId") int id,
            //@FormParam("id") int id,
            @FormParam("name") String name,
            @FormParam("address") String address,
            @FormParam("course") String course,
            @Context HttpServletResponse servletResponse) throws IOException {
                System.out.println("Doing PUT");
                Student student = new Student();
                student.setId(id);
                student.setCourse(course);
                student.setName(name);
                student.setAddress(address);
                System.out.println("Putting student" + student.toString());
                StudentDao_OLD.instance.create(student);

                servletResponse.sendRedirect("../../index.html");
            }
    
    @DELETE
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{studentId}")
    public Response deleteStudent(@PathParam("studentId") String id) {
        
        StudentDao_OLD.instance.delete(Integer.parseInt(id));
        return Response.status(200).entity("Operation Successful").build();
    }
    
    
    @DELETE
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteStudents() {     
        StudentDao_OLD.instance.deleteStudents();
        return Response.status(200).entity("Operation Successful").build();
    }
    
    @HEAD
    public Response doHead(){
        return Response
                .noContent()
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
    
}