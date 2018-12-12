package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SessionMgr
{

	public static void getSession(Configuration cfg, SessionFactory sf, Session hsession, Transaction ts)
	{
		try
		{
			cfg = new Configuration().configure();
			sf = cfg.buildSessionFactory();
			hsession = sf.openSession();
			ts = hsession.beginTransaction();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void releaseConnect(SessionFactory sf, Session hsession)
	{
		if (hsession.isOpen())
			hsession.close();
		sf.close();
	}
}
