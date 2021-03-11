package client.Network;

import shared.dataTransfer.ShopItem;
import shared.dataTransfer.ShopItemList;
import shared.dataTransfer.User;
import shared.util.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject {
  void login(User user);
  void registerUser(User user);
  void getItems();
  void checkOut(ShopItemList checkoutList);
  void broadcastRemoveItem(ShopItem item);
  void addItem(ShopItem item);
  void removeItem(ShopItem item);
}
