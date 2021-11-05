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
import com.qa.armoury.domain.Weapon;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // random port prevents conflicts
@AutoConfigureMockMvc // creates MockMVC object for sending tests
@Sql(scripts = { "classpath:weapon-schema.sql",
		"classpath:weapon-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class WeaponIntegreationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper; // using the same mapper that spring convert objects to and from JSON

	@Test
	void testForge() throws Exception {
		Weapon requestBody = new Weapon("Longsword", "Martial", 15, "1d10"); // setting up the example to test
		String requestBodyAsJSON = this.mapper.writeValueAsString(requestBody); // translates to JSON

		RequestBuilder request = post("/weapon/forge").contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyAsJSON); // builds the request

		Weapon responseBody = new Weapon(2, "Longsword", "Martial", 15, "1d10"); // shows the expected result
		String responseBodyAsJSON = this.mapper.writeValueAsString(responseBody); // wraps it in JSON

		ResultMatcher checkStatus = status().isCreated(); // checks the status code is correct
		ResultMatcher checkBody = content().json(responseBodyAsJSON); // checks the body matches the response

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody); // The actual check
		// Makes the request and checks the
		// response
	}

	@Test
	void testGetWeapon() throws Exception {
		RequestBuilder request = get("/weapon/weapon/1");
		ResultMatcher checkStatus = status().isOk();

		Weapon weapon = new Weapon(1, "Longsword", "Martial", 15, "1d10");
		String responseBody = this.mapper.writeValueAsString(weapon);
		ResultMatcher checkBody = content().json(responseBody);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testWeaponRack() throws Exception {
		RequestBuilder request = get("/weapon/weaponRack");
		ResultMatcher checkStatus = status().isOk();

		Weapon weapon = new Weapon(1, "Longsword", "Martial", 15, "1d10");
		List<Weapon> weaponList = List.of(weapon);
		String responseBody = this.mapper.writeValueAsString(weaponList);
		ResultMatcher checkBody = content().json(responseBody);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testReforge() throws Exception {
		Weapon requestBody = new Weapon("Rapier", "Martial", 25, "1d8 Piercing");
		String requestBodyAsJSON = this.mapper.writeValueAsString(requestBody);

		RequestBuilder request = put("/weapon/reforge/1").contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyAsJSON);

		Weapon responseBody = new Weapon(1, "Rapier", "Martial", 25, "1d8 Piercing");
		String responseBodyAsJSON = this.mapper.writeValueAsString(responseBody);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(responseBodyAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testSmelt() throws Exception {
		RequestBuilder request = delete("/weapon/smelt/1");
		ResultMatcher checkStatus = status().isNoContent();

		this.mvc.perform(request).andExpect(checkStatus);

	}

}
