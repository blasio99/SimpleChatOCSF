// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import ocsf.server.*;
import common.*;

/**
 * @author Benedek Balazs (blasio99)
 * @version Oct 2020
 */
public class EchoServer extends AbstractServer {
  final public static int DEFAULT_PORT = 5555;
  ChatIF serverUI;

  public EchoServer(int port) {
    super(port);
  }

  public EchoServer(int port, ChatIF serverUI) throws IOException {
    super(port);
    this.serverUI = serverUI;
  }

  public void handleMessageFromClient(Object message, ConnectionToClient client) 
  {
    if (message.toString().startsWith("#login ")) 
    {
      if (client.getInfo("loginID") != null) 
      {
        try 
        {
          client.sendToClient("You are already logged in.");
        } 
        catch (IOException e) {}
        return;
      }
      client.setInfo("loginID", message.toString().substring(7));
    } 
    else 
    {
      if (client.getInfo("loginID") == null) 
      {
        try 
        {
          client.sendToClient("You need to login before you can chat.");
          client.close();
        } 
        catch (IOException e) {}
        return;
      }
      System.out.println("Message received: " + message + " from \"" + client.getInfo("loginID") + "\" " + client);
      this.sendToAllClients(client.getInfo("loginID") + "> " + message);
    }
  }

  public void handleMessageFromServerUI(String message) {
    if (message.charAt(0) == '#') 
    {
      runCommand(message);
    } 
    else 
    {
      serverUI.display(message);
      this.sendToAllClients("SERVER> " + message);
    }
  }

  private void runCommand(String message) {

    if (message.equalsIgnoreCase("#quit"))       {quit();} 
    else if (message.equalsIgnoreCase("#stop"))  {stopListening();} 
    else if (message.equalsIgnoreCase("#close")) {try {close();} catch (IOException e) {}} 
    else if (message.toLowerCase().startsWith("#setport")) {
      if (getNumberOfClients() == 0 && !isListening()) {

        int newPort = Integer.parseInt(message.substring(9));
        setPort(newPort);
        serverUI.display("Server port changed to " + getPort());
      } 
      else 
      {
        serverUI.display("The server is not closed. Port cannot be changed.");
      }
    } 
    else if (message.equalsIgnoreCase("#start")) {
      if (!isListening()) 
      {
        try
        {
          listen();
        } 
        catch (Exception ex) {
          serverUI.display("Error - Could not listen for clients!");
        }
      } 
      else {
        serverUI.display("The server is already listening for clients.");
      }
    } 
    else if (message.equalsIgnoreCase("#getport")) {
      serverUI.display("Currently port: " + Integer.toString(getPort()));
    }
  }

  protected void serverStarted() {
    System.out.println("Server listening for connections on port " + getPort());
  }

  protected void serverStopped() {
    System.out.println("Server has stopped listening for connections.");
  }

  protected void clientConnected(ConnectionToClient client) {
    // display on server and clients that the client has connected.
    String message = "A Client has connected";
    System.out.println(message);
    this.sendToAllClients(message);
  }

  synchronized protected void clientDisconnected(ConnectionToClient client) {
    // display on server and clients when a user disconnects
    String message = client.getInfo("loginID").toString() + " has disconnected";

    System.out.println(message);
    this.sendToAllClients(message);
  }

  synchronized protected void clientException(ConnectionToClient client, Throwable exception) {
    String message = client.getInfo("loginID").toString() + " has disconnected";

    System.out.println(message);
    this.sendToAllClients(message);
  }

  public void quit() {
    try {
      close();
    } catch (IOException e) {
    }
    System.exit(0);
  }

  public static void main(String[] args) {
    int port = 0; // Port to listen on

    try {
      port = Integer.parseInt(args[0]); // Get port from command line
    } catch (Throwable t) {
      port = DEFAULT_PORT; // Set port to 5555
    }

    EchoServer sv = new EchoServer(port);

    try {
      sv.listen(); // Start listening for connections
    } catch (Exception ex) {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}
// End of EchoServer class
