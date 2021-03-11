package client.view.addItemView;

import client.model.ShopItemModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.dataTransfer.ShopItem;

public class AddItemViewModel {
  private ShopItemModel model;
  private IntegerProperty id;
  private StringProperty name;
  private IntegerProperty barcode;
  private StringProperty error;

  public AddItemViewModel(ShopItemModel model) {
    this.model = model;

    id = new SimpleIntegerProperty();
    name = new SimpleStringProperty();
    barcode = new SimpleIntegerProperty();
    error = new SimpleStringProperty();
  }

  public void clear() {
    error.set(null);
    id.set(0);
    name.set(null);
    barcode.set(0);
  }

  public IntegerProperty getId() {
    return id;
  }

  public StringProperty getName() {
    return name;
  }

  public IntegerProperty getBarcode() {
    return barcode;
  }

  public StringProperty getError() {
    return error;
  }

  public boolean addItem() {
    error.set(null);
    try {
      if (id.get() < 0 || barcode.get() < 0 || name.get().equals("")) {
        error.set("Input legal values");
        return false;
      }
      ShopItem item = new ShopItem(id.get(), name.get(), barcode.get());
      model.addShopItem(item);
      return true;
    }
    catch (Exception e) {
      error.set("Input legal values");
    }
    return false;
  }
}
