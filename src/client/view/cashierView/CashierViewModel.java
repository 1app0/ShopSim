package client.view.cashierView;

import client.model.ShopItemModel;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.EventType;
import shared.dataTransfer.ShopItem;

import java.beans.PropertyChangeEvent;

public class CashierViewModel {
  private ShopItemModel shopItemModel;
  private ObservableList<TableRowData> list;
  private ObjectProperty<TableRowData> selected;
  private StringProperty error;

  public CashierViewModel(ShopItemModel shopItemModel){
    this.shopItemModel = shopItemModel;
    shopItemModel.addPropertyChangeListener(EventType.BROADCAST_REMOVE.toString(), this::onBroadcastRemove);

    list = createList();
    selected = new SimpleObjectProperty<>();
    error = new SimpleStringProperty();
  }

  private void onBroadcastRemove(PropertyChangeEvent evt) {
    ShopItem item = (ShopItem) evt.getNewValue();
    Platform.runLater(() -> {
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).getIdProperty().get() == item.getId()
            && list.get(i).getNameProperty().get().equals(item.getName())
            && list.get(i).getBarcodeProperty().get() == item.getBarcode())
        {
          list.remove(i);
          break;
        }
      }
    });
  }


  private synchronized ObservableList<TableRowData> createList() {
    ObservableList<TableRowData> obsList = FXCollections.observableArrayList();

    for (int i = 0; i < shopItemModel.getSize(); i++) {
      obsList.add(new TableRowData(shopItemModel.getShopItem(i)));
    }
    return obsList;
  }

  public ObservableList<TableRowData> getList() {
    return list;
  }

  public ObjectProperty<TableRowData> getSelectedProperty() {
    return selected;
  }

  public StringProperty getErrorProperty() {
    return error;
  }

  public void clear() {error.set(null);}

  public void addToBasket() {
    if (selected.get() == null) {
      error.set("Select an item");
      return;
    }
    error.set("");
    ShopItem item = selected.get().getShopItem();
    shopItemModel.addToBasket(item);
  }
}
