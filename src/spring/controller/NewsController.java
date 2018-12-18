package spring.controller;

import com.google.gson.Gson;
import dao.AO;
import dao.MessageDAO;
import dao.NewsDAO;
import dao.UserInfoDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import po.Message;
import po.News;
import po.UserInfo;

import javax.lang.model.element.NestingKind;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsController {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @RequestMapping(value = "/getAllNews.action")
    @ResponseBody
    @ModelAttribute("allNewsList")
    public List<News> getAllNews(HttpServletRequest request)
    {
        NewsDAO nDAO=new NewsDAO();
        System.out.println("allNewsListallNewsListallNewsList");
        try
        {
            List<News> allNewsList = nDAO.getAllNews();
            request.setAttribute("allNewsList", allNewsList);
            return allNewsList;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "/getAllPassedNews.action")
    @ResponseBody
    @ModelAttribute("allPassedNewsList")
    public List<News> getAllPassedNews(HttpServletRequest request)
    {
        NewsDAO nDAO=new NewsDAO();
        try
        {
            List<News> l = nDAO.getAllNews();
            List<News> passedList = new ArrayList<>();
            for (News n : l)
                if (n.getIsPass() == (byte) 1)
                    passedList.add(n);
            System.out.println("allPassedNewsListallPassedNewsListallPassedNewsList");
            request.setAttribute("allPassedNewsList", passedList);
            return passedList;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "/News.html")
    public ModelAndView initNewsPage(HttpServletRequest request)
    {

        getAllNews(request);
        System.out.println("NewsNewsNewsNews");
        return new ModelAndView("News","command",this);
    }

    @RequestMapping(value = "/addNews.action")
    @ResponseBody
    public NewsController addNew(@RequestBody String json, HttpServletRequest request){
        try
        {
            new NewsDAO().addNews(new Gson().fromJson(json, News.class));
            return this;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "/delNews.action")
    @ResponseBody
    public NewsController adelNew(@RequestBody String json, HttpServletRequest request){
        try
        {
            new NewsDAO().delNews(new Gson().fromJson(json, News.class).getNewsId());
            return this;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "/getNew.action")
    @ResponseBody
    public News getNew(@RequestBody String json, HttpServletRequest request){
        try
        {
            return new NewsDAO().getNewsById(new Gson().fromJson(json, News.class).getNewsId());
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @ModelAttribute("pageNewsList")
    @RequestMapping(value = "/getNewByPage.action")
    @ResponseBody
    public List getNewsByPage(@RequestParam("page")String page, HttpServletRequest request){
        //第一个参数存用户id，第二个参数存page
//        AO temp = new Gson().fromJson(json, AO.class);
        try
        {
            return new NewsDAO().getNewsByPage(Integer.parseInt(page));
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @ModelAttribute("searchNewsList")
    @RequestMapping(value = "/searchNews.action")
    public List searchNews(@RequestBody String json , HttpServletRequest request){
        //第一个参数是模糊查询还是精确查询，第二个参数关键字
        AO temp = new Gson().fromJson(json, AO.class);

        try
        {
            if(temp.getFirst().equals("模糊查询"))
                return new NewsDAO().getNewsByKeyword(temp.getFirst());
            else
                return new NewsDAO().getNewsByTitle(temp.getFirst());
        }
        catch (Exception e)
        {
            return null;
        }
    }

    //NewsDAO的审核方法改为String参数
    @RequestMapping(value = "/passNew.action", method = RequestMethod.POST)
    @ResponseBody
    public NewsController passProduct(@RequestBody String json , HttpServletRequest request){
        News temp = new Gson().fromJson(json, News.class);
        try
        {
            if(new NewsDAO().setAsPass(temp.getNewsId()))
                this.setMessage("审核成功");
            else
                this.setMessage("审核失败");
            return this;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "getNewsById",method = RequestMethod.GET)
    public String getNewsById(@RequestParam("newsId") String newsId, Model model)
    {
        NewsDAO nDAO=new NewsDAO();
        UserInfoDAO uiDAO=new UserInfoDAO();
        try
        {
            News n=nDAO.getNewsById(newsId);
            UserInfo u=uiDAO.getUserInfo(n.getUserId());
            model.addAttribute("news",n);
            model.addAttribute("userInfo",u);

            MessageDAO mDAO = new MessageDAO();
            List<Message> mList = mDAO.getMessageById(newsId,-1);
            List<AO> umList = new ArrayList<>();//存储用户名和留言
            for (Message m : mList)
            {
                UserInfo ui = uiDAO.getUserInfo(m.getUserId());
                AO a = new AO();
                a.setFirst(ui.getNickName());
                a.setSecond(m.getContent());
                umList.add(a);
            }
            model.addAttribute("umList", umList);
            return "NewsDetail";
        }
        catch (Exception e)
        {
            return "Login";
        }
    }

}
