package client.view.basketView;

import client.model.ShopItemModel;
import client.view.cashierView.TableRowData;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.EventType;
import shared.dataTransfer.ShopItem;
import shared.dataTransfer.ShopItemList;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class BasketViewModel {
  private ShopItemModel shopItemModel;
  private StringProperty checkoutResult;
  private ObservableList<TableRowData> list;

  public BasketViewModel(ShopItemModel shopItemModel) {
    this.shopItemModel = shopItemModel;
    checkoutResult = new SimpleStringProperty();
    list = FXCollections.observableArrayList();

    shopItemModel.addPropertyChangeListener("basketItemAdded", this::addToBasket);
    shopItemModel.addPropertyChangeListener(EventType.CHECKOUT_RESULT.toString(), this::checkoutResponse);
  }

  private void checkoutResponse(PropertyChangeEvent evt) {
    String result = (String) evt.getNewValue();
    if (result.equals("Checkout failed")) {
      Platform.runLater(() -> {
        checkoutResult.set(result);
      });
    }
    else if(result.equals("Checkout successful")) {
      Platform.runLater(() -> {
        checkoutResult.set(result);
        list.clear();
      });
    }
  }

  private void addToBasket(PropertyChangeEvent evt) {
    ShopItem item = (ShopItem) evt.getNewValue();
    Platform.runLater(() -> {
      list.add(new TableRowData(item));
    });
  }

  public ObservableList<TableRowData> getShopItemList() {
    return list;
  }

  public StringProperty getCheckoutResult() {
    return checkoutResult;
  }

  public void checkout() {
    ShopItemList checkoutList = new ShopItemList();
    for (int i = 0; i < list.size(); i++) {
      checkoutList.addItem(list.get(i).getShopItem());
    }
    shopItemModel.checkout(checkoutList);
  }
}
