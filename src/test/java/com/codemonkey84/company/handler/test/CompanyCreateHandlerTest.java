/**
 * 
 */
package com.codemonkey84.company.handler.test;

import java.util.Collections;
import java.util.UUID;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.codemonkey84.company.domain.Answer;
import com.codemonkey84.company.domain.BeneficiaryOwner;
import com.codemonkey84.company.domain.Company;
import com.codemonkey84.company.handler.CompanyCreateHandler;
import com.codemonkey84.company.service.CompanyService;

/**
 * @author lenovo Test class which tests behavior of company create
 *         functionality
 */
public class CompanyCreateHandlerTest {

	@Test
	public void anInvalidCompanyReturnsBadRequest() {
		Company company = new Company();
		company.setName("comp");
		company.setAddress("addr");
		company.setCity("city");
		company.setEmail("email");
		Assert.assertFalse(company.isValid()); // No country

		CompanyService companyService = EasyMock
				.createMock(CompanyService.class);
		EasyMock.replay(companyService);

		CompanyCreateHandler handler = new CompanyCreateHandler(companyService);
		Assert.assertEquals(new Answer(400),
				handler.process(company, Collections.emptyMap()));
		EasyMock.verify(companyService);
	}

	@Test
	public void aValidCompanyIsCreated() {
		Company company = new Company();
		company.setName("comp");
		company.setAddress("addr");
		company.setCity("city");
		company.setCountry("country");
		company.addBeneficiaryOwner(new BeneficiaryOwner());
		Assert.assertTrue(company.isValid());

		CompanyService companyService = EasyMock
				.createMock(CompanyService.class);
		EasyMock.expect(companyService.create(company))
				.andReturn(
						UUID.fromString("728084e8-7c9a-4133-a9a7-f2bb491ef436"))
				.once();
		EasyMock.replay(companyService);

		CompanyCreateHandler handler = new CompanyCreateHandler(companyService);
		Assert.assertEquals(new Answer(201,
				"728084e8-7c9a-4133-a9a7-f2bb491ef436"), handler.process(
				company, Collections.emptyMap()));
		EasyMock.verify(companyService);
	}
}
