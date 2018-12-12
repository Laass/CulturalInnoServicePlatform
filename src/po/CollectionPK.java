package po;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CollectionPK implements Serializable
{
    private String userId;
    private String originId;

    @Column(name = "userID")
    @Id
    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    @Column(name = "originID")
    @Id
    public String getOriginId()
    {
        return originId;
    }

    public void setOriginId(String originId)
    {
        this.originId = originId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionPK that = (CollectionPK) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(originId, that.originId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, originId);
    }
}
