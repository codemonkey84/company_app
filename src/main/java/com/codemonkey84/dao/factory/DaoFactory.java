/**
 * 
 */
package com.codemonkey84.dao.factory;

import java.util.UUID;

import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;

import com.codemonkey84.company.dao.CompanyDao;
import com.codemonkey84.company.dao.impl.CompanyDaoImpl;

/**
 * @author lenovo Factory class for creating DaoImpl class instance
 */
public class DaoFactory {

	private static Sql2o sql2o;

	public static CompanyDao createCompanyDao() {
		if (sql2o == null) {
			sql2o = new Sql2o("jdbc:postgresql://localhost:5432/company",
					"company_owner", "sparkforthewin", new PostgresQuirks() {
						{
							converters.put(UUID.class, new UUIDConverter());
						}
					});
		}
		return new CompanyDaoImpl(sql2o);
	}
}