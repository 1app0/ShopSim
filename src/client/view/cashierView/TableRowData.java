package client.view.cashierView;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.dataTransfer.ShopItem;

public class TableRowData {
  private IntegerProperty idProperty;
  private StringProperty nameProperty;
  private IntegerProperty barcodeProperty;

  public TableRowData(ShopItem item) {
    idProperty = new SimpleIntegerProperty(item.getId());
    nameProperty = new SimpleStringProperty(item.getName());
    barcodeProperty = new SimpleIntegerProperty(item.getBarcode());
  }

  public IntegerProperty getIdProperty() {
    return idProperty;
  }

  public StringProperty getNameProperty() {
    return nameProperty;
  }

  public IntegerProperty getBarcodeProperty() {
    return barcodeProperty;
  }

  public ShopItem getShopItem() {
    return new ShopItem(idProperty.get(), nameProperty.get(), barcodeProperty.get());
  }
}
