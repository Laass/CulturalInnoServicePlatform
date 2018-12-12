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

    /**
     * 测试通过
     * @param newImage
     * @return
     */
    public Boolean addImage(Image newImage)
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
            e.printStackTrace();
            releaseSession();
            return false;
        }
    }

    /**
     * 测试通过
     * 返回用户或文章的图片
     *
     * @param id 用户或文章的id
     */
    public List<Image> getImageByOriginId(String id)
    {
        getSession();
        try
        {

            Query hquery = hsession.createQuery("from Image im where im.originId=?1");
            hquery.setParameter(1, id);
            List<Image> list = hquery.list();

            releaseSession();

            return list;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            releaseSession();
            return null;
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
