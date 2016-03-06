/**
 * 
 */
package com.codemonkey84.company.service.impl;

import java.util.List;
import java.util.UUID;

import com.codemonkey84.company.dao.CompanyDao;
import com.codemonkey84.company.domain.BeneficiaryOwner;
import com.codemonkey84.company.domain.Company;
import com.codemonkey84.company.service.CompanyService;
import com.codemonkey84.dao.factory.DaoFactory;

/**
 * @author lenovo
 * Service implementation class for company API interface
 */
public class CompanyServiceImpl implements CompanyService {
	
	private CompanyDao companyDao;
	
	public CompanyServiceImpl() {
		companyDao = DaoFactory.createCompanyDao();
	}

	/* (non-Javadoc)
	 * @see com.codemonkey84.company.service.CompanyService#create(com.codemonkey84.company.domain.Company)
	 */
	@Override
	public UUID create(Company company) {
		return companyDao.create(company);
	}

	/* (non-Javadoc)
	 * @see com.codemonkey84.company.service.CompanyService#getAllCompanies()
	 */
	@Override
	public List<Company> getAllCompanies() {
		return companyDao.getAllCompanies();
	}

	/* (non-Javadoc)
	 * @see com.codemonkey84.company.service.CompanyService#getCompany(java.lang.Long)
	 */
	@Override
	public Company getCompany(UUID id) {
		return companyDao.getCompany(id);
	}

	/* (non-Javadoc)
	 * @see com.codemonkey84.company.service.CompanyService#update(com.codemonkey84.company.domain.Company)
	 */
	@Override
	public boolean update(Company company) {
		return companyDao.update(company);
	}

	/* (non-Javadoc)
	 * @see com.codemonkey84.company.service.CompanyService#addBeneficialOwners(java.util.List)
	 */
	@Override
	public boolean addBeneficialOwners(UUID id, List<BeneficiaryOwner> beneficiaryOwners) {
		return companyDao.addBeneficialOwners(id, beneficiaryOwners);
	}

}
