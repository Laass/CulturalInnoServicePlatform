package spring.controller;

import dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import po.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequestMapping(value = "/Manage")
public class ManageLoginController {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @RequestMapping(value = "/ManageLogin.html", method = RequestMethod.GET)
    public String initManageLogin(@RequestParam("flag")String flag, HttpServletRequest request, HttpSession session){
        request.setAttribute("message",message);
        if(session.getAttribute("currentUser")!=null && flag.equals("in"))
        {
            return "Manage/ManageIndex";
        }
        return"Manage/ManageLogin";
    }

    @RequestMapping(value = "login.action",method = RequestMethod.POST)
    public String validateLogin(@ModelAttribute("user") User user, HttpServletRequest request, HttpSession session, Model model)
    {
        System.out.println(user.getUserId());


        UserDAO ud = new UserDAO();
        int displayNum=6;
        try
        {
            if( ud.validateUser(user.getUserId(),user.getPassword()) )
            {
                this.message="登录成功";
                user = ud.getUser(user.getUserId());
                session.setAttribute("currentUser",user);
                model.addAttribute("message",message);

                return "Manage/ManageIndex";
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            this.message = "用户名/密码错误";
            model.addAttribute("message",message);
        }
        return "Manage/ManageLogin";
    }

    @RequestMapping(value = "ManageWelcome.html")
    public ModelAndView initManageWelcomPage(HttpServletRequest request, HttpSession session, Model model) throws Exception{
        User user = (User)request.getSession().getAttribute("currentUser");
        if(user.getType() >= 16) {
            model.addAttribute("sdNumbers", new SupplyDemandDAO().getUserSD(user.getUserId()).size());
            model.addAttribute("newsNumbers", new NewsDAO().getNewsByUserId(user.getUserId()).size());
            model.addAttribute("exhibitionNumbers", new ExhibitionDAO().getExhibitionByUserId(user.getUserId()).size());
            model.addAttribute("productNumbers", new ProductDAO().getUserProducts(user.getUserId()).size());
        }
        else {
            model.addAttribute("sdNumbers", new SupplyDemandDAO().getAllSD().size());
            model.addAttribute("newsNumbers", new NewsDAO().getAllNews().size());
            model.addAttribute("exhibitionNumbers", new ExhibitionDAO().getAllExhibition().size());
            model.addAttribute("productNumbers", new ProductDAO().getAllProducts().size());
        }
        return new ModelAndView("ManageWelcome", "command", this);
    }

    @RequestMapping(value = "delPortrait")
    public ModelAndView delPortrait(HttpServletRequest request,HttpSession session,Model model)
    {
        User u=(User)session.getAttribute("currentUser");
        try
        {
            ImageDAO iDAO = new ImageDAO();
            Image i = iDAO.getFirstImageOfOriginId(u.getUserId());
            String location = i.getStoreLocation();
            File portrait = new File("C:\\Users\\user0\\IdeaProjects\\CulturalInnoServicePlatform\\web\\WEB-INF\\images\\portrait\\" + location.substring(location.lastIndexOf('/') + 1));
            if (portrait.exists() && portrait.isFile())
                portrait.delete();
            iDAO.deleteImage(u.getUserId());
            return new ModelAndView("ManageWelcome", "command", this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ModelAndView("ManageLogin","command",this);
    }

    @RequestMapping(value = "editPasswd")
    @ResponseBody
    public ManageLoginController editPasswd(String userId,String password,HttpSession session)
    {
        try
        {
            new UserDAO().updatePasswd(userId,password);
            this.setMessage("密码修改成功");
            return this;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "goToEditPasswd")
    public String goToEditPasswd()
    {
        return "Manage/EditPasswd";
    }

}
