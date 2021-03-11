package client.model;

import shared.dataTransfer.ShopItem;
import shared.dataTransfer.ShopItemList;
import shared.util.PropertyChangeSubject;

public interface ShopItemModel extends PropertyChangeSubject {
  void addShopItem(ShopItem item);
  void addToBasket(ShopItem item);
  ShopItem getShopItem(int index);
  int getSize();
  void checkout(ShopItemList checkoutList);
  void removeItem(ShopItem item);
}
