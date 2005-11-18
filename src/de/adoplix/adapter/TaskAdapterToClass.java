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
    
    
    public TaskAdapterToClass (Task task, Socket clientSocket, XMLContainer xmlContainer) {
        super (task, clientSocket, xmlContainer);
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


//    Object obj = instantiateClassForName( args[0] );
//    if( obj instanceof Runnable )
//      ((Runnable)obj).run();
//    else
//      System.out.println( obj );
//  }
//
//  public static Object instantiateClassForName( String className ) throws Exception
//  {
//    Class c = Class.forName( className );
//    if( null == c )
//      throw new Exception( "Error: Unable to access class '" + className + "'." );
//    Object o = c.newInstance();
//    if( null == o )
//      throw new Exception( "Error: Unable to create instance of class '" + className + "'." );
//    return o;
//  }
//

//class Example {
//    public static int method( int x, int y ){
//        return x + y;
//    }
//    
//    public static double method( double x, double y ) {
//        return x + y;
//    }
//}
