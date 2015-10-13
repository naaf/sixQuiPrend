package com.naaf.sixQuiPrend.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.naaf.sixQuiPrend.i18n.I18n;
import com.naaf.sixQuiPrend.service.SixQuiprendService;

public class ServerLauncher
{
  private static final Logger logger = LogManager.getLogger(ServerLauncher.class);
  private static final ResourceBundle i18n = I18n.getI18n();
  public static SixQuiprendService ts = null;

  private ServerLauncher()
  {}

  public static SixQuiprendService getService()
  {
    return ts;
  }

  public static void main(String[] args)
  {
    try
    {
      ts = new SixQuiprendService();
      logger.info(i18n.getString("SRV_start"));
    }
    catch (RemoteException re)
    {
      re.printStackTrace();
    }
    try
    {
      Registry rg = LocateRegistry.createRegistry(7777);
      rg.bind("test", ts);
      String bindings[] = rg.list();
      System.out.println(bindings[0]);
    }
    catch (Exception e)
    {
      logger.error(i18n.getString("SRV_error_creation"), e);
    }

  }
}
