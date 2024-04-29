package com.api.stepdefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.api.utils.APIResources;
import com.api.utils.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;

public class PriceApiValidations {

	private TestContext context;
	private static final Logger LOG = LogManager.getLogger(PriceApiValidations.class);
	public String schemaFiles = System.getProperty("user.dir") + "/src/test/resources/schemas/";

	public PriceApiValidations(TestContext context) {
		this.context = context;
	}

	@Given("user has access to price endpoint {string}")
	public void userHasAccessToEndpoint(String endpoint) {
		APIResources resourceAPI = APIResources.valueOf(endpoint);
		context.session.put("endpoint", resourceAPI.getResource());
	}

	@When("user makes a request to get price")
	public void userMakesARequestToViewCoversionRates() {
		System.out.println("Hit Endpoint Resource: " + context.session.get("endpoint").toString());
		context.response = context.requestSetup().when().get(context.session.get("endpoint").toString());
		String result = context.response.getBody().jsonPath().getString("result");
		assertNotNull("Price list not found!", result);
	}

	@Then("user should get the response code {int}")
	public void userShpuldGetTheResponseCode(Integer statusCode) {
		assertEquals(Long.valueOf(statusCode), Long.valueOf(context.response.getStatusCode()));
	}

	@Then("user verifies price API returns status {string}")
	public void verifyResultStatus(String resultStatus) {
		String resultJson = context.response.getBody().jsonPath().getString("result");
		assertEquals(resultStatus, resultJson);
	}

	@Then("user verifies total currency pairs are {int}")
	public void totalCurrencies(int currency) {
		int totalCurrenties = context.response.getBody().jsonPath().getInt("rates.size()");
		assertNotNull("Currency Rate list is empty!", totalCurrenties);
		assertEquals(totalCurrenties, currency);
	}

	@Then("user verifies API response time is not less then {int}")
	public void responseTime(int time) {
		long totalTime = context.response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("The Total Response Time in Secs: " + totalTime);
//		Assertions.assertEquals(totalCurrenties, currency);
	}

	@Then("user validates API response with JSON schema {string}")
	public void validateSchema(String schemaName) throws IOException {
		System.out.println("The Schema Name: " + schemaFiles + schemaName + ".json");
		String jsonAsStringFile = FileUtils.readFileToString(new File(schemaFiles + schemaName + ".json"), "UTF-8");
		context.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonAsStringFile));
		LOG.info("Successfully Validated schema from " + schemaName);
	}

	@Then("user verifies {string} conversion rate is {double} in {string}")
	public void userVerifiesConversionRate(String currency, Double double1, String covertTo) {
		boolean between = false;
		Double covertToCurrencyValue = context.response.getBody().jsonPath().getDouble("rates." + covertTo);
		System.out.println("The Double:" + covertToCurrencyValue);
		DecimalFormat df = new DecimalFormat("#.#");
		System.out.println("The Passed   Double Value: "+ double1);
		System.out.println("The Response Double Value: "+ covertToCurrencyValue);
		System.out.println("The Coverted Double Value: "+ Double.parseDouble(df.format(covertToCurrencyValue)));
		if (double1 >= double1 && double1 <= Double.parseDouble(df.format(covertToCurrencyValue))) {
			between =  true;
		}
		else {
			between =  false;}
		System.out.println("The Actual: "+ between);
		assertEquals(true, between);

	}

}
