package po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class News
{
    private String newsId;
    private String userId;
    private String title;
    private String content;
    private Timestamp establishTime;
    private Integer hits;
    private byte isPass;

    @Id
    @Column(name = "newsID")
    public String getNewsId()
    {
        return newsId;
    }

    public void setNewsId(String newsId)
    {
        this.newsId = newsId;
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
        News news = (News) o;
        return isPass == news.isPass &&
                Objects.equals(newsId, news.newsId) &&
                Objects.equals(userId, news.userId) &&
                Objects.equals(title, news.title) &&
                Objects.equals(content, news.content) &&
                Objects.equals(establishTime, news.establishTime) &&
                Objects.equals(hits, news.hits);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(newsId, userId, title, content, establishTime, hits, isPass);
    }

    public News(String newsId, String userId, String title, String content, Timestamp establishTime, Integer hits, byte isPass)
    {
        this.newsId = newsId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.establishTime = establishTime;
        this.hits = hits;
        this.isPass = isPass;
    }

    public News()
    {
    }
}
