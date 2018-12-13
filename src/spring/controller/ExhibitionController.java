package spring.controller;

import com.google.gson.Gson;
import dao.AO;
import dao.ExhibitionDAO;
import dao.ProductDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import po.Exhibition;
import po.Product;
import po.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ExhibitionController {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @RequestMapping(value = "/Exhibition.html")
    public ModelAndView initEPage(Model model){
        return new ModelAndView("Exhibition","command",this);
    }

    @RequestMapping(value = "getExhibition.action", method = RequestMethod.POST)
    @ResponseBody
    public List getExhibitionByPage(@RequestParam("page")String page){
        //第一个参数存用户id，第二个参数存page
//        AO temp = new Gson().fromJson(json, AO.class);
        return new ExhibitionDAO().getExhibitionByPage(Integer.parseInt(page));
    }

    @RequestMapping(value = "/getUserExhibition.action", method = RequestMethod.POST)
    @ResponseBody
    public List getUserExhibition(@RequestBody String json){
        User u = new Gson().fromJson(json, User.class);
        return new ExhibitionDAO().getExhibitionByUserId(u.getUserId());
    }

    @RequestMapping(value = "/searchExhibition.action", method = RequestMethod.POST)
    @ResponseBody
    public List searchExhibition(@RequestBody String json , HttpServletRequest request){
        //第一个参数是模糊查询还是精确查询，第二个参数关键字
        AO temp = new Gson().fromJson(json, AO.class);

        if(temp.getFirst().equals("模糊查询"))
            return new ExhibitionDAO().getExhibitionByKeyword(temp.getSecond());
        else
            return new ExhibitionDAO().getExhibitionByTheme(temp.getSecond());
    }

    @RequestMapping(value = "/addExhibition.action", method = RequestMethod.POST)
    @ResponseBody
    public Exhibition addExhibition(@RequestBody String json , HttpServletRequest request){
        Exhibition temp = new Gson().fromJson(json, Exhibition.class);

        return new ExhibitionDAO().addExhibition(temp);
    }

    @RequestMapping(value = "/delExhibition.action", method = RequestMethod.POST)
    @ResponseBody
    public ExhibitionController delExhibition(@RequestBody String json , HttpServletRequest request){
        Exhibition temp = new Gson().fromJson(json, Exhibition.class);
        if(new ExhibitionDAO().delExhibition(temp.getExId()))
            this.setMessage("删除成功");
        else
            this.setMessage("删除失败");
        return this;
    }

    @RequestMapping(value = "/passExhibition.action", method = RequestMethod.POST)
    @ResponseBody
    public ExhibitionController passProduct(@RequestBody String json , HttpServletRequest request){
        Exhibition temp = new Gson().fromJson(json, Exhibition.class);
        if(new ProductDAO().setAsPass(temp.getExId()))
            this.setMessage("审核成功");
        else
            this.setMessage("审核失败");
        return this;
    }
}
