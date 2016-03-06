/**
 * 
 */
package com.codemonkey84.company.handler.test;

import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.codemonkey84.company.domain.Answer;
import com.codemonkey84.company.domain.BeneficiaryOwner;
import com.codemonkey84.company.domain.Company;
import com.codemonkey84.company.handler.CompanyUpdateHandler;
import com.codemonkey84.company.service.CompanyService;

/**
 * @author lenovo Test class which tests behavior of update company
 *         functionality
 */
public class CompanyUpdateHandlerTest {

	@Test
	public void anInvalidCompanyReturnsBadRequest() {
		Company company = new Company();
		company.setAddress("addr");
		Assert.assertFalse(company.isValid());

		CompanyService companyService = EasyMock
				.createMock(CompanyService.class);
		EasyMock.replay(companyService);

		CompanyUpdateHandler companyUpdateHandler = new CompanyUpdateHandler(
				companyService);
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("id", "728084e8-7c9a-4133-a9a7-f2bb491ef436");
		Assert.assertEquals(new Answer(400),
				companyUpdateHandler.process(company, urlParams));
		EasyMock.verify(companyService);
	}

	@Test
	public void aValidCompanyIsUpdated() {
		Company company = new Company();
		company.setAddress("addr");
		company.setName("comp");
		company.setCity("city");
		company.setCountry("country");
		company.addBeneficiaryOwner(new BeneficiaryOwner());
		Assert.assertTrue(company.isValid());

		CompanyService companyService = EasyMock
				.createMock(CompanyService.class);
		EasyMock.expect(companyService.update(company)).andReturn(true).once();
		EasyMock.replay(companyService);

		CompanyUpdateHandler companyUpdateHandler = new CompanyUpdateHandler(
				companyService);
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(":id", "728084e8-7c9a-4133-a9a7-f2bb491ef436");
		Assert.assertEquals(new Answer(200),
				companyUpdateHandler.process(company, urlParams));
		EasyMock.verify(companyService);
	}
}
