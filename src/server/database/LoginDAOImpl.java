package server.database;

import shared.dataTransfer.User;

import java.sql.*;
import java.util.ArrayList;

public class LoginDAOImpl implements LoginDAO
{

  private User user;
  private Connection getConnection() throws SQLException
  {
    return DriverManager
        .getConnection("jdbc:postgresql://localhost:5432/postgres",
            "postgres", "2031");
  }



  @Override public void create(User user) throws SQLException
  {

      Connection connection = getConnection();
      try
      {
         PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO login(password,name,type) VALUES(?,?,?);");
          statement.setString(1, user.getPassword());
          statement.setString(2, user.getUsername());
          statement.setString(3, user.getUserType());
          statement.executeUpdate();
          statement.close();
          connection.close();

      }
      catch (Exception e)
      {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        System.out.println("error create user(register)");
        System.exit(0);
      }

    }


  @Override public ArrayList<User> readByManager() throws SQLException
  {
    Connection connection = getConnection();
    ArrayList<User> users = new ArrayList<>();
    try
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM login WHERE type= 'MANAGER' ");
      ResultSet rs = statement.executeQuery();
      System.out.println("---login---");

      while (rs.next())
      {
        String username = rs.getString("name");
        String password = rs.getString("password");
        User user = new User(username, password, User.UserType.MANAGER);
        users.add(user);
        statement.close();
        connection.close();
      }
      return users;
    }
    catch (Exception e)
    {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return null;
  }

  @Override public ArrayList<User> readByCashier() throws SQLException
  {
    Connection connection = getConnection();
    ArrayList<User> users = new ArrayList<>();
    try
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM login WHERE type='CASHIER'");
      ResultSet rs = statement.executeQuery();
      System.out.println("---login---");

      while (rs.next())
      {
        String username = rs.getString("name");
        String password = rs.getString("password");
        User user = new User(username, password, User.UserType.CASHIER);
        users.add(user);
        statement.close();
        connection.close();
      }
      return users;
    }
    catch (Exception e)
    {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return null;
  }

  @Override public boolean checkNameByInput(String name) throws SQLException
  {

    Connection connection = getConnection();
    try
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT COUNT(*) FROM login WHERE name=?");
      statement.setString(1, name);
      ResultSet rs = statement.executeQuery();
      rs.next();
      return rs.getInt(1) > 0;

    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println("error!!!!!!");
      return false;

    }

  }

  @Override public boolean checkPasswordByInput(String username,String password) throws SQLException
  {

    Connection connection = getConnection();
    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT COUNT(*) FROM login WHERE name=? AND password=?");
      statement.setString(1, username);
      statement.setString(2, password);
      ResultSet rs = statement.executeQuery();
      rs.next();
      return rs.getInt(1) > 0;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println("error!!!!!!");
      return false;
    }
  }

  @Override public void update(User user) throws SQLException
  {
//    Connection connection = getConnection();
//    try
//    {
//      if (!user.getUserType().equals("Administrator"))
//      {
//
//        PreparedStatement statement = connection.prepareStatement(
//            "UPDATE Login SET name=?,password=? WHERE type=?");
//        statement.setString(1, user.getUsername());
//        statement.setString(2, user.getPassword());
//        statement.executeUpdate();
//        connection.close();
//        System.out.println("Database updates ok");
//      }
//      else
//      {
//        connection.close();
//        System.out
//            .println("You don't have the authority to update administrator!");
//      }
//    }
//    catch (Exception e)
//    {
//      System.err.println(e.getClass().getName() + ": " + e.getMessage());
//      System.exit(0);
//    }
  }

  @Override public void delete(User user) throws SQLException
  {
//    Connection connection = getConnection();
//    try
//    {
//      if (!user.getUserType().equals("Administrator"))
//      {
//        PreparedStatement statement = connection
//            .prepareStatement("DELETE FROM Login WHERE type=?");
//        statement.setString(1, user.getUserType());
//        statement.executeUpdate();
//        System.out.println("Database updates ok");
//      }
//      else
//      {
//        connection.close();
//        System.out
//            .println("You don't have the authority to delete administrator!");
//      }
//
//    }
//
//    catch (Exception e)
//    {
//      System.err.println(e.getClass().getName() + ": " + e.getMessage());
//      System.exit(0);
//    }
  }

  @Override public String getTypeByName(String username) throws SQLException
  {

    Connection connection = getConnection();
    try
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT type FROM login WHERE name=?;");
      statement.setString(1, username);
      ResultSet rs = statement.executeQuery();
      rs.next();
      return rs.getString(1);

    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println("error gettype by name it is");
      return "getTypeByName ERROR HERE IT IS";
    }

  }

  public User.UserType getTypeByNameTest(String username) throws SQLException{
    Connection connection=getConnection();
    try
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT type FROM login WHERE name=?;");
      statement.setString(1, username);
      ResultSet rs = statement.executeQuery();
      rs.next();
      if(rs.getString(1).equals("ADMINISTRATOR")){
        return User.UserType.ADMINISTRATOR;
      }
      else if(rs.getString(1).equals( "MANAGER")){
        return User.UserType.MANAGER;
      }
      else {
        return User.UserType.CASHIER;
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println("error gettype by name it is");
      return null;
    }


  }

  public String getPasswordByName(String username) throws SQLException
  {
    Connection connection = getConnection();
    try
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT password FROM login WHERE name=?;");
      statement.setString(1, username);
      ResultSet rs = statement.executeQuery();
      rs.next();
      return rs.getString(1);

    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println("error getpassword by name it is");
      return "getPasswordeByName ERROR HERE IT IS";
    }
  }







  //  public String getPassWordByName(String username) throws SQLException  //post condition that user exists
  //  {
  //
  //    Connection connection = getConnection();
  //    try
  //    {
  //      PreparedStatement statement = connection
  //          .prepareStatement("SELECT password FROM login WHERE name=?;");
  //      statement.setString(1, user.getUsername());
  //      ResultSet rs = statement.executeQuery();
  //      rs.next();
  //      return rs.getString("password");
  //    }
  //    catch (Exception e)
  //    {
  //      e.printStackTrace();
  //      return "error getTypeByName";
  //    }
  //
  //  }

}