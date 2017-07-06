/**
 *
 * @author Ashen Jayasinghe
 */

import java.rmi.*;

public interface ServerClientInterface extends Remote
        
{
     public String sendData() throws RemoteException;
      
} 
