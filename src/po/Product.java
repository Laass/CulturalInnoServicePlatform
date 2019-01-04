package po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Product
{
    private String proId;
    private String userId;
    private String proName;
    private double price;
    private Integer hits;
    private Integer purchase;
    private byte isPass;
    private String productType;
    private String info;

    @Id
    @Column(name = "proID")
    public String getProId()
    {
        return proId;
    }

    public void setProId(String proId)
    {
        this.proId = proId;
    }

    @Basic
    @Column(name = "userID")
    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    @Basic
    @Column(name = "proName")
    public String getProName()
    {
        return proName;
    }

    public void setProName(String proName)
    {
        this.proName = proName;
    }

    @Basic
    @Column(name = "price")
    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    @Basic
    @Column(name = "hits")
    public Integer getHits()
    {
        return hits;
    }

    public void setHits(Integer hits)
    {
        this.hits = hits;
    }

    @Basic
    @Column(name = "purchase")
    public Integer getPurchase()
    {
        return purchase;
    }

    public void setPurchase(Integer purchase)
    {
        this.purchase = purchase;
    }

    @Basic
    @Column(name = "isPass")
    public byte getIsPass()
    {
        return isPass;
    }

    public void setIsPass(byte isPass)
    {
        this.isPass = isPass;
    }

    @Basic
    @Column(name = "productType")
    public String getProductType()
    {
        return productType;
    }

    public void setProductType(String productType)
    {
        this.productType = productType;
    }

    @Basic
    @Column(name = "info")
    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                isPass == product.isPass &&
                Objects.equals(proId, product.proId) &&
                Objects.equals(proName, product.proName) &&
                Objects.equals(hits, product.hits) &&
                Objects.equals(purchase, product.purchase) &&
                Objects.equals(productType, product.productType) &&
                Objects.equals(info, product.info);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(proId, proName, price, hits, purchase, isPass, productType, info);
    }

    public Product(String proId, String userId, String proName, double price, Integer hits, Integer purchase, byte isPass, String productType, String info)
    {
        this.proId = proId;
        this.userId = userId;
        this.proName = proName;
        this.price = price;
        this.hits = hits;
        this.purchase = purchase;
        this.isPass = isPass;
        this.productType = productType;
        this.info = info;
    }

    public Product()
    {
    }
}
