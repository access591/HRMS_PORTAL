package com.hrms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "M_AWARD")
public class Award implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7852182075397663902L;
	 @Id
	 @GeneratedValue(strategy= GenerationType.IDENTITY)
	 private Long id;
	    @Column(name = "AWARD_NAME")
		private String awardName;
	    
	    @Column(name = "ACTIVE_YN")
		private String active;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getAwardName() {
			return awardName;
		}

		public void setAwardName(String awardName) {
			this.awardName = awardName;
		}

		public String getActive() {
			return active;
		}

		public void setActive(String active) {
			this.active = active;
		}
		
}
