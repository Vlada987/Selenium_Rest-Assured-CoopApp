package pages;

import java.util.List;

import org.testng.annotations.Test;

public class ActionClass extends DriverSetup {

	PageMethods pm;

	public List<String> gatherApiData() {

		setup();
		pm = new PageMethods(driver);
		pm.getUrl();
		pm.registration();
		String userId = pm.getUserID();
		System.out.println(userId);
		pm.createApp();
		List<String> data = pm.getAppData();
		data.add(userId);
//String code=pm.redirectUrlAndGetToken(data);
//data.add(code);
		teardown();
		return data;
	}

}
