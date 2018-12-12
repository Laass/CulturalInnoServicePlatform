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
     * ��ҳ��ȡList����ȡ��Page-1��*maxEssayNum -- page*maxEssayNum �������
     * @param page ҳ��
     * @return List
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
     * ��ȡĳ���û�����������product
     * @param userId �û�Id
     * @return List
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
     * ��ȡĳ����Ʒ����ϸ��Ϣ
     * @param productId ��ƷId
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
     * ģ����ѯ
     * @param keyword �ؼ���
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
     * ��ȷ��ѯ
     * @param keyword �ؼ���
     */
    public List getProductByTitle(String keyword) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            String sql = "from Product where Product .proName like :keyword";
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
     * ��������ȡ��Ʒ
     * @param ptype ��Ʒ���
     * @return List
     */
    public List getProductByType(String ptype) {
        SessionMgr.getSession(cfg, sf, hsession, ts);
        try {
            Query q = hsession.createQuery("from Product where Product .type = :ptitle");
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
