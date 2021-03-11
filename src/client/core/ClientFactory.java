package client.core;

import client.Network.Client;
//import client.Network.DummyClient;
import client.Network.SocketClient;

public class ClientFactory {
  private Client client;

  public ClientFactory() {
    client = new SocketClient();
  }

  public Client getClient() {
    return client;
  }
}
