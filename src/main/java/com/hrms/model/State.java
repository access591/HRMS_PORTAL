package com.hrms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "M_STATE")
public class State implements Serializable{

	/**
	 *  @author Access Surendra
	 */
	private static final long serialVersionUID = -6376128149451393606L;
	@Id
	@Column(name = "STATE_CODE")
	private String stateCode;
}
