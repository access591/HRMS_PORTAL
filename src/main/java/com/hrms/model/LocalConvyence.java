package com.hrms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "EMP_LOCAL_RMBSMT")
public class LocalConvyence implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6235454307588629360L;
	@Id
	@Column(name = "LOCAL_CONV_ID")
	private String localConvId;
}
