package spring.controller;

import com.alibaba.fastjson.JSON;
import dao.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;
import po.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping(value = "/Manage")
public class UploadController
{
    @RequestMapping(value = "/upload.html",method = RequestMethod.GET)
    public ModelAndView initUpload()
    {
        return new ModelAndView("upload","command",this);
    }

    @RequestMapping(value = "editEssay",method = RequestMethod.GET)
    public ModelAndView ManagerEditor()
    {
        return new ModelAndView("EditEssay","command",this);
    }

    @RequestMapping(value = "editProduct",method = RequestMethod.GET)
    public ModelAndView initProductEdit()
    {
        return new ModelAndView("EditProduct","command",this);
    }

    //form上传多图用
    @RequestMapping(value = "uploadFile",method=RequestMethod.POST)
    public String getFiles(@RequestParam("files") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        User u=(User)session.getAttribute("currentUser");
        Product p=(Product)session.getAttribute("currentProduct");
        if(files!=null&&files.length>0)
        {
            System.out.println(files);
            for(MultipartFile image:files)
            {
                String path="C:\\Users\\user0\\IdeaProjects\\CulturalInnoServicePlatform\\web\\WEB-INF\\images\\product\\";
                String result=saveFile(image,path,request);
                int pathIndex=result.indexOf("data");
                com.alibaba.fastjson.JSONObject json= JSON.parseObject(result);
                String innerPath=json.getString("data");
                innerPath=innerPath.substring(2,innerPath.length()-2);
                innerPath=innerPath.replace("\\","/");
                innerPath=innerPath.replace("//","/");
                innerPath=innerPath.substring(innerPath.indexOf("image"));
                try
                {
                    new ImageDAO().addImage(new Image(UUID.randomUUID().toString().replace("-",""), p.getProId(), innerPath));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    return "Login";
                }
            }
            return "Manage/ManageWelcome";
        }
        return "Login";
    }


    //wangEditor上传图片用
    @RequestMapping(value = "uploadFileFromWang",method = RequestMethod.POST)
    @ResponseBody
    public String getFileFromWang(@RequestParam("image") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        String path="C:\\Users\\user0\\IdeaProjects\\CulturalInnoServicePlatform\\web\\WEB-INF\\images\\product\\";
        return saveFile(file,path,request);
    }

    //保存图片用
    private String saveFile(MultipartFile file,String path, HttpServletRequest request)
    {
        if(file!=null)
        {
            String type;
            String fileName=file.getOriginalFilename();
            System.out.println("original file name: "+fileName);
            type=fileName.contains(".")?fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()):null;
            if(type!=null)
            {
                if (type.equalsIgnoreCase("gif") || type.equalsIgnoreCase("png") || type.equalsIgnoreCase("jpg"))
                {
                    String realPath=request.getSession().getServletContext().getRealPath("/");
                    String trueFileName=String.valueOf(System.currentTimeMillis()+fileName);
                    path+=trueFileName;
                    System.out.println("path to store file: "+path);
                    try
                    {
                        file.transferTo(new File(path));
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                        System.out.println("save file error");
                        return "save file error";
                    }
                    System.out.println("save file success");
                    JSONObject json=new JSONObject();
                    JSONArray array=new JSONArray();
                    array.put(path);
                    json.put("errno",0);
                    json.put("data",array);
                    System.out.println(json.toString());
                    return json.toString();
                }
                else
                {
                    System.out.println("unacceptable file type");
                    return "unacceptable file type";
                }
            }
            else
            {
                System.out.println("empty file type");
                return "empty file type";
            }
        }
        else
        {
            System.out.println("no file found");
            return "no file found";
        }
    }

    @RequestMapping(value ="addEssay",method = RequestMethod.POST)
    @ResponseBody
    public String addEssay(String essayTitle, String essayContent, String essayType,
                           String sdType,String startTime,String endTime,
                           HttpSession session)
    {
            User u=(User)session.getAttribute("currentUser");
            if(u==null)
                return "Login Required";
        try
        {
            switch (essayType)
            {
                case "exhi":
                    Exhibition e=new Exhibition(
                            UUID.randomUUID().toString().replace("-",""),
                            u.getUserId(),
                            essayTitle,
                            essayContent,
                            new Timestamp(new Date().getTime()),
                            0,
                            (byte)(0)
                    );
                    new ExhibitionDAO().addExhibition(e);
                    break;
                case "news":
                    News n=new News(
                            UUID.randomUUID().toString().replace("-",""),
                            u.getUserId(),
                            essayTitle,
                            essayContent,
                            new Timestamp(new Date().getTime()),
                            0,
                            (byte)0
                    );
                    new NewsDAO().addNews(n);
                    break;
                case "sd":
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date startDate=sdf.parse(startTime);
                    Date endDate=sdf.parse(endTime);
                    Character type=sdType.equals("supply")?'S':'D';
                    SupplyDemand sd=new SupplyDemand(
                            UUID.randomUUID().toString().replace("-",""),
                            u.getUserId(),
                            essayTitle,
                            essayContent,
                            new Timestamp(startDate.getTime()),
                            new Timestamp(endDate.getTime()),
                            0,
                            (byte)0,
                            type.toString()
                    );
                    new SupplyDemandDAO().addSD(sd);
                    break;
                default:
                    return "error";
            }
            return "success";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value = "addProduct",method = RequestMethod.POST)
    @ResponseBody
    public String addProduct(String proName,@RequestParam("price") Double price,String proType,String info,
                             HttpSession session)
    {
        User u=(User)session.getAttribute("currentUser");
        if(u==null)
            return "Login Required";
        Product p=new Product(
                UUID.randomUUID().toString().replace("-",""),
                u.getUserId(),
                proName,
                price,
                0,
                0,
                (byte)0,
                proType,
                info
        );
        try
        {
            new ProductDAO().addProduct(p);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
        session.setAttribute("currentProduct",p);
        return "success";
    }

    @RequestMapping(value = "savePortrait",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object savePortrait(@RequestParam() MultipartFile file, HttpServletRequest request,HttpServletResponse response,HttpSession session)
    {
        User u=(User)session.getAttribute("currentUser");
        if(u==null)
            return "Login Required";
        String path="C:\\Users\\user0\\IdeaProjects\\CulturalInnoServicePlatform\\web\\WEB-INF\\images\\portrait\\";
        String result=saveFile(file,path,request);
        int pathIndex=result.indexOf("data");
        com.alibaba.fastjson.JSONObject json= JSON.parseObject(result);
        String innerPath=json.getString("data");
        innerPath=innerPath.substring(2,innerPath.length()-2);
        innerPath=innerPath.replace("\\","/");
        innerPath=innerPath.replace("//","/");
        innerPath=innerPath.substring(innerPath.indexOf("image"));
        ImageDAO iDAO=new ImageDAO();
        try
        {
            iDAO.deleteImage(u.getUserId());
            iDAO.addImage(new Image(UUID.randomUUID().toString().replace("-",""),u.getUserId(),innerPath));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        JSONObject retJ=new JSONObject();
        retJ.put("code",0);
        retJ.put("msg","头像保存成功");
        retJ.put("picLoc",innerPath);
        return retJ.toString();
    }
}
