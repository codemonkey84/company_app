/**
 * 
 */
package com.codemonkey84.company.handler;

import java.util.List;
import java.util.Map;

import com.codemonkey84.company.domain.Answer;
import com.codemonkey84.company.domain.Company;
import com.codemonkey84.company.domain.EmptyPayload;
import com.codemonkey84.company.service.CompanyService;

/**
 * @author lenovo Handler class for listing all companies
 */
public class CompanyListAllHandler extends AbstractRequestHandler<EmptyPayload> {

	private CompanyService companyService;

	public CompanyListAllHandler(CompanyService companyService) {
		super(EmptyPayload.class);
		this.companyService = companyService;
	}

	@Override
	public Answer processImpl(EmptyPayload value, Map<String, String> urlParams) {
		List<Company> allCompanies = companyService.getAllCompanies();
		return new Answer(200, dataToJson(allCompanies));
	}

}
