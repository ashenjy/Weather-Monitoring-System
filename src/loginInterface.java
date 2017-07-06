/**
 *
 * @author Ashen Jayasinghe
 */

import java.rmi.*;

public interface loginInterface extends Remote 
        
{
    public boolean getLogin(String user, String pass ) throws RemoteException;
   
}
