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


public class ExhibitionDAO {
	
	private Configuration cfg;
	private SessionFactory sf=null;
	private Session hs =null;
	private Transaction ts=null;
	private int pageCapacity = 3;
	
	/**
	 * 获取某个展会信息新闻的详细内容
	 * 
	 * @param exhibitionId 文章id
	 * @return Exhibition
	 */
    public Exhibition getExhibitionById(String exhibitionId)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            Exhibition e=hs.get(Exhibition.class,exhibitionId);

            SessionMgr.releaseConnect(sf, hs);
            return e;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }
	
	/**
	 * 获取所有的Exhibition文章
	 * 
	 * @return list
	 */
    public List getAllExhibition()
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            String getAllExhibitionHql="from Exhibition";
            Query getAllExhibitionQuery=hs.createQuery(getAllExhibitionHql);
            List allExhibitionList=getAllExhibitionQuery.list();

            SessionMgr.releaseConnect(sf, hs);

            return allExhibitionList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }
	
	/**
	 * 分页获取文章List，获取（Page-1）*pageCapacity -- page*pageCapacity 间的内容
	 * @param page 页数
	 * @return List
	 */
    public List getExhibitionByPage(int page)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            String getExhibitionHql="from Exhibition";
            Query getExhibitionQuery=hs.createQuery(getExhibitionHql);
            getExhibitionQuery.setFirstResult((page-1)*pageCapacity);
            getExhibitionQuery.setMaxResults(pageCapacity);
            List exhibitionList=getExhibitionQuery.list();

            SessionMgr.releaseConnect(sf, hs);

            return exhibitionList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }
	
	/**
	 * 获取某个用户发布的所有Exhibition
	 * @param userId
	 * @return
	 */
    public List getExhibitionByUserId(int userId)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            String getExhibitionHql="from Exhibition where userId=?";
            Query getExhibitionQuery=hs.createQuery(getExhibitionHql);
            getExhibitionQuery.setParameter(0,userId);
            List exhibitionList=getExhibitionQuery.list();

            SessionMgr.releaseConnect(sf, hs);

            return exhibitionList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }
	
	/**
	 * 模糊查询
	 * @param keyword 关键字
	 */
    public List getExhibitionByKeyword(String keyword)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            String getExhibitionHql="from Exhibition where theme in %?%";
            Query getExhibitionQuery=hs.createQuery(getExhibitionHql);
            getExhibitionQuery.setParameter(0,keyword);
            List exhibitionList=getExhibitionQuery.list();

            SessionMgr.releaseConnect(sf, hs);

            return exhibitionList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }
	
	/**
	 * 精确查询
	 * @param title 精确查询字段
	 * @return
	 */
    public List getExhibitionByTitle(String title)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            String getExhibitionHql="from Exhibition where theme=?";
            Query getExhibitionQuery=hs.createQuery(getExhibitionHql);
            getExhibitionQuery.setParameter(0,title);
            List exhibitionList=getExhibitionQuery.list();

            SessionMgr.releaseConnect(sf, hs);

            return exhibitionList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }

    public Boolean delExhibition(String exhibitionId)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            Exhibition toDel=(Exhibition)hs.get(Exhibition.class,exhibitionId);

            //删除可能的收藏
            String getCollectionHql="from Collection where originId=?";
            Query getCollectionQuery=hs.createQuery(getCollectionHql);
            getCollectionQuery.setParameter(0,toDel.getExId());
            List<Collection> collectionList=getCollectionQuery.list();
            for(Collection c:collectionList)
                hs.delete(c);

            //删除可能的图片
            String getImageHql="from Image where originId=?";
            Query getImageQuery=hs.createQuery(getImageHql);
            getImageQuery.setParameter(0,toDel.getExId());
            List<Image> imageList=getImageQuery.list();
            for(Image i:imageList)
                hs.delete(i);

            //最后删除展会
            hs.delete(toDel);

            SessionMgr.releaseConnect(sf, hs);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }

    public Exhibition addExhibition(Exhibition newExhibition)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            hs.save(newExhibition);

            SessionMgr.releaseConnect(sf, hs);

            return newExhibition;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }

    public boolean setAsPass(Exhibition validatedExhibition)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            validatedExhibition.setIsPass((byte)1);
            hs.save(validatedExhibition);

            SessionMgr.releaseConnect(sf, hs);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return false;
    }

	
}
