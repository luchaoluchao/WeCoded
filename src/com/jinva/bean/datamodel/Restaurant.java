package com.jinva.bean.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Restaurant")
public class Restaurant {

	private String id;

	private String name;

	private String ownerId;

	private String address;

	private String telphone;
	
	private Integer belong; 

	private String introduction;

	/////////////////////////////
	//非数据库字段
	private String ownerName;
	private Long dishCount;
	
	public static final int BELONG_PRIVATE = 1;
    public static final int BELONG_PUBLIC = 2;

	private List<String> telphoneList;

	@GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", length = 36, unique = true, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", length = 300)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ownerId", length = 36, nullable = true)
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	@Column(name = "address", length = 600)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "telphone", length = 600)
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "belong")
    public Integer getBelong() {
        return belong;
    }

    public void setBelong(Integer belong) {
        this.belong = belong;
    }
    
	@Column(name = "introduction", length = 600)
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Transient
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Transient
	public List<String> getTelphoneList() {
		return telphoneList;
	}

	public void setTelphoneList(List<String> telphoneList) {
		this.telphoneList = telphoneList;
	}

	@Transient
	public Long getDishCount() {
		return dishCount;
	}

	public void setDishCount(Long dishCount) {
		this.dishCount = dishCount;
	}


}
