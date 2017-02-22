package org.itglance.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "mobiletbl")
@Inheritance(strategy = InheritanceType.JOINED)
public class Mobile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mobileId")
	private int mobileId;

	@Column(name = "modelNo")
	private String model;

	public int getMobileId() {
		return mobileId;
	}

	public void setMobileId(int mobileId) {
		this.mobileId = mobileId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Mobile [mobileId=" + mobileId + ", model=" + model + "]";
	}

}
