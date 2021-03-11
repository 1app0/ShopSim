package shared.dataTransfer;

import java.io.Serializable;

public class User implements Serializable {
  private String username;
  private String password;
  private UserType userType;

  public enum UserType {
    MANAGER,
    CASHIER,
    ADMINISTRATOR
  }

  public User(String username, String password, UserType userType) {
    this.username = username;
    this.password = password;
    this.userType = userType;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getUserType() {
    if(userType.equals(UserType.ADMINISTRATOR)){
      return "ADMINISTRATOR";
    }
    else if(userType.equals(UserType.MANAGER)){
      return "MANAGER";
    }
    else {
      return "CASHIER";
    }
  }

  @Override public String toString() {
    return "User{" + "username='" + username + '\'' + ", password='" + password
        + '\'' + '}';
  }
}
