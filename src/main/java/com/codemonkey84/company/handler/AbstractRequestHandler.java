/**
 * 
 */
package com.codemonkey84.company.handler;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.utils.StringUtils;

import com.codemonkey84.company.domain.Answer;
import com.codemonkey84.company.domain.Validable;
import com.codemonkey84.company.service.CompanyService;
import com.codemonkey84.service.factory.ServiceFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author lenovo
 *
 */
public abstract class AbstractRequestHandler<V extends Validable> implements
		RequestHandler<V>, Route {

	private Class<V> valueClass;

	public AbstractRequestHandler(Class<V> valueClass) {
		this.valueClass = valueClass;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			V value = null;
			if (StringUtils.isNotEmpty(request.body())) {
				value = objectMapper.readValue(request.body(), valueClass);
			}
			Map<String, String> urlParams = request.params();
			Answer answer = process(value, urlParams);
			response.status(answer.getCode());
			response.body(answer.getBody());
			return answer.getBody();
		} catch (JsonParseException jpe) {
			response.status(400);
			response.body(jpe.getLocalizedMessage());
			return response.body();
		}
	}

	@Override
	public Answer process(V value, Map<String, String> urlParams) {
		if (value != null && !value.isValid()) {
			return new Answer(400);
		} else {
			return processImpl(value, urlParams);
		}
	}

	public static String dataToJson(Object data) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			StringWriter sw = new StringWriter();
			mapper.writeValue(sw, data);
			return sw.toString();
		} catch (IOException e) {
			throw new RuntimeException("IOException from a StringWriter: "
					+ e.getLocalizedMessage());
		}
	}

	public abstract Answer processImpl(V value, Map<String, String> urlParams);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CompanyService companyService = ServiceFactory.createCompanyService();

		/**
		 * Create new company POST /companies.json
		 */
		Spark.post("/companies", new CompanyCreateHandler(companyService));

		/**
		 * Get a list of all companies GET /companies.json
		 */
		Spark.get("/companies", new CompanyListAllHandler(companyService));

		/**
		 * Get details about a company GET /companies/1.json
		 */
		Spark.get("/companies/:id", new CompanyGetHandler(companyService));

		/**
		 * Update a company PUT /companies/1.json
		 */
		Spark.put("/companies/:id", new CompanyUpdateHandler(companyService));

		/**
		 * Add beneficial owner(s) of the company POST /companies/1/owners.json
		 */
		Spark.post("/companies/:id/owners", new CompanyAddOwnersHandler(
				companyService));

		/**
		 * Enable CORS for preflight request
		 */
		Spark.options(
				"/*",
				(request, response) -> {

					String accessControlRequestHeaders = request
							.headers("Access-Control-Request-Headers");
					if (accessControlRequestHeaders != null) {
						response.header("Access-Control-Allow-Headers",
								accessControlRequestHeaders);
					}

					String accessControlRequestMethod = request
							.headers("Access-Control-Request-Method");
					if (accessControlRequestMethod != null) {
						response.header("Access-Control-Allow-Methods",
								accessControlRequestMethod);
					}

					return "OK";
				});

		/**
		 * Enable CORS - allowing all origins before any request
		 */
		Spark.before((request, response) -> {
			response.header("Access-Control-Allow-Origin", "*");
		});
		
		/**
		 * Filter that intercepts each response and set the content type as
		 * application/json
		 */
		Spark.after((request, response) -> {
			response.type("application/json");
		});

	}

}
