package spring.controller;

import com.google.gson.Gson;
import dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import po.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.ParagraphView;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
//procuct 分页获取 参数多一个id
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @RequestMapping(value = "/getAllProduct.action")
    @ResponseBody
//    @ModelAttribute("allProductList")
    public List<Product> getAllProduct(HttpServletRequest request)
    {
        ProductDAO pDAO=new ProductDAO();
        System.out.println("allProductListallProductListallProductListallProductList");
        try
        {
            List<Product> l=pDAO.getAllProducts();
            request.setAttribute("allProductList",l);
            return l;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "/getAllPassedProduct.action")
    @ResponseBody
//    @ModelAttribute("allPassedProductList")
    public List<Product> getAllPassedProduct(HttpServletRequest request)
    {
        ProductDAO pDAO =new ProductDAO();
        try
        {
            List<Product> list=pDAO.getAllProducts();
            List<Product> passedList=new ArrayList<>();
            System.out.println("allPassedProductListallPassedProductListallPassedProductList");
            for(Product p:list)
                if(p.getIsPass()==(byte)1)
                    passedList.add(p);
            request.setAttribute("passedList",passedList);
            return passedList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "/Product.html")
    public ModelAndView initProductPage(Model model,HttpServletRequest request){
//        getAllPassedProduct(request);
//        List<Product> passedList=(List<Product>)request.getAttribute("passedList");
//        List<Product> calliList=new ArrayList<>();
//        List<Product> paintList=new ArrayList<>();
//        List<Product> musicList=new ArrayList<>();
//        List<Product> garmentList=new ArrayList<>();
//        List<AO> calliInfoList=new ArrayList<>();
//        List<AO> paintInfoList=new ArrayList<>();
//        List<AO> musicInfoList=new ArrayList<>();
//        List<AO> garmentInfoList=new ArrayList<>();
//        ImageDAO iDAO=new ImageDAO();
//        try
//        {
//            for(Product p:passedList)
//            {
//                Image i=iDAO.getFirstImageOfOriginId(p.getProId());
//                AO a=new AO();
//                a.setFirst(p.getProName());
//                a.setSecond(p.getProId());
//                a.setThird(p.getProductType());
//                a.setFourth(Double.toString(p.getPrice()));
//                a.setFifth(p.getHits().toString());
//                if(i!=null)
//                    a.setSixth(i.getStoreLocation());
//                if(p.getProductType().equals(ProductType.CALLIGRAPHY.name))
//                {
//                    calliList.add(p);
//                    calliInfoList.add(a);
//                }
//                else
//                    if(p.getProductType().equals(ProductType.PAINTING.name))
//                    {
//                        paintList.add(p);
//                        paintInfoList.add(a);
//                    }
//                    else
//                        if(p.getProductType().equals(ProductType.MUSINSTRU.name))
//                        {
//                            musicList.add(p);
//                            musicInfoList.add(a);
//                        }
//                        else
//                        {
//                            garmentList.add(p);
//                            garmentInfoList.add(a);
//                        }
//            }
//            System.out.println("ProductProductProduct");
//            model.addAttribute("calliList",calliList);
//            model.addAttribute("paintList",paintList);
//            model.addAttribute("musicList",musicList);
//            model.addAttribute("garmentList",garmentList);
//            model.addAttribute("calliInfoList",calliInfoList);
//            model.addAttribute("paintInfoList",paintInfoList);
//            model.addAttribute("musicInfoList",musicInfoList);
//            model.addAttribute("garmentInfoList",garmentInfoList);
        if(!getProducts(model,request))
            return null;
        return new ModelAndView("Product","command",this);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            return null;
//        }
    }

    @RequestMapping(value = "/Product",method = RequestMethod.GET)
    public ModelAndView initProductType(@RequestParam("ptype") String type,Model model,HttpServletRequest request){
//        getAllPassedProduct(request);
//        List<Product> passedList=(List<Product>)request.getAttribute("passedList");
//        List<Product> calliList=new ArrayList<>();
//        List<Product> paintList=new ArrayList<>();
//        List<Product> musicList=new ArrayList<>();
//        List<Product> garmentList=new ArrayList<>();
//        for(Product p:passedList)
//        {
//
//            if(p.getProductType().equals(ProductType.CALLIGRAPHY.name))
//                calliList.add(p);
//            else
//            if(p.getProductType().equals(ProductType.PAINTING.name))
//                paintList.add(p);
//            else
//            if(p.getProductType().equals(ProductType.MUSINSTRU.name))
//                musicList.add(p);
//            else
//                garmentList.add(p);
//        }
//        System.out.println("ProductProductProduct");
//        model.addAttribute("calliList",calliList);
//        model.addAttribute("paintList",paintList);
//        model.addAttribute("musicList",musicList);
//        model.addAttribute("garmentList",garmentList);
        if(!getProducts(model,request))
            return null;
        model.addAttribute("ptype",type);
        return new ModelAndView("Product","command",this);

    }

    private Boolean getProducts(Model model,HttpServletRequest request)
    {
        getAllPassedProduct(request);
        List<Product> passedList=(List<Product>)request.getAttribute("passedList");
        List<Product> calliList=new ArrayList<>();
        List<Product> paintList=new ArrayList<>();
        List<Product> musicList=new ArrayList<>();
        List<Product> garmentList=new ArrayList<>();
        List<AO> calliInfoList=new ArrayList<>();
        List<AO> paintInfoList=new ArrayList<>();
        List<AO> musicInfoList=new ArrayList<>();
        List<AO> garmentInfoList=new ArrayList<>();
        ImageDAO iDAO=new ImageDAO();
        try
        {
            for (Product p : passedList)
            {
                Image i = iDAO.getFirstImageOfOriginId(p.getProId());
                AO a = new AO();
                a.setFirst(p.getProName());
                a.setSecond(p.getProId());
                a.setThird(p.getProductType());
                a.setFourth(Double.toString(p.getPrice()));
                a.setFifth(p.getHits().toString());
                if (i != null)
                    a.setSixth(i.getStoreLocation());
                if (p.getProductType().equals(ProductType.CALLIGRAPHY.name))
                {
                    calliList.add(p);
                    calliInfoList.add(a);
                }
                else if (p.getProductType().equals(ProductType.PAINTING.name))
                {
                    paintList.add(p);
                    paintInfoList.add(a);
                }
                else if (p.getProductType().equals(ProductType.MUSINSTRU.name))
                {
                    musicList.add(p);
                    musicInfoList.add(a);
                }
                else
                {
                    garmentList.add(p);
                    garmentInfoList.add(a);
                }
            }
            System.out.println("ProductProductProduct");
            model.addAttribute("calliList", calliList);
            model.addAttribute("paintList", paintList);
            model.addAttribute("musicList", musicList);
            model.addAttribute("garmentList", garmentList);
            model.addAttribute("calliInfoList", calliInfoList);
            model.addAttribute("paintInfoList", paintInfoList);
            model.addAttribute("musicInfoList", musicInfoList);
            model.addAttribute("garmentInfoList", garmentInfoList);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "getProductById", method = RequestMethod.GET)
//    @ResponseBody
    public String getProductById(@RequestParam("productId") String productId,Model model)
    {
        try
        {
            ProductDAO pDAO = new ProductDAO();
            Product p = pDAO.getProducById(productId);
            MessageDAO mDAO = new MessageDAO();
            List<Message> msgList = mDAO.getMessageById(p.getProId(), -1);//不分页获取产品的所有评论
            List<AO> umList = new ArrayList<>();//存储用户名和留言
            UserInfoDAO uiDAO = new UserInfoDAO();
            ImageDAO iDAO=new ImageDAO();
            List<Image> iList=iDAO.getImageByOriginId(p.getProId(),999);
            for (Message m : msgList)
            {
                UserInfo ui = uiDAO.getUserInfo(m.getUserId());
                AO a = new AO();
                a.setFirst(ui.getNickName());
                a.setSecond(m.getContent());
                umList.add(a);
            }
            model.addAttribute("product", p);
            model.addAttribute("umList", umList);
            model.addAttribute("imageList",iList);
//        return new ModelAndView("ProductDetail","command",this);
            return "ProductDetail";
        }
        catch (Exception e)
        {
            return "Login";
        }
    }

    @ModelAttribute("pageProductList")
    @RequestMapping(value = "/getProductByPage.action", method = RequestMethod.POST)
    @ResponseBody
    public List getProductByPage(@RequestParam("page")String page){
        //第一个参数存用户id，第二个参数存page
//        AO temp = new Gson().fromJson(json, AO.class);
        try
        {
            return new ProductDAO().getProductByPage(Integer.parseInt(page));
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @ModelAttribute("typeProductList")
    @RequestMapping(value = "/getProductByType.action", method = RequestMethod.POST)
    @ResponseBody
    public List getProductByType(@RequestBody String json){
        //第一个参数存type，第二个参数存userid（可无，如果没有就是场频也的分类展示，有就是收藏夹的货比三家）
        AO temp = new Gson().fromJson(json, AO.class);
        try
        {
            return new ProductDAO().getProductByType(temp.getFirst());
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @ModelAttribute("userProductList")
    @RequestMapping(value = "/getUserProduct.action", method = RequestMethod.POST)
    @ResponseBody
    public List getUserProduct(@RequestBody String json){
        User u = new Gson().fromJson(json, User.class);
        try
        {
            return new ProductDAO().getUserProducts(u.getUserId());
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "/searchProduct.action", method = RequestMethod.POST)
    @ResponseBody
    public List searchProduct(@RequestBody String json , HttpServletRequest request){
        //第一个参数是模糊查询还是精确查询，第二个参数关键字
        AO temp = new Gson().fromJson(json, AO.class);

        try
        {
            if(temp.getFirst().equals("模糊查询"))
                return new ProductDAO().getProductByKeyword(temp.getSecond());
            else
                return new ProductDAO().getProductByTitle(temp.getSecond());
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "/addProduct.action", method = RequestMethod.POST)
    @ResponseBody
    public Product addProduct(@RequestBody String json , HttpServletRequest request){
        Product temp = new Gson().fromJson(json, Product.class);
        try
        {
            return new ProductDAO().addProduct(temp);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @RequestMapping(value = "/Manage/deleteProduct.action")
    @ResponseBody
    public ProductController deleteProduct(@RequestBody String json, HttpServletRequest request){
        try {
            if (new ProductDAO().delProduct(new Gson().fromJson(json, AO.class).getFirst()))
                this.message = "Delete Success!";
            else
                this.message = "Delete Fail!";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            return this;
        }
    }

    @RequestMapping(value = "/passProduct.action", method = RequestMethod.POST)
    @ResponseBody
    public ProductController passProduct(@RequestBody String json , HttpServletRequest request){
        Product temp = new Gson().fromJson(json, Product.class);
        try
        {
            if(new ProductDAO().setAsPass(temp.getProId()))
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

    @RequestMapping(value = "/purchaseProduct.action", method = RequestMethod.POST)
    @ResponseBody
    public ProductController purchaseProduct(@RequestBody String json , HttpServletRequest request){
        //第一个参数为用户Id，第二个参数为产品Id,第三个参数为产品数量
        AO temp = new Gson().fromJson(json, AO.class);
        User user = (User)request.getSession().getAttribute("currentUser");

        if(user != null) {
            temp.setFirst(user.getUserId());
            try {
                if (new UserDAO().purchaseProduct(temp.getFirst(), temp.getSecond(), Integer.parseInt(temp.getThird())))
                    this.setMessage("Success");
                else
                    this.setMessage("fail");
            } catch (Exception e) {
                e.printStackTrace();
                this.setMessage("Fail");
            }
        }
        else
            this.setMessage("订单失败");

        return this;
    }

}
