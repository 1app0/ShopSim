package server;

import shared.EventType;
import shared.dataTransfer.Request;
import shared.dataTransfer.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TestClient {
  public static void main(String[] args)
      throws ClassNotFoundException, IOException {
    Socket socket = new Socket("localhost", 1234);
    User user1 = new User("Alex1", "alex123", null);
    User user2 = new User("Alex", "alex111", null);
    User user3 = new User("Alex", "alex123", null);

    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

    out.writeObject(new Request(EventType.LOGIN_REQUEST, user1));
    Request request = (Request) in.readObject();
    System.out.println(request.object);

    out.writeObject(new Request(EventType.LOGIN_REQUEST, user2));
    Request request1 = (Request) in.readObject();
    System.out.println(request1.object);

    out.writeObject(new Request(EventType.LOGIN_REQUEST, user3));
    Request request2 = (Request) in.readObject();
    System.out.println(request2.object);
  }
}
