/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.ait;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public enum StudentDao{
    
    instance;
    
    private Map<Integer, Student> studentsMap = new HashMap<Integer, Student>();
    
    private StudentDao() {
        Student student = new Student();
        student.setId(1);
        student.setCourse("Software");
        student.setName("Joe Bloggs");
        student.setAddress("Location");
        studentsMap.put(1, student);
        
        Student student1 = new Student();
        student1.setId(1);
        student1.setCourse("Software");
        student1.setName("Joe Bloggs");
        student1.setAddress("Location");
        studentsMap.put(2, student1);
    }
    
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<Student>();
        students.addAll(studentsMap.values());
        return students;
    }
    
    public Student getStudent(int id) {
        return studentsMap.get(id);
    }
    
    public void create(Student student) {
        studentsMap.put(student.getId(), student);
    }
    
    public void delete(int id) {
        if (studentsMap.remove(id) != null) {
            System.out.print("Removed");
        }
        else {
            System.out.print("Not removed");
        }
    }
            
}