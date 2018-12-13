package spring.controller;

import com.google.gson.Gson;
import dao.AO;
import dao.ProductDAO;
import dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import po.Product;
import po.User;

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

//    @RequestMapping(value = "/getAllProduct.action")
//    @ResponseBody
    @ModelAttribute("allProductList")
    public List<Product> getAllProduct()
    {
        ProductDAO pDAO=new ProductDAO();
        System.out.println("allProductListallProductListallProductListallProductList");
        List<Product> l=pDAO.getAllProducts();
        return l;
    }

//    @RequestMapping(value = "/getAllPassedProduct.action")
//    @ResponseBody
    @ModelAttribute("allPassedProductList")
    public List<Product> getAllPassedProduct()
    {
        ProductDAO pDAO =new ProductDAO();
        List<Product> list=pDAO.getAllProducts();
        List<Product> passedList=new ArrayList<>();
        System.out.println("allPassedProductListallPassedProductListallPassedProductList");
        for(Product p:list)
            if(p.getIsPass()==(byte)1)
                passedList.add(p);
        return passedList;
    }

    @RequestMapping(value = "/Product.html")
    public ModelAndView initProductPage(Model model){
        System.out.println("ProductProductProduct");
        return new ModelAndView("Product","command",this);
    }

    @ModelAttribute("pageProductList")
    @RequestMapping(value = "/getProductByPage.action", method = RequestMethod.POST)
    @ResponseBody
    public List getProductByPage(@RequestParam("page")String page){
        //第一个参数存用户id，第二个参数存page
//        AO temp = new Gson().fromJson(json, AO.class);
        return new ProductDAO().getProductByPage(Integer.parseInt(page));
    }

    @ModelAttribute("typeProductList")
    @RequestMapping(value = "/getProductByType.action", method = RequestMethod.POST)
    @ResponseBody
    public List getProductByType(@RequestBody String json){
        //第一个参数存type，第二个参数存userid（可无，如果没有就是场频也的分类展示，有就是收藏夹的货比三家）
        AO temp = new Gson().fromJson(json, AO.class);
        return new ProductDAO().getProductByType(temp.getFirst());
    }

    @ModelAttribute("userProductList")
    @RequestMapping(value = "/getUserProduct.action", method = RequestMethod.POST)
    @ResponseBody
    public List getUserProduct(@RequestBody String json){
        User u = new Gson().fromJson(json, User.class);
        return new ProductDAO().getUserProducts(u.getUserId());
    }

    @RequestMapping(value = "/searchProduct.action", method = RequestMethod.POST)
    @ResponseBody
    public List searchProduct(@RequestBody String json , HttpServletRequest request){
        //第一个参数是模糊查询还是精确查询，第二个参数关键字
        AO temp = new Gson().fromJson(json, AO.class);

        if(temp.getFirst().equals("模糊查询"))
            return new ProductDAO().getProductByKeyword(temp.getSecond());
        else
            return new ProductDAO().getProductByTitle(temp.getSecond());
    }

    @RequestMapping(value = "/addProduct.action", method = RequestMethod.POST)
    @ResponseBody
    public Product addProduct(@RequestBody String json , HttpServletRequest request){
        Product temp = new Gson().fromJson(json, Product.class);
        return new ProductDAO().addProduct(temp);
    }

    @RequestMapping(value = "/delProduct.action", method = RequestMethod.POST)
    @ResponseBody
    public ProductController delProduct(@RequestBody String json , HttpServletRequest request){
        Product temp = new Gson().fromJson(json, Product.class);
        if(new ProductDAO().delProduct(temp.getProId()))
            this.setMessage("删除成功");
        else
            this.setMessage("删除失败");
        return this;
    }

    @RequestMapping(value = "/passProduct.action", method = RequestMethod.POST)
    @ResponseBody
    public ProductController passProduct(@RequestBody String json , HttpServletRequest request){
        Product temp = new Gson().fromJson(json, Product.class);
        if(new ProductDAO().setAsPass(temp.getProId()))
            this.setMessage("审核成功");
        else
            this.setMessage("审核失败");
        return this;
    }

    @RequestMapping(value = "/purchaseProduct.action", method = RequestMethod.POST)
    @ResponseBody
    public ProductController purchaseProduct(@RequestBody String json , HttpServletRequest request){
        //第一个参数为用户Id，第二个参数为产品Id,第三个参数为场频数量
        AO temp = new Gson().fromJson(json, AO.class);
        if(new UserDAO().purchaseProduct(temp.getFirst(), temp.getSecond(), Integer.parseInt(temp.getThird())))
            this.setMessage("订单成功");
        else
            this.setMessage("订单失败");
        return this;
    }

}
