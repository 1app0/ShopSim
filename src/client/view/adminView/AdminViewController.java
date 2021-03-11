package client.view.adminView;

import client.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminViewController {
  @FXML private TextField username, password, confirmPassword;
  @FXML private Label responseLabel;
  @FXML private Button registerCashierButton, registerManagerButton, cancelButton;
  private AdminViewModel adminViewModel;
  private ViewHandler viewHandler;

  public void init(AdminViewModel adminViewModel, ViewHandler viewHandler) {
    this.adminViewModel = adminViewModel;
    this.viewHandler = viewHandler;
    username.textProperty().bindBidirectional(adminViewModel.getUsernameProperty());
    password.textProperty().bindBidirectional(adminViewModel.getPasswordProperty());
    confirmPassword.textProperty().bindBidirectional(adminViewModel.getConfirmPasswordProperty());
    responseLabel.textProperty().bindBidirectional(adminViewModel.getResponseProperty());
    registerCashierButton.disableProperty().bind(adminViewModel.getDisableRegisterButtonsProperty());
    registerManagerButton.disableProperty().bind(adminViewModel.getDisableRegisterButtonsProperty());
  }

  @FXML public void onRegisterCashierButton() {
    adminViewModel.registerCashier();
  }

  @FXML public void onRegisterManagerButton() {
    adminViewModel.registerManager();
  }

  @FXML public void onCancelButton() {
    adminViewModel.clear();
    viewHandler.openLoginView();
  }
}
