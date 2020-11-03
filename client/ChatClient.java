// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;
import java.io.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Benedek Balazs (blasio99)
 * @version Oct 2020
 */
public class ChatClient extends AbstractClient
{
  ChatIF clientUI; 
  String loginID;

  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
    this.loginID = "ANONYMOUS";
    sendToServer("#login ANONYMOUS");
  }

  
  public ChatClient(String loginID, String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    this.loginID = loginID;
    openConnection();
    sendToServer("#login " + loginID);
  }

  
  public void handleMessageFromServer(Object msg) 
  {
    clientUI.display(msg.toString());
  }

  public void handleMessageFromClientUI(String message)
  {
    // detect commands
    if (message.charAt(0) == '#')
    {
      runCommand(message);
    }
    else
    {
      try
      {
        sendToServer(message);
      }
      catch(IOException e)
      {
        clientUI.display ("[ERROR] Could not send message to server. Terminating client.");
        quit();
      }
    }
  }

  private void runCommand(String message)
  {
    // a bunch of ifs

    if (message.equalsIgnoreCase("#quit"))
    {
      quit();
    }
    else if (message.equalsIgnoreCase("#logoff"))
    {
      try
      {
        closeConnection();
      }
      catch(IOException e) {}

      clientUI.display("You have logged off.");
    }
    else if (message.toLowerCase().startsWith("#setport"))
    {
      try 
      {
        int newPort = Integer.parseInt(message.substring(9));
        setPort(newPort);
        
        clientUI.display("Port changed to " + getPort());
      }
      catch (Exception e)
      {
        System.out.println("[ERROR] Unexpected error while setting client port!");
      }
    }
    else if (message.toLowerCase().startsWith("#sethost"))
    {
      setHost(message.substring(9));
      clientUI.display ("Host changed to " + getHost());
    }
    else if (message.toLowerCase().startsWith("#login"))
    {
      if (isConnected())
      {
        clientUI.display("You must logout before you can login.");
        return;
      }
      // login
      // if no id, login anonymous
      loginID = message.substring(7);
      try
      {
        openConnection();
        sendToServer("#login " + loginID);
      }
      catch (Exception e)
      {
        clientUI.display("Connection could not be established.");
      }
    }
    else if (message.equalsIgnoreCase("#gethost"))
    {
      clientUI.display("Current host: " + getHost());
    }
    else if (message.equalsIgnoreCase("#getport"))
    {
      clientUI.display("Current port: " + Integer.toString(getPort()));
    }
  }
  
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }

  protected void connectionException(Exception exception)
  {
    clientUI.display("The connection to the Server (" + getHost() + ", " + getPort() + ") has been disconnected");
  }
}
