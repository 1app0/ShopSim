package server;

import server.dataaccess.InMemoryUser;
import server.dataaccess.ShopListAccess;
import server.dataaccess.ShopListHome;
import server.dataaccess.UserHome;
import server.model.Model;
import server.model.ModelManager;
import server.network.ShopServer;

import java.io.IOException;

public class RunShopServer {
  public static void main(String[] args) {
    UserHome userHome = new InMemoryUser();
    ShopListHome shopListHome = new ShopListAccess();
    Model model = new ModelManager(userHome, shopListHome);
    ShopServer server = new ShopServer(model);
    try {
      server.startServer();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
