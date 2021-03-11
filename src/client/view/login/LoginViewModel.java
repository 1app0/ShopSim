package client.view.login;

import client.model.UserModel;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.EventType;

import java.beans.PropertyChangeEvent;

public class LoginViewModel {
  private StringProperty username, password, loginResponse;
  private BooleanProperty loginButtonDisabled;
  private UserModel userModel;

  public LoginViewModel(UserModel userModel) {
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    loginResponse = new SimpleStringProperty();
    loginButtonDisabled = new SimpleBooleanProperty(true);
    this.userModel = userModel;

    userModel.addPropertyChangeListener(EventType.LOGIN_RESULT.toString(), this::onLoginResponse);

    username.addListener(((observableValue, oldValue, newValue) -> onUserNameChange(newValue)));
  }

  private void onUserNameChange(String newValue) {
    boolean disabledButton = (newValue == null || newValue.equals(""));
    loginButtonDisabled.setValue(disabledButton);
  }

  private void onLoginResponse(PropertyChangeEvent evt) {
    String result = (String) evt.getNewValue();
    Platform.runLater(() -> {
      loginResponse.set(result);
    });
  }

  public StringProperty getUsernameProperty() {
    return username;
  }

  public StringProperty getPasswordProperty() {
    return password;
  }

  public StringProperty getLoginResponseProperty() {
    return loginResponse;
  }

  public BooleanProperty getLoginButtonDisabled() {
    return loginButtonDisabled;
  }

  public void login() {
    userModel.login(username.get(), password.get());
  }

  public void clear() {
    username.set("");
    password.set("");
  }
}
