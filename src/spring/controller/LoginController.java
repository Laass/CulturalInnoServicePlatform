package spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import dao.*;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.stereotype.Service;

import po.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("registeredUser")
public class LoginController {

    private String message;

    public String getMessage(){
        return message;
    }

    @RequestMapping(value = "/Login.html")
    public ModelAndView initLogin(HttpServletRequest request){
        request.setAttribute("message",message);
        return new ModelAndView("Login","command",this);
    }

    @RequestMapping(value = "login.action",method = RequestMethod.POST)
    public String validateLogin(@ModelAttribute("user")User user, HttpServletRequest request,HttpSession session, Model model)
    {
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

                //获取资讯
                NewsDAO nDAO=new NewsDAO();
                List<News> allNewsList=nDAO.getAllNews();
                List<News> latestNewsList=new ArrayList<>();
                if(allNewsList.size()<=displayNum)
                    latestNewsList=allNewsList;
                else
                    for(int i=allNewsList.size()-displayNum-1;i<allNewsList.size();i++)
                        latestNewsList.add(allNewsList.get(i));
                System.out.println("latestNewsList.size="+latestNewsList.size());
                model.addAttribute("latestNewsList",latestNewsList);

                //获取供应
                SupplyDemandDAO sdDAO=new SupplyDemandDAO();
                List<Supply> allSupplyList=sdDAO.getAllSupplies();
                List<Supply> latestSupplyList=new ArrayList<>();
                if(allSupplyList.size()<=displayNum)
                    latestSupplyList=allSupplyList;
                else
                    for(int i=allSupplyList.size()-displayNum+1;i<allSupplyList.size();i++)
                        latestSupplyList.add(allSupplyList.get(i));

                model.addAttribute("latestSupplyList",latestSupplyList);

                //获取需求
                List<Demand> allDemandList=sdDAO.getAllDemands();
                List<Demand> latestDemandList=new ArrayList<>();
                if(allDemandList.size()<=displayNum)
                    latestDemandList=allDemandList;
                else
                    for (int i=allDemandList.size()-displayNum+1;i<allDemandList.size();i++)
                        latestDemandList.add(allDemandList.get(i));
                model.addAttribute("latestDemandList",latestDemandList);

                //获取展会
                ExhibitionDAO eDAO=new ExhibitionDAO();
                List<Exhibition> allExhibitionList=eDAO.getAllExhibition();
                List<Exhibition> latestExhibitionList=new ArrayList<>();
                if(allExhibitionList.size()<=displayNum)
                    latestExhibitionList=allExhibitionList;
                else
                    for(int i=allExhibitionList.size()-displayNum+1;i<allExhibitionList.size();i++)
                        latestExhibitionList.add(allExhibitionList.get(i));
                model.addAttribute("latestExhibitionList",latestExhibitionList);

                //获取产品
                ProductDAO pDAO=new ProductDAO();
                List<Product> allProductList=pDAO.getAllProducts();
                List<Product> latestProductList=new ArrayList<>();
                if(allProductList.size()<=6)
                    latestProductList=allProductList;
                else
                    for(int i=allProductList.size()-displayNum+1;i<allProductList.size();i++)
                        latestProductList.add(allProductList.get(i));
                //model.addAttribute("latestProductList",latestProductList);

                //获取产品图片
                List<String> picPath=new ArrayList<>(latestProductList.size());
                for(Product p:latestProductList)
                {
                    ImageDAO iDAO=new ImageDAO();
                    Image i=iDAO.getFirstImageOfOriginId(p.getProId());
                    picPath.add(i.getStoreLocation());
                }
//                model.addAttribute("picPath",picPath);

                //组装成ao列表
                List<AO> pList=new ArrayList<>(latestProductList.size());
                for(int i=0;i<latestProductList.size();i++)
                {
                    AO a=new AO();
                    Product p=latestProductList.get(i);
                    a.setFirst(p.getProName());
                    a.setSecond(p.getProId());
                    a.setThird(p.getInfo());
                    a.setFourth(picPath.get(i));
                    a.setFifth(Double.toString(p.getPrice()));
                    a.setSixth(p.getHits().toString());
                    pList.add(a);
                }
                model.addAttribute("pList",pList);


                return "index";
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

    @RequestMapping(value = "/Register.html")
    public ModelAndView initRegister(){
        return new ModelAndView("Register","command",new User());
    }


    @RequestMapping(value = "registerUserInfo.action", method = RequestMethod.POST)
    public String registerUserInfo(@ModelAttribute("userInfo") UserInfo userInfo, HttpServletRequest request, HttpSession session, Model model)
    {
        User registeredUser=(User)session.getAttribute("registeredUser");
        userInfo.setUserId(registeredUser.getUserId());
        userInfo.setTel(registeredUser.getUserId());
        UserInfoDAO uiDAO=new UserInfoDAO();
        try
        {
            if( uiDAO.addUserInfo(userInfo) != null)
            {
                System.out.println("注册详细信息成功");
                return "index";
            }
        }
        catch (Exception e)
        {
            this.message = "注册详细信息失败";
            System.out.println(message);
        }
        return "redirect:/Login.html";
    }

    @RequestMapping(value = "registerUser.action", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("registerUser") User user, HttpServletRequest request, HttpSession session, ModelMap model)
    {
        UserDAO uDAO=new UserDAO();
        try
        {
            if( uDAO.addUser(user))
            {
                session.setAttribute("registeredUser",user);
                System.out.println("注册成功");
                return "RegisterUserInfo";
            }
        }
        catch (Exception e)
        {
            this.message = "注册失败";
            System.out.println(message);
        }
        return "redirect:/Login.html";
    }

    @RequestMapping(value = "getUserInfo.action", method = RequestMethod.GET)
    public String getUserInfo(HttpServletRequest request,HttpSession session,Model model)
    {
        UserInfoDAO uiDAO=new UserInfoDAO();
        User currentUser=(User)session.getAttribute("currentUser");
        try
        {
            UserInfo currentUserInfo = uiDAO.getUserInfo(currentUser.getUserId());
            model.addAttribute("userId", currentUserInfo.getUserId());
            model.addAttribute("nickName", currentUserInfo.getNickName());
            model.addAttribute("realName", currentUserInfo.getRealName());
            model.addAttribute("intro", currentUserInfo.getIntro());
            model.addAttribute("email", currentUserInfo.getEmail());
            model.addAttribute("address", currentUserInfo.getAddress());
            model.addAttribute("qq", currentUserInfo.getQq());
            model.addAttribute("tel", currentUserInfo.getTel());
            return "UserInfo";
        }
        catch(Exception e)
        {
            return "redirect:/Login.html";
        }
    }

    @RequestMapping(value = "testEditor",method = RequestMethod.GET)
    public String testEditor()
    {
        return "TestEditor";
    }

}
