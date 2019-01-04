package po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Exhibition
{
    private String exId;
    private String userId;
    private String theme;
    private String content;
    private Timestamp establishTime;
    private Integer hits;
    private byte isPass;

    @Id
    @Column(name = "exID")
    public String getExId()
    {
        return exId;
    }

    public void setExId(String exId)
    {
        this.exId = exId;
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
    @Column(name = "theme")
    public String getTheme()
    {
        return theme;
    }

    public void setTheme(String theme)
    {
        this.theme = theme;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exhibition that = (Exhibition) o;
        return isPass == that.isPass &&
                Objects.equals(exId, that.exId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(theme, that.theme) &&
                Objects.equals(content, that.content) &&
                Objects.equals(establishTime, that.establishTime) &&
                Objects.equals(hits, that.hits);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(exId, userId, theme, content, establishTime, hits, isPass);
    }

    public Exhibition(String exId, String userId, String theme, String content, Timestamp establishTime, Integer hits, byte isPass)
    {
        this.exId = exId;
        this.userId = userId;
        this.theme = theme;
        this.content = content;
        this.establishTime = establishTime;
        this.hits = hits;
        this.isPass = isPass;
    }

    public Exhibition(String exId)
    {
        this.exId = exId;
    }

    public Exhibition()
    {
    }
}
