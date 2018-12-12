import dao.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import po.*;

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
//        SupplyDemandDAO sdDAO=new SupplyDemandDAO();
//        Supply s=new Supply();
//        s.setSdId(UUID.randomUUID().toString().replace("-", ""));
//        s.setUserId("13051205199");
//        s.setTitle("supplyTitle");
//        s.setContent("supplyContent");
//        Supply s1=new Supply();
//        s.setSdId(UUID.randomUUID().toString().replace("-", ""));
//        s.setUserId("13051205199");
//        s.setTitle("supplyTitle");
//        s.setContent("supplyContent");
//        Supply s2=new Supply();
//        s.setSdId(UUID.randomUUID().toString().replace("-", ""));
//        s.setUserId("13051205199");
//        s.setTitle("supplyTitle");
//        s.setContent("supplyContent");
//
//        Demand d=new Demand();
//        d.setSdId(UUID.randomUUID().toString().replace("-", ""));
//        d.setUserId("13051205196");
//        d.setTitle("demandTitle");
//        d.setContent("demandContent");
//        Demand d1=new Demand();
//        d1.setSdId(UUID.randomUUID().toString().replace("-", ""));
//        d1.setUserId("13051205196");
//        d1.setTitle("demandTitle");
//        d1.setContent("demandContent");
//        Demand d2=new Demand();
//        d2.setSdId(UUID.randomUUID().toString().replace("-", ""));
//        d2.setUserId("13051205196");
//        d2.setTitle("demandTitle");
//        d2.setContent("demandContent");
//        Demand d3=new Demand();
//        d3.setSdId(UUID.randomUUID().toString().replace("-", ""));
//        d3.setUserId("13051205197");
//        d3.setTitle("demandTitle");
//        d3.setContent("demandContent");

//        sdDAO.addSupply(s);
//        sdDAO.addDemand(d);

//        sdDAO.delSupplyDemand("e2acda0b7c4948aaa8c43705f7ec20f9");
//        System.out.println(sdDAO.getSupplyByTitle("supplyTitle"));
//        System.out.println(sdDAO.getSupplyById("2645bfa645cf4ef68fc14c8f21a4ab3a"));
//        System.out.println(sdDAO.getSuppliesByKeyword("supply"));

//        System.out.println(sdDAO.getAllSupplies());

//        sdDAO.addSupply(s);
//        sdDAO.addSupply(s1);
//        sdDAO.addSupply(s2);
//        System.out.println(sdDAO.getSuppliesByPage(2));

//        System.out.println(sdDAO.getUserSupplies("13051205199"));

//        sdDAO.addDemand(d1);
//        sdDAO.addDemand(d2);
//        sdDAO.addDemand(d3);
//        System.out.println(sdDAO.getDemandByTitle("demandTitle"));

//        System.out.println(sdDAO.getDemandById("db243f186c504c23820a374f6da21e34"));

//        System.out.println(sdDAO.getDemandsByKeyWord("demand"));
//        System.out.println(sdDAO.getAllDemands());
//        System.out.println(sdDAO.getDemandsByPage(2));
//        System.out.println(sdDAO.getUserDemands("13051205196"));

//        sdDAO.addSupply(s);
//        sdDAO.addDemand(d);
//        sdDAO.setAsPass(s);
//        sdDAO.setAsPass(d);

//        System.out.println(sdDAO.getUserSuppliesByPage("13051205199",1));
//        System.out.println(sdDAO.getUserSuppliesByPage("13051205199",2));
//        System.out.println(sdDAO.getUserSuppliesByPage("13051205199",3));

//        System.out.println(sdDAO.getUserDemandsByPage("13051205196",1));
//        System.out.println(sdDAO.getUserDemandsByPage("13051205196",2));
//        System.out.println(sdDAO.getUserDemandsByPage("13051205196",3));


//        测试ProductDAO
//        ProductDAO pDAO=new ProductDAO();
//        Product p=new Product();
//        p.setProId(UUID.randomUUID().toString().replace("-", ""));
//        p.setUserId("13051205199");
//        p.setProName("aa");
//        p.setPrice(84.21);
//        p.setIsPass((byte)0);
//        pDAO.addProduct(p);
//        System.out.println(pDAO.getAllProducts());
//        System.out.println(pDAO.getProductByPage(1));
//        System.out.println(pDAO.getProductByPage(2));
//        System.out.println(pDAO.getUserProducts("13051205199"));
//        System.out.println(pDAO.getProducById("pro1"));
//        System.out.println(pDAO.getProductByKeyword("pro"));
//        System.out.println(pDAO.getProductByTitle("iPhoneX"));
//        System.out.println(pDAO.getProductByType("phone"));
//        System.out.println(pDAO.delProduct("35099e00be5d4b8c89aa138564161a35"));
//        System.out.println(pDAO.setAsPass(p.getProId()));
//        System.out.println(pDAO.delProduct("0c787dd4079b4809897007f3e9b7be2c"));
//        System.out.println(pDAO.delProduct("16b6b57280a84008ac81a3b392352ef8"));
//        System.out.println(pDAO.getUserProductByPage("13051205199",1));
//        System.out.println(pDAO.getUserProductByPage("13051205199",2));

//        测试OrderDAO
//        OrderDAO oDAO=new OrderDAO();
//        Order o=new Order();
//        o.setOrderId(UUID.randomUUID().toString().replace("-", ""));
//        o.setUserId("13051205197");
//        o.setProId("pro2");
//        o.setEstablishTime(new Timestamp(new Date().getTime()));
//        o.setCount(3);
//        Order o1=new Order();
//        o1.setOrderId(UUID.randomUUID().toString().replace("-", ""));
//        o1.setUserId("13051205197");
//        o1.setProId("pro2");
//        o1.setEstablishTime(new Timestamp(new Date().getTime()));
//        o1.setCount(3);
//        oDAO.addOrder(o);
//        oDAO.addOrder(o1);
//        System.out.println(oDAO.getUserOrders("13051205196"));
//        System.out.println(oDAO.getOrdersByPage(1));
//        System.out.println(oDAO.getOrdersByPage(2));
//        System.out.println(oDAO.getUserOrdersByPage("13051205197",1));
//        System.out.println(oDAO.getUserOrdersByPage("13051205197",2));

//        测试NewsDAO
//        NewsDAO nDAO=new NewsDAO();
//        News n=new News();
//        n.setNewsId(UUID.randomUUID().toString().replace("-", ""));
//        n.setUserId("13051205199");
//        n.setTitle("newsTtitle");
//        n.setContent("newsContent");
//        n.setEstablishTime(new Timestamp(new Date().getTime()));
//        n.setIsPass((byte)0);
//        nDAO.addNews(n);
//        nDAO.delNews("7d3859432d4e460dbf80aa38225a181d");
//        nDAO.setAsPass(n);
//        System.out.println(nDAO.getNewsByUserId("13051205199"));
//        System.out.println(nDAO.getNewsByPage(1));
//        System.out.println(nDAO.getNewsByPage(2));
//        System.out.println(nDAO.getNewsByPage(3));
//        System.out.println(nDAO.getNewsByKeyword("news"));
//        System.out.println(nDAO.getNewsByTitle("newsTtitle"));
//        System.out.println(nDAO.getAllNews());
//        System.out.println(nDAO.getNewsById("2f868518a7354dada1dd6c970d712dae"));
//        System.out.println(nDAO.delNews("15e51171dab84478a38f54bab5798856"));
//        System.out.println(nDAO.getUserNewsByPage("13051205199",1));
//        System.out.println(nDAO.getUserNewsByPage("13051205199",2));

//        测试MessageDAO
//        MessageDAO mDAO=new MessageDAO();
//        Message m=new Message();
//        m.setMesId(UUID.randomUUID().toString().replace("-",""));
//        m.setUserId("13051205198");
//        m.setOriginId("pro2");
//        m.setOriginType("product");
//        m.setContent("messageContent");
//        Message m1=new Message();
//        m1.setMesId(UUID.randomUUID().toString().replace("-",""));
//        m1.setUserId("13051205198");
//        m1.setOriginId("pro2");
//        m1.setOriginType("product");
//        m1.setContent("messageContent");
//        mDAO.addMessage(m);
//        mDAO.delMessage("24e6973cf92a4036aff8d91085c58203");
//        System.out.println(mDAO.getMessageInfo(m.getMesId()));
//        System.out.println(mDAO.getMessageById("pro1",1));
//        System.out.println(mDAO.getUserMessage("13051205199"));
//        System.out.println(mDAO.getUserMessageByPage("13051205198",1));
//        System.out.println(mDAO.getUserMessageByPage("13051205198",2));

//        测试ImageDAO
//        ImageDAO iDAO=new ImageDAO();
//        Image i=new Image();
//        i.setImageId(UUID.randomUUID().toString().replace("-",""));
//        i.setOriginId("13051205197");
//        i.setStoreLocation("D:\\servletTest\\");
//        iDAO.addImage(i);
//        System.out.println(iDAO.getImageByOriginId("13051205197"));

//        测试ExhibitionDAO
//        ExhibitionDAO eDAO=new ExhibitionDAO();
//        Exhibition e=new Exhibition();
//        e.setExId(UUID.randomUUID().toString().replace("-",""));
//        e.setUserId("13051205199");
//        e.setTheme("Silver Dog Theme");
//        e.setContent("JD Exhi Content");
//        e.setEstablishTime(new Timestamp(new Date().getTime()));
//        e.setIsPass((byte)0);
//        eDAO.addExhibition(e);
//        eDAO.delExhibition("948a09d78e4e47e389bc320708a6a4b1");
//        System.out.println(eDAO.getExhibitionById(e.getExId()));
//        System.out.println(eDAO.getAllExhibition());
//        System.out.println(eDAO.getExhibitionByPage(1));
//        System.out.println(eDAO.getExhibitionByPage(2));
//        System.out.println(eDAO.getExhibitionByPage(3));
//        System.out.println(eDAO.getExhibitionByUserId("13051205199"));
//        System.out.println(eDAO.getExhibitionByKeyword("Dog"));
//        System.out.println(eDAO.getExhibitionByTheme("theme1"));
//        eDAO.setAsPass(e);
//        System.out.println(eDAO.getUserExhibitionByPage("13051205199",1));
//        System.out.println(eDAO.getUserExhibitionByPage("13051205199",2));

//        测试CollectionDAO
//        CollectionDAO cDAO=new CollectionDAO();
//        Collection c=new Collection();
//        c.setUserId("13051205197");
//        c.setOriginId("pro2");
//        c.setOriginType("product");
//        c.setEstablishTime(new Timestamp(new Date().getTime()));
//        cDAO.addToCollection(c);
//        System.out.println(cDAO.delFromCollection("13051205197","pro2"));
//        System.out.println(cDAO.getUserCollectionByType("13051205199","phone"));
//        System.out.println(cDAO.getUserCollection("13051205199"));
//        System.out.println(cDAO.getCollectionByPage("13051205199",1));
//        System.out.println(cDAO.getCollectionByPage("13051205199",2));
    }
}
