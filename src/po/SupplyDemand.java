package po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "supply_demand", schema = "service_platform_db", catalog = "")
public class SupplyDemand
{
    private String sdId;
    private String userId;
    private String title;
    private String content;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer hits;
    private byte isPass;
    private String type;

    @Id
    @Column(name = "sdID")
    public String getSdId()
    {
        return sdId;
    }

    public void setSdId(String sdId)
    {
        this.sdId = sdId;
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
    @Column(name = "title")
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Basic
    @Column(name = "content")
    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Basic
    @Column(name = "startTime")
    public Timestamp getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Timestamp startTime)
    {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime")
    public Timestamp getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Timestamp endTime)
    {
        this.endTime = endTime;
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
    @Column(name = "type")
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplyDemand that = (SupplyDemand) o;
        return isPass == that.isPass &&
                Objects.equals(sdId, that.sdId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(hits, that.hits) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(sdId, userId, title, content, startTime, endTime, hits, isPass, type);
    }
}
