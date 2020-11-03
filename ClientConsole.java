// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import client.*;
import common.*;

/**
 * @author Benedek Balazs (blasio99)
 * @version Oct 2020
 */
public class ClientConsole implements ChatIF 
{
  final public static int DEFAULT_PORT = 5555;
  ChatClient client;


  public ClientConsole(String loginID, String host, int port) 
  {
    try 
    {
      client= new ChatClient(loginID, host, port, this);
    } 
    catch(IOException exception) 
    {
      System.out.println("Error: Can't setup connection!"
                + " Terminating client.");
      System.exit(1);
    }
  }

  
  public void accept() 
  {
    try
    {
      BufferedReader fromConsole = 
        new BufferedReader(new InputStreamReader(System.in));
      String message;

      while (true) 
      {
        message = fromConsole.readLine();
        client.handleMessageFromClientUI(message);
      }
    } 
    catch (Exception ex) 
    {
      System.out.println
        ("Unexpected error while reading from console!");
    }
  }

  public void display(String message) 
  {
    System.out.println(message);
  }


  public static void main(String[] args) 
  {
    String host = "";
    int port = 0;  //The port number
    String loginID = "";
    try
    {
      loginID = args[0];
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
      System.out.println("usage: java ClientConsole loginID [host [port]]");
      System.exit(1);
    }
    try
    {
      host = args[1];
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
      host = "localhost";
    }
    try {
      port = Integer.parseInt(args[2]);
    } catch (ArrayIndexOutOfBoundsException e){
      port = DEFAULT_PORT;
    }
    ClientConsole chat= new ClientConsole(loginID, host, port);
    chat.accept();  //Wait for console data
  }
}
//End of ConsoleChat class
