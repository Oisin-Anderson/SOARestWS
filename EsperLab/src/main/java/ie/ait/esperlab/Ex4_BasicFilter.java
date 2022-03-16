package ie.ait.esperlab;

import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class Ex4_BasicFilter {
    
    private static void generateEvent(EPRuntime epRuntime) {
        String names[] = {"Joe", "Bill", "Cathal", "Jim", "Chris", "Oisin", "Brian"};
        int min1 = 0;
        int max1 = 6;
        int number = (int)(Math.random()*(max1-min1+1)+min1);
        String name = names[number];
        
        int min2 = 1;
        int max2 = 100;
        int age = (int)(Math.random()*(max2-min2+1)+min2);
        
        PersonEvent evt = new PersonEvent(name, age);
        System.out.println("Sending event: " + evt.toString());
        epRuntime.sendEvent(evt);
    }
    
    public static void main(String args[]){
       
        // Get default engine instance
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();
        
        // Register an event - tells the engine to listen for PersonEvents
        engine.getEPAdministrator().getConfiguration().addEventType(PersonEvent.class);
        
        // Create an Event Processing Language (EPL) statement
        // When a PersonEvent arrives, get the name and age..
        String epl = "select name, age, count(age) as Ages from PersonEvent(age > 50)";
        EPStatement statement = engine.getEPAdministrator().createEPL(epl);
        
        // Callback to invoke when a PersonEvent arrives
        // This happens after the statement above is ran 
        statement.addListener( (newData, oldData) -> {
            String name = (String) newData[0].get("name");
            int age = (int) newData[0].get("age");
            long ages = (long) newData[0].get("Ages");
            System.out.println(String.format("name: %s, age: %d, Ages: %d", name, age, ages));
        });
        
        for(int i=0; i<20; i++){
            generateEvent(engine.getEPRuntime());
        }	
    }

}
