package com.example.springbatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "facebook_csv")
public class Facebook {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "date_fb")
    private String date;

    @Column(name = "media")
    private String media;

    @Column(name = "namelpid")
    private String namelpid;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "impression")
    private Integer impression;

    @Column(name = "click")
    private Integer click;

    @Column(name = "cv")
    private Integer cv;

    public Facebook() {

    }

    public Facebook(Integer id, String date, String media, String namelpid, Double cost, Integer impression,
            Integer click, Integer cv) {
        this.id = id;
        this.date = date;
        this.media = media;
        this.namelpid = namelpid;
        this.cost = cost;
        this.impression = impression;
        this.click = click;
        this.cv = cv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getNamelpid() {
        return namelpid;
    }

    public void setNamelpid(String namelpid) {
        this.namelpid = namelpid;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getImpression() {
        return impression;
    }

    public void setImpression(Integer impression) {
        this.impression = impression;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Integer getCv() {
        return cv;
    }

    public void setCv(Integer cv) {
        this.cv = cv;
    }

    @Override
    public String toString() {
        return "Facebook [namelpid=" + namelpid + ", click=" + click + ", cost=" + cost + ", cv=" + cv + ", date=" + date
                + ", id=" + id + ", impression=" + impression + ", media=" + media + "]";
    }

}
