package spring.controller;

import com.google.gson.Gson;
import dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import po.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        ExhibitionDAO eDAO=new ExhibitionDAO();
        UserInfoDAO uiDAO=new UserInfoDAO();
        try{
            List<Exhibition> eList=eDAO.getAllExhibition();
            List<AO> eInfoList=new ArrayList<>();
            for(Exhibition e:eList)
            {
                UserInfo ui=uiDAO.getUserInfo(e.getUserId());
                AO a=new AO();
                a.setFirst(e.getTheme());
                a.setSecond(ui.getNickName());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = df.format(e.getEstablishTime());
                a.setThird(time);
                a.setFourth(e.getContent());
                a.setFifth(e.getExId());
                eInfoList.add(a);
            }
            model.addAttribute("eInfoList",eInfoList);
            return new ModelAndView("Exhibition","command",this);
        }
        catch(Exception e){
            return null;
        }
    }

    @RequestMapping(value = "getExhibitionInfo",method = RequestMethod.GET)
    public String getExhibitionInfo(@RequestParam("exhiId") String exhiId,Model model)
    {
        ExhibitionDAO eDAO=new ExhibitionDAO();
        UserInfoDAO uDAO=new UserInfoDAO();
        MessageDAO mDAO=new MessageDAO();
        UserInfoDAO uiDAO=new UserInfoDAO();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Exhibition e=eDAO.getExhibitionById(exhiId);
            UserInfo u=uDAO.getUserInfo(e.getUserId());
            List<Message> mList=mDAO.getMessageById(e.getExId(),-1);
            List<AO> umList=new ArrayList<>();
            for(Message m:mList)
            {
                AO a=new AO();
                a.setFirst(uiDAO.getUserInfo(m.getUserId()).getNickName());
                a.setSecond(m.getContent());
                a.setThird(df.format(m.getEstablishTime()));
                umList.add(a);
            }
            model.addAttribute("exhi",e);
            model.addAttribute("userInfo",u);
            model.addAttribute("umList",umList);
            return "ExhibitionDetail";
        }
        catch (Exception e)
        {
            return "Login";
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
        try
        {
            if (new ProductDAO().setAsPass(temp.getExId()))
                this.setMessage("审核成功");
            else
                this.setMessage("审核失败");
            return this;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
