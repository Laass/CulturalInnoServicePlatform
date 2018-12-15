package dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.*;
import org.hibernate.query.Query;
import po.*;

public class UserDAO
{
    private Session hs =null;
    private Transaction ts=null;

    private void getSession()
    {
        //从SessionMgr获取Session和Transaction
        Object[] connectionList=SessionMgr.getSession();
        hs =(Session)connectionList[0];
        ts=(Transaction)connectionList[1];
    }

    private void releaseSession()
    {
        SessionMgr.releaseConnect(hs,ts);
    }

    private void releaseSession(Session hs)
    {
        SessionMgr.releaseConnect(hs);
    }


    /**
     * 测试通过
     * @param newUser
     * @return
     */
    public Boolean addUser(User newUser) throws Exception
    {
        try
        {
            getSession();

            hs.save(newUser);

            releaseSession();
            return true;
        }
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
//        return false;
    }

    /**
     * 测试通过
     * @param userId
     * @return
     */
    public Boolean delUser(String userId)  throws Exception
    {
        try
        {
           getSession();

            User toDel = (User) hs.get(User.class, userId);
            //删除头像
            String getPortraitHql="from Image where originId=?1";
            Query getPortraitQuery=hs.createQuery(getPortraitHql);
            getPortraitQuery.setParameter(1,toDel.getUserId());
            List<Image> portraitList=getPortraitQuery.list();
            for(Image i:portraitList)
                hs.delete(i);

            //删除详细信息
            UserInfo infoToDel=(UserInfo)hs.get(UserInfo.class,toDel.getUserId());
            if(infoToDel!=null)
                hs.delete(infoToDel);

            //最后删除用户信息
            hs.delete(toDel);

            releaseSession();
            return true;
        }
        catch (Exception e)
        {
            releaseSession(hs);
            throw e;
        }
        //return false;
    }

    /**
     * 测试通过
     * @param userId
     * @return
     */
    public User getUser(String userId)  throws Exception
    {
        try
        {
           getSession();

            User user=hs.get(User.class,userId);

            releaseSession();
            return user;
        }
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
        //return null;
    }

    /**
     * 测试通过
     * @return
     */
    public List<User> getAllUser()  throws Exception
    {
        try
        {
           getSession();

            String getAllUserHql="from User";
            Query getAllUserQuery=hs.createQuery(getAllUserHql);
            List<User> allUserList=getAllUserQuery.list();

            releaseSession();
            return allUserList;
        }
        catch (Exception e)
        {
           releaseSession(hs);
           throw e;
        }
//        return null;
    }

    /**
     * 测试通过
     * @param userId
     * @param productId
     * @param num
     * @return
     */
    public Boolean purchaseProduct(String userId, String productId, int num)  throws Exception
    {
        try
        {
           getSession();

            //创建新订单
            Order newOrder=new Order();
            newOrder.setOrderId(UUID.randomUUID().toString().replace("-",""));
            newOrder.setUserId(userId);
            newOrder.setProId(productId);
            newOrder.setEstablishTime(new Timestamp(new Date().getTime()));
            newOrder.setCount(num);

            hs.save(newOrder);

            //增加产品的购买数量
            Product hasBought=(Product)hs.get(Product.class,productId);
            hasBought.setPurchase(hasBought.getPurchase()+num);

            hs.save(hasBought);
            releaseSession();
        }
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
        return false;
    }

    /**
     * 测试通过
     * @param userId
     * @param password
     * @return
     */
    public boolean validateUser(String userId, String password)  throws Exception
    {
        try
        {
            getSession();

            User registeredUser=(User)hs.get(User.class,userId);

            releaseSession();

            if(registeredUser!=null&&registeredUser.getPassword().equals(password))
                return true;
        }
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
        return false;
    }

}
