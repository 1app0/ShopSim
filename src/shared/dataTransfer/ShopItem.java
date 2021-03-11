package shared.dataTransfer;

import java.io.Serializable;
//DONT CHANGE ANYTHING IN HERE
public  class ShopItem implements Serializable {
  private int id;
  private String name;
  private int barcode;

  public ShopItem(int id, String name, int barcode) {
    this.id = id;
    this.name = name;
    this.barcode = barcode;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getBarcode() {
    return barcode;
  }

  public String toString() {
    return id  + ", " + name + ", " + barcode;
  }
}
