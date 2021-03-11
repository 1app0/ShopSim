package server.database;

import shared.dataTransfer.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoginDAO
{
  void create(User user) throws SQLException;
  ArrayList<User> readByManager() throws SQLException;
  ArrayList<User> readByCashier() throws SQLException;
  boolean checkNameByInput(String username) throws SQLException;
  boolean checkPasswordByInput(String username, String password) throws SQLException;
  String getTypeByName(String username) throws SQLException;
  void update(User user) throws SQLException;
  void delete(User user) throws SQLException;
}
