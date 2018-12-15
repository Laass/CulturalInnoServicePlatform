package spring.controller;

import com.google.gson.Gson;
import dao.AO;
import dao.NewsDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import po.News;

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
        List<News> allNewsList=nDAO.getAllNews();
        request.setAttribute("allNewsList",allNewsList);
        return allNewsList;
    }

    @RequestMapping(value = "/getAllPassedNews.action")
    @ResponseBody
    @ModelAttribute("allPassedNewsList")
    public List<News> getAllPassedNews(HttpServletRequest request)
    {
        NewsDAO nDAO=new NewsDAO();
        List<News> l=nDAO.getAllNews();
        List<News> passedList=new ArrayList<>();
        for(News n:l)
            if(n.getIsPass()==(byte)1)
                passedList.add(n);
        System.out.println("allPassedNewsListallPassedNewsListallPassedNewsList");
        request.setAttribute("allPassedNewsList",passedList);
        return passedList;
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
        new NewsDAO().addNews(new Gson().fromJson(json, News.class));
        return this;
    }

    @RequestMapping(value = "/delNews.action")
    @ResponseBody
    public NewsController adelNew(@RequestBody String json, HttpServletRequest request){
        new NewsDAO().delNews(new Gson().fromJson(json, News.class).getNewsId());
        return this;
    }

    @RequestMapping(value = "/getNew.action")
    @ResponseBody
    public News getNew(@RequestBody String json, HttpServletRequest request){
        return new NewsDAO().getNewsById(new Gson().fromJson(json, News.class).getNewsId());
    }

    @ModelAttribute("pageNewsList")
    @RequestMapping(value = "/getNewByPage.action")
    @ResponseBody
    public List getNewsByPage(@RequestParam("page")String page, HttpServletRequest request){
        //第一个参数存用户id，第二个参数存page
//        AO temp = new Gson().fromJson(json, AO.class);
        return new NewsDAO().getNewsByPage(Integer.parseInt(page));
    }

    @ModelAttribute("searchNewsList")
    @RequestMapping(value = "/searchNews.action")
    public List searchNews(@RequestBody String json , HttpServletRequest request){
        //第一个参数是模糊查询还是精确查询，第二个参数关键字
        AO temp = new Gson().fromJson(json, AO.class);

        if(temp.getFirst().equals("模糊查询"))
            return new NewsDAO().getNewsByKeyword(temp.getFirst());
        else
            return new NewsDAO().getNewsByTitle(temp.getFirst());
    }

    //NewsDAO的审核方法改为String参数
    @RequestMapping(value = "/passNew.action", method = RequestMethod.POST)
    @ResponseBody
    public NewsController passProduct(@RequestBody String json , HttpServletRequest request){
        News temp = new Gson().fromJson(json, News.class);
        if(new NewsDAO().setAsPass(temp.getNewsId()))
            this.setMessage("审核成功");
        else
            this.setMessage("审核失败");
        return this;
    }

}
