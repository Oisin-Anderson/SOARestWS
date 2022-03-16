package ie.ait;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author a00266219
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

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
        resources.add(ie.ait.ItemsResource.class);
        resources.add(ie.ait.StudentResource.class);
        resources.add(ie.ait.StudentsResource_OLD.class);
    }
    
}