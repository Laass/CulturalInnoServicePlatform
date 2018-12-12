package po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Order
{
    private String orderId;
    private String userId;
    private String proId;
    private Timestamp establishTime;
    private int count;

    @Id
    @Column(name = "orderID")
    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
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
    @Column(name = "count")
    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return count == order.count &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(proId, order.proId) &&
                Objects.equals(establishTime, order.establishTime);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(orderId, userId, proId, establishTime, count);
    }
}
