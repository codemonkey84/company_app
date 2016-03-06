/**
 * 
 */
package com.codemonkey84.service.factory;

import com.codemonkey84.company.service.CompanyService;
import com.codemonkey84.company.service.impl.CompanyServiceImpl;

/**
 * @author lenovo
 * Factory class for creating ServiceImpl class instance
 */
public class ServiceFactory {

	public static CompanyService createCompanyService() {
		return new CompanyServiceImpl();
	}
}