package com.fangda.rbac.domain;
// default package

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


/**
 * MySystem entity. @author MyEclipse Persistence Tools
 */

public class MySystem  implements java.io.Serializable {


    // Fields    

     private String id;
     private String ip;
     private Timestamp date;
     private String user;
     private String userid;

    // Constructors

    public String getLoginDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/** default constructor */
    public MySystem() {
    }

	/** minimal constructor */
    public MySystem(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public MySystem(String id, String ip, Timestamp date, String user, String userid) {
        this.id = id;
        this.ip = ip;
        this.date = date;
        this.user = user;
        this.userid = userid;
    }
    /** full constructor */
    public MySystem(String ip, Timestamp date, String user, String userid) {
    	this.ip = ip;
    	this.date = date;
    	this.user = user;
    	this.userid = userid;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }

    public Timestamp getDate() {
        return this.date;
    }
    
    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getUser() {
        return this.user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }

    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }

}