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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                isPass == product.isPass &&
                Objects.equals(proId, product.proId) &&
                Objects.equals(userId, product.userId) &&
                Objects.equals(proName, product.proName) &&
                Objects.equals(hits, product.hits) &&
                Objects.equals(purchase, product.purchase) &&
                Objects.equals(productType, product.productType);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(proId, userId, proName, price, hits, purchase, isPass, productType);
    }
}
