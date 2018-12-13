package spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import dao.UserDAO;
import dao.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.stereotype.Service;

import po.User;
import po.UserInfo;

@Controller
@SessionAttributes("registeredUser")
public class LoginController {

    private String message;

    public String getMessage(){
        return message;
    }

    @RequestMapping(value = "/Login.html")
    public ModelAndView initLogin(){
        return new ModelAndView("login","command",this);
    }

    @RequestMapping(value = "login.action", method = RequestMethod.POST)
    public LoginController validateLogin(@ModelAttribute("user")User user, HttpServletRequest request,HttpSession session, Model model)
    {
        System.out.println(user.getUserId());
        session.setAttribute("currentUser",user);
        UserDAO ud = new UserDAO();
        if( ud.validateUser(user.getUserId(),user.getPassword()) ){
            this.message="登录成功";
            model.addAttribute("message",message);
            return this;
        }
        this.message = "用户名/密码错误";
        model.addAttribute("message",message);
        return this;
    }

    @RequestMapping(value = "/Register.html")
    public ModelAndView initRegister(){
        return new ModelAndView("Register","command",new User());
    }


    @RequestMapping(value = "registerUserInfo.action", method = RequestMethod.POST)
    public String registerUserInfo(@ModelAttribute("userInfo") UserInfo userInfo, HttpServletRequest request, HttpSession session, Model model)
    {
        User registeredUser=(User)session.getAttribute("registeredUser");
        userInfo.setUserId(registeredUser.getUserId());
        UserInfoDAO uiDAO=new UserInfoDAO();
        if( uiDAO.addUserInfo(userInfo) != null)
        {
            return "index";
        }
        this.message = "注册失败";
        return "redirect:/Login.html";
    }

    @RequestMapping(value = "registerUser.action", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("registerUser") User user, HttpServletRequest request, HttpSession session, ModelMap model)
    {
        UserDAO uDAO=new UserDAO();
        if( uDAO.addUser(user))
        {
            session.setAttribute("registeredUser",user);
            return "RegisterUserInfo";
        }
        this.message = "注册失败";
        return "redirect:/Login.html";
    }

    @RequestMapping(value = "getUserInfo.action", method = RequestMethod.GET)
    public String getUserInfo(HttpServletRequest request,HttpSession session,Model model)
    {
        UserInfoDAO uiDAO=new UserInfoDAO();
        User currentUser=(User)session.getAttribute("currentUser");
        UserInfo currentUserInfo=uiDAO.getUserInfo(currentUser.getUserId());
        model.addAttribute("userId",currentUserInfo.getUserId());
        model.addAttribute("nickName",currentUserInfo.getNickName());
        model.addAttribute("realName",currentUserInfo.getRealName());
        model.addAttribute("intro",currentUserInfo.getIntro());
        model.addAttribute("email",currentUserInfo.getEmail());
        model.addAttribute("address",currentUserInfo.getAddress());
        model.addAttribute("qq",currentUserInfo.getQq());
        model.addAttribute("tel",currentUserInfo.getTel());
        return "UserInfo";
    }

}
