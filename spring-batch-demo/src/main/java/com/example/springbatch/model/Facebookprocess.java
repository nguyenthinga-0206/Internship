package com.example.springbatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "facebook_process_csv")
public class Facebookprocess {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String ad_name_lpid;
    private float cpm;
    private float ctr;
    private float cpc;
    private Integer sumcost;
    private Integer sumclick;
    private Integer sumimpression;
    private String date_fb;

    public Facebookprocess(){}
    
    public Facebookprocess(String ad_name_lpid, float cpm, float ctr, float cpc, String date) {
        this.ad_name_lpid = ad_name_lpid;
        this.cpm = cpm;
        this.ctr = ctr;
        this.cpc = cpc;
        this.date_fb=date;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAd_name_lpid() {
        return ad_name_lpid;
    }
    public void setAd_name_lpid(String ad_name_lpid) {
        this.ad_name_lpid = ad_name_lpid;
    }
    public float getCpm() {
        return cpm;
    }
    public void setCpm(float cpm) {
        this.cpm = cpm;
    }
    public float getCtr() {
        return ctr;
    }
    public void setCtr(float ctr) {
        this.ctr = ctr;
    }
    public float getCpc() {
        return cpc;
    }
    public void setCpc(float cpc) {
        this.cpc = cpc;
    }

    public float getSumcost() {
        return sumcost;
    }

    public void setSumcost(Integer sumcost) {
        this.sumcost = sumcost;
    }

    public Integer getSumclick() {
        return sumclick;
    }

    public void setSumclick(Integer sumclick) {
        this.sumclick = sumclick;
    }

    public Integer getSumimpression() {
        return sumimpression;
    }

    public void setSumimpression(Integer sumimpression) {
        this.sumimpression = sumimpression;
    }

    public String getDate_fb() {
        return date_fb;
    }

    public void setDate_fb(String date_fb) {
        this.date_fb = date_fb;
    }

    @Override
    public String toString() {
        return ad_name_lpid + "," + cpc + "," + cpm + "," + ctr + "," + id + "," + sumclick + "," + sumcost + "," + sumimpression + "," + date_fb;
    }

    
}
