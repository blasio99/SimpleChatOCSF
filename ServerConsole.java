// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import common.*;

/**
 * @author Benedek Balazs (blasio99)
 * @version Oct 2020
 */
public class ServerConsole implements ChatIF 
{
    final public static int DEFAULT_PORT = 5555;
    EchoServer server;

    public ServerConsole(int port) 
    {
        try 
        {
            server = new EchoServer(port, this);
        }catch(IOException exception) 
        {
            System.out.println("[ERROR] Can't setup connection! Terminating client.");
            System.exit(1);
        }
        
        try
        {
            server.listen();
        }
        catch (Exception ex)
        {
            System.out.println("[ERROR] Could not listen for clients!");
        }
    }

  
    public void accept() 
    {
        try
        {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            while (true) 
            {
                String message = console.readLine();
                server.handleMessageFromServerUI(message);
            }
        }catch (Exception ex) 
        {
            System.out.println ("[ERROR] Unexpected error while reading from console!");
        }
    }
    public void display(String message) 
    {
        System.out.println("> " + message);
    }

    public static void main(String[] args) 
    {
        int port = 0;

        try
        {
            port = Integer.parseInt(args[0]);
        }
        catch(Throwable e)
        {
            port = DEFAULT_PORT;
        }
        ServerConsole server = new ServerConsole(port);
        server.accept();
    }
}
