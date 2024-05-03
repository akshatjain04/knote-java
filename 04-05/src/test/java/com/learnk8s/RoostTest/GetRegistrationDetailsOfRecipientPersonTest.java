// ********RoostGPT********
/*
Test generated by RoostGPT for test MiniProjects using AI Type Open AI and AI Model gpt-4-1106-preview

ROOST_METHOD_HASH=a991089179
ROOST_METHOD_SIG_HASH=5a667c5d20

 ########## Scenario ##########

{
  background: 'Given the base URL is "http://localhost:8080"',
  rule: null,
  scenario: 'Get Registration Details Of Recipient Person\r\n' +
    '    Given id of recipient as 27364922937 in path parameter\r\n' +
    '    And size=13 and offset=42 in request query\r\n' +
    '    When the client sends a GET request to endpoint "/recipients/persons/{id}/registrations" \r\n' +
    '    When authentication token is sent in request\r\n' +
    '    Then for successful request having status code 200, Verify that headers have api-version, page-size and  page-offset\r\n' +
    '    And response body must have element registrations and total.',
  title: 'Get Registration Details Of Recipient Person'
}

*/

// ********RoostGPT********
package com.learnk8s.RoostTest;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRegistrationDetailsOfRecipientPersonTest {

	private Map<String, String> headersMap;

	private String baseURL;

	private String endpoint;

	private String payloadFilePath;

	@BeforeEach
	public void setup() throws IOException {
		RestAssured.baseURI = "http://localhost:8080";
		baseURL = RestAssured.baseURI;
		payloadFilePath = Paths
			.get("src", "test", "java", "com", "learnk8s", "RoostTest",
					"GetRegistrationDetailsOfRecipientPersonTest.csv")
			.toString();
		BufferedReader reader = new BufferedReader(new FileReader(payloadFilePath));
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.trim().isEmpty()) {
				continue;
			}
			String[] payloadData = line.split("\\^\\|\\^");
			if (payloadData[0].equals("METHOD")) {
				continue;
			}
			String method = payloadData[0];
			endpoint = payloadData[1];
			String reqHeaders = payloadData[2];
			String reqBody = payloadData[3]; // Not used in a GET request
			String responseCode = payloadData[4];
			String responseBody = payloadData[5];

			headersMap = new HashMap<>();
			String[] headersArray = reqHeaders.replaceAll("[{}\"]", "").split(",");
			for (String header : headersArray) {
				String[] headerKeyValue = header.split(":");
				headersMap.put(headerKeyValue[0].trim(), headerKeyValue[1].trim());
			}
			break;
		}
		reader.close();
	}

	@Test
	public void testGetRegistrationDetailsOfRecipientPerson() {
		// Given id of recipient as 27364922937 in path parameter
		// And size=13 and offset=42 in request query
		// When the client sends a GET request to endpoint
		// "/recipients/persons/{id}/registrations"
		// When authentication token is sent in request
		// Then for successful request having status code 200, Verify that headers have
		// api-version, page-size and page-offset
		// And response body must have element registrations and total.

		// Define the path parameters for the request
		Map<String, Object> pathParams = new HashMap<>();
		pathParams.put("id", "27364922937");

		// Define the query parameters for the request
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("size", 13);
		queryParams.put("offset", 42);

		// Send GET request and validate the response
		Response response = given().headers(headersMap)
			.pathParams(pathParams)
			.queryParams(queryParams)
			.when()
			.get(endpoint)
			.then()
			.assertThat()
			.statusCode(200)
			// Validate the headers
			.and()
			.headers("api-version", equalTo(headersMap.get("api-version")), "page-size",
					equalTo(Integer.parseInt(headersMap.get("page-size"))), "page-offset",
					equalTo(Integer.parseInt(headersMap.get("page-offset"))))
			// Validate the response body
			.and()
			.body("total", isA(Integer.class), "registrations", isA(List.class))
			// Validate the structure of the registrations array elements if any
			.and()
			.body("registrations.size()", greaterThanOrEqualTo(0))
			// If registrations are present, validate the fields of the first registration
			// object
			.body("registrations", not(hasItem(nullValue())))
			.body("registrations[0]",
					allOf(hasKey("id"), hasKey("reference"), hasKey("startDate"), hasKey("endDate"), hasKey("status"),
							hasKey("credentialUrl"), hasKey("suspendedUntil"), hasKey("issuedAt"), hasKey("createdAt"),
							hasKey("updatedAt")))
			.extract()
			.response();

		// Verify that the response contains the expected data according to the API_SPEC
		// This step is optional, as the above assertions already validate the response
		// structure
		// However, it demonstrates how to extract response details for further validation
		// if needed
		Headers responseHeaders = response.getHeaders();
		assertThat(responseHeaders.getValue("api-version"), equalTo(headersMap.get("api-version")));
		assertThat(responseHeaders.getValue("page-size"), equalTo(headersMap.get("page-size")));
		assertThat(responseHeaders.getValue("page-offset"), equalTo(headersMap.get("page-offset")));
	}

}
