package top.hjie.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="auto_task_user")
public class AutoTaskUser implements Serializable {

	private static final long serialVersionUID = -4926846032870744364L;
	
	@Id
	@Column(name = "atuId")
	private Long atuId;
	@Column(name = "userCode")
	private String userCode;
	@Column(name = "cookie")
	private String cookie;
	@Column(name = "formHash")
	private String formHash;
	@Column(name = "bbsName")
	private String bbsName;
	@Column(name = "taskStatus")
	private Integer taskStatus;	// 1.等待，2.进行中，3.结束
	@Column(name = "createTime")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;	// 任务创建时间
	@Column(name = "stopTime")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date stopTime;	// 任务停止时间
	@Column(name = "ipAddress")
	private String ipAddress;
	@Column(name = "lockStatus")
	private Integer lockStatus;
	
	@Transient
	private String taskStatusName;
	
	public String getTaskStatusName() {
		return taskStatusName;
	}
	public void setTaskStatusName(String taskStatusName) {
		this.taskStatusName = taskStatusName;
	}
	public Integer getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}
	public Long getAtuId() {
		return atuId;
	}
	public void setAtuId(Long atuId) {
		this.atuId = atuId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getFormHash() {
		return formHash;
	}
	public void setFormHash(String formHash) {
		this.formHash = formHash;
	}
	public String getBbsName() {
		return bbsName;
	}
	public void setBbsName(String bbsName) {
		this.bbsName = bbsName;
	}
	public Integer getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStopTime() {
		return stopTime;
	}
	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
