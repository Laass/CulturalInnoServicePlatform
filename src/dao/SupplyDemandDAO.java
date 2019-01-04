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
    
    private void releaseSession(Session hs)
    {
        SessionMgr.releaseConnect(hs);
    }


//    /**
//     * 测试通过
//     * @param newSupply
//     * @return
//     */
//	public Supply addSupply(Supply newSupply) throws Exception
//	{
//        getSession();
//		try
//		{
//            SupplyDemand newSD=newSupply.toSupplyDemand();
//            hs.save(newSD);
//
//            releaseSession();
//			return newSupply;
//		}
//		catch (Exception e)
//		{
//            releaseSession(hs);
//            throw e;
//		}
////        return null;
//	}

//    /**
//     * 测试通过
//     * @param newDemand
//     * @return
//     */
//    public Demand addDemand(Demand newDemand) throws Exception
//    {
//        getSession();
//        try
//        {
//            SupplyDemand newSD=newDemand.toSupplyDemand();
//            hs.save(newSD);
//
//            releaseSession();
//            return newDemand;
//        }
//        catch (Exception e)
//        {
//            releaseSession(hs);
//            throw e;
//        }
////        return null;
//    }

//    //上面两个函数可以合成这一个函数
    public SupplyDemand addSD(SupplyDemand newSD) throws Exception
    {
        getSession();
        try
        {
            hs.save(newSD);

            releaseSession();
            return newSD;
        }
        catch (Exception e)
        {
            releaseSession(hs);
            throw e;
        }
//        return null;
    }

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
//        catch(Exception e)
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
//        catch(Exception e)
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
    public Boolean delSD(String Id) throws Exception
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
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
//        return false;
    }
	
	/**
     * 测试通过
	 * 精确查找
	 * @param supplyTitle 关键字
	 * @return
	 */
    public List getSupplyByTitle(String supplyTitle) throws Exception
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
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
//        return null;
    }

    public List<SupplyDemand> getSDByTitle(String title) throws Exception
    {
        getSession();
        try
        {
            Query getSD=hs.createQuery("from  SupplyDemand where title=?1");
            getSD.setParameter(1,title);
            List<SupplyDemand> sdList=getSD.list();
            releaseSession();
            return sdList;
        }
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
    }

    /**
     * 测试通过
     * @param supplyId
     * @return
     */
    public Supply getSupplyById(String supplyId) throws Exception
    {
        getSession();
        try
        {
            SupplyDemand sd=(SupplyDemand) hs.get(SupplyDemand.class,supplyId);

            releaseSession();

            return new Supply(sd);
        }
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
//        return null;
    }

    public SupplyDemand getSDById(String id) throws Exception
    {
        getSession();
        try
        {
            SupplyDemand sd=(SupplyDemand)hs.get(SupplyDemand.class,id);
            releaseSession();
            return sd;
        }
        catch(Exception e)
        {
            releaseSession();
            throw e;
        }
    }
	
	/**
     * 测试通过
	 * 模糊查找
	 * @param keyword
	 * @return
	 */
    public List getSuppliesByKeyword(String keyword) throws Exception
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
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
//        return null;
    }

    public List<SupplyDemand> getSDByKeyWord(String keyword) throws Exception
    {
        getSession();
        try
        {
            Query getSDQuery=hs.createQuery("from SupplyDemand where title like ?1");
            getSDQuery.setParameter(1,'%'+keyword+'%');
            List<SupplyDemand> sdList=getSDQuery.list();
            releaseSession();
            return sdList;
        }
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
    }

    /**
     *测试通过
     * @return
     */
    public List<Supply> getAllSupplies() throws Exception
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
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
//        return null;
    }

    public List<SupplyDemand> getAllSD() throws Exception
    {
        getSession();
        try
        {
            Query getAllSDQuery=hs.createQuery("from SupplyDemand ");
            List<SupplyDemand> sdList=getAllSDQuery.list();
            releaseSession();
            return sdList;
        }
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
    }
	
	/**
     * 测试通过
	 * 分页获取List，获取（Page-1）*pageCapacity -- page*pageCapacity 间的内容
	 * @param page 页数
	 * @return List
	 */
    public List<Supply> getSuppliesByPage(int page) throws Exception
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
            releaseSession(hs);
            throw e;
        }
//        return null;
    }

    public List<SupplyDemand> getSDByPage(int page) throws Exception
    {
        getSession();
        try
        {
            Query getSDQuery=hs.createQuery("from SupplyDemand");
            getSDQuery.setFirstResult((page-1)*pageCapacity);
            getSDQuery.setMaxResults(pageCapacity);
            List<SupplyDemand> sdList=getSDQuery.list();
            releaseSession();
            return sdList;
        }
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
    }

    /**
     * 测试通过
     * 分页获取企业的供应
     * @param userId
     * @param page
     * @return
     */
    public List<Supply> getUserSuppliesByPage(String userId,int page) throws Exception
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
            releaseSession(hs);
            throw e;
        }
//        return null;
    }

    public List<SupplyDemand> getUserSDByPage(String userId,int page) throws Exception
    {
        getSession();
        try
        {
            Query getSDQuery=hs.createQuery("from SupplyDemand where userId=?1");
            getSDQuery.setParameter(1,userId);
            getSDQuery.setFirstResult((page-1)*pageCapacity);
            getSDQuery.setMaxResults(pageCapacity);
            List<SupplyDemand> sdList=getSDQuery.list();
            releaseSession();
            return sdList;
        }
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
    }
	
	/**
     *
	 * 获取某个用户发布的所有supply
	 * @param userId 用户Id
	 * @return List
	 */
    public List<Supply> getUserSupplies(String userId) throws Exception
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
            releaseSession(hs);
            throw e;
        }
//        return null;
    }

    public List<SupplyDemand> getUserSD(String userId)
    {
        getSession();
        try
        {
            Query getSDQuery=hs.createQuery("from SupplyDemand where userId=?1");
            getSDQuery.setParameter(1,userId);
            List<SupplyDemand> sdList=getSDQuery.list();
            releaseSession();
            return sdList;
        }
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
    }

	
	/**
     * 测试通过
	 * 精确查找
	 * @param demandTitle 要查找的需求的标题
	 * @return 按标题精确查找到的需求列表
	 */
	public List<Demand> getDemandByTitle(String demandTitle) throws Exception 
    {
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
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
//        return null;
	}

    /**
     * 测试通过
     * @param demandId
     * @return
     */
    public Demand getDemandById(String demandId) throws Exception
    {
        getSession();
        try
        {
            SupplyDemand sd=(SupplyDemand) hs.get(SupplyDemand.class,demandId);

            releaseSession();

            return new Demand(sd);
        }
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
//        return null;
    }
	
	/**
     * 测试通过
	 * 模糊查找
	 * @param keyword 查找的关键字
	 * @return 标题中含有关键字的需求列表
	 */
    public List<Demand> getDemandsByKeyWord(String keyword) throws Exception
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
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
//        return null;
    }

    /**
     *
     * @return
     */
	public List<Demand> getAllDemands() throws Exception
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
        catch (Exception e)
        {
            releaseSession(hs);
            throw e;
        }
//        return null;
    }
	
	/**
     * 测试通过
	 * 分页获取List，获取（Page-1）*pageCapacity -- page*pageCapacity 间的内容
     * 也就是获取第page页的内容
	 * @param page 页数
	 * @return List
	 */
    public List<Demand> getDemandsByPage(int page) throws Exception
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
            releaseSession(hs);
            throw e;
        }
//        return null;
    }

    /**
     * 测试通过
     * 分页获取某企业用户需求
     * @param userId
     * @param page
     * @return
     */
    public List<Demand> getUserDemandsByPage(String userId,int page) throws Exception
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
            releaseSession(hs);
            throw e;
        }
//        return null;
    }
	
	/**
     * 测试通过
	 * 获取某个用户发布的所有demand
	 * @param userId 用户Id
	 * @return List
	 */
    public List<Demand> getUserDemands(String userId) throws Exception
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
            releaseSession(hs);
            throw e;
        }
//        return null;
    }

//    /**
//     * 测试通过
//     * @param validatedSupply
//     * @return
//     */
//    public Boolean setAsPass(Supply validatedSupply) throws Exception
//    {
//        getSession();
//        try
//        {
//            validatedSupply.setIsPass((byte)1);
//            SupplyDemand sd=validatedSupply.toSupplyDemand();
//            hs.update(sd);
//
//            releaseSession();
//
//            return true;
//        }
//        catch (Exception e)
//        {
//            releaseSession(hs);
//            throw e;
//        }
////        return false;
//    }

//    /**
//     * 测试通过
//     * @param validatedDemand
//     * @return
//     */
//    public Boolean setAsPass(Demand validatedDemand) throws Exception
//    {
//        getSession();
//        try
//        {
//            validatedDemand.setIsPass((byte)1);
//            SupplyDemand sd=validatedDemand.toSupplyDemand();
//            hs.update(sd);
//
//            releaseSession();
//
//            return true;
//        }
//        catch(Exception e)
//        {
//            releaseSession(hs);
//            throw e;
//        }
////        return null;
//    }

    //是否可以替换上面两个函数？
    public Boolean setAsPass(SupplyDemand validatedSD) throws Exception
    {
        try
        {
            validatedSD=getSDById(validatedSD.getSdId());
            validatedSD.setIsPass((byte)1);
            getSession();
            hs.update(validatedSD);

            releaseSession();
            return true;
        }
        catch(Exception e)
        {
            releaseSession(hs);
            throw e;
        }
//        return null;
    }
}
