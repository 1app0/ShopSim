package server.dataaccess;

import shared.dataTransfer.Response;
import shared.dataTransfer.ShopItem;
import shared.dataTransfer.ShopItemList;

import java.sql.SQLException;

public interface ShopListHome {
  Response getList() throws SQLException;
  Response checkout(ShopItemList checkoutList) throws SQLException;
  void addItem(ShopItem item) throws SQLException;
  void removeItem(ShopItem item) throws SQLException;
}
