package org.itglance.hibernate.entity;

import javax.persistence.Entity;

@Entity
public class Android extends Mobile {

	private String OSName;

	public String getOSName() {
		return OSName;
	}

	public void setOSName(String oSName) {
		OSName = oSName;
	}

}
