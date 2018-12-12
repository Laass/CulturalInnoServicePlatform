package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import po.Order;

public class OrderDAO {

	private Configuration cfg;
	private SessionFactory sf=null;
	private Session hsession=null;
	private Transaction ts=null;
	private int maxEssayNum = 12;

	public Order addOrder(Order newOrder) {
		SessionMgr.getSession(cfg, sf, hsession, ts);
		try {

			//hsession.add(newOrder);
			hsession.save(newOrder);

            SessionMgr.releaseConnect(sf, hsession);

			return newOrder;
		}catch(Exception e) {
			e.printStackTrace();
//			SessionMgr.releaseConnect(sf, hsession);
			return null;
		}
	}
	
	public List getUserOrders(String userId) {
		SessionMgr.getSession(cfg, sf, hsession, ts);
		try {

			Query hquery = hsession.createQuery("from Order o where o.userId=?");
			hquery.setString(0,userId);
			List<Order> list = hquery.list();

            SessionMgr.releaseConnect(sf, hsession);

			return list;
		}catch(Exception e) {
			e.printStackTrace();
//			SessionMgr.releaseConnect(sf, hsession);
			return null;
		}
	}
	
	/**
	 * 分页获取List，获取（Page-1）*maxEssayNum -- page*maxEssayNum 间的内容
	 * @param page 页数
	 * @return List
	 */
	public List getOrdersByPage(int page) {
		SessionMgr.getSession(cfg, sf, hsession, ts);
		try {

//			Query hquery = hsession.createQuery("from Order o limit ?,?");
//			hquery.setInteger(0,(page-1)*maxEssayNum-1);
//			hquery.setInteger(1,maxEssayNum);
//          List<Order> list = hquery.list();
//          return list;

			Query hQuery=hsession.createQuery("from Order");
			hQuery.setFirstResult((page-1)*maxEssayNum);
			hQuery.setMaxResults(maxEssayNum);
			List<Order> orderList=hQuery.list();

            SessionMgr.releaseConnect(sf, hsession);
	
			return orderList;
			}catch(Exception e) {
			e.printStackTrace();
//			SessionMgr.releaseConnect(sf, hsession);
			return null;
		}
	}
	
}
