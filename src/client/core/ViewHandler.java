package client.core;

import client.view.addItemView.AddItemViewController;
import client.view.adminView.AdminViewController;
import client.view.basketView.BasketViewController;
import client.view.cashierView.CashierViewController;
import client.view.login.LoginController;
import client.view.managerView.ManagerViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
  private Stage primaryStage;
  private ViewModelFactory viewModelFactory;

  public ViewHandler(ViewModelFactory viewModelFactory) {
    primaryStage = new Stage();
    this.viewModelFactory = viewModelFactory;
  }

  public void start() {
    openLoginView();
    primaryStage.show();
  }

  public void openLoginView() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/login/Login.fxml"));
    try {
      Parent root = loader.load();
      LoginController loginController = loader.getController();
      loginController.init(viewModelFactory.getLoginViewModel(), this);
      primaryStage.setTitle("Log in");
      Scene loginScene = new Scene(root);
      primaryStage.setScene(loginScene);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openAdminView() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/adminView/AdminView.fxml"));
    try {
      Parent root = loader.load();
      AdminViewController adminViewController = loader.getController();
      adminViewController.init(viewModelFactory.getAdminViewModel(), this);
      primaryStage.setTitle("Admin");
      Scene adminScene = new Scene(root);
      primaryStage.setScene(adminScene);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openCashierView() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/cashierView/cashierView.fxml"));
    try {
      Parent root = loader.load();
      CashierViewController cashierViewController = loader.getController();
      cashierViewController.init(viewModelFactory.getCashierViewModel(), this);
      primaryStage.setTitle("Cashier");
      Scene cashierScene = new Scene(root);
      primaryStage.setScene(cashierScene);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openBasketView() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/basketView/BasketView.fxml"));
    try {
      Parent root = loader.load();
      BasketViewController basketViewController = loader.getController();
      basketViewController.init(viewModelFactory.getBasketViewModel(), this);
      primaryStage.setTitle("Basket");
      Scene basketScene = new Scene(root);
      primaryStage.setScene(basketScene);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openManagerView() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/managerView/ManagerView.fxml"));
    try {
      Parent root = loader.load();
      ManagerViewController managerViewController = loader.getController();
      managerViewController.init(viewModelFactory.getManagerViewModel(), this);
      primaryStage.setTitle("Manager");
      Scene managerScene = new Scene(root);
      primaryStage.setScene(managerScene);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openAddItemsView() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/addItemView/AddItemView.fxml"));
    try {
      Parent root = loader.load();
      AddItemViewController addItemViewController = loader.getController();
      addItemViewController.init(viewModelFactory.getAddItemViewModel(), this);
      primaryStage.setTitle("Add Item");
      Scene addItemScene = new Scene(root);
      primaryStage.setScene(addItemScene);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
