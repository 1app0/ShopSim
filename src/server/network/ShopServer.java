package server.network;

import server.model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ShopServer {
  private Model model;

  public ShopServer(Model model) {
    this.model = model;
  }

  public void startServer() throws IOException {
    System.out.println("Server started");
    ServerSocket welcomeSocket = new ServerSocket(1122);

    while (true) {
      try {
        System.out.println("Waiting for client");
        Socket socket = welcomeSocket.accept();
        Thread thread = new Thread(new ServerSocketHandler(model, socket));
        thread.start();
        System.out.println("Client connected");
      }
      catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
