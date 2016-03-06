/**
 * 
 */
package com.codemonkey84.company.handler.test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.codemonkey84.company.domain.Answer;
import com.codemonkey84.company.domain.BeneficiaryOwner;
import com.codemonkey84.company.domain.Company;
import com.codemonkey84.company.domain.EmptyPayload;
import com.codemonkey84.company.handler.CompanyGetHandler;
import com.codemonkey84.company.service.CompanyService;

/**
 * @author lenovo Test class which tests behavior of specific company retrieval
 *         functionality
 */
public class CompanyGetHandlerTest {

	@Test
	public void anInvalidRequestReturnsNotFound() {
		CompanyService companyService = EasyMock
				.createMock(CompanyService.class);
		EasyMock.expect(
				companyService.getCompany(UUID
						.fromString("728084e8-7c9a-4133-a9a7-f2bb491ef436")))
				.andReturn(null).once();
		EasyMock.replay(companyService);

		CompanyGetHandler companyGetHandler = new CompanyGetHandler(
				companyService);
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(":id", "728084e8-7c9a-4133-a9a7-f2bb491ef436");
		Assert.assertEquals(new Answer(404),
				companyGetHandler.process(new EmptyPayload(), urlParams));
		EasyMock.verify(companyService);
	}

	@Test
	public void aValidRequestReturnsCompany() {
		Company company = new Company();
		company.setName("comp");
		company.setAddress("addr");
		company.setCity("city");
		company.setCountry("country");
		
		company.addBeneficiaryOwner(new BeneficiaryOwner());
		CompanyService companyService = EasyMock
				.createMock(CompanyService.class);
		EasyMock.expect(
				companyService.getCompany(UUID
						.fromString("728084e8-7c9a-4133-a9a7-f2bb491ef436")))
				.andReturn(company).once();
		EasyMock.replay(companyService);

		CompanyGetHandler companyGetHandler = new CompanyGetHandler(
				companyService);
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(":id", "728084e8-7c9a-4133-a9a7-f2bb491ef436");
		Assert.assertEquals(
				new Answer(200, CompanyGetHandler.dataToJson(company)),
				companyGetHandler.process(new EmptyPayload(), urlParams));
		EasyMock.verify(companyService);
	}
}
