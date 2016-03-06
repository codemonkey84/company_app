/**
 * 
 */
package com.codemonkey84.company.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lenovo
 *
 */
public class BeneficiaryOwnerList implements Validable {

	private List<BeneficiaryOwner> beneficiaryOwners = new LinkedList<BeneficiaryOwner>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codemonkey84.company.domain.Validable#isValid()
	 */
	@Override
	public boolean isValid() {
		return beneficiaryOwners.size() > 0;
	}

	/**
	 * @return the beneficiaryOwners
	 */
	public List<BeneficiaryOwner> getBeneficiaryOwners() {
		return beneficiaryOwners;
	}

	/**
	 * @param beneficiaryOwners
	 *            the beneficiaryOwners to set
	 */
	public void setBeneficiaryOwners(List<BeneficiaryOwner> beneficiaryOwners) {
		this.beneficiaryOwners = beneficiaryOwners;
	}

}
