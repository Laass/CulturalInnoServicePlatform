package po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Message
{
    private String mesId;
    private String userId;
    private String originId;
    private String originType;
    private String content;
    private Timestamp establishTime;

    @Id
    @Column(name = "mesID")
    public String getMesId()
    {
        return mesId;
    }

    public void setMesId(String mesId)
    {
        this.mesId = mesId;
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
    @Column(name = "establishTime")
    public Timestamp getEstablishTime()
    {
        return establishTime;
    }

    public void setEstablishTime(Timestamp establishTime)
    {
        this.establishTime = establishTime;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(mesId, message.mesId) &&
                Objects.equals(userId, message.userId) &&
                Objects.equals(originId, message.originId) &&
                Objects.equals(originType, message.originType) &&
                Objects.equals(content, message.content) &&
                Objects.equals(establishTime, message.establishTime);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mesId, userId, originId, originType, content, establishTime);
    }
}
