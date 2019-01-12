package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

import org.hibernate.query.Query;
import po.Collection;
import po.Image;
import po.News;

public class NewsDAO
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
     * 获取一条资讯
     *
     * @param newsId 资讯的id
     */
    public News getNewsById(String newsId) throws Exception
    {
        getSession();
        try
        {
            News news = hsession.get(News.class, newsId);

            releaseSession();
            return news;
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
     * @return
     */
    public List<News> getAllNews() throws Exception
    {
        getSession();
        try
        {
            Query q = hsession.createQuery("from News ");
            List<News> t = q.list();

            releaseSession();
            return t;
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
     * @param title
     * @return
     */
    public List<News> getNewsByTitle(String title) throws Exception
    {
        getSession();
        try
        {
            Query q = hsession.createQuery("from News where title = ?1");
            q.setParameter(1,title);
            List<News> t = q.list();

            releaseSession();
            return t;
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
     * @param keyword
     * @return
     */
    public List<News> getNewsByKeyword(String keyword) throws Exception
    {
        getSession();
        try
        {
            Query q = hsession.createQuery("from News where title like ?1");
            q.setParameter(1,'%'+keyword+'%');
            List<News> t = q.list();

            releaseSession();
            return t;
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
     * 分页获取资讯 获取news表中第(Page-1)*maxEssayNum -- page*maxEssayNum行的信息
     *
     * @param page 获取第几页的资讯
     * @return List 资讯列表
     */
    public List<News> getNewsByPage(int page) throws Exception
    {
        getSession();
        try
        {
            String sql = "from News";
            Query q = hsession.createQuery(sql);
            q.setFirstResult((page-1)*maxEssayNum);
            q.setMaxResults(maxEssayNum);
            List<News> t = q.list();

            releaseSession();
            return t;
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
     * 获取某个企业用户的全部资讯
     *
     * @param userId 企业用户Id
     * @return List 资讯列表
     */
    public List<News> getNewsByUserId(String userId) throws Exception
    {
        getSession();
        try
        {
            Query q = hsession.createQuery("from News where userId = ?1");
            q.setParameter(1,userId);
            List<News> t = q.list();

            releaseSession();
            return t;
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
     * 分页获取某个企业用户的全部资讯
     * @param userId
     * @param page
     * @return
     */
    public List<News> getUserNewsByPage(String userId,int page) throws Exception
    {
        getSession();
        try
        {
            Query q = hsession.createQuery("from News where userId = ?1");
            q.setParameter(1,userId);
            q.setFirstResult((page-1)*maxEssayNum);
            q.setMaxResults(maxEssayNum);
            List<News> t = q.list();

            releaseSession();
            return t;
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
     * @param newNews
     * @return
     */
    public News addNews(News newNews) throws Exception
    {
        getSession();
        try
        {
            hsession.save(newNews);
            releaseSession();
            return newNews;
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
     * @param newsId
     * @return
     */
    public Boolean delNews(String newsId) throws Exception
    {
        getSession();
        try
        {
            News news = hsession.get(News.class, newsId);

            //删除可能的图片
            Query getImagesQuery=hsession.createQuery("from Image where originId=?1");
            getImagesQuery.setParameter(1,news.getNewsId());
            List<Image> list=getImagesQuery.list();
            list.forEach(image -> hsession.delete(image));

            //删除可能的收藏
            Query getCollectionQuery=hsession.createQuery("from Collection coll where coll.id.originId = ?1");
            getCollectionQuery.setParameter(1,news.getNewsId());
            List<Collection> collList=getCollectionQuery.list();
            collList.forEach(indi->hsession.delete(indi));

            //最后删除资讯
            hsession.delete(news);

            releaseSession();
            return true;
        }
        catch (Exception e)
        {
            releaseSession();
            throw e;
//            return false;
        }
    }

    /**
     * 测试通过
     * @param validatedNews
     * @return
     */
    public Boolean setAsPass(News validatedNews) throws Exception
    {
        getSession();
        try
        {
//            Query q=hsession.createQuery("update News set isPass='1' where newsId=?1");
//            q.setParameter(1,validatedNews.getNewsId());
//            q.executeUpdate();
            validatedNews.setIsPass((byte)1);
            hsession.update(validatedNews);

            releaseSession();
            return true;
        }
        catch (Exception e)
        {
            releaseSession(hsession);
            throw e;
//            return false;
        }
    }

    /**
     * 测试通过
     * @param newsId
     * @return
     */
    public Boolean setAsPass(String newsId) throws Exception
    {
        getSession();
        try
        {
            Query q=hsession.createQuery("update News set isPass='1' where newsId=?1");
            q.setParameter(1,newsId);
            q.executeUpdate();

            releaseSession();
            return true;
        }
        catch (Exception e)
        {
            releaseSession(hsession);
            return false;
        }
    }

    public Boolean update(News n) throws Exception
    {
        getSession();
        try
        {
            hsession.update(n);
            releaseSession();
            return true;
        }
        catch(Exception e)
        {
            releaseSession(hsession);
            return false;
        }
    }
}
