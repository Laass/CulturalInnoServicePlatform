package spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;

import po.*;

import java.io.UnsupportedEncodingException;
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

    @RequestMapping(value = "/index.html")
    public ModelAndView initIndex(Model model) throws Exception {
        intiIndexData(model, 6);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "login.action",method = RequestMethod.POST)
    public String validateLogin(@ModelAttribute("user")User user, HttpServletRequest request,HttpSession session, Model model) throws Exception
    {
        System.out.println(user.getUserId());

        UserDAO ud = new UserDAO();
        int displayNum=6;
        try
        {
            if( ud.validateUser(user.getUserId(),user.getPassword()) )
            {
                this.message="登录成功";
                model.addAttribute("message",message);
                session.setAttribute("currentUser",new UserDAO().getUser(user.getUserId()));
                intiIndexData(model, displayNum);
                session.setAttribute("loginMessage","");
                return "index";
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            this.message = "用户名/密码错误";
            model.addAttribute("message",message);
        }
        session.setAttribute("loginMessage","用户名/密码错误");
        return "Login";
    }

    @RequestMapping(value = "/Register.html")
    public ModelAndView initRegister(){
        return new ModelAndView("Register","command",new User());
    }


    @RequestMapping(value = "registerUserInfo.action", method = RequestMethod.POST)
    public String registerUserInfo(@ModelAttribute("userInfo") UserInfo userInfo, HttpServletRequest request, HttpSession session, Model model)
    {
        try
        {
            request.setCharacterEncoding("UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        User registeredUser=(User)session.getAttribute("currentUser");
        userInfo.setUserId(registeredUser.getUserId());
        userInfo.setTel(registeredUser.getUserId());
        UserInfoDAO uiDAO=new UserInfoDAO();
        try
        {
            if( uiDAO.addUserInfo(userInfo) != null)
            {
                System.out.println("注册详细信息成功");
                return "redirect:/index.html";
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
                session.setAttribute("currentUser",user);
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
            boolean unExist=currentUserInfo==null;
            model.addAttribute("userId", unExist?"":currentUserInfo.getUserId());
            model.addAttribute("nickName", unExist?"":currentUserInfo.getNickName());
            model.addAttribute("realName", unExist?"":currentUserInfo.getRealName());
            model.addAttribute("intro", unExist?"":currentUserInfo.getIntro());
            model.addAttribute("email", unExist?"":currentUserInfo.getEmail());
            model.addAttribute("address", unExist?"":currentUserInfo.getAddress());
            model.addAttribute("qq", unExist?"":currentUserInfo.getQq());
            model.addAttribute("tel", unExist?"":currentUserInfo.getTel());
            List<Image> i=new ImageDAO().getImageByOriginId(currentUser.getUserId(),1);
            if(i!=null)
                model.addAttribute("imgLocation",i.get(0).getStoreLocation());
            return "UserInfo";
        }
        catch(Exception e)
        {
            return "redirect:/Login.html";
        }
    }

    @RequestMapping(value="editUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public String editUserInfo(String nickName,String realName,String intro,
                               String email,String address,String qq,String tel,
                               HttpSession session)
    {
        User u=(User)session.getAttribute("currentUser");
        if(u==null)
            return "Login Required";
        UserInfo ui=new UserInfo(
                u.getUserId(),
                nickName,
                realName,
                intro,
                email,
                address,
                qq,
                tel
        );
        UserInfoDAO uiDAO=new UserInfoDAO();
        try
        {
            if(uiDAO.getUserInfo(u.getUserId())!=null)
                uiDAO.modifyUserInfo(ui);
            else
                uiDAO.addUserInfo(ui);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public static String getPortraitLoc(HttpSession session)
    {
        User u=(User)session.getAttribute("currentUser");
        if(u!=null)
        {
            List<Image> pics= null;
            try
            {
                pics = new ImageDAO().getImageByOriginId(u.getUserId(),1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
            if(pics!=null)
            {
                return pics.get(0).getStoreLocation();
            }
        }
        return null;
    }

    @RequestMapping(value = "/potrait")
    public String potraitClick(Model model, HttpServletRequest request, HttpSession session) throws Exception {
        if(session.getAttribute("currentUser") == null)
            return "redirect:/Login.html";
        else
            return "redirect:/Manage/ManageLogin.html?flag=in";
    }

    public void intiIndexData(Model model, int displayNum) throws Exception{
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
    }



}
