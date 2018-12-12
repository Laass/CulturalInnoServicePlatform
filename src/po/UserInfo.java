package po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class UserInfo
{
    private String userId;
    private String nickName;
    private String realName;
    private String intro;
    private String email;
    private String address;
    private String qq;
    private String tel;

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
    @Column(name = "nickName")
    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "realName")
    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    @Basic
    @Column(name = "intro")
    public String getIntro()
    {
        return intro;
    }

    public void setIntro(String intro)
    {
        this.intro = intro;
    }

    @Basic
    @Column(name = "email")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Basic
    @Column(name = "address")
    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Basic
    @Column(name = "qq")
    public String getQq()
    {
        return qq;
    }

    public void setQq(String qq)
    {
        this.qq = qq;
    }

    @Basic
    @Column(name = "tel")
    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userinfo = (UserInfo) o;
        return Objects.equals(userId, userinfo.userId) &&
                Objects.equals(nickName, userinfo.nickName) &&
                Objects.equals(realName, userinfo.realName) &&
                Objects.equals(intro, userinfo.intro) &&
                Objects.equals(email, userinfo.email) &&
                Objects.equals(address, userinfo.address) &&
                Objects.equals(qq, userinfo.qq) &&
                Objects.equals(tel, userinfo.tel);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, nickName, realName, intro, email, address, qq, tel);
    }
}
