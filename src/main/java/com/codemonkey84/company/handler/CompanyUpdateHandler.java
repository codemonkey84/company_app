/**
 * 
 */
package com.codemonkey84.company.handler;

import java.util.Map;
import java.util.UUID;

import com.codemonkey84.company.domain.Answer;
import com.codemonkey84.company.domain.Company;
import com.codemonkey84.company.service.CompanyService;

/**
 * @author lenovo Handler class for retrieving a company details
 */
public class CompanyUpdateHandler extends AbstractRequestHandler<Company> {

	private CompanyService companyService;

	public CompanyUpdateHandler(CompanyService companyService) {
		super(Company.class);
		this.companyService = companyService;
	}

	@Override
	public Answer processImpl(Company value, Map<String, String> urlParams) {
		value.setId(UUID.fromString(urlParams.get(":id")));
		boolean updated = companyService.update(value);
		int status = updated ? 200 : 400;
		return new Answer(status);
	}

}
