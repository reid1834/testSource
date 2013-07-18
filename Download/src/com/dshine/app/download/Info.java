package com.dshine.app.download;
/** 
 * @author WikerYong  Email:<a href="#">yw_312@foxmail.com</a>
 * @version 2012-3-31 上午11:19:14
 */
public class Info {

	private String path;
	private Integer thid;
	private Integer done;
	
	public Info(String path,Integer thid,Integer done){
		this.path = path;
		this.thid = thid;
		this.done = done;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getThid() {
		return thid;
	}
	public void setThid(Integer thid) {
		this.thid = thid;
	}
	public Integer getDone() {
		return done;
	}
	public void setDone(Integer done) {
		this.done = done;
	}
}

