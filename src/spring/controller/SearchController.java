package spring.controller;

import com.google.gson.Gson;
import dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;
import po.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @RequestMapping(value = "/search.html")
    public ModelAndView initSeacherPage(){
        return new ModelAndView("search","command",this);
    }

    @RequestMapping(value = "/demo.html")
    public ModelAndView initdemoPage()
    {
        return new ModelAndView("demo","command",this);
    }

    @RequestMapping(value = "/search.action", method = RequestMethod.POST)
    @ResponseBody
    public List searchFunc(@RequestBody String json, HttpServletRequest request)throws Exception {
        //第一个参数为搜索的种类，第二个参数为keywords,第三个参数为模糊查询还是精确chaxun
        AO temp = new Gson().fromJson(json, AO.class);
        List<AO> t = new ArrayList<AO>();
        User u = (User)request.getSession().getAttribute("currentUser");
        Boolean flag = false;

        if(temp.getFourth() != null && temp.getFourth().equals("Manage") && (new UserDAO().getUser(u.getUserId()).getType() >= 16 ))
            flag = true;

        if (temp.getFirst().equals("Exhibition")) {

            List<Exhibition> tt = null;
            if(temp.getThird().equals("MH"))
                tt = new ExhibitionDAO().getExhibitionByKeyword(temp.getSecond());
            else
                tt = new ExhibitionDAO().getExhibitionByTheme(temp.getSecond());
            for(Exhibition i : tt){

                if(flag && !i.getUserId().equals(u.getUserId()))
                    continue;

                AO ii = new AO();
                ii.setFirst(i.getTheme());
                ii.setSecond(i.getEstablishTime().toString());
                ii.setThird(i.getExId());
                ii.setFourth(i.getHits()+"");
                ii.setFifth(i.getIsPass()+"");
                t.add(ii);
            }


        }
        else if(temp.getFirst().equals("News")) {
            List<News> tt = null;
            if(temp.getThird().equals("MH"))
                tt = new NewsDAO().getNewsByKeyword(temp.getSecond());
            else
                tt = new NewsDAO().getNewsByTitle(temp.getSecond());
            for(News i : tt){

                if(flag && !i.getUserId().equals(u.getUserId()))
                    continue;

                AO ii = new AO();
                ii.setFirst(i.getTitle());
                ii.setSecond(i.getEstablishTime().toString());
                ii.setThird(i.getNewsId());
                ii.setFourth(i.getHits()+"");
                ii.setFifth(i.getIsPass()+"");
                t.add(ii);
            }
        }
        else if (temp.getFirst().equals("SD")){
            List<SupplyDemand> tt = null;
            if(temp.getThird().equals("MH") == true)
                tt = new SupplyDemandDAO().getSDByKeyWord(temp.getSecond());
            else
                tt = new SupplyDemandDAO().getSDByTitle(temp.getSecond());
            for(SupplyDemand i : tt){

                if(flag && !i.getUserId().equals(u.getUserId()))
                    continue;

                AO ii = new AO();
                ii.setFirst(i.getTitle());
                ii.setSecond(i.getStartTime().toString());
                ii.setThird(i.getSdId());
                ii.setFourth(i.getHits()+"");
                ii.setFifth(i.getIsPass()+"");
                t.add(ii);
            }
        }
        else if (temp.getFirst().equals("PRODUCT")){
            List<Product> tt = null;
            if(temp.getThird().equals("MH"))
                tt = new ProductDAO().getProductByKeyword(temp.getSecond());
            else
                tt = new ProductDAO().getProductByTitle(temp.getSecond());
            for(Product i : tt){

                if(flag && !i.getUserId().equals(u.getUserId()))
                    continue;

                AO ii = new AO();
                ii.setFirst(i.getProName());
                ii.setSecond("产品类别"+i.getProductType());
                ii.setThird(i.getProId());
                ii.setFourth(i.getHits()+"");
                ii.setFifth(i.getIsPass()+"");
                t.add(ii);
            }
        }
        return t;
    }
}
