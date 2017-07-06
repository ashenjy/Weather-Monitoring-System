/**
 *
 * @author Ashen Jayasinghe
 */

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming; 
import java.rmi.RemoteException;


public class Client extends UnicastRemoteObject implements loginInterface
{
    
     public Client() throws RemoteException
            {

            }
      
      public static void main(String[] args)
    {    
     
         try 
         {
            String registration = "//127.0.0.1/WeatherData";
            
            //calls the lookup method to look for the object by the name "WeatherDara" in the server's host registry.
	    Remote remoteService = Naming.lookup(registration);
              
	    ServerClientInterface sci = (ServerClientInterface) remoteService;
                       
	    String reading = sci.sendData();
         
            //Client Authentication using a login form.            
            loginGUI a = new loginGUI();
                       
            a.setVisible(true);
                        
                        
                       
                        
         }
         catch(Exception e)
         {
             System.out.println(e);
         }
          
         
         
         
    }

   
    public boolean getLogin(String user, String pass) throws RemoteException 
    {
         boolean log = false;
         
        try 
        {
            if(user.equals("admin") && pass.equals("123"))
            {
                return log = true;
            }
            else
            {
                return log =  false;
            }
        }
        catch (Exception e) 
        {
            System.out.println(e);
        }
        return log;
    }

   

}
    

