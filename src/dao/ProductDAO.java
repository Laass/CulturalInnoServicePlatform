package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
import po.Product;

public class ProductDAO {

    private Configuration cfg;
    private SessionFactory sf=null;
    private Session hsession=null;
    private Transaction ts=null;
    private int maxEssayNum = 12;

    public Product addProduct(Product newSupply) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            hsession.save(newSupply);
            SessionMgr.releaseConnect(sf,hsession);
            return newSupply;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }

    public List getAllProducts() {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("from Product ");
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
     * 分页获取所有产品 获取范围为(Page-1)*maxEssayNum -- page*maxEssayNum
     * @param page 获取第几页的产品
     * @return List 产品列表
     */
    public List getProductByPage(int page) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("select * from Product limit"+(page-1)*maxEssayNum+","+page*maxEssayNum);
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
     * 获取企业用户的所有产品
     * @param userId 企业用户的id
     * @return List 产品列表
     */
    public List getUserProducts(String userId) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("from Product where Product .userId = :userId");
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
     *获取指定的产品
     * @param productId 要查找的产品的id
     * @return 指定id的产品
     */
    public Product getProducById(String productId) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Product product =  hsession.get(Product.class, productId);

            SessionMgr.releaseConnect(sf, hsession);
            return product;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }

    /**
     * 模糊查找 获取名称中包含{@param keyword}的所有产品
     * @param keyword 要查找的关键字
     */
    public List getProductByKeyword(String keyword) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("from Product where Product .proName = "+'%'+keyword+'%');
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
     * 精确查找
     * @param productName 要查找的产品名
     */
    public List getProductByTitle(String productName) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            String sql = "from Product where Product .proName like :productName";
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
     * 获取一类产品
     * @param ptype 要获取的产品类型
     * @return List 一类产品的列表
     */
    public List getProductByType(String ptype) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("from Product where Product .productType = :ptitle");
            List t = q.list();

            SessionMgr.releaseConnect(sf, hsession);
            return t;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return null;
        }
    }

    public Boolean delProduct(String productId) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Product product  = hsession.get(Product.class,productId);
            hsession.delete(product);

            SessionMgr.releaseConnect(sf, hsession);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return false;
        }
    }

    public Boolean setAsPass(String productId){
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try{
            hsession.createQuery("update Product set Product .isPass='1'");

            SessionMgr.releaseConnect(sf, hsession);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            SessionMgr.releaseConnect(sf, hsession);
            return false;
        }
    }
}
