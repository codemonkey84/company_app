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
 * @author lenovo Handler class for creating a company
 */
public class CompanyCreateHandler extends AbstractRequestHandler<Company> {

	private CompanyService companyService;

	public CompanyCreateHandler(CompanyService companyService) {
		super(Company.class);
		this.companyService = companyService;
	}

	@Override
	public Answer processImpl(Company value, Map<String, String> urlParams) {
		UUID id = companyService.create(value);
		return new Answer(201, id.toString());
	}

}
