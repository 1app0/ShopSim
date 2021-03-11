package client.model;

import client.Network.Client;
import shared.EventType;
import shared.dataTransfer.ShopItem;
import shared.dataTransfer.ShopItemList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShopItemModelManager implements ShopItemModel {
  private PropertyChangeSupport propertyChangeSupport;
  private ShopItemList shopItemList;
  private ShopItemList basketList;
  private Client client;

  public ShopItemModelManager(Client client) {
    this.client = client;
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    shopItemList = new ShopItemList();
    basketList = new ShopItemList();
    client.getItems();
    client.addPropertyChangeListener(EventType.LIST_RESULT.toString(), this::onGetListResult);
    client.addPropertyChangeListener(EventType.CHECKOUT_RESULT.toString(), this::onCheckoutResult);
    client.addPropertyChangeListener(EventType.BROADCAST_REMOVE.toString(), this::onBroadcast);
  }

  private void onBroadcast(PropertyChangeEvent evt) {
    ShopItem item = (ShopItem) evt.getNewValue();
    propertyChangeSupport.firePropertyChange(EventType.BROADCAST_REMOVE.toString(), null, item);
  }

  private void onCheckoutResult(PropertyChangeEvent evt) {
    String result = (String) evt.getNewValue();
    propertyChangeSupport.firePropertyChange(EventType.CHECKOUT_RESULT.toString(), null, result);
  }

  private void onGetListResult(PropertyChangeEvent evt) {
    shopItemList = (ShopItemList) evt.getNewValue();
  }

  @Override public void addShopItem(ShopItem item) {
    shopItemList.addItem(item);
    client.addItem(item);
    propertyChangeSupport.firePropertyChange("addItem", null, item);
  }

  @Override public void addToBasket(ShopItem item) {
    shopItemList.removeItem(item);
    basketList.addItem(item);
    client.broadcastRemoveItem(item);
    propertyChangeSupport.firePropertyChange("basketItemAdded", null, item);
  }

  @Override public ShopItem getShopItem(int index) {
    return shopItemList.getItem(index);
  }

  @Override public int getSize() {
    return shopItemList.getSize();
  }

  @Override public void checkout(ShopItemList checkoutList) {
    client.checkOut(checkoutList);
  }

  @Override public void removeItem(ShopItem item) {
    client.removeItem(item);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener) {
    if (name == null)
      addPropertyChangeListener(listener);
    else {
      propertyChangeSupport.addPropertyChangeListener(name, listener);
    }
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener) {
    if (name == null)
      removePropertyChangeListener(listener);
    else {
      propertyChangeSupport.removePropertyChangeListener(name, listener);
    }
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
}
