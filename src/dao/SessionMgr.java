package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SessionMgr
{
    //SessionFactory只在这个类内部使用 所以设成类的属性
    private static SessionFactory sf;

	public static Object[] getSession()
	{
		try
		{
		    //获取连接的准备工作
            Configuration cfg = new Configuration().configure();
            sf = cfg.buildSessionFactory();

            //获取连接 将连接放在一个Object[]中 传给DAO
			Object[] connectionList=new Object[2];
			connectionList[0]=sf.openSession();
			connectionList[1]=((Session)connectionList[0]).beginTransaction();

			return connectionList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static void releaseConnect(Session hs,Transaction ts)
	{
	    //关闭连接
	    ts.commit();
	    hs.clear();
		if (hs.isOpen())
			hs.close();
		sf.close();
	}

    public static void releaseConnect(Session hs)
    {
		if (hs.isOpen())
			hs.close();
    }
}
