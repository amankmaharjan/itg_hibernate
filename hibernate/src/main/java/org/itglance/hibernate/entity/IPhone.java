package org.itglance.hibernate.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverrides({ @AttributeOverride(name = "mobileId", column = @Column(name = "iphone_id")),
		@AttributeOverride(name = "model", column = @Column(name = "ihpone_model")) })
public class IPhone extends Mobile {
	private String OSName;

	public String getOSName() {
		return OSName;
	}

	public void setOSName(String oSName) {
		OSName = oSName;
	}
}
