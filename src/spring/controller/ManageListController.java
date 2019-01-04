package spring.controller;

import dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
                    AO temp = new AO(i.getSdId(), i.getTitle(), i.getStartTime().toString(), i.getEndTime().toString(), i.getHits().toString(), "", "");
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
        }

        return "List";
    }

}
