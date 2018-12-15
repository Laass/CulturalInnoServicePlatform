package spring.controller;

import com.google.gson.Gson;
import dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import po.Exhibition;
import po.Product;
import po.User;
import po.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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

    @ModelAttribute("allExhibitionList")
    public List<Exhibition> getAllExhibitionList()
    {
        ExhibitionDAO eDAO=new ExhibitionDAO();
        try
        {
            return eDAO.getAllExhibition();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "/Exhibition.html")
    public ModelAndView initEPage(Model model){
        //仅做测试用
        ExhibitionDAO eDAO=new ExhibitionDAO();
        UserInfoDAO uiDAO=new UserInfoDAO();
        AO a=new AO();
        try{
            Exhibition e=eDAO.getExhibitionById("33598a66959c4e4b8edca8c470f31199");
            UserInfo ui=uiDAO.getUserInfo(e.getUserId());
            a.setFirst(e.getTheme());
            a.setSecond(ui.getNickName());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = df.format(e.getEstablishTime());
            a.setThird(time);
            a.setFourth(e.getContent());
            model.addAttribute("exhi",a);
            return new ModelAndView("Exhibition","command",this);
        }
        catch(Exception e){
            return null;
        }
    }

    @RequestMapping(value = "getExhibition.action", method = RequestMethod.POST)
    @ResponseBody
    public List getExhibitionByPage(@RequestParam("page")String page){
        //第一个参数存用户id，第二个参数存page
//        AO temp = new Gson().fromJson(json, AO.class);
        try{
            return new ExhibitionDAO().getExhibitionByPage(Integer.parseInt(page));
        }
       catch(Exception e){
            return null;
       }
    }

    @RequestMapping(value = "/getUserExhibition.action", method = RequestMethod.POST)
    @ResponseBody
    public List getUserExhibition(@RequestBody String json){
        User u = new Gson().fromJson(json, User.class);
        try{
            return new ExhibitionDAO().getExhibitionByUserId(u.getUserId());
        }
        catch(Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/searchExhibition.action", method = RequestMethod.POST)
    @ResponseBody
    public List searchExhibition(@RequestBody String json , HttpServletRequest request){
        //第一个参数是模糊查询还是精确查询，第二个参数关键字
        AO temp = new Gson().fromJson(json, AO.class);

        try {
            if (temp.getFirst().equals("模糊查询"))
                return new ExhibitionDAO().getExhibitionByKeyword(temp.getSecond());
            else
                return new ExhibitionDAO().getExhibitionByTheme(temp.getSecond());
        }
        catch(Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/addExhibition.action", method = RequestMethod.POST)
    @ResponseBody
    public Exhibition addExhibition(@RequestBody String json , HttpServletRequest request){
        Exhibition temp = new Gson().fromJson(json, Exhibition.class);

        try{
            return new ExhibitionDAO().addExhibition(temp);
        }
        catch(Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/delExhibition.action", method = RequestMethod.POST)
    @ResponseBody
    public ExhibitionController delExhibition(@RequestBody String json , HttpServletRequest request){
        Exhibition temp = new Gson().fromJson(json, Exhibition.class);
        try{
            if (new ExhibitionDAO().delExhibition(temp.getExId()))
                this.setMessage("删除成功");
            else
                this.setMessage("删除失败");
            return this;
        }
        catch(Exception e){
            return null;
        }
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
