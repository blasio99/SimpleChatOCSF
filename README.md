<p align="center">
  <a href="https://github.com/blasio99">
    <img src="images/v3.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Simple Chat with OCSF</h3>

  <p align="center">
    Simple chat program using the ocsf framework
    <br />
    <a href="https://github.com/blasio99/SimpleChatOCSF"><strong>Explore the project</strong></a>
    <br />
    <br />
    <a href="https://github.com/blasio99/SimpleChatOCSF/issues">Report Bug</a>
    ·
    <a href="https://github.com/blasio99/SimpleChatOCSF/issues">Request Feature</a>
  </p>
</p>

## Table of Contents

* [About the Project](#about-the-project)
* [Using OCSF](#using-ocsf)
* [Getting Started](#getting-started)
  * [Problem 1](#problem1)
  * [Problem 2](#problem2)
* [To Run](#to-run)
* [Contributing](#contributing)
* [Contact](#contact)
* [Requirements](#requirements)

## About The Project
Simple chat program using the ocsf framework to work with OpDemand.  
OCSF is a simple framework that provides TCP-based client-server connections.  
Without the framework, we would need several labs just to implement network communication.  
A framework reduces development time and usually improves software quality. Most frameworks are extensively tested both by developers and users, so they have fewer bugs and a better architecture than software written from scratch.  
  

## Using OCSF
The Object Client-Server Framework (OCSF) provides client-server communications using TCP/IP.  
The OCSF framework is described in a textbook by Lethbridge (chapter on OCSF is on class web).  

### Built With
* [OCSF](http://www.site.uottawa.ca/school/research/lloseng/supportMaterial/ocsf/ocsf.html)  


### Getting Started

## Problem 1: Write a Client that connects to server and sends Strings
You can write a console-based client or GUI client  
Create a client as a subclass of AbstractClient or ObservableClient. that does this:  
1. Connect to the server and display a "connected" message.  
2. Show any messages from the server. Print on console or display in a GUI field.  
3. Accept input from the user and send it to the server.  
4. Close the connection when you want to quit. If you write a GUI, there should be a "Disconnect" button. On the console, if the user enters "quit" then disconnect.  

## Problem 2: Write a Chat Server for 1-to-1 Chat
Write your own server using OCSF's AbstractServer class.  
You should create a server that requires clients to identify themselves, so you know which user is connected on which ClientConnection object.  
1. Write a class that extends AbstractServer or ObservableServer (both classes have the same methods).  
2. When a new client connects, you should wait for the client to (somehow) identify the user. Design your own solution to this. The ClientConnection object has a map that you can use to store arbitrary values. You can use this to store the user's name. For example: client.setInfo("username", clientname );  
3. When a user logs in, servers send a message to all clients telling them "Clientname connected" (you can design the format of this message. It doesn't have to be a String.)  
4. When a logged-in client sends a one-to-one message like this:  
        To: Anchan  
        Hi, Anchan. How are you?  
your server should find a client connection with login name "Anchan" and send the message. Be sure to tell Anchan who the message is from!  
5. If a client sends the String message "Logout" then close the client connection and tell all other clients "Anchan logged off".  
6. If the client sends any other message, the server responds that message is not recognized.  


## To Run
------
From command line  
`cd` into directory.  
Compile with `javac ServerConsole.java`    
Then run with `java ServerConsole`  
  
In another terminal   
Compile with `javac ClientConsole.java`    
Then run with `java ClientConsole loginID [host [port]]` where loginID, host and port should be replaced (for example: java ClientConsole client1 localhost 5555)  

## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.  

1. Fork the Project  
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)  
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)  
4. Push to the Branch (`git push origin feature/AmazingFeature`)  
5. Open a Pull Request  

## Contact

Benedek Balazs - [LinkedIn profile - ME](https://www.linkedin.com/in/balazs-benedek-009322183/) - benedekbalazs1999@gmail.com

Project Link: [GitHub - blasio99 - SimpleChatOCSF](https://github.com/blasio99/SimpleChatOCSF)


## References

Lethbridge and Lagariere, Object-Oriented Software Engineering, 2E. Textbook describes use of OCSF and a chat project.  
A standard, high-performance framework for chat and other applications is XMPP.  
XMPP is a standard protocol for real-time messaging; XMPP was originally called Jabber. Google Talk uses XMPP. You can use XMPP to write your own Chat client or other Internet application. There are many several free XMPP servers (such as Jabberd and OpenFire), clients, and libraries. XMPP can be used for more than just chat. SMACK is an open-source XMPP library for Java. It is used by several chat applications. http://www.igniterealtime.org/projects/smack/  
* How to use SMACK to write a Java client: http://www.javacodegeeks.com/2010/09/xmpp-imwith-smack-for-java.html  
* Other two articles in the same series describe infrastructure for using XMPP.  
XEP-0045 Multi-User Chat. Protocol for a multi-user chat using XMPP. http://xmpp.org/extensions/xep0045.html#bizrules-message  