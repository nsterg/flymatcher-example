package com.flymatcher.skyscanner.client;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SkyscannerAPIClient {

  private static final String API_KEY = "fl234911264411451448532625636303";

  public ClientResponse pollSession(String url) {

    Client client = Client.create();

    WebResource webResource = client.resource(url + "?apiKey=" + API_KEY);

    return webResource.accept("application/json").get(ClientResponse.class);

  }

  public String createSearchSession() {

      Client client = Client.create();

      WebResource webResource = client.resource(
          "http://partners.api.skyscanner.net/apiservices/pricing/v1.0?apiKey=" + API_KEY);

      MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
      formData.add("country", "GB");
      formData.add("currency", "GBP");
      formData.add("locale", "en-GB");
      formData.add("originplace", "EDI-sky");
      formData.add("destinationplace", "LHR-sky");
      formData.add("outbounddate", "2016-06-05");
      formData.add("inbounddate", "2016-06-06");
      formData.add("adults", "1");

      ClientResponse response =
          webResource.header("Content-Type", "application/x-www-form-urlencoded")
              .header("accept", "application/json").post(ClientResponse.class, formData);

      if (response.getStatus() != 201) {
        throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
      }

      List<String> output = response.getHeaders().get("Location");
      return output.get(0);

  }

}
