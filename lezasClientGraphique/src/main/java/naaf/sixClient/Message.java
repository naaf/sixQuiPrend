package naaf.sixClient;

public class Message
{

  private String login;
  private String msg;

  public String getLogin()
  {
    return login;
  }

  public void setLogin(String login)
  {
    this.login = login;
  }

  public String getMsg()
  {
    return msg;
  }

  public void setMsg(String msg)
  {
    this.msg = msg;
  }

  public Message()
  {}

  public Message(String login, String msg)
  {
    super();
    this.login = login;
    this.msg = msg;
  }

  @Override
  public String toString()
  {
    if (msg.isEmpty() || msg == "")
    {
      return "";
    }
    return login + " : " + msg + "\n";
  }

}
