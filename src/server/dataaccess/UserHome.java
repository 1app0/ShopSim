package server.dataaccess;

import shared.dataTransfer.Response;
import shared.dataTransfer.User;

import java.sql.SQLException;

public interface UserHome {
  Response validateUser(User user) throws SQLException;
  Response registerUser(User user) throws SQLException;
}
