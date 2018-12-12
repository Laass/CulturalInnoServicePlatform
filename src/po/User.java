package po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User
{
    private String userId;
    private String password;
    private int type;

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

    @Basic
    @Column(name = "password")
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Basic
    @Column(name = "type")
    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return type == user.type &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, password, type);
    }
}
