package po;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@IdClass(CollectionPK.class)
public class Collection implements Serializable
{
    private String userId;
    private String originId;
    private String originType;
    private Timestamp establishTime;
    private String productType;

    @Id
    @Column(name = "userID")
    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    @Id
    @Column(name = "originID")
    public String getOriginId()
    {
        return originId;
    }

    public void setOriginId(String originId)
    {
        this.originId = originId;
    }

    @Basic
    @Column(name = "originType")
    public String getOriginType()
    {
        return originType;
    }

    public void setOriginType(String originType)
    {
        this.originType = originType;
    }

    @Basic
    @Column(name = "establishTime")
    public Timestamp getEstablishTime()
    {
        return establishTime;
    }

    public void setEstablishTime(Timestamp establishTime)
    {
        this.establishTime = establishTime;
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
        Collection that = (Collection) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(originId, that.originId) &&
                Objects.equals(originType, that.originType) &&
                Objects.equals(establishTime, that.establishTime) &&
                Objects.equals(productType, that.productType);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, originId, originType, establishTime, productType);
    }
}
