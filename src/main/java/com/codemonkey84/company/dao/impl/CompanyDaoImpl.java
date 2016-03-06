/**
 * 
 */
package com.codemonkey84.company.dao.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.codemonkey84.company.dao.CompanyDao;
import com.codemonkey84.company.domain.BeneficiaryOwner;
import com.codemonkey84.company.domain.Company;

/**
 * @author lenovo DAO implementation class for DAO API interfaces
 */
public class CompanyDaoImpl implements CompanyDao {

	private Sql2o sql2o;
	private static final String INSERT_INTO_COMAPNIES = "insert into companies ("
			+ "id, name, city, address, country, email, phone) "
			+ "values (:id, :name, :city, :address, :country, "
			+ ":email, :phone)";
	private static final String INSERT_INTO_BENEFICIAL_OWNERS = 
			"insert into beneficial_owners (id, company_id, name) "
			+ "values (:id, :company_id, :name)";
	private static final String SELECT_ALL_COMPANIES = "select * from companies";
	private static final String SELECT_A_COMPANY = "select * from companies where id = :id";
	private static final String UPDATE_A_COMPANY = "update companies "
			+ "set name = :name, city = :city, address = :address, "
			+ "country = :country, email = :email, phone = :phone "
			+ "where id = :id";
	private static final String SELECT_BENEFICIARIES_FOR_A_COMPANY = 
			"select id, name from beneficial_owners where company_id = :company_id";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.codemonkey84.company.dao.CompanyDao#create(com.codemonkey84.company
	 * .domain.Company)
	 */

	public CompanyDaoImpl(Sql2o sql2o) {
		this.sql2o = sql2o;
	}

	@Override
	public UUID create(Company company) {
		UUID companyUuid = null;
		try (Connection conn = sql2o.beginTransaction()) {
			companyUuid = UUID.randomUUID();
			UUID id = companyUuid;
			conn.createQuery(INSERT_INTO_COMAPNIES).addParameter("id", id)
					.addParameter("name", company.getName())
					.addParameter("city", company.getCity())
					.addParameter("address", company.getAddress())
					.addParameter("country", company.getCountry())
					.addParameter("phone", company.getPhone())
					.addParameter("email", company.getEmail()).executeUpdate();
			company.getBeneficiaryOwners().forEach(
					beneficiaryOwner -> conn
							.createQuery(INSERT_INTO_BENEFICIAL_OWNERS)
							.addParameter("id", UUID.randomUUID())
							.addParameter("company_id", id)
							.addParameter("name", beneficiaryOwner.getName())
							.executeUpdate());
			conn.commit();
		}
		return companyUuid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codemonkey84.company.dao.CompanyDao#getAllCompanies()
	 */
	@Override
	public List<Company> getAllCompanies() {
		List<Company> companies = new LinkedList<>();
		try (Connection conn = sql2o.beginTransaction()) {
			companies = conn.createQuery(SELECT_ALL_COMPANIES).executeAndFetch(
					Company.class);
			companies.forEach(company -> {
				List<BeneficiaryOwner> owners = this.retrieveOwnersByCompany(
						conn, company.getId());
				company.addBeneficiaryOwners(owners);
			});
		}
		return companies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codemonkey84.company.dao.CompanyDao#getCompany(java.lang.Long)
	 */
	@Override
	public Company getCompany(UUID id) {
		Company company = null;
		try (Connection conn = sql2o.beginTransaction()) {
			company = conn.createQuery(SELECT_A_COMPANY).addParameter("id", id)
					.executeAndFetchFirst(Company.class);
			List<BeneficiaryOwner> owners = this.retrieveOwnersByCompany(conn,
					company.getId());
			company.addBeneficiaryOwners(owners);
		}
		return company;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.codemonkey84.company.dao.CompanyDao#update(com.codemonkey84.company
	 * .domain.Company)
	 */
	@Override
	public boolean update(Company company) {
		boolean isUpdated = false;
		try (Connection conn = sql2o.beginTransaction()) {
			conn.createQuery(UPDATE_A_COMPANY)
					.addParameter("name", company.getName())
					.addParameter("address", company.getAddress())
					.addParameter("city", company.getCity())
					.addParameter("country", company.getCountry())
					.addParameter("phone", company.getPhone())
					.addParameter("email", company.getEmail())
					.addParameter("id", company.getId()).executeUpdate();
			conn.commit();
			isUpdated = true;
		}
		return isUpdated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.codemonkey84.company.dao.CompanyDao#addBeneficialOwners(java.util
	 * .List)
	 */
	@Override
	public boolean addBeneficialOwners(UUID id,
			List<BeneficiaryOwner> beneficiaryOwners) {
		boolean isAdded = false;
		try (Connection conn = sql2o.beginTransaction()) {
			beneficiaryOwners.forEach(beneficiaryOwner -> conn
					.createQuery(INSERT_INTO_BENEFICIAL_OWNERS)
					.addParameter("id", UUID.randomUUID())
					.addParameter("company_id", id)
					.addParameter("name", beneficiaryOwner.getName())
					.executeUpdate());
			conn.commit();
			isAdded = true;
		}
		return isAdded;
	}

	private List<BeneficiaryOwner> retrieveOwnersByCompany(Connection conn,
			UUID companyId) {
		List<BeneficiaryOwner> owners = conn
				.createQuery(SELECT_BENEFICIARIES_FOR_A_COMPANY)
				.addParameter("company_id", companyId)
				.executeAndFetch(BeneficiaryOwner.class);
		return owners;
	}

}
