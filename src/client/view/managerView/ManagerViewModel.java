package client.view.managerView;

import client.model.ShopItemModel;
import client.view.cashierView.TableRowData;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.dataTransfer.ShopItem;

import java.beans.PropertyChangeEvent;

public class ManagerViewModel {
  private ObservableList<TableRowData> list;
  private ObjectProperty<TableRowData> selected;
  private StringProperty error;
  private ShopItemModel model;

  public ManagerViewModel(ShopItemModel model) {
    this.model = model;

    list = createList();
    selected = new SimpleObjectProperty<>();
    error = new SimpleStringProperty();

    model.addPropertyChangeListener("addItem", this::addItem);
  }

  private void addItem(PropertyChangeEvent evt) {
    ShopItem item = (ShopItem) evt.getNewValue();
    Platform.runLater(() -> {
      list.add(new TableRowData(item));
    });
  }

  private synchronized ObservableList<TableRowData> createList() {
    ObservableList<TableRowData> obsList = FXCollections.observableArrayList();

    for (int i = 0; i < model.getSize(); i++) {
      obsList.add(new TableRowData(model.getShopItem(i)));
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

  public void clear() {
    error.set(null);
  }

  public void removeItem() {
    if (selected.get() == null) {
      error.set("No item selected");
    }
    error.set("");
    ShopItem item = selected.get().getShopItem();
    model.removeItem(item);
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getIdProperty().get() == item.getId()
          && list.get(i).getNameProperty().get().equals(item.getName())
          && list.get(i).getBarcodeProperty().get() == item.getBarcode())
      {
        list.remove(i);
        break;
      }
    }
  }
}
