package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import po.Collection;

public class CollectionDAO {

    private Configuration cfg;
    private SessionFactory sf=null;
    private Session hsession=null;
    private Transaction ts=null;
    private int maxEssayNum = 12;


    public Boolean delFromCollection(String collectionId) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Collection coll  = hsession.get(Collection.class, collectionId);
            hsession.delete(coll);

            SessionMgr.releaseConnect(sf, hsession);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return false;
        }
    }

    public Collection addToCollection(Collection coll) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            hsession.save(coll);

            return coll;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }

    public List getUserCollectionByType(String userId, String colltype) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("from Collection  where Collection .originType = :colltype");
            List t = q.list();

            SessionMgr.releaseConnect(sf,hsession);
            return t;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }

    public List getCompareUserCollectionByType(String userId, String colltype) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("from Collection  where Collection .productType = :colltype");
            List t = q.list();

            SessionMgr.releaseConnect(sf,hsession);
            return t;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }

    /**
     * 获取一个用户的所有收藏
     * @param userId 用户的id
     * @return 用户的所有收藏列表
     */
    public List getUserCollection(String userId) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("from Collection  where Collection .userId = :userId");
            List t = q.list();

            SessionMgr.releaseConnect(sf,hsession);
            return t;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }

    /**
     * 分页获取某个用户的收藏 获取一页的内容 数量为(Page-1)*maxEssayNum -- page*maxEssayNum
     * @param page 获取第几页的内容
     * @return List 获取的结果
     */
    public List getCollectionByPage(String userId,int page) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("from Collection where userId=? and limit"+(page-1)*maxEssayNum+","+page*maxEssayNum);
            q.setParameter(0,userId);
            List t = q.list();

            SessionMgr.releaseConnect(sf, hsession);
            return t;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }
}
