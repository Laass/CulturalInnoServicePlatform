package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

import org.hibernate.query.Query;
import po.News;

public class NewsDAO {

    private Configuration cfg;
    private SessionFactory sf=null;
    private Session hsession=null;
    private Transaction ts=null;
    private int maxEssayNum = 12;

    /**
     * ��ȡıƪNew����ϸ����
     * @param newsId New��ID
     */
    public News getNewsById(String newsId) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try{
            News news = hsession.get(News.class, newsId);

            SessionMgr.releaseConnect(sf,hsession);
            return news;
        }catch (Exception e) {
           e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }

    public List getAllNews() {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("from News ");
            List t = q.list();

            SessionMgr.releaseConnect(sf, hsession);
            return t;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }

    public List getNewsByTitle(String title) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("from Product where News.title = :title");
            List t = q.list();

            SessionMgr.releaseConnect(sf, hsession);
            return t;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }

    public List getNewsByKeyword(String keyword) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("from Product where News.title = '%'"+keyword+"'%'");
            List t = q.list();

            SessionMgr.releaseConnect(sf, hsession);
            return t;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }

    /**
     * ��ҳ��ȡ����List����ȡ��Page-1��*maxEssayNum -- page*maxEssayNum �������
     * @param page ҳ��
     * @return List
     */
    public List getNewsByPage(int page) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            String sql = "select * from News limit"+(page-1)*maxEssayNum+","+page*maxEssayNum;
            Query q = hsession.createQuery(sql);
            List t = q.list();

            SessionMgr.releaseConnect(sf, hsession);
            return t;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }

    /**
     * ��ȡĳ���û�����������News
     * @param userId 用户Id
     * @return List
     */
    public List getNewsByUserId(int userId) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("from News where News .userId = :userId");
            List t =  q.list();

            SessionMgr.releaseConnect(sf, hsession);
            return t;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }

    public News addNews(News newNews) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            hsession.save(newNews);
            SessionMgr.releaseConnect(sf, hsession);
            return newNews;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
        }
        return null;
    }

    public Boolean delNews(String newsId) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            News news  = hsession.get(News.class,newsId);
            hsession.delete(news);

            SessionMgr.releaseConnect(sf, hsession);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return false;
        }
    }

    public Boolean setAsPass(News validatedNews) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try{
            hsession.createQuery("update News set News .isPass='1'");

            SessionMgr.releaseConnect(sf, hsession);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return false;
        }
    }
}
