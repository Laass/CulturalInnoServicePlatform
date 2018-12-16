package spring.controller;

import dao.AO;
import dao.OrderDAO;
import dao.ProductDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import po.Order;
import po.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @ModelAttribute("orderInfoList")
    public List<AO> getAllOrderList()
    {
        OrderDAO oDAO=new OrderDAO();
        ProductDAO pDAO=new ProductDAO();
        try
        {
            List<Order> orderList=oDAO.getUserOrders("13051205197");
            //利用AO 将订单和商品信息拼在一起 这样就可以同时显示同时显示
            List<AO> orderInfoList=new ArrayList<>();
            for(Order o:orderList)
            {
                Product p=pDAO.getProducById(o.getProId());
                AO a=new AO();
                a.setFirst(o.getOrderId());
                a.setSecond(p.getProName());
                a.setThird(Integer.toString(o.getCount()));
                a.setFourth(Double.toString(p.getPrice()));
                a.setFifth(o.getEstablishTime().toString());
                orderInfoList.add(a);
            }
            return orderInfoList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "/Order.html")
    public ModelAndView initOrderPage(Model model){
        return new ModelAndView("Order","command",this);
    }


    @ModelAttribute("pageOrderList")
    @RequestMapping(value = "/getOrderByPage.action")
    @ResponseBody
    public List getOrderByPage(@RequestBody String json){
        //第一个参数存用户id，第二个参数存page
        //AO temp = new Gson().fromJson(json, AO.class);
        //List t = new OrderDAO().getOrdersByPage(temp.getFirst(),Integer.parseInt(temp.getSecond()));
        //return t;
        return null;
    }


}
