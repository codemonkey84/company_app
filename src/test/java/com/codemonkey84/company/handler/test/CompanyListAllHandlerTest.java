/**
 * 
 */
package com.codemonkey84.company.handler.test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.codemonkey84.company.domain.Answer;
import com.codemonkey84.company.domain.Company;
import com.codemonkey84.company.domain.EmptyPayload;
import com.codemonkey84.company.handler.CompanyGetHandler;
import com.codemonkey84.company.handler.CompanyListAllHandler;
import com.codemonkey84.company.service.CompanyService;

/**
 * @author lenovo Test class which tests behavior of list all companies
 *         functionality
 */
public class CompanyListAllHandlerTest {

	@Test
	public void emptyListIsHandledCorrectly() {
		CompanyService companyService = EasyMock
				.createMock(CompanyService.class);
		EasyMock.expect(companyService.getAllCompanies())
				.andReturn(Collections.emptyList()).once();
		EasyMock.replay(companyService);

		CompanyListAllHandler companyListAllHandler = new CompanyListAllHandler(
				companyService);
		Assert.assertEquals(
				new Answer(200, CompanyGetHandler.dataToJson(Collections
						.emptyList())), companyListAllHandler.process(
						new EmptyPayload(), Collections.emptyMap()));
		EasyMock.verify(companyService);

	}

	@Test
	public void nonEmptyListIsHandledCorrectly() {
		List<Company> companies = new LinkedList<>();
		companies.add(new Company());
		companies.add(new Company());

		CompanyService companyService = EasyMock
				.createMock(CompanyService.class);
		EasyMock.expect(companyService.getAllCompanies()).andReturn(companies)
				.once();
		EasyMock.replay(companyService);

		CompanyListAllHandler companyListAllHandler = new CompanyListAllHandler(
				companyService);
		Assert.assertEquals(
				new Answer(200, CompanyListAllHandler.dataToJson(companies)),
				companyListAllHandler.process(new EmptyPayload(),
						Collections.emptyMap()));
		EasyMock.verify(companyService);
	}
}
