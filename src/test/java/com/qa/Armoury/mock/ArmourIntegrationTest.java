package com.qa.armoury.mock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.armoury.domain.Armour;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // random port prevents conflicts
@AutoConfigureMockMvc // creates MockMVC object for sending tests
public class ArmourIntegrationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper; // using the same mapper that spring convert objects to and from JSON

	@Test
	void testForge() throws Exception {
		Armour requestBody = new Armour("Plate", 1500, 18, true, 65);// setting up the example to test
		String requestBodyAsJSON = this.mapper.writeValueAsString(requestBody); // translates to JSON

		RequestBuilder request = post("/armour/forge").contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyAsJSON); // builds the request

		Armour responseBody = new Armour(1, "Plate", 1500, 18, true, 65);// shows the expected result
		String responseBodyAsJSON = this.mapper.writeValueAsString(responseBody); // wraps it in JSON

		ResultMatcher checkStatus = status().isCreated(); // checks the status code is correct
		ResultMatcher checkBody = content().json(responseBodyAsJSON); // checks the body matches the response

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody); // The actual check
																				// Makes the request and checks the
																				// response
	}
}
