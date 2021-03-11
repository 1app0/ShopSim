package shared.dataTransfer;

import shared.EventType;

import java.io.Serializable;

public class Response implements Serializable {
  public Object object;
  public EventType type;

  public Response(EventType type, Object object) {
    this.type = type;
    this.object = object;
  }
}
