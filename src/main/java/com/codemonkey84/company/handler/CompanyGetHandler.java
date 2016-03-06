/**
 * 
 */
package com.codemonkey84.company.handler;

import java.util.Map;
import java.util.UUID;

import com.codemonkey84.company.domain.Answer;
import com.codemonkey84.company.domain.Company;
import com.codemonkey84.company.domain.EmptyPayload;
import com.codemonkey84.company.service.CompanyService;

/**
 * @author lenovo Handler class for retrieving a company details
 */
public class CompanyGetHandler extends AbstractRequestHandler<EmptyPayload> {

	private CompanyService companyService;

	public CompanyGetHandler(CompanyService companyService) {
		super(EmptyPayload.class);
		this.companyService = companyService;
	}

	@Override
	public Answer processImpl(EmptyPayload value, Map<String, String> urlParams) {
		Company company = companyService.getCompany(UUID.fromString(urlParams
				.get(":id")));
		if (company != null) {
			return new Answer(200, dataToJson(company));
		} else {
			return new Answer(404);
		}
	}

}
