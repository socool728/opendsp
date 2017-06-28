package mobi.opendsp.model;

import java.util.Date;

public class Campaign {
    private Integer id;

    private Integer advertiserId;

    private String name;

    private Integer budget;

    private Integer trackingSolution;

    private String impTracker;

    private String clkTracker;

    private Integer status;

    private Date beginTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Integer advertiserId) {
        this.advertiserId = advertiserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getTrackingSolution() {
        return trackingSolution;
    }

    public void setTrackingSolution(Integer trackingSolution) {
        this.trackingSolution = trackingSolution;
    }

    public String getImpTracker() {
        return impTracker;
    }

    public void setImpTracker(String impTracker) {
        this.impTracker = impTracker;
    }

    public String getClkTracker() {
        return clkTracker;
    }

    public void setClkTracker(String clkTracker) {
        this.clkTracker = clkTracker;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}