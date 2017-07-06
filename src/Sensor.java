/**
 *
 * @author Ashen Jayasinghe
 */

import java.net.*;
import java.io.*;
import java.util.Random;


public class Sensor extends Thread {
    
     public static int counter;

     public static void main(String[] args) 
     { 
         System.out.println("Sensor is sending data to the server");
         
         Sensor s = new Sensor();
         
         s.start(); //invoking run() from Thread.
           
         
      
     }
     
      public void run()
              
      {   
          
        try
            
        {
            
              //create socket
              Socket sensor = new Socket("127.0.0.1",1478);
        
              //creating character streams
              InputStreamReader in = new InputStreamReader(sensor.getInputStream());
              BufferedReader br = new BufferedReader(in);
        
              OutputStream ou = sensor.getOutputStream();
              DataOutputStream out= new DataOutputStream(ou);
        
              //generate a random number
              Random randm = new Random(); 
              
              
             
              double temp = randm.nextInt(50 - 20) + 20;//random number between 100 - 10 for temperature
              double hum =  randm.nextInt(100 - 10) + 10;//random number between 100 - 10 for humidity
              double pr = randm.nextInt(20 - 10) + 10;   //random number betweeb 20 - 10 for air pressure
              double rain = randm.nextInt(30 - 15) + 5;    //random number betweeb 20 - 10 for rainfall
              
           // --------------------------------------------------------------------------------------------  
              
              if  ( rain > 20) // if rainfall is greater tham 20 mm sending alerts to base stations.
              {
                  out.writeBytes("Rainfall ALERT!!! : " + rain +"C.");
                 
              }
              
              else 
              {
                  
                  out.writeBytes("--Rainfall : " + rain + "mm.");
              }
              
              if(temp > 35) // if temperature is greater tham 35C sending alerts to base stations.
                  
              {
                  out.writeBytes("--Temperature ALERT!!! : " + temp + "C.");
              }
              
              else
                  
              {
                  out.writeBytes("--Temperature : " + temp + "C.");
              }
              
            // -------------------------------------------------------------------------------------------- 
               
              out.writeBytes("--Air pressure :" + pr + "psi.");
              
              out.writeBytes("--Humidity : " + hum + "%.");
              
              counter = 4;
              
              out.writeBytes("-- " + counter);
               
             
             
              
              
              //closing created socket connection ,input and Outputs.
              in.close(); 
              br.close();
              out.close();
              sensor.close();
              
              Thread.currentThread();
              Thread.sleep(1000);
              
            
              
        }
        catch(Exception e) 
        {
            System.out.println(e);
        }
   
 
    }
     
}

