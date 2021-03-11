package shared.dataTransfer;

import server.database.ProductDAOImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShopItemList implements Serializable {
  private List<ShopItem> shopItemList;

  public ShopItemList() {
    shopItemList = new ArrayList<>();
  }

  public int getSize() {
    return shopItemList.size();
  }

  public void addItem(ShopItem item) {
    shopItemList.add(item);
  }

  public void removeItem(ShopItem item)
  {
    for (int i = 0; i < getSize(); i++) {
      if (getItem(i).getId() == item.getId() &&
          getItem(i).getName().equals(item.getName()) &&
          getItem(i).getBarcode() == item.getBarcode()) {
        shopItemList.remove(i);
      }
    }
  }

  public ShopItem getItem(int index) {
    return shopItemList.get(index);
  }

  public String toString() {
    String s = "";
    for (int i = 0; i < shopItemList.size(); i++) {
      s += shopItemList.get(i);
    }
    return s;
  }
}
