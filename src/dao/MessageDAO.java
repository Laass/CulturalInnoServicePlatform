package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import po.Message;

public class MessageDAO
{
    private Session hsession;
    private Transaction ts;
    private int maxEssayNum = 2;

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
     * @param newMessage
     * @return
     */
    public Message addMessage(Message newMessage)
    {
        getSession();
        try
        {
            hsession.save(newMessage);

            releaseSession();

            return newMessage;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            releaseSession();
            return null;
        }
    }


    /**
     * 测试通过
     * @param messageId
     * @return
     */
    public Boolean delMessage(String messageId)
    {
        getSession();
        try
        {

//			Message message = new Message();
//            message.setMesId(messageId);
            Message message = hsession.get(Message.class, messageId);
            hsession.delete(message);

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
     * 按id获取留言
     * @param messageId
     * @return
     */
    //
    public Message getMessageInfo(String messageId)
    {
        getSession();
        try
        {

            //为什么要获取一个列表？
            Query hquery = hsession.createQuery("from Message m where m.mesId=?1");
            hquery.setParameter(1, messageId);
            List<Message> list = hquery.list();
            Message mes = list.get(0);

            releaseSession();

            return mes;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            releaseSession();
            return null;
        }
    }

    /**
     * 测试通过
     * 获取（Page-1）*maxEssayNum -- page*maxEssayNum 间的内容
     * 分页获取文章或产品的留言（评价）
     * 如果page=-1 则不分页获取
     * @param originId 文章或产品的id
     * @param page
     * @return
     */
    public List<Message> getMessageById(String originId, int page)
    {
        getSession();
        try
        {
//
//			Query hquery = hsession.createQuery("from Message m where m.originId=? limit ?,?");
//			hquery.setString(0,originId);
//			hquery.setInteger(1,(page-1)*maxEssayNum-1);
//			hquery.setInteger(2,maxEssayNum);
//          List<Message> list = hquery.list();

            Query hQuery = hsession.createQuery("from Message where originId=?1");
            hQuery.setParameter(1, originId);
            if(page!=-1)
            {
                hQuery.setFirstResult((page - 1) * maxEssayNum);
                hQuery.setMaxResults(maxEssayNum);
            }
            List<Message> list = hQuery.list();

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

    /**
     * 测试通过
     * 获取某个用户的所有留言
     * @param userId
     * @return
     */
    public List<Message> getUserMessage(String userId)
    {
        getSession();
        try
        {
            Query getMessageQuery= hsession.createQuery("from Message where userId=?1");
            getMessageQuery.setParameter(1,userId);
            List<Message> list=getMessageQuery.list();

            releaseSession();

            return list;
        }
        catch (HibernateException he)
        {
            he.printStackTrace();
        }
        return null;
    }

    /**
     * 测试通过
     * 分页获取某个用户的所有留言
     * @param userId
     * @param page
     * @return
     */
    public List<Message> getUserMessageByPage(String userId,int page)
    {
        getSession();
        try
        {
            Query getMessageQuery= hsession.createQuery("from Message where userId=?1");
            getMessageQuery.setParameter(1,userId);
            getMessageQuery.setFirstResult((page-1)*maxEssayNum);
            getMessageQuery.setMaxResults(maxEssayNum);
            List<Message> list=getMessageQuery.list();

            releaseSession();

            return list;
        }
        catch (HibernateException he)
        {
            he.printStackTrace();
        }
        return null;
    }
}
