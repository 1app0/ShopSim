package client.model;

import client.Network.Client;
import javafx.event.Event;
import shared.EventType;
import shared.dataTransfer.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserModelManager implements UserModel {
  private Client client;
  private User loggedInUser;
  private PropertyChangeSupport propertyChangeSupport;

  public UserModelManager(Client client) {
    this.client = client;
    propertyChangeSupport = new PropertyChangeSupport(this);
    client.addPropertyChangeListener(EventType.LOGIN_RESULT.toString(), this::onLoginResult);
    client.addPropertyChangeListener(EventType.REGISTER_RESULT.toString(), this::onRegisterResult);
  }

  private void onRegisterResult(PropertyChangeEvent evt) {
    String registerResult = (String) evt.getNewValue();
    propertyChangeSupport.firePropertyChange(EventType.REGISTER_RESULT.toString(), null, registerResult);
  }

  private void onLoginResult(PropertyChangeEvent evt) {
    String loginResult = (String) evt.getNewValue();
    if (!(User.UserType.ADMINISTRATOR.toString().equals(loginResult) || User.UserType.MANAGER.toString().equals(loginResult)
        || User.UserType.CASHIER.toString().equals(loginResult))) {
      loggedInUser = null;
    }
    propertyChangeSupport.firePropertyChange(EventType.LOGIN_RESULT.toString(), null, loginResult);
  }

  @Override public void login(String username, String password) {
    loggedInUser = new User(username, password, null);
    client.login(loggedInUser);
  }

  @Override public void registerUser(String username, String password, User.UserType userType) {
    User user = new User(username, password, userType);
    client.registerUser(user);
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
