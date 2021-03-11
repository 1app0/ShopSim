package client.view.login;

import client.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import shared.dataTransfer.User;

public class LoginController {
  @FXML private TextField usernameTextField, passwordTextField;
  @FXML private Label loginResultLabel;
  @FXML private Button loginButton;

  private LoginViewModel loginViewModel;

  private ViewHandler viewHandler;

  public void init(LoginViewModel loginViewModel, ViewHandler viewHandler) {
    this.loginViewModel = loginViewModel;
    this.viewHandler = viewHandler;
    usernameTextField.textProperty().bindBidirectional(loginViewModel.getUsernameProperty());
    passwordTextField.textProperty().bindBidirectional(loginViewModel.getPasswordProperty());
    loginResultLabel.textProperty().bindBidirectional(loginViewModel.getLoginResponseProperty());
    loginButton.disableProperty().bind(loginViewModel.getLoginButtonDisabled());
    loginResultLabel.textProperty().addListener(((observableValue, oldValue, newValue) -> onLabelChange(newValue)));
  }

  private void onLabelChange(String newValue) {
    if (newValue.equals(User.UserType.ADMINISTRATOR.toString())) {
      loginViewModel.clear();
      viewHandler.openAdminView();
    }
    else if (newValue.equals(User.UserType.CASHIER.toString())) {
      loginViewModel.clear();
      viewHandler.openCashierView();
    }
    else if (newValue.equals(User.UserType.MANAGER.toString())) {
      loginViewModel.clear();
      viewHandler.openManagerView();
    }
  }

  @FXML public void onLoginButton() {
    loginResultLabel.setText("Type");
    loginViewModel.login();
  }
}
