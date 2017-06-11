package com.fangda.rbac.domain;
// default package

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User  implements java.io.Serializable {


    // Fields    

     private String id;
     private String username;
     private String password;
     private Double salary;
     private Date birthday;
     private String gender;
     private String telephone;
     private String remark;
     private Set<Role> roles = new HashSet(0);
     
     private Set<Leave> leaves = new HashSet(0);
     

 	public String getRole() {
     	String role = null;
     	 for (Role rolea : roles) {
 			role = rolea.getName()+" ";
 		}
 		return role;
 	}
	private String myBirthday;
   	 public void setMyBirthday(String myBirthday) {
		this.myBirthday = myBirthday;
		try {
			birthday = new SimpleDateFormat("yyyy-MM-dd").parse(myBirthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	 }
   	 public String getMyBirthday() {
    	if(birthday!=null){
    		return new SimpleDateFormat("yyyy-MM-dd").format(birthday);
    	}else {
    		return "";
		}
	}

	/** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public User(String id, String username, String password, Double salary, Date birthday, String gender, String telephone, String remark, Set roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salary = salary;
        this.birthday = birthday;
        this.gender = gender;
        this.telephone = telephone;
        this.remark = remark;
        this.roles = roles;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public Double getSalary() {
        return this.salary;
    }
    
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set getRoles() {
        return this.roles;
    }
    
    public void setRoles(Set roles) {
        this.roles = roles;
    }
   
    public Set getLeaves() {
        return this.leaves;
    }
    
    public void setLeaves(Set leaves) {
        this.leaves = leaves;
    }
    
}