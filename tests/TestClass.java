package tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import OAuth2.AuthOAuth2;
import OAuth2.Context;
import OAuth2.Methods;
import io.restassured.response.Response;
import pages.ActionClass;

public class TestClass {

	ActionClass ac = new ActionClass();
	String baseUrl = "http://coop.apps.symfonycasts.com";
	String userID = "";
	String client_secret = "";
	public String token = "";
	String clientID = "";
	String code = "";
	String redirectUrl = "https://www.google.com/";

	@BeforeTest
	public void setData() {

		List<String> apiData = ac.gatherApiData();
		clientID = apiData.get(0);
		client_secret = apiData.get(1);
		userID = apiData.get(3);

	}

	@Test(priority = 1)
	public void test_00_getBearerToken() throws JSONException {

		Context context = new Context();
		context.baseURL = baseUrl;
		context.URI = "/token";
		Map<String, Object> forms = new HashMap<>();
		forms.put("client_id", clientID);
		forms.put("client_secret", client_secret);
		forms.put("grant_type", "client_credentials");
		forms.put("redirect_uri", redirectUrl);
		context.formParams = forms;

		Response resp = Methods.POST(context);
		token = resp.jsonPath().get("access_token");

		Assert.assertTrue(resp.statusCode() == 200);
		Assert.assertFalse(token.isEmpty());
	}

	@Test(priority = 2)
	public void test01_Authenticate() throws JSONException {

		Context context = new Context();

		context.baseURL = baseUrl;
		context.URI = "/api/" + userID + "/barn-unlock";
		System.out.println("USER ID??? " + userID);
		context.auth = new AuthOAuth2();
		System.out.println("TOEKN " + token);
		context.baererToken = token;
		Response resp = Methods.POST(context);
		resp.then().log().all();

		Assert.assertTrue(resp.statusCode() == 200);
		Assert.assertTrue(resp.jsonPath().get("action").equals("barn-unlock"));
		Assert.assertTrue(resp.jsonPath().get("success"));

	}

}
