package ie.ait.soacondget;

import java.util.Set;
import javax.ws.rs.core.Application;


@javax.ws.rs.ApplicationPath("webresources")
public class ApplictaionConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ie.ait.soacondget.ItemsResource.class);
        resources.add(ie.ait.soacondget.PlayResource.class);
        //resources.add(ie.ait.rest.student.StudentResource.class);
        //resources.add(ie.ait.rest.student.StudentsResource_OLD.class);
    }
    
}