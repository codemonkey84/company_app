/**
 * 
 */
package com.codemonkey84.company.dao.copy;

import java.util.List;
import java.util.UUID;

import com.codemonkey84.company.domain.BeneficiaryOwner;
import com.codemonkey84.company.domain.Company;

/**
 * @author lenovo DAO inteface API for accessing database
 */
public interface CompanyDao {

	/**
	 * Create new company
	 * 
	 * @param Company
	 *            object to create
	 * @return company ID
	 */
	UUID create(Company company);

	/**
	 * Get a list of all companies
	 * 
	 * @return list of Company objects
	 */
	List<Company> getAllCompanies();

	/**
	 * Get details about a company
	 * 
	 * @param company
	 *            ID
	 * @return company object
	 */
	Company getCompany(UUID id);

	/**
	 * Update a company
	 * 
	 * @param Company
	 *            object
	 * @return boolean
	 */
	boolean update(Company company);

	/**
	 * Add benefical owner(s)
	 * 
	 * @param copmany
	 *            ID, list of BeneficialOwners object to add
	 * @return boolean
	 */
	boolean addBeneficialOwners(UUID id,
			List<BeneficiaryOwner> beneficiaryOwners);
}
