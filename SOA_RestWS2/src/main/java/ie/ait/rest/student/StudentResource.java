package ie.ait.rest.student;

import ie.ait.dao.student.Student;
import ie.ait.dao.student.StudentDao;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("students")
public class StudentResource {
   
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Student> getStudents() {
        return StudentDao.instance.getStudents();
    }
    
}