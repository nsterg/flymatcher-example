package com.flymatcher.skyscanner.client;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;



public class SkyscannerAPIClientTest {

  @Test
  public void testItinerarySearch() {
    SkyscannerAPIClient client = new SkyscannerAPIClient();
    String sessionUrl = client.createSearchSession();
    
    ClientResponse response = client.pollSession(sessionUrl);
    assertEquals("Unexpected response status", response.getStatus(), 200);
    assertFalse("Empty response", response.getEntity(String.class).isEmpty());
    
  }

}
