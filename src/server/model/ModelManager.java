package server.model;

import server.dataaccess.ShopListHome;
import server.dataaccess.UserHome;
import shared.EventType;
import shared.dataTransfer.Response;
import shared.dataTransfer.ShopItem;
import shared.dataTransfer.ShopItemList;
import shared.dataTransfer.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class ModelManager implements Model {
  private UserHome userHome;
  private ShopListHome shopListHome;
  private PropertyChangeSupport propertyChangeSupport;

  public ModelManager(UserHome userHome, ShopListHome shopListHome) {
    this.userHome = userHome;
    this.shopListHome = shopListHome;
    propertyChangeSupport = new PropertyChangeSupport(this);
  }

  @Override public Response validateUser(User user) throws SQLException
  {
    return userHome.validateUser(user);
  }

  @Override public Response registerUser(User user) throws SQLException {
    return userHome.registerUser(user);
  }

  @Override public Response getList() throws SQLException{
    return shopListHome.getList();
  }

  @Override public Response checkout(ShopItemList checkoutList) throws SQLException
  {
    return shopListHome.checkout(checkoutList);
  }

  @Override public void broadcastRemove(ShopItem item) {
    propertyChangeSupport.firePropertyChange(EventType.BROADCAST_REMOVE.toString(), null, item);
  }

  @Override public void addItem(ShopItem item) throws SQLException {
    shopListHome.addItem(item);
  }

  @Override public void removeItem(ShopItem item) throws SQLException {
    shopListHome.removeItem(item);
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
