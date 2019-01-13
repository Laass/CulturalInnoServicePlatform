package spring.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import po.User;

@Controller
public class CommunicateController {

    @RequestMapping("/communicate")
    public ModelAndView config(@RequestParam("proUserId") String proUserId, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("username",((User)request.getSession().getAttribute("currentUser")).getUserId());
        request.setAttribute("proUserId",proUserId);
        return new ModelAndView("Communicate");
    }
}