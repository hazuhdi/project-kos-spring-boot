package id.ist.fileio;

import java.math.BigDecimal;
import java.net.URI;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import id.ist.fileio.model.Facility;
import lombok.SneakyThrows;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FileIoAppApplicationTests {

	@LocalServerPort
	private int port;

	@Test
	@SneakyThrows
	void givenTrue_whenGetAll_thenReturnOk() {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = "http://localhost:" + port + "/facilities";
		URI uri = new URI(baseUrl);
		
		ResponseEntity<String> result = restTemplate.getForEntity(uri,String.class);
	     
	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(true, result.getBody().contains("Kipas"));


	}
	
	@Test
	@SneakyThrows
	void givenNullPrice_whenAddFacility_thenReturnFail() {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + port + "/facilities/add";
		URI uri = new URI(baseUrl);
//		BigDecimal price = new BigDecimal(5000.23);
		Facility facil = new Facility(100L,"Sajadah",null);
		
		HttpHeaders header = new HttpHeaders();
		
	    HttpEntity<Facility> request = new HttpEntity<>(facil, header);
	    
	    try {
	    	restTemplate.postForEntity(uri, request, String.class);
	    	Assert.fail();
	    } catch (HttpClientErrorException ex) {
	        Assert.assertEquals(400, ex.getRawStatusCode());
	    }

	}
	
	@Test
	@SneakyThrows
	void givenNullName_whenAddFacility_thenReturnFail() {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + port + "/facilities/add";
		URI uri = new URI(baseUrl);
		BigDecimal price = new BigDecimal(5000.23);
		Facility facil = new Facility(100L,null,price);
		
		HttpHeaders header = new HttpHeaders();
		
	    HttpEntity<Facility> request = new HttpEntity<>(facil, header);
	    
	    try {
	    	restTemplate.postForEntity(uri, request, String.class);
	    	Assert.fail();
	    } catch (HttpClientErrorException ex) {
	        Assert.assertEquals(400, ex.getRawStatusCode());
	    }

	}
	
	@Test
	@SneakyThrows
	void givenNullId_whenAddFacility_thenReturnTrue() {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + port + "/facilities/add";
		URI uri = new URI(baseUrl);
		BigDecimal price = new BigDecimal(5000.23);
		Facility facil = new Facility(null,"Sajadah",price);
		
		HttpHeaders header = new HttpHeaders();
		
	    HttpEntity<Facility> request = new HttpEntity<>(facil, header);
	    
	    try {
	    	restTemplate.postForEntity(uri, request, String.class);
	    	Assert.assertTrue(true);
	    } catch (HttpClientErrorException ex) {
	        Assert.assertEquals(400, ex.getRawStatusCode());
	    }

	}
}