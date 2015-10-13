package com.naaf.sixQuiPrend.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.naaf.sixQuiPrend.ihm.SixQuiPrendConsole;
import com.naaf.sixQuiPrend.service.SixQuiPrendServiceIF;

public class ServerConnect
{

  public static void main(String[] args)
  {
    SixQuiPrendServiceIF serviceSixQuiPrend = null;
    try
    {
      Registry rg = LocateRegistry.getRegistry(7777);
      serviceSixQuiPrend = (SixQuiPrendServiceIF) rg.lookup("test");

      SixQuiPrendConsole cons = new SixQuiPrendConsole(serviceSixQuiPrend);
      cons.start();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

}
