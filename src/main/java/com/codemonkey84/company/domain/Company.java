/**
 * 
 */
package com.codemonkey84.company.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import spark.utils.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author lenovo POJO/VO/DTO class for company
 */
public class Company implements Validable {

	private UUID id;
	private String name;
	private String address;
	private String city;
	private String country;
	private String email;
	private String phone;
	private List<BeneficiaryOwner> beneficiaryOwners = new LinkedList<>();

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the beneficiaryOwners
	 */
	public List<BeneficiaryOwner> getBeneficiaryOwners() {
		return beneficiaryOwners;
	}

	/**
	 * @param beneficiaryOwner
	 *            the beneficiaryOwner to add
	 * @return boolean - whether added or not
	 */
	public boolean addBeneficiaryOwner(BeneficiaryOwner beneficiaryOwner) {
		return beneficiaryOwners.add(beneficiaryOwner);
	}
	
	/**
	 * @param List<BeneficiaryOwner>
	 *            the list of BeneficiaryOwners to add
	 * @return boolean - whether added or not
	 */
	public boolean addBeneficiaryOwners(List<BeneficiaryOwner> beneficiaryOwners) {
		return this.beneficiaryOwners.addAll(beneficiaryOwners);
	}

	/**
	 * @param beneficiaryOwner
	 *            the beneficiaryOwner to remove
	 * @return boolean - whether removed or not
	 */
	public void removeBeneficiaryOwner(BeneficiaryOwner beneficiaryOwner) {
		// Removal logic
	}

	@Override
	@JsonIgnore
	public boolean isValid() {
		return StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(city)
				&& StringUtils.isNotEmpty(address)
				&& StringUtils.isNotEmpty(country);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Company [id=").append(id).append(", name=")
				.append(name).append(", address=").append(address)
				.append(", city=").append(city).append(", country=")
				.append(country).append(", email=").append(email)
				.append(", phone=").append(phone)
				.append(", beneficiaryOwners=").append(beneficiaryOwners)
				.append("]");
		return builder.toString();
	}
	
	

}
