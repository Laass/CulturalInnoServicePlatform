package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import po.Collection;

public class CollectionDAO
{

    private Session hs;
    private Transaction ts;
    private final int maxEssayNum = 2;

    private void getSession()
    {
        //从SessionMgr获取Session和Transaction
        Object[] connectionList = SessionMgr.getSession();
        hs = (Session) connectionList[0];
        ts = (Transaction) connectionList[1];
    }

    private void releaseSession()
    {
        SessionMgr.releaseConnect(hs, ts);
    }

    private void releaseSession(Session hs)
    {
        SessionMgr.releaseConnect(hs);
    }


    /**
     * 测试通过
     * @param userId
     * @param originId
     * @return
     */
    public Boolean delFromCollection(String userId,String originId) throws Exception
    {
        getSession();
        try
        {
            String getOneHql="from Collection where userId=?1 and originId=?2";
            Query getOneQuery=hs.createQuery(getOneHql);
            getOneQuery.setParameter(1,userId);
            getOneQuery.setParameter(2,originId);
            List<Collection> list=getOneQuery.list();
            if(list.size()==1)
            {
                hs.delete(list.get(0));

                releaseSession();
                return true;
            }
            else
            {
                releaseSession();
                return false;
            }
        }
        catch (Exception e)
        {
            releaseSession(hs);
            throw e;
//            return false;
        }
    }

    /**
     * 测试通过
     * @param coll
     * @return
     */
    public Collection addToCollection(Collection coll) throws Exception
    {
        getSession();
        try
        {
            hs.save(coll);

            releaseSession();

            return coll;
        }
        catch (Exception e)
        {
            releaseSession(hs);

            throw e;
//            return null;
        }
    }

//    public List getUserCollectionByType(String userId, String colltype)
//    {
//        getSession();
//        try
//        {
//            Query q = hs.createQuery("from Collection  where Collection .originType = :colltype");
//            List t = q.list();
//
//            releaseSession();
//            return t;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            releaseSession();
//            return null;
//        }
//    }

    /**
     * 测试通过
     * 获取一个用户的某种收藏
     * @param userId
     * @param colltype
     * @return
     */
    public List<Collection> getUserCollectionByType(String userId, String colltype) throws Exception
    {
        getSession();
        try
        {
            Query q = hs.createQuery("from Collection where userId = ?1 and productType = ?2");
            q.setParameter(1,userId);
            q.setParameter(2,colltype);
            List<Collection> t = q.list();

            releaseSession();
            return t;
        }
        catch (Exception e)
        {
            releaseSession(hs);
            throw e;
//            return null;
        }
    }

    /**
     * 测试通过
     * 获取一个用户的所有收藏
     *
     * @param userId 用户的id
     * @return 用户的所有收藏列表
     */
    public List<Collection> getUserCollection(String userId) throws Exception
    {
        getSession();
        try
        {
            Query q = hs.createQuery("from Collection coll where coll.id.userId = "+userId);
//            q.setParameter(1,"userId"); //不知道为什么用这种方法不行
            List<Collection> t = q.list();

            releaseSession();
            return t;
        }
        catch (Exception e)
        {
            releaseSession(hs);
            throw e;
//            return null;
        }
    }

    /**
     * 测试通过
     * 分页获取某个用户的收藏 获取一页的内容 数量为(Page-1)*maxEssayNum -- page*maxEssayNum
     *
     * @param page 获取第几页的内容
     * @return List 获取的结果
     */
    public List<Collection> getCollectionByPage(String userId, int page) throws Exception
    {
        getSession();
        try
        {
            Query q = hs.createQuery("from Collection coll where coll.id.userId="+userId);
//            q.setParameter(1, userId);
            q.setFirstResult((page-1)*maxEssayNum);
            q.setMaxResults(maxEssayNum);
            List<Collection> t = q.list();

            releaseSession();
            return t;
        }
        catch (Exception e)
        {
            releaseSession(hs);
            throw e;
//            return null;
        }
    }
}
