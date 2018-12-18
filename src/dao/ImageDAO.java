package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import po.Image;

public class ImageDAO
{
    private Session hsession;
    private Transaction ts;

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
     * @param newImage
     * @return
     */
    public Boolean addImage(Image newImage) throws Exception
    {
        getSession();
        try
        {
            hsession.save(newImage);

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
     * 返回用户或文章的图片
     * 从表头开始获取 获取num个
     * @param id 用户或文章的id
     * @param num 指定获取个数 -1为获取全部
     */
    public List<Image> getImageByOriginId(String id,int num) throws Exception
    {
        getSession();
        try
        {

            Query hquery = hsession.createQuery("from Image im where im.originId=?1");
            hquery.setParameter(1, id);
            if(num>-1)
                hquery.setMaxResults(num);
            List<Image> list = hquery.list();

            releaseSession();

            return list;
        }
        catch (Exception e)
        {
            releaseSession();
            throw e;
//            return null;
        }
    }

    public Image getFirstImageOfOriginId(String id) throws Exception
    {
        getSession();
        try
        {

            Query hquery = hsession.createQuery("from Image im where im.originId=?1");
            hquery.setParameter(1, id);
            hquery.setMaxResults(1);
            List<Image> list = hquery.list();

            releaseSession();

            if(list.size()>0)
                return list.get(0);
            else
                return null;
        }
        catch (Exception e)
        {
            releaseSession();
            throw e;
//            return null;
        }
    }

    // 此处假设设置好了级联删除
//	public Boolean deleteImage(String imageId) {
//		SessionMgr.getSession(cfg, sf, hsession, ts);
//		try {
//
//			Image image = new Iamge();
//			image.setImageId(imageId);
//			hsession.delete(image);
//
//			return true;
//		}catch(Exception e) {
//			e.printStackTrace();
//			SessionMgr.releaseConnect(sf, hsession);
//			return false;
//		}
//	}
}
