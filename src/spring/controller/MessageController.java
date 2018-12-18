package spring.controller;

import com.google.gson.Gson;
import dao.AO;
import dao.MessageDAO;
import dao.UserInfoDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Message;
import po.User;
import po.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @RequestMapping(value = "/getMessageInfo.action")
    @ResponseBody
    public Message getMessageInfo(@RequestBody String json){
        Message temp = new Gson().fromJson(json, Message.class);
        try
        {
            return new MessageDAO().getMessageInfo(temp.getMesId());
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @ModelAttribute("pageMessageList")
    @RequestMapping(value = "/getMessageByPage.action")
    @ResponseBody
    public List getMessageByPage(@RequestBody String json){
        //第一个参数为id，第二个为页数
        AO temp = new Gson().fromJson(json, AO.class);
        try
        {
            List t = new MessageDAO().getMessageById(temp.getFirst(), Integer.parseInt(temp.getSecond()));
            return t;
        }
        catch(Exception e)
        {
            return null;
        }
    }


    @RequestMapping(value = "/addMessage.action")
    @ResponseBody
    public MessageController addMessage(@RequestBody String json, HttpServletRequest request){

        Message temp = new Gson().fromJson(json, Message.class);
        User u=(User)request.getSession().getAttribute("currentUser");

        if(u == null) {
            this.setMessage("fail,please login");
            return this;
        }

        temp.setEstablishTime(new Timestamp(new Date().getTime()));
        temp.setUserId(u.getUserId());
        try
        {
            if(new MessageDAO().addMessage(temp) != null)
                this.setMessage("Success");
            else
                this.setMessage("fail");

            MessageDAO mDAO = new MessageDAO();
            UserInfoDAO uiDAO=new UserInfoDAO();
            List<Message> mList = mDAO.getMessageById(temp.getOriginId(),-1);
            List<AO> umList = new ArrayList<>();//存储用户名和留言
            for (Message m : mList)
            {
                UserInfo ui = uiDAO.getUserInfo(m.getUserId());
                AO a = new AO();
                a.setFirst(ui.getNickName());
                a.setSecond(m.getContent());
                umList.add(a);
            }
            request.setAttribute("umList", umList);
            return this;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "/delMessage.action")
    @ResponseBody
    public MessageController delMessage(@RequestBody String json){
        Message temp = new Gson().fromJson(json, Message.class);
        try
        {
            if(new MessageDAO().delMessage(temp.getMesId()) != null)
                this.setMessage("添加成功");
            else
                this.setMessage("添加失败");
            return this;
        }
        catch(Exception e)
        {
            return null;
        }

    }

}
