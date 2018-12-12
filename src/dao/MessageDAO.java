package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import po.Message;

public class MessageDAO {
	
	private Configuration cfg;
	private SessionFactory sf=null;
	private Session hsession=null;
	private Transaction ts=null;
	private int maxEssayNum = 12;
	
	public Message addMessage(Message newMessage) {
		SessionMgr.getSession(cfg, sf, hsession, ts);
		try {

			hsession.save(newMessage);

			SessionMgr.releaseConnect(sf, hsession);

			return newMessage;
		}catch(Exception e) {
			e.printStackTrace();
			SessionMgr.releaseConnect(sf, hsession);
			return null;
		}
	}
	

	public Boolean delMessage(String messageId) {
		SessionMgr.getSession(cfg, sf, hsession, ts);
		try {

//			Message message = new Message();
//            message.setMesId(messageId);
            Message message=hsession.get(Message.class,messageId);
            hsession.delete(message);

            SessionMgr.releaseConnect(sf, hsession);

			return true;
		}catch(Exception e) {
			e.printStackTrace();
			SessionMgr.releaseConnect(sf, hsession);
			return false;
		}
	}
	
	//获取留言详细信息
	public Message getMessageInfo(String messageId) {
		SessionMgr.getSession(cfg, sf, hsession, ts);
		try {

		    //为什么要获取一个列表？
			Query hquery = hsession.createQuery("from Message m where m.mesId=?");
			hquery.setString(0,messageId);
			List<Message> list = hquery.list();
			Message mes = list.get(0);

            SessionMgr.releaseConnect(sf, hsession);

			return mes;
		}catch(Exception e) {
			e.printStackTrace();
			SessionMgr.releaseConnect(sf, hsession);
			return null;
		}
	}
	
	/**
	 * 获取（Page-1）*maxEssayNum -- page*maxEssayNum 间的内容
     * 分页获取文章或产品的留言（评价）
	 * @param originId 文章或产品的id
	 * @param page
	 * @return
	 */
	public List getMessageById(String originId,int page) {
		SessionMgr.getSession(cfg, sf, hsession, ts);
		try {
//
//			Query hquery = hsession.createQuery("from Message m where m.originId=? limit ?,?");
//			hquery.setString(0,originId);
//			hquery.setInteger(1,(page-1)*maxEssayNum-1);
//			hquery.setInteger(2,maxEssayNum);
//          List<Message> list = hquery.list();

            Query hQuery=hsession.createQuery("from Message where originId=?");
            hQuery.setString(0,originId);
            hQuery.setFirstResult((page-1)*maxEssayNum);
            hQuery.setMaxResults(maxEssayNum);
			List<Message> list = hQuery.list();

            SessionMgr.releaseConnect(sf, hsession);

			return list;
		}catch(Exception e) {
			e.printStackTrace();
			SessionMgr.releaseConnect(sf, hsession);
			return null;
		}
	}
	
	
}
