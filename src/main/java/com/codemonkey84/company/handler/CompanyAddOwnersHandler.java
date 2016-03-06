/**
 * 
 */
package com.codemonkey84.company.handler;

import java.util.Map;
import java.util.UUID;

import com.codemonkey84.company.domain.Answer;
import com.codemonkey84.company.domain.BeneficiaryOwnerList;
import com.codemonkey84.company.service.CompanyService;

/**
 * @author lenovo Handler class for creating a company
 */
public class CompanyAddOwnersHandler extends
		AbstractRequestHandler<BeneficiaryOwnerList> {

	private CompanyService companyService;

	public CompanyAddOwnersHandler(CompanyService companyService) {
		super(BeneficiaryOwnerList.class);
		this.companyService = companyService;
	}

	@Override
	public Answer processImpl(BeneficiaryOwnerList value,
			Map<String, String> urlParams) {
		boolean isAdded = companyService.addBeneficialOwners(
				UUID.fromString(urlParams.get(":id")),
				value.getBeneficiaryOwners());
		int status = isAdded ? 201 : 500;
		return new Answer(status);
	}

}
