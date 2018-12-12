package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import po.Image;

public class ImageDAO {

	private Configuration cfg;
	private SessionFactory sf=null;
	private Session hsession=null;
	private Transaction ts=null;
	private int maxEssayNum = 12;
	
	public Boolean addImage(Image newImage) {
		SessionMgr.getSession(cfg, sf, hsession, ts);
		try {

			hsession.save(newImage);

			SessionMgr.releaseConnect(sf, hsession);

			return true;
		}catch(Exception e) {
			e.printStackTrace();
			SessionMgr.releaseConnect(sf, hsession);
			return false;
		}
	}
	
	/**
	 * 返回用户或文章的图片
	 * 
	 * @param id 用户或文章的id
	 */
	public List getImageById(String id) {
		SessionMgr.getSession(cfg, sf, hsession, ts);
		try {

			Query hquery = hsession.createQuery("from Image im where im.originId=?");
			hquery.setString(0,id);
			List<Image> list = hquery.list();

            SessionMgr.releaseConnect(sf, hsession);

			return list;
		}catch(Exception e) {
			e.printStackTrace();
			SessionMgr.releaseConnect(sf, hsession);
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
