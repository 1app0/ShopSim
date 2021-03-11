package server.database;

import shared.dataTransfer.ShopItem;
import shared.dataTransfer.ShopItemList;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO
{

  private Connection getConnection() throws SQLException
  {
    return DriverManager
        .getConnection("jdbc:postgresql://localhost:5432/postgres",
            "postgres", "2031");
  }

  @Override public void addProduct(ShopItem shopItem) throws SQLException
  {
    Connection connection=getConnection();
    try
    {
      PreparedStatement statement=connection.prepareStatement("INSERT INTO product(id,name,barcode) VALUES(?,?,?);");
      statement.setInt(1,shopItem.getId());
      statement.setString(2,shopItem.getName());
      statement.setInt(3, shopItem.getBarcode());
      statement.executeUpdate();
      statement.close();
      connection.close();

    }
    catch (Exception e)
    {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.out.println("~error adding product~");
      System.exit(0);
    }
  }

  @Override public void removeProduct(ShopItem shopItem) throws SQLException //WE CLICK ON THE PRODUCT TO REMOVE
  {

    Connection connection=getConnection();
    try
    {
      PreparedStatement statement=connection.prepareStatement("DELETE FROM product WHERE id=?");
      System.out.println(shopItem.getId());
      statement.setInt(1,shopItem.getId());
      statement.executeUpdate();
      statement.close();
    }
    catch (Exception e)
    {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.out.println("~error removing product~");
      System.exit(0);
    }
  }

public ShopItemList selectAll() throws SQLException
  {
    ShopItemList list=new ShopItemList();
    Connection connection=getConnection();
    try
    {
      PreparedStatement statement=connection.prepareStatement("SELECT * FROM product");
      statement.executeQuery();
      ResultSet rs=statement.executeQuery();

      while(rs.next())
      {
        ShopItem item=new ShopItem(rs.getInt("id"),rs.getString("name"),rs.getInt("barcode"));
        list.addItem(item);
      }
      return list;
    }
    catch (Exception e)
    {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return null;
  }
}

