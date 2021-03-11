package client.view.adminView;

import client.model.UserModel;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.EventType;
import shared.dataTransfer.User;

import java.beans.PropertyChangeEvent;

public class AdminViewModel {
  private StringProperty username, password, confirmPassword, response;
  private BooleanProperty disableRegisterButtons;
  private UserModel userModel;

  public AdminViewModel(UserModel userModel) {
    this.userModel = userModel;
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    confirmPassword = new SimpleStringProperty();
    response = new SimpleStringProperty();
    disableRegisterButtons = new SimpleBooleanProperty(true);
    userModel.addPropertyChangeListener(EventType.REGISTER_RESULT.toString(), this::onRegisterResponse);
    username.addListener(((observableValue, oldValue, newValue) -> onInputFieldsChange()));
    password.addListener((observableValue, oldValue, newValue) -> onInputFieldsChange());
    confirmPassword.addListener((observableValue, oldValue, newValue) -> onInputFieldsChange());

  }

  private void onInputFieldsChange() {
    boolean disabled = username.get() == null ||
        username.get().equals("") ||
        password.get() == null ||
        password.get().equals("") ||
        confirmPassword.get() == null ||
        confirmPassword.get().equals("");
    disableRegisterButtons.set(disabled);
  }

  private void onRegisterResponse(PropertyChangeEvent evt) {
    String result = (String) evt.getNewValue();
    Platform.runLater(() -> {
      response.set(result);
    });
  }

  public void registerCashier() {
    userModel.registerUser(username.get(), password.get(), User.UserType.CASHIER);
  }

  public void registerManager() {
    registerUser(User.UserType.MANAGER);
  }
  private void registerUser(User.UserType userType) {
    String userNameVer = username.get();
    if (userNameVer == null || userNameVer.equals("")) {
      response.set("Username cannot be empty");
      return;
    }
    String passwordVer = password.get();
    if (passwordVer == null) {
      response.set("Password cannot be empty");
      return;
    }
    if (!confirmPassword.get().equals(passwordVer)) {
      response.set("Passwords do not match");
      return;
    }
    userModel.registerUser(username.get(), password.get(), User.UserType.MANAGER);
  }

  public StringProperty getUsernameProperty() {
    return username;
  }

  public StringProperty getPasswordProperty() {
    return password;
  }

  public StringProperty getConfirmPasswordProperty() {
    return confirmPassword;
  }

  public StringProperty getResponseProperty() {
    return response;
  }

  public BooleanProperty getDisableRegisterButtonsProperty() {
    return disableRegisterButtons;
  }

  public void clear() {
    username.set("");
    password.set("");
    confirmPassword.set("");
  }
}
