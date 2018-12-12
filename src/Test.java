import dao.UserInfoDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import po.UserInfo;

import java.util.List;
import java.util.UUID;

public class Test
{
    public static void main(String[] args)
    {
        Configuration cfg=new Configuration().configure();
        SessionFactory sf=cfg.buildSessionFactory();
        Session hs=sf.openSession();
        Transaction ts=hs.beginTransaction();
//
//        String s="from User";
//        Query q=hs.createQuery(s);
//        System.out.println(q.list());
//
//        ts.commit();
//        hs.close();

//        UUID uuid=UUID.randomUUID();
//        System.out.println(uuid.toString());
        UserInfoDAO uiDAO=new UserInfoDAO();
        List<UserInfo> l=uiDAO.getAllUserWithInfo();
        System.out.println(l);

        UserInfo aInfo=uiDAO.getUserInfo(l.get(0).getUserId());
        System.out.println(aInfo);

        aInfo.setNickName("nick1");
        uiDAO.modifyUserInfo(aInfo);

        UserInfo aInfo2=uiDAO.getUserInfo(aInfo.getUserId());
        System.out.println(aInfo2);

        UserInfo toAdd=new UserInfo();
        toAdd.setUserId(UUID.randomUUID().toString().replace("-", ""));
        toAdd.setNickName("testNickName");
        toAdd.setRealName("testRealName");
        toAdd.setQq("168135975");
        toAdd.setTel("13051205197");
        uiDAO.addUserInfo(toAdd);


    }
}
