package spring.controller;

import com.google.gson.Gson;
import dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import po.Message;
import po.SupplyDemand;
import po.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public ModelAndView initSDPage(Model model){
        SupplyDemandDAO sdDAO=new SupplyDemandDAO();
        try
        {
            List<Supply> supplyList=sdDAO.getAllSupplies();
            List<Demand> demandList=sdDAO.getAllDemands();
            model.addAttribute("supplyList",supplyList);
            model.addAttribute("demandList",demandList);
            return new ModelAndView("SupplyDemand","command",this);
        }
        catch (Exception e)
        {
            return null;
        }
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

    @RequestMapping(value = "/getSDInfo",method = RequestMethod.GET)
    public String getSDInfo(@RequestParam("sdId")String sdId, Model model)
    {
        SupplyDemandDAO sdDAO=new SupplyDemandDAO();
        UserInfoDAO uiDAO=new UserInfoDAO();
        try
        {
            SupplyDemand sd=sdDAO.getSDById(sdId);
            UserInfo ui=uiDAO.getUserInfo(sd.getUserId());
            MessageDAO mDAO=new MessageDAO();
            List<Message> mList=mDAO.getMessageById(sd.getSdId(),-1);
            List<AO> umList=new ArrayList<>();
//            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(Message m:mList)
            {
                UserInfo uitemp = uiDAO.getUserInfo(m.getUserId());
                AO a = new AO();
                if(uitemp != null)
                    a.setFirst(uitemp.getNickName());
                else
                    a.setFirst(m.getUserId());
                a.setSecond(m.getContent());
                umList.add(a);
            }
            model.addAttribute("sd",sd);
            model.addAttribute("ui",ui);
            model.addAttribute("umList",umList);
            return "SupplyDemandDetail";
        }
        catch (Exception e)
        {
            return "login";
        }
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
        return null;
        //return new SupplyDemandDAO().addDemand(temp);
    }

    @RequestMapping(value = "/Manage/deleteSD.action")
    @ResponseBody
    public SDController deleteSD(@RequestBody String json, HttpServletRequest request){
        try {
            if (new SupplyDemandDAO().delSD(new Gson().fromJson(json, AO.class).getFirst()))
                this.message = "Delete Success!";
            else
                this.message = "Delete Fail!";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            return this;
        }
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
    @RequestMapping(value = "/passSD.action", method = RequestMethod.POST)
    @ResponseBody
    public SDController passSD(@RequestBody String json , HttpServletRequest request) throws Exception {
        SupplyDemand temp = new Gson().fromJson(json, SupplyDemand.class);
        if(new SupplyDemandDAO().setAsPass(temp))
            this.setMessage("审核成功");
        else
            this.setMessage("审核失败");
        return this;
    }


}
