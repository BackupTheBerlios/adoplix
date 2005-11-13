/*
 * TaskAdapterToPort.java
 *
 */

package de.adoplix.adapter;
import de.adoplix.internal.tasks.Task;
import de.adoplix.internal.telegram.Acknowledge;
import de.adoplix.internal.telegram.XMLContainer;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author dirk
 */
public class TaskAdapterToClass extends TaskAdapter {
    
    /** Creates a new instance of TaskAdapterToPort */
    public TaskAdapterToClass () {
    }
    
    public TaskAdapterToClass (Task task, XMLContainer xmlContainer) {
        super (task, xmlContainer);
    }
    
    public Acknowledge sendAdoplixMsg () {
        // in taskid there is the ip and the port for connection...
        String className = _task.getLocalAdapterClass ();
        try {
            Class AdapterClass = Class.forName( className );
            Class[] parameterType = new Class[]{ Integer.TYPE, Integer.TYPE };
            java.lang.reflect.Method method = AdapterClass.getMethod( "method", parameterType );
            Object[] argument = new Object[]{ new Integer( 5 ), new Integer( 9 )};
            Object instance = null; 
            Integer result =  ( Integer )method.invoke( instance, argument );
            System.out.print( result.intValue() );
        } catch( Exception e ){}
        
        return null;
    }
}

//class Example {
//    public static int method( int x, int y ){
//        return x + y;
//    }
//    
//    public static double method( double x, double y ) {
//        return x + y;
//    }
//}
