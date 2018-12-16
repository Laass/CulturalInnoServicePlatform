package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import po.Order;

public class OrderDAO
{
    private Session hsession;
    private Transaction ts;
    private int maxEssayNum = 2;

    private void getSession()
    {
        //从SessionMgr获取Session和Transaction
        Object[] connectionList = SessionMgr.getSession();
        hsession = (Session) connectionList[0];
        ts = (Transaction) connectionList[1];
    }

    private void releaseSession()
    {
        SessionMgr.releaseConnect(hsession, ts);
    }

    private void releaseSession(Session hsession)
    {
        SessionMgr.releaseConnect(hsession);
    }

    /**
     * 测试通过
     * @param newOrder
     * @return
     */
    public Order addOrder(Order newOrder) throws Exception
    {
        getSession();
        try
        {

            //hsession.add(newOrder);
            hsession.save(newOrder);

            releaseSession();

            return newOrder;
        }
        catch (Exception e)
        {
            releaseSession(hsession);
            throw e;
        }
//        return null;
    }

    /**
     * 测试通过
     * 获取用户的所有订单
     * @param userId
     * @return
     */
    public List<Order> getUserOrders(String userId) throws Exception
    {
        getSession();
        try
        {

            Query hquery = hsession.createQuery("from Order o where o.userId=?1");
            hquery.setParameter(1, userId);
            List<Order> list = hquery.list();

            releaseSession();

            return list;
        }
        catch (Exception e)
        {
            releaseSession(hsession);
            throw e;
//            return null;
        }
    }

    /**
     * 测试通过
     * 分页获取用户所有订单
     * @param userId
     * @param page
     * @return
     */
    public List<Order> getUserOrdersByPage(String userId,int page) throws Exception
    {
        getSession();
        try
        {

            Query hquery = hsession.createQuery("from Order o where o.userId=?1");
            hquery.setParameter(1, userId);
            hquery.setFirstResult((page-1)*maxEssayNum);
            hquery.setMaxResults(maxEssayNum);
            List<Order> list = hquery.list();

            releaseSession();

            return list;
        }
        catch (Exception e)
        {
            releaseSession(hsession);
            throw e;
//            return null;
        }
    }

    /**
     * 测试通过
     * 分页获取List，获取（Page-1）*maxEssayNum -- page*maxEssayNum 间的内容
     *
     * @param page 页数
     * @return List
     */
    public List getOrdersByPage(int page) throws Exception
    {
       getSession();
        try
        {

//			Query hquery = hsession.createQuery("from Order o limit ?,?");
//			hquery.setInteger(0,(page-1)*maxEssayNum-1);
//			hquery.setInteger(1,maxEssayNum);
//          List<Order> list = hquery.list();
//          return list;

            Query hQuery = hsession.createQuery("from Order");
            hQuery.setFirstResult((page - 1) * maxEssayNum);
            hQuery.setMaxResults(maxEssayNum);
            List<Order> orderList = hQuery.list();

            releaseSession();

            return orderList;
        }
        catch (Exception e)
        {
			releaseSession(hsession);
			throw e;
//            return null;
        }
    }

    public Order getOrderById(String orderId) throws Exception
    {
        getSession();
        try
        {
            Order o=(Order)hsession.get(Order.class,orderId);
            releaseSession();
            return o;
        }
        catch (Exception e)
        {
            releaseSession(hsession);
            throw e;
        }
    }
}
