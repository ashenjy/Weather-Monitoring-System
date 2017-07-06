/**
 *
 * @author Ashen Jayasinghe
 */

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.*;
import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server extends UnicastRemoteObject implements ServerClientInterface{

    public static String data;
    
   
      
    public Server() throws RemoteException 
    {
        
        System.out.println("Weather server is running");
    }
    
    public static void main(String[] args) throws RemoteException 
    {
            
        try
            
        {     
         
        //RMI-------------------------------------------------------------------
              Server s = new Server(); 
              
              Registry reg = LocateRegistry.createRegistry(1099); //Creating Registry.
              
              Client c = new Client(); 
              
              reg.bind("WeatherData", s);  //Registering Server in the RMI Registry.
              
              reg.rebind("login",c); //Registering client in the RMi Registry.
              
              System.out.println("Server is Ready"); 
        
        //SOCKET----------------------------------------------------------------
        
              //create socket
              ServerSocket createSocket = new ServerSocket(1478);
        
              //accept the socket
              Socket server = createSocket.accept();
              
              //Creating input and ouput character streams.
              InputStreamReader input = new InputStreamReader(server.getInputStream());
              BufferedReader bufferRead = new BufferedReader(input);
        
              OutputStream output = server.getOutputStream();
              DataOutputStream out= new DataOutputStream(output);
        
        //Reading the data received from the sensor class----------------------      
              data = bufferRead.readLine(); 
            
               String[] sep = data.split("--");
              
              System.out.println(sep[1]  + "\n" + sep[2] + "\n" + sep[3] + "\n" + sep[4] + "\nSensors:" + sep[5]); 
              //printing the data to the server's console.
          
              
        //closing all connections----------------------------------------------
              input.close();
              bufferRead.close();
              out.close();
        
              server.close();
          
        }
        
        catch(IOException e) 
        {
              System.out.println(e);
        }
        catch (AlreadyBoundException  e) 
        {
              System.out.println("Name is already bound: " + e); 
              System.exit(0); 
        } 
       
    }


    public String sendData() throws RemoteException 
    {
        
        return data; //returning the data sent from the Sensor's OutputStreamreader and collected 
                     //by the server's InputStreamReader.
    }

   
    
}