/**
 * 
 */
package com.codemonkey84.dao.factory;

import java.net.URI;
import java.net.URISyntaxException;
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
			URI dbUri;
			try {
				dbUri = new URI(System.getenv("HEROKU_POSTGRESQL_PUCE_URL"));
				String username = dbUri.getUserInfo().split(":")[0];
				String password = dbUri.getUserInfo().split(":")[1];
				String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
				sql2o = new Sql2o(dbUrl, username, password, new PostgresQuirks() {
					{
						converters.put(UUID.class, new UUIDConverter());
					}
				});
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new CompanyDaoImpl(sql2o);
	}
}