package spring.controller;

import com.google.gson.Gson;
import dao.AO;
import dao.SupplyDemandDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import po.SupplyDemand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SDController {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @RequestMapping(value = "/SupplyAndDemand.html")
    public ModelAndView initSDPage(){
        return new ModelAndView("SupplyDemand","command",this);
    }

    @ModelAttribute("supplyList")
    @RequestMapping(value = "/getSupply.action", method = RequestMethod.POST)
    @ResponseBody
    public List getSupply(@RequestBody String json, HttpServletRequest request, Model model){
        //第一个参数存用户id，第二个参数存page
//        AO temp = new Gson().fromJson(json, AO.class);
//        return new SupplyDemandDAO().getSuppliesByPage();
        return null;
    }

    @RequestMapping(value = "/searchSupplyDemand.action", method = RequestMethod.POST)
    @ResponseBody
    public List searchSupplyDemand(@RequestBody String json , HttpServletRequest request){
        //第一个参数是模糊查询还是精确查询，第二个参数关键字
        AO temp = new Gson().fromJson(json, AO.class);

        try
        {
            if(temp.getFirst().equals("模糊查询"))
                return new SupplyDemandDAO().getDemandsByKeyWord(temp.getSecond());
            else
                return new SupplyDemandDAO().getDemandByTitle(temp.getSecond());
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "/addSupplyDemand.action", method = RequestMethod.POST)
    @ResponseBody
    public SupplyDemand addSupplyDemand(@RequestBody String json , HttpServletRequest request){
        SupplyDemand temp = new Gson().fromJson(json, SupplyDemand.class);
        //合并不合并？？？
        return null;
        //return new SupplyDemandDAO().addDemand(temp);
    }

    @RequestMapping(value = "/delSupplyDemand.action", method = RequestMethod.POST)
    @ResponseBody
    public SDController deSupplyDemand(@RequestBody String json , HttpServletRequest request){
        SupplyDemand temp = new Gson().fromJson(json, SupplyDemand.class);
        try
        {
            if(new SupplyDemandDAO().delSD(temp.getSdId()))
                this.setMessage("删除成功");
            else
                this.setMessage("删除失败");
            return this;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    //暂时未确定
//    @RequestMapping(value = "/passSD.action", method = RequestMethod.POST)
//    @ResponseBody
//    public SDController passSD(@RequestBody String json , HttpServletRequest request){
//        SupplyDemand temp = new Gson().fromJson(json, SupplyDemand.class);
//        if(new SupplyDemandDAO().setAsPass())
//            this.setMessage("删除成功");
//        else
//            this.setMessage("删除失败");
//        return this;
//    }


}
