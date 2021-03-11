package server.database;

import shared.dataTransfer.ShopItem;

import java.sql.SQLException;

public interface ProductDAO
{
  void addProduct(ShopItem shopItem) throws SQLException;
  void removeProduct(ShopItem shopItem) throws SQLException;


}
