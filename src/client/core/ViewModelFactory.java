package client.core;

import client.view.addItemView.AddItemViewModel;
import client.view.adminView.AdminViewModel;
import client.view.basketView.BasketViewModel;
import client.view.cashierView.CashierViewModel;
import client.view.login.LoginViewModel;
import client.view.managerView.ManagerViewModel;

public class ViewModelFactory {
  private LoginViewModel loginViewModel;
  private AdminViewModel adminViewModel;
  private CashierViewModel cashierViewModel;
  private BasketViewModel basketViewModel;
  private ManagerViewModel managerViewModel;
  private AddItemViewModel addItemViewModel;

  public ViewModelFactory(ModelFactory modelFactory) {
    loginViewModel = new LoginViewModel(modelFactory.getUserModel());
    adminViewModel = new AdminViewModel(modelFactory.getUserModel());
    cashierViewModel = new CashierViewModel(modelFactory.getShopItemModel());
    basketViewModel = new BasketViewModel(modelFactory.getShopItemModel());
    managerViewModel = new ManagerViewModel(modelFactory.getShopItemModel());
    addItemViewModel = new AddItemViewModel(modelFactory.getShopItemModel());
  }

  public LoginViewModel getLoginViewModel() {
    return loginViewModel;
  }

  public AdminViewModel getAdminViewModel() {
    return adminViewModel;
  }

  public CashierViewModel getCashierViewModel() {
    return cashierViewModel;
  }

  public BasketViewModel getBasketViewModel() {
    return basketViewModel;
  }

  public ManagerViewModel getManagerViewModel() {
    return managerViewModel;
  }

  public AddItemViewModel getAddItemViewModel() {
    return addItemViewModel;
  }
}
