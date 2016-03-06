/**
 * 
 */
package com.codemonkey84.company.handler.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.codemonkey84.company.domain.Answer;
import com.codemonkey84.company.domain.BeneficiaryOwner;
import com.codemonkey84.company.domain.BeneficiaryOwnerList;
import com.codemonkey84.company.domain.Company;
import com.codemonkey84.company.handler.CompanyAddOwnersHandler;
import com.codemonkey84.company.service.CompanyService;

/**
 * @author lenovo Test class which tests behavior of add owner(s) to a company
 *         functionality
 */
public class CompanyAddOwnersHandlerTest {

	@Test
	public void noBeneficiaryReturnsBadRequest() {
		BeneficiaryOwnerList owners = new BeneficiaryOwnerList();
		Assert.assertFalse(owners.isValid());

		CompanyService companyService = EasyMock
				.createMock(CompanyService.class);
		EasyMock.replay(companyService);

		CompanyAddOwnersHandler companyAddOwnersHandler = new CompanyAddOwnersHandler(
				companyService);
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("id", "728084e8-7c9a-4133-a9a7-f2bb491ef436");
		Assert.assertEquals(new Answer(400),
				companyAddOwnersHandler.process(owners, urlParams));
		EasyMock.verify(companyService);
	}

	@Test
	public void aValidCompanyBeneficiariesUpdated() {
		Company company = new Company();
		List<BeneficiaryOwner> owners = new LinkedList<>();
		owners.add(new BeneficiaryOwner());
		owners.add(new BeneficiaryOwner());
		company.addBeneficiaryOwners(owners);

		BeneficiaryOwnerList ownerList = new BeneficiaryOwnerList();
		ownerList.setBeneficiaryOwners(owners);

		CompanyService companyService = EasyMock
				.createMock(CompanyService.class);
		EasyMock.expect(
				companyService.addBeneficialOwners(
						UUID.fromString("728084e8-7c9a-4133-a9a7-f2bb491ef436"),
						owners)).andReturn(true).once();
		EasyMock.replay(companyService);

		CompanyAddOwnersHandler companyAddOwnersHandler = new CompanyAddOwnersHandler(
				companyService);
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(":id", "728084e8-7c9a-4133-a9a7-f2bb491ef436");
		Assert.assertEquals(new Answer(201),
				companyAddOwnersHandler.process(ownerList, urlParams));
		EasyMock.verify(companyService);
	}
}
