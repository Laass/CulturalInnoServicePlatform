package dao;

import po.SupplyDemand;

import java.sql.Timestamp;
import java.util.UUID;

public class Supply extends SupplyDemand
{

    public Supply()
    {
        setType("S");
        setIsPass((byte)0);
    }
    public Supply(SupplyDemand sd)
    {
        setSdId(sd.getSdId());
        setUserId(sd.getUserId());
        setTitle(sd.getTitle());
        setContent(sd.getContent());
        setStartTime(getStartTime());
        setEndTime(getEndTime());
        setHits(getHits());
        setIsPass(getIsPass());
        setType("S");
    }
    public SupplyDemand toSupplyDemand()
    {
        SupplyDemand sd=new SupplyDemand();
        sd.setSdId(getSdId());
        sd.setUserId(getUserId());
        sd.setTitle(getTitle());
        sd.setContent(getContent());
        sd.setStartTime(getStartTime());
        sd.setEndTime(getEndTime());
        sd.setHits(getHits());
        sd.setIsPass(getIsPass());
        sd.setType("S");
        return sd;
    }
    public void setAsPass()
    {
        setIsPass((byte)1);
    }
//    private String sId;
//    private String userId;
//    private String title;
//    private String content;
//    private Timestamp startTime;
//    private Timestamp endTime;
//    private Integer hits;
//    private byte isPass;
//    private String type;
//
//    public String getsId()
//    {
//        return sId;
//    }
//
//    public void setsId(String sId)
//    {
//        this.sId = sId;
//    }
//
//    public String getUserId()
//    {
//        return userId;
//    }
//
//    public void setUserId(String userId)
//    {
//        this.userId = userId;
//    }
//
//    public String getTitle()
//    {
//        return title;
//    }
//
//    public void setTitle(String title)
//    {
//        this.title = title;
//    }
//
//    public String getContent()
//    {
//        return content;
//    }
//
//    public void setContent(String content)
//    {
//        this.content = content;
//    }
//
//    public Timestamp getStartTime()
//    {
//        return startTime;
//    }
//
//    public void setStartTime(Timestamp startTime)
//    {
//        this.startTime = startTime;
//    }
//
//    public Timestamp getEndTime()
//    {
//        return endTime;
//    }
//
//    public void setEndTime(Timestamp endTime)
//    {
//        this.endTime = endTime;
//    }
//
//    public Integer getHits()
//    {
//        return hits;
//    }
//
//    public void setHits(Integer hits)
//    {
//        this.hits = hits;
//    }
//
//    public byte getIsPass()
//    {
//        return isPass;
//    }
//
//    public void setIsPass(byte isPass)
//    {
//        this.isPass = isPass;
//    }
//
//    public String getType()
//    {
//        return type;
//    }
//
//    public void setType(String type)
//    {
//        this.type = type;
//    }
//
//    public SupplyDemand toSupplyDemand()
//    {
//        SupplyDemand sd=new SupplyDemand();
//        sd.setSdId(sId);
//        sd.setUserId(userId);
//        sd.setTitle(title);
//        sd.setContent(content);
//        sd.setStartTime(startTime);
//        sd.setEndTime(endTime);
//        sd.setHits(hits);
//        sd.setIsPass(isPass);
//        sd.setType("supply");
//        return sd;
//    }
}
