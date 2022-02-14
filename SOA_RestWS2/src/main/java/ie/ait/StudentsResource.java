package ie.ait;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("students")
public class StudentsResource {
   
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Student> getStudents() {
        return StudentDao.instance.getStudents();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{studentId}")
    public Student getStudent(@PathParam("studentId") String id) {
        return StudentDao.instance.getStudent(Integer.parseInt(id));
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
                   student.setId(id);
                   student.setCourse(course);
                   student.setName(name);
                   student.setAddress(address);
                   StudentDao.instance.create(student);
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
                StudentDao.instance.create(student);

                servletResponse.sendRedirect("../../index.html");
            }
    
    @DELETE
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{studentId}")
    public Response deleteStudent(@PathParam("studentId") String id) {
        
        StudentDao.instance.delete(Integer.parseInt(id));
        return Response.status(200).entity("Operation Successful").build();
    }
    
    
    @DELETE
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteStudents() {     
        StudentDao.instance.deleteStudents();
        return Response.status(200).entity("Operation Successful").build();
    }
    
}