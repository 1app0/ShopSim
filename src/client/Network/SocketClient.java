package client.Network;

import shared.EventType;
import shared.dataTransfer.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient implements Client {
  private PropertyChangeSupport propertyChangeSupport;
  private ObjectOutputStream out;
  private ObjectInputStream in;

  public SocketClient() {
    propertyChangeSupport = new PropertyChangeSupport(this);
    connect();
  }

  public void connect() {
    try {
      Socket socket = new Socket("localhost", 1122);
      out = new ObjectOutputStream(socket.getOutputStream());
      in = new ObjectInputStream(socket.getInputStream());
      Thread thread = new Thread(new ClientSocketHandler(this, in));
      thread.setDaemon(true);
      thread.start();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void login(User user) {
    System.out.println(user.toString()+"client login!!!");
    Request request = new Request(EventType.LOGIN_REQUEST, user);
    sendToServer(request);
  }

  @Override public void registerUser(User user) {
    Request request = new Request(EventType.REGISTER_REQUEST, user);
    sendToServer(request);
  }

  @Override public void getItems() {
    Request request = new Request(EventType.LIST_REQUEST, null);
    sendToServer(request);
  }

  @Override public void checkOut(ShopItemList checkoutList) {
    Request request = new Request(EventType.CHECKOUT_REQUEST, checkoutList);
    sendToServer(request);
  }

  @Override public void broadcastRemoveItem(ShopItem item) {
    Request request = new Request(EventType.BROADCAST_REMOVE, item);
    sendToServer(request);
  }

  @Override public void addItem(ShopItem item) {
    Request request = new Request(EventType.ADD_ITEM_REQUEST, item);
    sendToServer(request);
  }

  @Override public void removeItem(ShopItem item) {
    Request request = new Request(EventType.REMOVE_ITEM_REQUEST, item);
    sendToServer(request);
  }

  private void sendToServer(Request request) {
    try {
      out.writeObject(request);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loginResult(Response response) {
    propertyChangeSupport.firePropertyChange(EventType.LOGIN_RESULT.toString(), null, response.object);
  }

  public void registerResult(Response response) {
    propertyChangeSupport.firePropertyChange(EventType.REGISTER_RESULT.toString(), null, response.object);
  }

  public void getListResult(Response response) {
    propertyChangeSupport.firePropertyChange(EventType.LIST_RESULT.toString(), null, response.object);
  }

  public void checkoutResult(Response response) {
    propertyChangeSupport.firePropertyChange(EventType.CHECKOUT_RESULT.toString(), null, response.object);
  }

  public void receiveBroadcast(Response response) {
    propertyChangeSupport.firePropertyChange(EventType.BROADCAST_REMOVE.toString(), null, response.object);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener) {
    if (name == null)
      addPropertyChangeListener(listener);
    else {
      propertyChangeSupport.addPropertyChangeListener(name, listener);
    }
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener) {
    if (name == null)
      removePropertyChangeListener(listener);
    else {
      propertyChangeSupport.removePropertyChangeListener(name, listener);
    }
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
}
