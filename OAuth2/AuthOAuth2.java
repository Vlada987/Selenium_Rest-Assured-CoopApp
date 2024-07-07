package OAuth2;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class AuthOAuth2 implements IAuth {

	@Override
	public RequestSpecification auth(String token) {

		RequestSpecification reqSpec = RestAssured.given().auth().oauth2(token);
		return reqSpec;
	}

}
