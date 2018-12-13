package spring.controller;

import com.google.gson.Gson;
import dao.AO;
import dao.MessageDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Message;

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
        return new MessageDAO().getMessageInfo(temp.getMesId());
    }

    @ModelAttribute("pageMessageList")
    @RequestMapping(value = "/getMessageByPage.action")
    @ResponseBody
    public List getMessageByPage(@RequestBody String json){
        //第一个参数为id，第二个为页数
        AO temp = new Gson().fromJson(json, AO.class);
        List t = new MessageDAO().getMessageById(temp.getFirst(), Integer.parseInt(temp.getSecond()));
        return t;
    }

    @RequestMapping(value = "/addMessage.action")
    @ResponseBody
    public MessageController addMessage(@RequestBody String json){
        //第一个参数为id，第二个为页数
        Message temp = new Gson().fromJson(json, Message.class);
        if(new MessageDAO().addMessage(temp) != null)
            this.setMessage("添加成功");
        else
            this.setMessage("添加失败");
        return this;
    }

    @RequestMapping(value = "/delMessage.action")
    @ResponseBody
    public MessageController delMessage(@RequestBody String json){
        Message temp = new Gson().fromJson(json, Message.class);
        if(new MessageDAO().delMessage(temp.getMesId()) != null)
            this.setMessage("添加成功");
        else
            this.setMessage("添加失败");
        return this;
    }

}
