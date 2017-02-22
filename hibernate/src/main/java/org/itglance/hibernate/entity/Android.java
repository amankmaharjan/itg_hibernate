package org.itglance.hibernate.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverrides({ @AttributeOverride(name = "mobileId", column = @Column(name = "android_id")),
		@AttributeOverride(name = "model", column = @Column(name = "android_model")) })
public class Android extends Mobile {

	private String OSName;

	public String getOSName() {
		return OSName;
	}

	public void setOSName(String oSName) {
		OSName = oSName;
	}

}
