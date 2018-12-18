package dao;

import po.SupplyDemand;

public class Demand extends SupplyDemand
{
    public Demand()
    {
        setType("D");
        setIsPass((byte)0);
    }
    public Demand(SupplyDemand sd)
    {
        setSdId(sd.getSdId());
        setUserId(sd.getUserId());
        setTitle(sd.getTitle());
        setContent(sd.getContent());
        setStartTime(sd.getStartTime());
        setEndTime(sd.getEndTime());
        setHits(sd.getHits());
        setIsPass(sd.getIsPass());
        setType("D");
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
        sd.setType("D");
        return sd;
    }
    public void setAsPass()
    {
        setIsPass((byte)1);
    }
}
