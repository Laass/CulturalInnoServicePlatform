package dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import po.*;

public class UserDAO
{

    private Configuration cfg;
    private SessionFactory sf = null;
    private Session hs = null;
    private Transaction ts = null;

    public Boolean addUser(User newUser)
    {
        try
        {
            SessionMgr.getSession(cfg, sf, hs, ts);

            hs.save(newUser);

            SessionMgr.releaseConnect(sf, hs);
            return true;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return false;
    }

    public Boolean delUser(String userId)
    {
        try
        {
            SessionMgr.getSession(cfg, sf, hs, ts);

            User toDel = (User) hs.get(User.class, userId);
            //删除头像
            String getPortraitHql="from Image where originId=?";
            Query getPortraitQuery=hs.createQuery(getPortraitHql);
            getPortraitQuery.setParameter(0,toDel.getUserId());
            List<Image> portraitList=getPortraitQuery.list();
            for(Image i:portraitList)
                hs.delete(i);

            //删除详细信息
            UserInfo infoToDel=(UserInfo)hs.get(UserInfo.class,toDel.getUserId());
            hs.delete(infoToDel);

            //最后删除用户信息
            hs.delete(toDel);

            SessionMgr.releaseConnect(sf, hs);
            return true;
        }
        catch (HibernateException he)
        {
            he.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return false;
    }

    public User getUser(String userId)
    {
        try
        {
            SessionMgr.getSession(cfg, sf, hs, ts);

            User user=hs.get(User.class,userId);

            SessionMgr.releaseConnect(sf, hs);
            return user;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }

    public List getAllUser()
    {
        try
        {
            SessionMgr.getSession(cfg, sf, hs, ts);

            String getAllUserHql="from User";
            Query getAllUserQuery=hs.createQuery(getAllUserHql);
            List allUserList=getAllUserQuery.list();

            SessionMgr.releaseConnect(sf, hs);
            return allUserList;
        }
        catch (HibernateException he)
        {
            he.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }

    public Boolean purchaseProduct(String userId, String productId, int num)
    {
        try
        {
            SessionMgr.getSession(cfg, sf, hs, ts);

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
            SessionMgr.releaseConnect(sf, hs);
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return false;
    }

    public boolean validateUser(String userId, String password)
    {
        try
        {
            SessionMgr.getSession(cfg, sf, hs, ts);

            User registeredUser=(User)hs.get(User.class,userId);

            SessionMgr.releaseConnect(sf, hs);

            if(registeredUser!=null&&registeredUser.getPassword().equals(password))
                return true;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return false;
    }

}
