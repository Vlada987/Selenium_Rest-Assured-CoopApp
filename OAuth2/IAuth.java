package OAuth2;

import io.restassured.specification.RequestSpecification;

public interface IAuth {
	
RequestSpecification auth(String token);

}
