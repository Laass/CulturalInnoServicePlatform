package spring.controller;

import com.google.gson.Gson;
import dao.AO;
import dao.OrderDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

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
