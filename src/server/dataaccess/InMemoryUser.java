package server.dataaccess;

import server.database.LoginDAOImpl;
import shared.EventType;
import shared.dataTransfer.Response;
import shared.dataTransfer.User;

import java.sql.SQLException;
import java.util.ArrayList;


public class InMemoryUser implements UserHome {

  private LoginDAOImpl loginDAO;

  public InMemoryUser() {
    this.loginDAO = new LoginDAOImpl();
  }

  public void tryClass()
  {
    try
    {
      Class.forName("org.postgresql.Driver");
      System.out.println("ok!!");
    }
    catch (Exception e)
    {
      System.out.println("Error loading Sql Driver!");
      e.printStackTrace();
    }
  }

  @Override public Response registerUser(User user) throws SQLException
  {
    tryClass();
    if (findUser(user.getUsername()))
    {
      System.out.println("already exists");
      return new Response(EventType.REGISTER_RESULT, "Username already exists");
    }
    else
    {
      loginDAO.create(user);
      System.out.println("register ok");
      return new Response(EventType.REGISTER_RESULT, "User registered");
    }

  }

  @Override public Response validateUser(User user) throws SQLException
  {
    tryClass();

    if (findUser(user.getUsername()))
    {

      if (loginDAO.checkPasswordByInput(user.getUsername(), user.getPassword()))
      {System.out.println("login ok");
        return new Response(EventType.LOGIN_RESULT, loginDAO.getTypeByName(user.getUsername()));
      }
      else
      {System.out.println("Wrong password");
        return new Response(EventType.LOGIN_RESULT, "Wrong password");
      }
    }
    else
    {System.out.println("No such a user found");
      return new Response(EventType.LOGIN_RESULT, "No such a user found");
    }

  }

  private boolean findUser(String username) throws SQLException
  {
    return loginDAO.checkNameByInput(username);
  }
}
