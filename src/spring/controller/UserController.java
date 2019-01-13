package spring.controller;

import com.google.gson.Gson;
import dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import po.User;

@Controller
public class UserController {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @RequestMapping(value = "/Manage/Authorize", method = RequestMethod.POST)
    @ResponseBody
    public UserController Authorize(@RequestBody String json) throws Exception {
        User user = new Gson().fromJson(json,User.class);
        this.message = "用户不存在！User does not existe！";
        if(new UserDAO().updateUserCompetence(user.getUserId(), user.getType()))
            this.message = "分配成功！Authorize Successfully";
        return this;
    }

}
