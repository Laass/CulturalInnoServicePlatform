import dao.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import po.Order;
import po.User;
import po.UserInfo;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
//        UUID.randomUUID().toString().replace("-", "")

        //测试UserInfoDAO
//        UserInfoDAO uiDAO=new UserInfoDAO();
//        List<UserInfo> l=uiDAO.getAllUserWithInfo();
//        System.out.println(l);
//
//        UserInfo aInfo=uiDAO.getUserInfo(l.get(0).getUserId());
//        System.out.println(aInfo);
//
//        aInfo.setNickName("nick1");
//        uiDAO.modifyUserInfo(aInfo);
//
//        UserInfo aInfo2=uiDAO.getUserInfo(aInfo.getUserId());
//        System.out.println(aInfo2);
//
//        UserInfo toAdd=new UserInfo();
//        toAdd.setUserId("13051205200");
//        toAdd.setNickName("testNickName");
//        toAdd.setRealName("testRealName");
//        toAdd.setQq("168135975");
//        toAdd.setTel("13051205197");
//        uiDAO.addUserInfo(toAdd);

//        测试UserDAO
//        UserDAO uDAO=new UserDAO();
//        User toAdd=new User();
//        toAdd.setUserId("13051205200");
//        toAdd.setPassword("123");
//        toAdd.setType(16);
//        uDAO.addUser(toAdd);
//        System.out.println(uDAO.delUser(toAdd.getUserId()));
//        User gettedFromTable=uDAO.getUser("13051205199");
//        System.out.println(gettedFromTable);
//        List<User> allUserList=uDAO.getAllUser();
//        System.out.println(allUserList);
//        uDAO.purchaseProduct(gettedFromTable.getUserId(),"pro1",2);
//        System.out.println(uDAO.validateUser("13051205196","123"));
//        System.out.println(uDAO.validateUser("13051205193","123"));
//        System.out.println(uDAO.validateUser("13051205196","121"));
//        System.out.println(uDAO.validateUser("13051205193","120"));

//        测试SupplyDemandDAO
        SupplyDemandDAO sdDAO=new SupplyDemandDAO();
        Supply s=new Supply();
        s.setSdId(UUID.randomUUID().toString().replace("-", ""));
        s.setUserId("13051205199");
        s.setTitle("supplyTitle");
        s.setContent("supplyContent");

        Demand d=new Demand();
        d.setSdId(UUID.randomUUID().toString().replace("-", ""));
        d.setUserId("13051205196");
        d.setTitle("demandTitle");
        d.setContent("demandContent");

        sdDAO.addSupply(s);
        sdDAO.addDemand(d);
    }
}
