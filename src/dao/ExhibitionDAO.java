package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import po.Collection;
import po.Exhibition;
import po.Image;


public class ExhibitionDAO
{
    private Session hs;
    private Transaction ts;
    private final int pageCapacity=2;

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
	 * 获取某个展会信息新闻的详细内容
	 * 
	 * @param exhibitionId 文章id
	 * @return Exhibition
	 */
    public Exhibition getExhibitionById(String exhibitionId) throws Exception
    {
        getSession();
        try
        {
            Exhibition e=hs.get(Exhibition.class,exhibitionId);

            releaseSession();
            return e;
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
	 * 获取所有的Exhibition文章
	 * 
	 * @return list
	 */
    public List<Exhibition> getAllExhibition() throws Exception
    {
        getSession();
        try
        {
            String getAllExhibitionHql="from Exhibition";
            Query getAllExhibitionQuery=hs.createQuery(getAllExhibitionHql);
            List<Exhibition> allExhibitionList=getAllExhibitionQuery.list();

            releaseSession();

            return allExhibitionList;
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
	 * 分页获取文章List，获取（Page-1）*pageCapacity -- page*pageCapacity 间的内容
	 * @param page 页数
	 * @return List
	 */
    public List<Exhibition> getExhibitionByPage(int page) throws Exception
    {
        getSession();
        try
        {
            String getExhibitionHql="from Exhibition";
            Query getExhibitionQuery=hs.createQuery(getExhibitionHql);
            getExhibitionQuery.setFirstResult((page-1)*pageCapacity);
            getExhibitionQuery.setMaxResults(pageCapacity);
            List<Exhibition> exhibitionList=getExhibitionQuery.list();

            releaseSession();

            return exhibitionList;
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
     * 分页获取某用户的展会 获取（Page-1）*pageCapacity -- page*pageCapacity 间的内容
     * @param page 页数
     * @return List
     */
    public List<Exhibition> getUserExhibitionByPage(String userId,int page) throws Exception
    {
        getSession();
        try
        {
            String getExhibitionHql="from Exhibition where userId=?1";
            Query getExhibitionQuery=hs.createQuery(getExhibitionHql);
            getExhibitionQuery.setParameter(1,userId);
            getExhibitionQuery.setFirstResult((page-1)*pageCapacity);
            getExhibitionQuery.setMaxResults(pageCapacity);
            List<Exhibition> exhibitionList=getExhibitionQuery.list();

            releaseSession();

            return exhibitionList;
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
	 * 获取某个用户发布的所有Exhibition
	 * @param userId
	 * @return
	 */
    public List<Exhibition> getExhibitionByUserId(String userId) throws Exception
    {
        getSession();
        try
        {
            String getExhibitionHql="from Exhibition where userId=?1";
            Query getExhibitionQuery=hs.createQuery(getExhibitionHql);
            getExhibitionQuery.setParameter(1,userId);
            List<Exhibition> exhibitionList=getExhibitionQuery.list();

            releaseSession();

            return exhibitionList;
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
	 * 根据关键字 在主题中模糊查询
	 * @param keyword 关键字
	 */
    public List<Exhibition> getExhibitionByKeyword(String keyword) throws Exception
    {
        getSession();
        try
        {
            String getExhibitionHql="from Exhibition where theme like ?1";
            Query getExhibitionQuery=hs.createQuery(getExhibitionHql);
            getExhibitionQuery.setParameter(1,'%'+keyword+'%');
            List<Exhibition> exhibitionList=getExhibitionQuery.list();

            releaseSession();

            return exhibitionList;
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
	 * 精确查询
	 * @param theme 精确查询字段
	 * @return
	 */
    public List<Exhibition> getExhibitionByTheme(String theme) throws Exception
    {
        getSession();
        try
        {
            String getExhibitionHql="from Exhibition where theme=?1";
            Query getExhibitionQuery=hs.createQuery(getExhibitionHql);
            getExhibitionQuery.setParameter(1,theme);
            List<Exhibition> exhibitionList=getExhibitionQuery.list();

            releaseSession();

            return exhibitionList;
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
     * @param exhibitionId
     * @return
     */
    public Boolean delExhibition(String exhibitionId) throws Exception
    {
        getSession();
        try
        {
            Exhibition toDel=(Exhibition)hs.get(Exhibition.class,exhibitionId);

            //删除可能的收藏
            String getCollectionHql="from Collection where originId=?1";
            Query getCollectionQuery=hs.createQuery(getCollectionHql);
            getCollectionQuery.setParameter(1,toDel.getExId());
            List<Collection> collectionList=getCollectionQuery.list();
            for(Collection c:collectionList)
                hs.delete(c);

            //删除可能的图片
            String getImageHql="from Image where originId=?1";
            Query getImageQuery=hs.createQuery(getImageHql);
            getImageQuery.setParameter(1,toDel.getExId());
            List<Image> imageList=getImageQuery.list();
            for(Image i:imageList)
                hs.delete(i);

            //最后删除展会
            hs.delete(toDel);

            releaseSession();

            return true;
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
     * @param newExhibition
     * @return
     */
    public Exhibition addExhibition(Exhibition newExhibition) throws Exception
    {
        getSession();
        try
        {
            hs.save(newExhibition);

            releaseSession();

            return newExhibition;
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
     * @param validatedExhibition
     * @return
     */
    public boolean setAsPass(Exhibition validatedExhibition) throws Exception
    {
        try
        {
            validatedExhibition=getExhibitionById(validatedExhibition.getExId());
            validatedExhibition.setIsPass((byte)1);
            getSession();
            hs.update(validatedExhibition);

            releaseSession();

            return true;
        }
        catch (Exception e)
        {
            releaseSession(hs);
            throw e;
        }
//        return false;
    }

    public boolean update(Exhibition e) throws Exception
    {
        getSession();
        try
        {
            hs.update(e);
            releaseSession();
            return true;
        }
        catch(Exception exc)
        {
            releaseSession(hs);
            throw exc;
        }
    }
	
}
