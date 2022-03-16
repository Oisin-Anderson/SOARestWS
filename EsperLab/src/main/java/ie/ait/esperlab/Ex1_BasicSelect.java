package ie.ait.esperlab;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class Ex1_BasicSelect {
    
    public static void main(String args[]){
       
        // Get default engine instance
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();
        
        // Register an event - tells the engine to listen for PersonEvents
        engine.getEPAdministrator().getConfiguration().addEventType(PersonEvent.class);
        
        // Create an Event Processing Language (EPL) statement
        // When a PersonEvent arrives, get the name and age..
        String epl = "select name, age from PersonEvent";
        EPStatement statement = engine.getEPAdministrator().createEPL(epl);
        
        // Callback to invoke when a PersonEvent arrives
        // This happens after the statement above is ran 
        statement.addListener( (newData, oldData) -> {
            String name = (String) newData[0].get("name");
            int age = (int) newData[0].get("age");
            System.out.println(String.format("Name: %s, Age: %d", name, age));
        });
        
        // Send an event into the engine
        engine.getEPRuntime().sendEvent(new PersonEvent("Peter", 10));	
    }
}
