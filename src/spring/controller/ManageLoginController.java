package spring.controller;

import dao.*;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import po.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @RequestMapping(value = "/ManageLogin.html")
    public ModelAndView initManageLogin(HttpServletRequest request){
        request.setAttribute("message",message);
        return new ModelAndView("ManageLogin","command",this);
    }

    @RequestMapping(value = "login.action",method = RequestMethod.POST)
    public String validateLogin(@ModelAttribute("user") User user, HttpServletRequest request, HttpSession session, Model model)
    {
        if(session.getAttribute("currentUser")!=null)
        {
            this.message="登录成功";
            model.addAttribute("message",message);
            return "Manage/ManageIndex";
        }

        System.out.println(user.getUserId());

        session.setAttribute("currentUser",user);
        UserDAO ud = new UserDAO();
        int displayNum=6;
        try
        {
            if( ud.validateUser(user.getUserId(),user.getPassword()) )
            {
                this.message="登录成功";
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
        return "Login";
    }

    @RequestMapping(value = "ManageWelcome.html")
    public ModelAndView initManageWelcomPage(HttpServletRequest request, HttpSession session, Model model) throws Exception{
        User user = (User)request.getSession().getAttribute("currentUser");
        if(user.getType() != 0) {
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
            new ImageDAO().deleteImage(u.getUserId());
            return new ModelAndView("ManageWelcome", "command", this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ModelAndView("ManageLogin","command",this);
    }

}