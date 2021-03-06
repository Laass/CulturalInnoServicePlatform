package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

import po.Collection;
import po.Image;
import po.Product;

public class ProductDAO
{
    private Session hsession;
    private Transaction ts;
    private int maxEssayNum = 2;

    private void getSession()
    {
        //从SessionMgr获取Session和Transaction
        Object[] connectionList=SessionMgr.getSession();
        hsession =(Session)connectionList[0];
        ts=(Transaction)connectionList[1];
    }

    private void releaseSession()
    {
        SessionMgr.releaseConnect(hsession,ts);
    }

    private void releaseSession(Session hsession)
    {
        SessionMgr.releaseConnect(hsession);
    }

    /**
     * 测试通过
     * @param newSupply
     * @return
     */
    public Product addProduct(Product newSupply) throws Exception
    {
        getSession();
        try
        {
            hsession.save(newSupply);
            releaseSession();
            return newSupply;
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
    public List<Product> getAllProducts() throws Exception
    {
       getSession();
        try
        {
            Query q = hsession.createQuery("from Product ");
            List<Product> t = q.list();

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
     * 分页获取所有产品 获取范围为(Page-1)*maxEssayNum -- page*maxEssayNum
     *
     * @param page 获取第几页的产品
     * @return List 产品列表
     */
    public List<Product> getProductByPage(int page) throws Exception
    {
       getSession();
        try
        {
            Query q = hsession.createQuery("from Product");
            q.setFirstResult((page-1)*maxEssayNum);
            q.setMaxResults(maxEssayNum);
            List<Product> t = q.list();

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
     * 获取企业用户的所有产品
     *
     * @param userId 企业用户的id
     * @return List 产品列表
     */
    public List<Product> getUserProducts(String userId) throws Exception
    {
       getSession();
        try
        {
//            Query q = hsession.createQuery("from Product where Product .userId = :userId");
            Query q = hsession.createQuery("from Product where userId = ?1");
            q.setParameter(1,userId);
            List<Product> t = q.list();

            releaseSession();
            return t;
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
     * 分页查找某个企业用户的产品
     * @param userId
     * @param page
     * @return
     */
    public List<Product> getUserProductByPage(String userId,int page) throws Exception
    {
        getSession();
        try
        {
            Query q = hsession.createQuery("from Product where userId = ?1");
            q.setFirstResult((page-1)*maxEssayNum);
            q.setMaxResults(maxEssayNum);
            q.setParameter(1,userId);
            List<Product> t = q.list();

            releaseSession();
            return t;
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
     * 获取指定的产品
     *
     * @param productId 要查找的产品的id
     * @return 指定id的产品
     */
    public Product getProducById(String productId) throws Exception
    {
       getSession();
        try
        {
            Product product = hsession.get(Product.class, productId);

            releaseSession();
            return product;
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
     * 模糊查找 获取名称中包含{@param keyword}的所有产品
     *
     * @param keyword 要查找的关键字
     */
    public List<Product> getProductByKeyword(String keyword) throws Exception
    {
       getSession();
        try
        {
            Query q = hsession.createQuery("from Product where proName like ?1");
            q.setParameter(1,'%' + keyword + '%');
            List<Product> t = q.list();

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
     * 精确查找
     *
     * @param productName 要查找的产品名
     */
    public List<Product> getProductByTitle(String productName) throws Exception
    {
       getSession();
        try
        {
            String sql = "from Product where proName=?1";
            Query q = hsession.createQuery(sql);
            q.setParameter(1,productName);
            List<Product> t = q.list();

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
     * 获取一类产品
     *
     * @param ptype 要获取的产品类型
     * @return List 一类产品的列表
     */
    public List<Product> getProductByType(String ptype) throws Exception
    {
       getSession();
        try
        {
            Query q = hsession.createQuery("from Product where productType = ?1");
            q.setParameter(1,ptype);
            List<Product> t = q.list();

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
     * @param productId
     * @return
     */
    public Boolean delProduct(String productId) throws Exception
    {
       getSession();
        try
        {
            Product product = hsession.get(Product.class, productId);
            //删除可能的图片
            Query getImageQuery=hsession.createQuery("from Image where originId=?1");
            getImageQuery.setParameter(1,product.getProId());
            List<Image> imageList=getImageQuery.list();
            imageList.forEach(indi->hsession.delete(indi));
            //删除可能的收藏
            Query getCollectionQuery=hsession.createQuery("from Collection coll where coll.id.originId=?1");
            getCollectionQuery.setParameter(1,product.getProId());
            List<Collection> list=getCollectionQuery.list();
            list.forEach(indi->hsession.delete(indi));
            //删除产品
            hsession.delete(product);

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
     * @param productId
     * @return
     */
    public Boolean setAsPass(String productId) throws Exception
    {
       getSession();
        try
        {
            Query q=hsession.createQuery("update Product set isPass='1' where proId=?1");
            q.setParameter(1,productId);
            q.executeUpdate();
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

    public Boolean update(Product p) throws Exception
    {
        getSession();
        try
        {
            hsession.update(p);
            releaseSession();
            return true;
        }
        catch(Exception e)
        {
            releaseSession(hsession);
            throw e;
        }
    }
}
