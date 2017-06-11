package com.fangda.rbac.domain;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Leave entity. @author MyEclipse Persistence Tools
 */

public class Leave  implements java.io.Serializable {


    // Fields    

     private String id;
     private User user;
     private Integer day;
     private Date start;
     private Date end;
     private String reason;
     private String mangercheck = "0";//是否审核 1:已审核 0:未审核
     private String checkprocess = "0";//对应流程是否已经启动 0:未启动 1:已启动

    public String getEndDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(end);
	}

	public String getStartDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(start);
	}

	/** default constructor */
    public Leave() {
    }

	/** minimal constructor */
    public Leave(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public Leave(String id, User user, Integer day, Date start, Date end, String reason, String mangercheck, String checkprocess) {
        this.id = id;
        this.user = user;
        this.day = day;
        this.start = start;
        this.end = end;
        this.reason = reason;
        this.mangercheck = mangercheck;
        this.checkprocess = checkprocess;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public Integer getDay() {
        return this.day;
    }
    
    public void setDay(Integer day) {
        this.day = day;
    }

    public Date getStart() {
        return this.start;
    }
    
    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return this.end;
    }
    
    public void setEnd(Date end) {
        this.end = end;
    }

    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMangercheck() {
        return this.mangercheck;
    }
    
    public void setMangercheck(String mangercheck) {
        this.mangercheck = mangercheck;
    }

    public String getCheckprocess() {
        return this.checkprocess;
    }
    
    public void setCheckprocess(String checkprocess) {
        this.checkprocess = checkprocess;
    }

	@Override
	public String toString() {
		return "请假条 [请假天数=" + day + ", 开始时间=" + new SimpleDateFormat("yyyy-MM-dd").format(start) + ", 结束时间=" + new SimpleDateFormat("yyyy-MM-dd").format(end)
				+ ", 请假原因=" + reason + "]";
	}
   
}