package ie.ait.dao.student;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="student")
@XmlType(propOrder = {"id", "name", "address", "course"})
public class Student {
    private int id;
    private String name;
    private String address;
    private String course;
   // private static int nextId = 0;
    
    public Student (int id, String name, String address, String course) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.course = course;
    }
    
    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", address=" + address + ", course=" + course + '}';
    }
    
    
}