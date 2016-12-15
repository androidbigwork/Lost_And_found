package com.example.Datajavabean;


import cn.bmob.v3.BmobObject;

public class UserTable extends BmobObject{
	 private String phonenum;
     
	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
}
