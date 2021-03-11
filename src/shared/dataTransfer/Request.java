package shared.dataTransfer;

import shared.EventType;

import java.io.Serializable;

public class Request implements Serializable {
  public Object object;
  public EventType type;


  public Request(EventType type, Object object) {
    this.type = type;
    this.object = object;
  }
}
