package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import po.Collection;
import po.Image;
import po.SupplyDemand;

public class SupplyDemandDAO
{
    private Session hs;
    private Transaction ts;
    private final int pageCapacity = 2;

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


    /**
     * 测试通过
     * @param newSupply
     * @return
     */
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
		}
        return null;
	}

    /**
     * 测试通过
     * @param newDemand
     * @return
     */
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
//        getSession();
//        try
//        {
//            SupplyDemand toDel=(SupplyDemand)hs.get(SupplyDemand.class,supplyId);
//
////            可能被收藏 从收藏中删除
//            String getCollectionHql="from Collection where originId=?";
//            Query getCollectionQuery=hs.createQuery(getCollectionHql);
//            getCollectionQuery.setParameter(0,toDel.getSdId());
//            List<Collection> collectionList=getCollectionQuery.list();
//            for(Collection c:collectionList)
//                hs.delete(c);
//
////            删除可能的图片
//            String getImageHql="from Image where originId=?";
//            Query getImageQuery=hs.createQuery(getImageHql);
//            getImageQuery.setParameter(0,toDel.getSdId());
//            List<Image> imageList=getImageQuery.list();
//            for(Image i:imageList)
//                hs.delete(i);
//
//            hs.delete(toDel);
//
//            releaseSession();
//            return true;
//        }
//        catch(HibernateException he)
//        {
//            he.printStackTrace();
//        }
//        return false;
//    }

//    public Boolean delDemand(String demandId)
//    {
//        getSession();
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
//            releaseSession();
//            return true;
//        }
//        catch(HibernateException he)
//        {
//            he.printStackTrace();
//        }
//        return false;
//    }

    /**
     * 测试通过
     * 将上面两个函数合成一个函数
     * @param Id
     * @return
     */
    public Boolean delSupplyDemand(String Id)
    {
        getSession();
        try
        {
            SupplyDemand toDel=(SupplyDemand)hs.get(SupplyDemand.class,Id);
            //删除可能的收藏
            String getCollectionHql="from Collection where originId=?1";
            Query getCollectionQuery=hs.createQuery(getCollectionHql);
            getCollectionQuery.setParameter(1,toDel.getSdId());
            List<Collection> collectionList=getCollectionQuery.list();
            for(Collection c:collectionList)
                hs.delete(c);

            //删除可能的图片
            String getImageHql="from Image where originId=?1";
            Query getImageQuery=hs.createQuery(getImageHql);
            getImageQuery.setParameter(1,toDel.getSdId());
            List<Image> imageList=getImageQuery.list();
            for(Image i:imageList)
                hs.delete(i);

            //最后删除SupplyDemand
            hs.delete(toDel);

            releaseSession();
            return true;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
        }
        return false;
    }
	
	/**
     * 测试通过
	 * 精确查找
	 * @param supplyTitle 关键字
	 * @return
	 */
    public List getSupplyByTitle(String supplyTitle)
    {
        getSession();
        try
        {
            String getSupplyHql="from SupplyDemand where type=?1 and title=?2";
            Query getSupplyQuery=hs.createQuery(getSupplyHql);
            getSupplyQuery.setParameter(1,"S");
            getSupplyQuery.setParameter(2,supplyTitle);
            List supplyList=getSupplyQuery.list();

            releaseSession();

            return supplyList;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
        }
        return null;
    }

    /**
     * 测试通过
     * @param supplyId
     * @return
     */
    public Supply getSupplyById(String supplyId)
    {
        getSession();
        try
        {
            SupplyDemand sd=(SupplyDemand) hs.get(SupplyDemand.class,supplyId);

            releaseSession();

            return new Supply(sd);
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
        }
        return null;
    }
	
	/**
     * 测试通过
	 * 模糊查找
	 * @param keyword
	 * @return
	 */
    public List getSuppliesByKeyword(String keyword)
    {
        getSession();
        try
        {
            String getSupplyHql="from SupplyDemand where type=?1 and title like ?2";
            Query getSupplyQuery=hs.createQuery(getSupplyHql);
            getSupplyQuery.setParameter(1,"S");
            getSupplyQuery.setParameter(2,"%"+keyword+"%");
            List<SupplyDemand> sdList=getSupplyQuery.list();
            List<Supply> supplyList=new ArrayList<>();
            for(SupplyDemand sd:sdList)
                supplyList.add(new Supply(sd));

            releaseSession();

            return supplyList;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
        }
        return null;
    }

    /**
     *测试通过
     * @return
     */
    public List<Supply> getAllSupplies()
    {
        getSession();
        try
        {
            String getAllSuppliesHql="from SupplyDemand where type=?1";
            Query getAllSuppliesQuery=hs.createQuery(getAllSuppliesHql);
            getAllSuppliesQuery.setParameter(1,"S");
            List<SupplyDemand> sdList=getAllSuppliesQuery.list();
            List<Supply> supplyList=new ArrayList<>();
            for(SupplyDemand sd:sdList)
                supplyList.add(new Supply(sd));

            releaseSession();

            return supplyList;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
        }
        return null;
    }
	
	/**
     * 测试通过
	 * 分页获取List，获取（Page-1）*pageCapacity -- page*pageCapacity 间的内容
	 * @param page 页数
	 * @return List
	 */
    public List<Supply> getSuppliesByPage(int page)
    {
        getSession();
        try
        {
            String getSupplyHql="from SupplyDemand where type=?1";
            Query getSupplyQuery=hs.createQuery(getSupplyHql);
            getSupplyQuery.setParameter(1,"S");
            getSupplyQuery.setFirstResult((page-1)*pageCapacity);
            getSupplyQuery.setMaxResults(pageCapacity);
            List<SupplyDemand> sdList=getSupplyQuery.list();
            List<Supply> supplyList=new ArrayList<>();
            sdList.forEach(supplyDemand -> supplyList.add(new Supply(supplyDemand)));

            releaseSession();

            return supplyList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 测试通过
     * 分页获取企业的供应
     * @param userId
     * @param page
     * @return
     */
    public List<Supply> getUserSuppliesByPage(String userId,int page)
    {
        getSession();
        try
        {
            String getSupplyHql="from SupplyDemand where userId=?1 and type=?2";
            Query getSupplyQuery=hs.createQuery(getSupplyHql);
            getSupplyQuery.setParameter(1,userId);
            getSupplyQuery.setParameter(2,"S");
            getSupplyQuery.setFirstResult((page-1)*pageCapacity);
            getSupplyQuery.setMaxResults(pageCapacity);
            List<SupplyDemand> sdList=getSupplyQuery.list();
            List<Supply> supplyList=new ArrayList<>();
            sdList.forEach(supplyDemand -> supplyList.add(new Supply(supplyDemand)));

            releaseSession();

            return supplyList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
	
	/**
     *
	 * 获取某个用户发布的所有supply
	 * @param userId 用户Id
	 * @return List
	 */
    public List<Supply> getUserSupplies(String userId)
    {
        getSession();
        try
        {
            String getSuppliesHql="from SupplyDemand where userId=?1 and type=?2";
            Query getSuppliesQuery=hs.createQuery(getSuppliesHql);
            getSuppliesQuery.setParameter(1,userId);
            getSuppliesQuery.setParameter(2,"S");
            List<SupplyDemand> sdList=getSuppliesQuery.list();
            List<Supply> supplyList=new ArrayList<>();
            for(SupplyDemand sd:sdList)
                supplyList.add(new Supply(sd));

            releaseSession();

            return supplyList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

	
	/**
     * 测试通过
	 * 精确查找
	 * @param demandTitle 要查找的需求的标题
	 * @return 按标题精确查找到的需求列表
	 */
	public List<Demand> getDemandByTitle(String demandTitle) {
        getSession();
        try
        {
            String getDemandHql="from SupplyDemand where type=?1 and title=?2";
            Query getDemandQuery=hs.createQuery(getDemandHql);
            getDemandQuery.setParameter(1,"D");
            getDemandQuery.setParameter(2,demandTitle);
            List<SupplyDemand> sdList=getDemandQuery.list();
            List<Demand> demandList=new ArrayList<>();
            for(SupplyDemand sd:sdList)
                demandList.add(new Demand(sd));

            releaseSession();

            return demandList;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
        }
        return null;
	}

    /**
     * 测试通过
     * @param demandId
     * @return
     */
    public Demand getDemandById(String demandId)
    {
        getSession();
        try
        {
            SupplyDemand sd=(SupplyDemand) hs.get(SupplyDemand.class,demandId);

            releaseSession();

            return new Demand(sd);
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
        }
        return null;
    }
	
	/**
     * 测试通过
	 * 模糊查找
	 * @param keyword 查找的关键字
	 * @return 标题中含有关键字的需求列表
	 */
    public List<Demand> getDemandsByKeyWord(String keyword)
    {
        getSession();
        try
        {
            String getDemandHql="from SupplyDemand where type=?1 and title like ?2";
            Query getDemandQuery=hs.createQuery(getDemandHql);
            getDemandQuery.setParameter(1,"D");
            getDemandQuery.setParameter(2,"%"+keyword+"%");
            List<SupplyDemand> sdList=getDemandQuery.list();
            List<Demand> demandList=new ArrayList<>();
            for(SupplyDemand sd:sdList)
                demandList.add(new Demand(sd));

            releaseSession();

            return demandList;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return
     */
	public List<Demand> getAllDemands()
    {
        getSession();
        try
        {
            String getAllDemandsHql="from SupplyDemand where type=?1";
            Query getAllDemandsQuery=hs.createQuery(getAllDemandsHql);
            getAllDemandsQuery.setParameter(1,"D");
            List<SupplyDemand> sdList=getAllDemandsQuery.list();
            List<Demand> demandList=new ArrayList<>();
            for(SupplyDemand sd:sdList)
                demandList.add(new Demand(sd));

            releaseSession();

            return demandList;
        }
        catch (HibernateException he)
        {
            he.printStackTrace();
        }
        return null;
    }
	
	/**
     * 测试通过
	 * 分页获取List，获取（Page-1）*pageCapacity -- page*pageCapacity 间的内容
     * 也就是获取第page页的内容
	 * @param page 页数
	 * @return List
	 */
    public List<Demand> getDemandsByPage(int page)
    {
        getSession();
        try
        {
            String getDemandHql="from SupplyDemand where type=?1";
            Query getDemandQuery=hs.createQuery(getDemandHql);
            getDemandQuery.setParameter(1,"D");
            getDemandQuery.setFirstResult((page-1)*pageCapacity);
            getDemandQuery.setMaxResults(pageCapacity);
            List<SupplyDemand> sdList=getDemandQuery.list();
            List<Demand> demandList=new ArrayList<>();
            sdList.forEach(supplyDemand -> demandList.add(new Demand(supplyDemand)));

            releaseSession();

            return demandList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 测试通过
     * 分页获取某企业用户需求
     * @param userId
     * @param page
     * @return
     */
    public List<Demand> getUserDemandsByPage(String userId,int page)
    {
        getSession();
        try
        {
            String getDemandHql="from SupplyDemand where userId=?1 and type=?2";
            Query getDemandQuery=hs.createQuery(getDemandHql);
            getDemandQuery.setParameter(1,userId);
            getDemandQuery.setParameter(2,"D");
            getDemandQuery.setFirstResult((page-1)*pageCapacity);
            getDemandQuery.setMaxResults(pageCapacity);
            List<SupplyDemand> sdList=getDemandQuery.list();
            List<Demand> demandList=new ArrayList<>();
            sdList.forEach(supplyDemand -> demandList.add(new Demand(supplyDemand)));

            releaseSession();

            return demandList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
	
	/**
     * 测试通过
	 * 获取某个用户发布的所有demand
	 * @param userId 用户Id
	 * @return List
	 */
    public List<Demand> getUserDemands(String userId)
    {
        getSession();
        try
        {
            String getDemandsHql="from SupplyDemand where userId=?1 and type=?2";
            Query getDemandsQuery=hs.createQuery(getDemandsHql);
            getDemandsQuery.setParameter(1,userId);
            getDemandsQuery.setParameter(2,"D");
            List<SupplyDemand> sdList=getDemandsQuery.list();
            List<Demand> demandList=new ArrayList<>();
            for(SupplyDemand sd:sdList)
                demandList.add(new Demand(sd));

          releaseSession();

            return demandList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 测试通过
     * @param validatedSupply
     * @return
     */
    public Boolean setAsPass(Supply validatedSupply)
    {
        getSession();
        try
        {
            validatedSupply.setIsPass((byte)1);
            SupplyDemand sd=validatedSupply.toSupplyDemand();
            hs.update(sd);

            releaseSession();

            return true;
        }
        catch (HibernateException he)
        {
            he.printStackTrace();
        }
        return false;
    }

    /**
     * 测试通过
     * @param validatedDemand
     * @return
     */
    public Boolean setAsPass(Demand validatedDemand)
    {
        getSession();
        try
        {
            validatedDemand.setIsPass((byte)1);
            SupplyDemand sd=validatedDemand.toSupplyDemand();
            hs.update(sd);

            releaseSession();

            return true;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
        }
        return null;
    }

//    //是否可以替换上面两个函数？
//    public Boolean setAsPass(SupplyDemand validatedSD)
//    {
//        getSession();
//        try
//        {
//            validatedSD.setIsPass((byte)1);
//            hs.update(validatedSD);
//
//            releaseSession();
//            return true;
//        }
//        catch(HibernateException he)
//        {
//            he.printStackTrace();
//        }
//        return null;
//    }
}
