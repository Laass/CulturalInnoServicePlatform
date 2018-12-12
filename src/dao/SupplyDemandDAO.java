package dao;

import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import po.Collection;
import po.Image;
import po.SupplyDemand;

public class SupplyDemandDAO
{
    private Configuration cfg;
    private SessionFactory sf=null;

    private Session hs;
    private Transaction ts;
    private final int pageCapacity = 3;

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



	public Supply addSupply(Supply newSupply)
	{
        getSession();
		try
		{
            SupplyDemand newSD=newSupply.toSupplyDemand();
            hs.save(newSD);

            releaseSession();
			return newSupply;
		}
		catch (Exception e)
		{
			e.printStackTrace();
            releaseSession();
		}
        return null;
	}

    public Demand addDemand(Demand newDemand)
    {
        getSession();
        try
        {
            SupplyDemand newSD=newDemand.toSupplyDemand();
            hs.save(newSD);

            releaseSession();
            return newDemand;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            releaseSession();
        }
        return null;
    }

//    //上面两个函数可以合成这一个函数
//    public SupplyDemand addSD(SupplyDemand newSD)
//    {
//        getSession();
//        try
//        {
//            hs.save(newSD);
//
//            releaseSession();
//            return newSD;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            releaseSession();
//        }
//        return null;
//    }

//    public Boolean delSupply(String supplyId)
//    {
//        SessionMgr.getSession(cfg, sf, hs, ts);
//        try
//        {
//            SupplyDemand toDel=(SupplyDemand)hs.get(SupplyDemand.class,supplyId);
//
//            //删除可能的收藏
////            String getCollectionHql="from Collection where originId=?";
////            Query getCollectionQuery=hs.createQuery(getCollectionHql);
////            getCollectionQuery.setParameter(0,toDel.getSdId());
////            List<Collection> collectionList=getCollectionQuery.list();
////            for(Collection c:collectionList)
////                hs.delete(c);
//
//            //删除可能的图片
////            String getImageHql="from Image where originId=?";
////            Query getImageQuery=hs.createQuery(getImageHql);
////            getImageQuery.setParameter(0,toDel.getSdId());
////            List<Image> imageList=getImageQuery.list();
////            for(Image i:imageList)
////                hs.delete(i);
//
//            hs.delete(toDel);
//
//            SessionMgr.releaseConnect(sf, hs);
//            return true;
//        }
//        catch(HibernateException he)
//        {
//            he.printStackTrace();
//        }
//        return false;
//    }
//
//    public Boolean delDemand(String demandId)
//    {
//        SessionMgr.getSession(cfg, sf, hs, ts);
//        try
//        {
//            SupplyDemand toDel=(SupplyDemand)hs.get(SupplyDemand.class,demandId);
//
//            //删除可能的收藏
////            String getCollectionHql="from Collection where originId=?";
////            Query getCollectionQuery=hs.createQuery(getCollectionHql);
////            getCollectionQuery.setParameter(0,toDel.getSdId());
////            List<Collection> collectionList=getCollectionQuery.list();
////            for(Collection c:collectionList)
////                hs.delete(c);
//
//            //删除可能的图片
////            String getImageHql="from Image where originId=?";
////            Query getImageQuery=hs.createQuery(getImageHql);
////            getImageQuery.setParameter(0,toDel.getSdId());
////            List<Image> imageList=getImageQuery.list();
////            for(Image i:imageList)
////                hs.delete(i);
//
//            hs.delete(toDel);
//
//            SessionMgr.releaseConnect(sf, hs);
//            return true;
//        }
//        catch(HibernateException he)
//        {
//            he.printStackTrace();
//        }
//        return false;
//    }

    //上面两个函数是否可以合成一个函数
    public Boolean delSupplyDemand(String Id)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            SupplyDemand toDel=(SupplyDemand)hs.get(SupplyDemand.class,Id);
            //删除可能的收藏
//            String getCollectionHql="from Collection where originId=?";
//            Query getCollectionQuery=hs.createQuery(getCollectionHql);
//            getCollectionQuery.setParameter(0,toDel.getSdId());
//            List<Collection> collectionList=getCollectionQuery.list();
//            for(Collection c:collectionList)
//                hs.delete(c);

            //删除可能的图片
            String getImageHql="from Image where originId=?";
            Query getImageQuery=hs.createQuery(getImageHql);
            getImageQuery.setParameter(0,toDel.getSdId());
            List<Image> imageList=getImageQuery.list();
            for(Image i:imageList)
                hs.delete(i);

            //最后删除SupplyDemand
            hs.delete(toDel);

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
	
	/**
	 * 精确查找
	 * @param supplyTitle 关键字
	 * @return
	 */
    public List getSupplybyTitle(String supplyTitle)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            String getSupplyHql="from SupplyDemand where type=? and title=?";
            Query getSupplyQuery=hs.createQuery(getSupplyHql);
            getSupplyQuery.setParameter(0,"S");
            getSupplyQuery.setParameter(1,supplyTitle);
            List supplyList=getSupplyQuery.list();

            SessionMgr.releaseConnect(sf, hs);

            return supplyList;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }

    //为什么返回list 按SupplyDemand的id查找 应该返回Supply？
    public Supply getSupplyById(String supplyId)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            Supply s=(Supply)hs.get(SupplyDemand.class,supplyId);

            SessionMgr.releaseConnect(sf, hs);

            return s;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }
	
	/**
	 * 模糊查找
	 * @param keyword
	 * @return
	 */
    public List getSuppliesByKeyword(String keyword)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            String getSupplyHql="from SupplyDemand where type=? and title like %?%";
            Query getSupplyQuery=hs.createQuery(getSupplyHql);
            getSupplyQuery.setParameter(0,"S");
            getSupplyQuery.setParameter(1,keyword);
            List supplyList=getSupplyQuery.list();

            SessionMgr.releaseConnect(sf, hs);

            return supplyList;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }

    public List getAllSupplies()
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        SessionMgr.releaseConnect(sf, hs);
        return null;
    }
	
	/**
	 * 分页获取List，获取（Page-1）*pageCapacity -- page*pageCapacity 间的内容
	 * @param page 页数
	 * @return List
	 */
    public List getSuppliesByPage(int page)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            String getSupplyHql="from SupplyDemand where type=?";
            Query getSupplyQuery=hs.createQuery(getSupplyHql);
            getSupplyQuery.setParameter(0,"S");
            getSupplyQuery.setFirstResult((page-1)*pageCapacity);
            getSupplyQuery.setMaxResults(pageCapacity);
            List supplyList=getSupplyQuery.list();

            SessionMgr.releaseConnect(sf, hs);

            return supplyList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }
	
	/**
	 * 获取某个用户发布的所有supply
	 * @param userId 用户Id
	 * @return List
	 */
    public List getUserSupplies(String userId)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            String getSuppliesHql="from SupplyDemand where userId=?";
            Query getSuppliesQuery=hs.createQuery(getSuppliesHql);
            getSuppliesQuery.setParameter(0,userId);
            List supplyList=getSuppliesQuery.list();

            SessionMgr.releaseConnect(sf, hs);

            return supplyList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }

	
	/**
	 * 精确查找
	 * @param demandTitle 要查找的需求的标题
	 * @return 按标题精确查找到的需求列表
	 */
	public List getDemandByTitle(String demandTitle) {
		SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            String getDemandHql="from SupplyDemand where type=? and title=?";
            Query getDemandQuery=hs.createQuery(getDemandHql);
            getDemandQuery.setParameter(0,"D");
            getDemandQuery.setParameter(1,demandTitle);
            List demandList=getDemandQuery.list();

            SessionMgr.releaseConnect(sf, hs);

            return demandList;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
	}

	//为什么要返回list 问题同上
    public Demand getDemandById(String demandId)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            Demand d=(Demand)hs.get(SupplyDemand.class,demandId);

            SessionMgr.releaseConnect(sf, hs);

            return d;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }
	
	/**
	 * 模糊查找
	 * @param keyword 查找的关键字
	 * @return 标题中含有关键字的需求列表
	 */
    public List getDemandsByKeyWord(String keyword)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            String getDemandHql="from SupplyDemand where type=? and title like %?%";
            Query getDemandQuery=hs.createQuery(getDemandHql);
            getDemandQuery.setParameter(0,"D");
            getDemandQuery.setParameter(1,keyword);
            List demandList=getDemandQuery.list();

            SessionMgr.releaseConnect(sf, hs);

            return demandList;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }
	
	public List getAllDemands() {
		SessionMgr.getSession(cfg, sf, hs, ts);
		SessionMgr.releaseConnect(sf, hs);
		return null;
	}
	
	/**
	 * 分页获取List，获取（Page-1）*pageCapacity -- page*pageCapacity 间的内容
     * 也就是获取第page页的内容
	 * @param page 页数
	 * @return List
	 */
    public List getDemandsByPage(int page)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            String getDemandHql="from SupplyDemand where type=?";
            Query getDemandQuery=hs.createQuery(getDemandHql);
            getDemandQuery.setParameter(0,"D");
            getDemandQuery.setFirstResult((page-1)*pageCapacity);
            getDemandQuery.setMaxResults(pageCapacity);
            List demandList=getDemandQuery.list();

            SessionMgr.releaseConnect(sf, hs);

            return demandList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }
	
	/**
	 * 获取某个用户发布的所有demand
	 * @param userId 用户Id
	 * @return List
	 */
    public List getUserDemands(String userId)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            String getDemandsHql="from SupplyDemand where userId=?";
            Query getDemandsQuery=hs.createQuery(getDemandsHql);
            getDemandsQuery.setParameter(0,userId);
            List demandList=getDemandsQuery.list();

            SessionMgr.releaseConnect(sf, hs);

            return demandList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }

//    public Boolean setAsPass(Supply validatedSupply)
//    {
//        SessionMgr.getSession(cfg, sf, hs, ts);
//        try
//        {
//            validatedSupply.setIsPass((byte)1);
//            hs.update(validatedSupply);
//
//            SessionMgr.releaseConnect(sf, hs);
//
//            return true;
//        }
//        catch (HibernateException he)
//        {
//            he.printStackTrace();
//        }
//        return false;
//    }
//
//    public Boolean setAsPass(Demand validatedDemand)
//    {
//        SessionMgr.getSession(cfg, sf, hs, ts);
//        try
//        {
//            validatedDemand.setIsPass((byte)1);
//            hs.update(validatedDemand);
//
//            SessionMgr.releaseConnect(sf, hs);
//            return true;
//        }
//        catch(HibernateException he)
//        {
//            he.printStackTrace();
//        }
//        return null;
//    }

    //是否可以替换上面两个函数？
    public Boolean setAsPass(SupplyDemand validatedSD)
    {
        SessionMgr.getSession(cfg, sf, hs, ts);
        try
        {
            validatedSD.setIsPass((byte)1);
            hs.update(validatedSD);

            SessionMgr.releaseConnect(sf, hs);
            return true;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            SessionMgr.releaseConnect(sf, hs);
        }
        return null;
    }
}
