package com.qa.armoury.mock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.armoury.domain.Armour;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // random port prevents conflicts
@AutoConfigureMockMvc // creates MockMVC object for sending tests
@Sql(scripts = { "classpath:armour-schema.sql",
		"classpath:armour-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test") // sets test to not drop the production database
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

		Armour responseBody = new Armour(2, "Plate", 1500, 18, true, 65);// shows the expected result
		String responseBodyAsJSON = this.mapper.writeValueAsString(responseBody); // wraps it in JSON

		ResultMatcher checkStatus = status().isCreated(); // checks the status code is correct
		ResultMatcher checkBody = content().json(responseBodyAsJSON); // checks the body matches the response

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody); // The actual check
																				// Makes the request and checks the
																				// response
	}

	@Test
	void testGetArmours() throws Exception {
		RequestBuilder request = get("/armour/armourRack");
		ResultMatcher checkStatus = status().isOk();

		Armour armour = new Armour(1, "Plate", 1500, 18, true, 65);
		List<Armour> armourList = List.of(armour);
		String responseBody = this.mapper.writeValueAsString(armourList);
		ResultMatcher checkBody = content().json(responseBody);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetArmour() throws Exception {
		RequestBuilder request = get("/armour/armour/1");
		ResultMatcher checkStatus = status().isOk();

		Armour armour = new Armour(1, "Plate", 1500, 18, true, 65);
		String responseBody = this.mapper.writeValueAsString(armour);
		ResultMatcher checkBody = content().json(responseBody);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testReforge() throws Exception {
		Armour requestBody = new Armour("Leather", 10, 11, false, 10);
		String requestBodyAsJSON = this.mapper.writeValueAsString(requestBody);

		RequestBuilder request = put("/armour/reforge/1").contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyAsJSON);

		Armour responseBody = new Armour(1, "Leather", 10, 11, false, 10);
		String responseBodyAsJSON = this.mapper.writeValueAsString(responseBody);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(responseBodyAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testSmelt() throws Exception {
		RequestBuilder request = delete("/armour/smelt/1");
		ResultMatcher checkStatus = status().isNoContent();

		this.mvc.perform(request).andExpect(checkStatus);

	}
}
