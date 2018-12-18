package spring.controller;

import com.google.gson.Gson;
import dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;
import po.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CollectionController{

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @RequestMapping(value = "/Collection.html")
    public String initCollectionPage(HttpSession session,Model model){
        User u=(User)session.getAttribute("currentUser");
        if(u == null)
            return "redirect:/Login.html";
        CollectionDAO cDAO=new CollectionDAO();
        ExhibitionDAO eDAO=new ExhibitionDAO();
        NewsDAO nDAO=new NewsDAO();
        ProductDAO pDAO=new ProductDAO();
        SupplyDemandDAO sdDAO=new SupplyDemandDAO();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            List<Collection> allList=cDAO.getUserCollection(u.getUserId());
//            List<Collection> exhiCollList=new ArrayList<>(),newsCollList=new ArrayList<>(),productCollList=new ArrayList<>(),demandCollList=new ArrayList<>(),supplyCollList=new ArrayList<>();
            List<AO> exhiInfoList=new ArrayList<>();
            List<AO> newsInfoList=new ArrayList<>();
            List<AO> productInfoList=new ArrayList<>();
            List<AO> supplyInfoList=new ArrayList<>();
            List<AO> demandInfoList=new ArrayList<>();
            for(Collection c:allList)
            {
                if(c.getOriginType().equals(CollectionType.EXHIBITION.name))
                {
//                    exhiCollList.add(c);
                    Exhibition e=eDAO.getExhibitionById(c.getOriginId());
                    AO a=new AO();
                    a.setFirst(e.getTheme());
                    a.setSecond(df.format(e.getEstablishTime()));
                    a.setThird(df.format(c.getEstablishTime()));
                    a.setFourth(c.getOriginId());
                    exhiInfoList.add(a);
                }
                else
                    if(c.getOriginType().equals(CollectionType.NEWS.name))
                    {
//                        newsCollList.add(c);
                        News n=nDAO.getNewsById(c.getOriginId());
                        AO a=new AO();
                        a.setFirst(n.getTitle());
                        a.setSecond(df.format(n.getEstablishTime()));
                        a.setThird(df.format(c.getEstablishTime()));
                        a.setFourth(c.getOriginId());
                        newsInfoList.add(a);
                    }
                    else
                        if(c.getOriginType().equals(CollectionType.PRODUCT.name))
                        {
//                            productCollList.add(c);
                            Product p=pDAO.getProducById(c.getOriginId());
                            AO a=new AO();
                            a.setFirst(p.getProName());
                            a.setSecond(df.format(c.getEstablishTime()));
                            a.setThird(df.format(c.getEstablishTime()));
                            a.setFourth(c.getOriginId());
                            productInfoList.add(a);
                        }
                        else
                            if(c.getOriginType().equals(CollectionType.SUPPLYDEMAND.name))
                            {
//                                supplyCollList.add(c);
                                Supply s=sdDAO.getSupplyById(c.getOriginId());
                                AO a=new AO();
                                a.setFirst(s.getTitle());
                                a.setSecond(df.format(s.getStartTime()));
                                a.setThird(df.format(s.getEndTime()));
                                a.setFourth(df.format(c.getEstablishTime()));
                                a.setFifth(c.getOriginId());
                                supplyInfoList.add(a);
                            }
//                            else
//                            {
////                                demandCollList.add(c);
//                                Demand d=sdDAO.getDemandById(c.getOriginId());
//                                AO a=new AO();
//                                a.setFirst(d.getTitle());
//                                a.setSecond(df.format(d.getStartTime()));
//                                a.setThird(df.format(d.getEndTime()));
//                                a.setFourth(df.format(c.getEstablishTime()));
//                                a.setFifth(c.getOriginId());
//                                demandInfoList.add(a);
//                            }

            }
//            model.addAttribute("exhiList",exhiCollList);
//            model.addAttribute("newsList",newsCollList);
//            model.addAttribute("productList",productCollList);
//            model.addAttribute("supplyList",supplyCollList);
//            model.addAttribute("demandList",demandCollList);
            model.addAttribute("exhiInfoList",exhiInfoList);
            model.addAttribute("newsInfoList",newsInfoList);
            model.addAttribute("productInfoList",productInfoList);
            model.addAttribute("supplyInfoList",supplyInfoList);
//            model.addAttribute("demandInfoList",demandInfoList);
            return "Collection";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping(value = "/addToCollection.action")
    @ResponseBody
    public CollectionController addToCollection(@RequestBody String json, HttpServletRequest request){

        try
        {
            Collection coll = new CollectionDAO().addToCollection(new Gson().fromJson(json, Collection.class));
            User user = (User)request.getSession().getAttribute("currentUser");
            coll.setUserId(user.getUserId());
            Date dnow = new Date();
            coll.setEstablishTime(new Timestamp(dnow.getTime()));
            coll = new CollectionDAO().addToCollection(coll);

            if(coll != null)
                this.setMessage("添加成功");
            else
                this.setMessage("添加失败");
            return this;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @ModelAttribute("pageCollectionList")
    @RequestMapping(value = "/getCollectionByPage.action")
    @ResponseBody
    public List getCollectionByPage(@RequestBody String json){
        try
        {
            //第一个参数存用户id，第二个参数存page
            AO temp = new Gson().fromJson(json, AO.class);
            List t = new CollectionDAO().getCollectionByPage(temp.getFirst(),Integer.parseInt(temp.getSecond()));
            return t;
        }
        catch(Exception e)
        {
            return null;
        }
    }

}
