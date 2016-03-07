/**
 * 
 */
package com.codemonkey84.company.domain;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author lenovo
 * POJO/VO/DTO class for company beneficiary owner
 */
public class BeneficiaryOwner implements Validable {

	private UUID id;
	private String name;
	
	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	@Override
	@JsonIgnore
	public boolean isValid() {
		return true;
	}	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BeneficiaryOwner [id=").append(id).append(", name=")
				.append(name).append("]");
		return builder.toString();
	}
	
}
