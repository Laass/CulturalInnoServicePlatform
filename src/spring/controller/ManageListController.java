package spring.controller;

import dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import po.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/Manage")
public class ManageListController {

    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public String initListPage(@RequestParam("listName")String listName, HttpServletRequest request, HttpSession session, Model model) throws Exception {

        User user = (User)request.getSession().getAttribute("currentUser");
        user = new UserDAO().getUser(user.getUserId());
        request.getSession().setAttribute("currentUser", user);
        List<AO> list = null;

        //AO存储：id、title、y
        switch (listName) {
            case "SD":
                List<SupplyDemand> sdList = null;
                if(user.getType() > 15)
                    sdList = new SupplyDemandDAO().getUserSD(user.getUserId());
                else
                    sdList = new SupplyDemandDAO().getAllSD();

               list = new ArrayList<AO>();
                for (SupplyDemand i : sdList) {
                    AO temp = new AO(i.getSdId(), i.getTitle(), i.getStartTime().toString(), i.getEndTime().toString(), i.getHits().toString(), i.getIsPass()+"", "");
                    list.add(temp);
                }
                model.addAttribute("list", list);
                request.setAttribute("listType", "SD");
                request.setAttribute("listNum", list.size());
                return "Manage/SDList";
            case "News" :
                List<News> newsList = null;
                if(user.getType() > 15)
                    newsList = new NewsDAO().getNewsByUserId(user.getUserId());
                else
                    newsList = new NewsDAO().getAllNews();

                list = new ArrayList<AO>();
                for (News i : newsList) {
                    AO temp = new AO(i.getNewsId(), i.getTitle(), i.getEstablishTime().toString(), i.getHits().toString(), i.getIsPass()+"", "", "");
                    list.add(temp);
                }
                model.addAttribute("list", list);
                model.addAttribute("listType", "News");
                model.addAttribute("listNum", list.size());
                return "Manage/List";
            case "Exh" :
                List<Exhibition> exList = null;
                if(user.getType() > 15)
                    exList = new ExhibitionDAO().getExhibitionByUserId(user.getUserId());
                else
                    exList = new ExhibitionDAO().getAllExhibition();

                list = new ArrayList<AO>();
                for (Exhibition i : exList) {
                    AO temp = new AO(i.getExId(), i.getTheme(), i.getEstablishTime().toString(), i.getHits().toString(), i.getIsPass()+"", "", "");
                    list.add(temp);
                }
                model.addAttribute("list", list);
                model.addAttribute("listType", "Exhibition");
                model.addAttribute("listNum", list.size());
                return "Manage/List";
            case "Product" :
                List<Product> proList = null;
                if(user.getType() > 15)
                    proList = new ProductDAO().getUserProducts(user.getUserId());
                else
                    proList = new ProductDAO().getAllProducts();

                list = new ArrayList<AO>();
                for (Product i : proList) {
                    AO temp = new AO(i.getProId(), i.getProName(), i.getProductType(), i.getPrice()+"", i.getHits().toString(), i.getIsPass()+"", "");
                    list.add(temp);
                }
                model.addAttribute("list", list);
                model.addAttribute("listType", "PRODUCT");
                model.addAttribute("listNum", list.size());
                return "Manage/ProList";
            case "Order" :
                List<Order> orderList = new OrderDAO().getUserOrders(user.getUserId());
                list = new ArrayList<AO>();
                for (Order i : orderList) {
                    Product productTemp = new ProductDAO().getProducById(i.getProId());
                    AO temp = new AO(i.getOrderId(), productTemp.getProName(), ""+productTemp.getPrice(), ""+i.getCount(), ""+(i.getCount()*productTemp.getPrice()), "", "");
                    list.add(temp);
                }
                model.addAttribute("list", list);
                request.setAttribute("listType", "Order");
                request.setAttribute("listNum", list.size());
                return "Manage/OrderList";
            case "Message" :
                List<Message> messageList;
                if(user.getType()==15)
                    messageList = new MessageDAO().getAll();
                else
                    messageList = new MessageDAO().getUserMessage(user.getUserId());
                list = new ArrayList<AO>();
                for (Message i : messageList) {
                    AO temp = new AO();
                    temp.setFirst(i.getMesId());
                    temp.setSecond(i.getContent());
                    temp.setThird(i.getEstablishTime().toString());
                    temp.setFourth(i.getOriginType());
                    switch (i.getOriginType()){
                        case "news":
                            temp.setFifth(new NewsDAO().getNewsById(i.getOriginId()).getTitle());
                            break;
                        case "exhibition" :
                            temp.setFifth(new ExhibitionDAO().getExhibitionById(i.getOriginId()).getTheme());
                            break;
                        case "sd" :
                            temp.setFifth(new SupplyDemandDAO().getSDById(i.getOriginId()).getTitle());
                            break;
                        default:
                            temp.setFifth(new ProductDAO().getProducById(i.getOriginId()).getProName());
                    }
                    list.add(temp);
                }
                model.addAttribute("list", list);
                request.setAttribute("listType", "Message");
                request.setAttribute("listNum", list.size());
                return "Manage/MessageList";

        }

        return "List";
    }

    @RequestMapping(value="goToEdit")
    public String goToEdit(String essayIdAndType, HttpServletRequest request)
    {
        String[] strs=essayIdAndType.split(" ");
        String essayId=strs[0];
        String essayType=strs[1];
        AO essay=null;
        try
        {
            switch(essayType)
            {
                case "News":
                    News n=new NewsDAO().getNewsById(essayId);
                    essay=new AO(n.getTitle(),n.getContent(),n.getNewsId());
                    break;
                case "Exh":
                    Exhibition e=new ExhibitionDAO().getExhibitionById(essayId);
                    essay=new AO(e.getTheme(),e.getContent(),e.getExId());
                    break;
                case "SD":
                    SupplyDemand sd=new SupplyDemandDAO().getSDById(essayId);
                    essay=new AO(sd.getTitle(),sd.getContent(),sd.getSdId());
                    break;
                case "Product":
                    Product p=new ProductDAO().getProducById(essayId);
                    essay=new AO(p.getProName(),p.getInfo(),p.getProId());
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Login";
        }
        request.setAttribute("essay",essay);
        request.setAttribute("essayType",essayType);
        return "Manage/editEssay";
    }

    @RequestMapping(value = "editEssay",method = RequestMethod.POST)
    @ResponseBody
    public String editEssay(String essayTitle,String essayContent,String essayId,String essayType)
    {
        try
        {
            switch(essayType)
            {
                case "News":
                    NewsDAO nDAO=new NewsDAO();
                    News n=nDAO.getNewsById(essayId);
                    n.setTitle(essayTitle);
                    n.setContent(essayContent);
                    nDAO.update(n);
                    break;
                case "Exh":
                    ExhibitionDAO eDAO=new ExhibitionDAO();
                    Exhibition e=eDAO.getExhibitionById(essayId);
                    e.setTheme(essayTitle);
                    e.setContent(essayContent);
                    eDAO.update(e);
                    break;
                case "SD":
                    SupplyDemandDAO sdDAO=new SupplyDemandDAO();
                    SupplyDemand sd=sdDAO.getSDById(essayId);
                    sd.setTitle(essayTitle);
                    sd.setContent(essayContent);
                    sdDAO.update(sd);
                    break;
                case "Product":
                    ProductDAO pDAO=new ProductDAO();
                    Product p=pDAO.getProducById(essayId);
                    p.setProName(essayTitle);
                    p.setInfo(essayContent);
                    pDAO.update(p);
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "update failed";
        }
        return "update success";
    }

}
