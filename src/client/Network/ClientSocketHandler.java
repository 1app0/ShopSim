package client.Network;

import shared.EventType;
import shared.dataTransfer.Request;
import shared.dataTransfer.Response;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientSocketHandler implements Runnable {
  private SocketClient socketClient;
  private ObjectInputStream in;

  public ClientSocketHandler(SocketClient socketClient, ObjectInputStream in) {
    this.socketClient = socketClient;
    this.in = in;
  }

  @Override public void run() {
    while (true) {
      try {
        Response response = (Response) in.readObject();
        switch (response.type) {
          case LOGIN_RESULT:
            socketClient.loginResult(response);
            break;
          case REGISTER_RESULT:
            socketClient.registerResult(response);
            break;
          case LIST_RESULT:
            socketClient.getListResult(response);
            break;
          case CHECKOUT_RESULT:
            socketClient.checkoutResult(response);
            break;
          case BROADCAST_REMOVE:
            socketClient.receiveBroadcast(response);
        }
      }
      catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }
}
