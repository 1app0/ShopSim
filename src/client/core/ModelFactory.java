package client.core;

import client.model.ShopItemModel;
import client.model.ShopItemModelManager;
import client.model.UserModel;
import client.model.UserModelManager;

public class ModelFactory {
  public Object getRegisterUserModel;
  private UserModel userModel;
  private ShopItemModel shopItemModel;

  public ModelFactory(ClientFactory clientFactory) {
    this.userModel = new UserModelManager(clientFactory.getClient());
    this.shopItemModel = new ShopItemModelManager(clientFactory.getClient());
  }

  public UserModel getUserModel() {
    return userModel;
  }

  public ShopItemModel getShopItemModel() {
    return shopItemModel;
  }
}
