package server.network;

import server.model.Model;
import shared.EventType;
import shared.dataTransfer.*;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class ServerSocketHandler implements Runnable {
  private Model model;
  private Socket socket;
  private ObjectInputStream in;
  private ObjectOutputStream out;

  public ServerSocketHandler(Model model, Socket socket)
      throws IOException, IOException {
    this.model = model;
    this.socket = socket;
    out = new ObjectOutputStream(socket.getOutputStream());
    in = new ObjectInputStream(socket.getInputStream());

    model.addPropertyChangeListener(EventType.BROADCAST_REMOVE.toString(), this::sendBroadcastRemove);
  }

  @Override public void run() {
    while (true) {
      try {
        Request request = (Request) in.readObject();
        System.out.println("server "+request);
        switch (request.type) {
          case LOGIN_REQUEST:
            handleRegister(request);
            break;
          case REGISTER_REQUEST:
            handleLogin(request);
            break;
          case LIST_REQUEST:
            handleList();
            break;
          case CHECKOUT_REQUEST:
            handleCheckout(request);
            break;
          case BROADCAST_REMOVE:
            handleBroadcastRemove(request);
            break;
          case ADD_ITEM_REQUEST:
            handleAddItem(request);
            break;
          case REMOVE_ITEM_REQUEST:
            handleRemoveItem(request);
            break;
        }
      }
      catch (IOException e) {
        System.out.println("Client error");
        close();
        break;
      }
      catch (SQLException | ClassNotFoundException e)
      {
        e.printStackTrace();
      }
    }
  }

  private void handleRemoveItem(Request request) throws SQLException
  {
    ShopItem item = (ShopItem) request.object;
    System.out.println("Remove item request");
    model.removeItem(item);
  }

  private void handleAddItem(Request request) throws SQLException
  {
    ShopItem item = (ShopItem) request.object;
    System.out.println("Add item request");
    model.addItem(item);
  }

  private void handleBroadcastRemove(Request request) throws IOException {
    ShopItem item = (ShopItem) request.object;
    System.out.println("Broadcast remove item: " + item);
    model.broadcastRemove(item);
  }

  private void handleCheckout(Request request) throws IOException, SQLException
  {
    System.out.println("Checkout request");
    ShopItemList checkoutList = (ShopItemList) request.object;
    Response response = model.checkout(checkoutList);
    out.writeObject(response);
  }

  private void handleList() throws IOException, SQLException
  {
    System.out.println("List request");
    Response response = model.getList();
    out.writeObject(response);
  }

  private void handleRegister(Request request) throws SQLException, IOException {
    System.out.println("Login requested");
    User user = (User) request.object;
    Response response = model.validateUser(user);
    out.writeObject(response);
  }

  private void handleLogin(Request request) throws IOException, SQLException
  {
    System.out.println("Register requested");
    User user = (User) request.object;
    Response response = model.registerUser(user);
    out.writeObject(response);
  }

  private void sendBroadcastRemove(PropertyChangeEvent evt) {
    ShopItem item = (ShopItem) evt.getNewValue();
    Response response = new Response(EventType.BROADCAST_REMOVE, item);
    try {
      out.writeObject(response);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void close() {
    try {
      in.close();
      out.close();
      socket.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
