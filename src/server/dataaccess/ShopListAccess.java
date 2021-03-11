package server.dataaccess;

import server.database.ProductDAOImpl;
import shared.EventType;
import shared.dataTransfer.Response;
import shared.dataTransfer.ShopItem;
import shared.dataTransfer.ShopItemList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ShopListAccess implements ShopListHome {
  private ShopItemList list;
  private ProductDAOImpl productDAO;
  public ShopListAccess() {
    this.productDAO = new ProductDAOImpl();
    try {
      list = productDAO.selectAll();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Override public Response getList()
  {
    return new Response(EventType.LIST_RESULT, list);
}

  @Override public Response checkout(ShopItemList checkoutList) //TODO cashier checks out with selected products
  {
    for (int i = 0; i < checkoutList.getSize(); i++) {
      try {
        ShopItem item = checkoutList.getItem(i);
        list.removeItem(item);
        productDAO.removeProduct(item);
      }
      catch (Exception e) {
        System.out.println("Checkout failed");
        return new Response(EventType.CHECKOUT_RESULT, "Checkout failed");
      }
    }
    return new Response(EventType.CHECKOUT_RESULT, "Checkout successful");
  }

  @Override public void addItem(ShopItem item) throws SQLException
  {
    list.addItem(item);
    productDAO.addProduct(item);
  }

  @Override public void removeItem(ShopItem item) throws SQLException {
    list.removeItem(item);
    productDAO.removeProduct(item);
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager
        .getConnection("jdbc:postgresql://localhost:5432/ProjectDatabase",
            "postgres", "2031");
  }
}
