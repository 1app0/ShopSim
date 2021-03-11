package server.model;

import shared.dataTransfer.Response;
import shared.dataTransfer.ShopItem;
import shared.dataTransfer.ShopItemList;
import shared.dataTransfer.User;
import shared.util.PropertyChangeSubject;

import java.sql.SQLException;

public interface Model extends PropertyChangeSubject {
  Response validateUser(User user) throws SQLException;
  Response registerUser(User user) throws SQLException;
  Response getList() throws SQLException;
  Response checkout(ShopItemList checkoutList) throws SQLException;
  void broadcastRemove(ShopItem item);
  void addItem(ShopItem item) throws SQLException;
  void removeItem(ShopItem item) throws SQLException;
}
