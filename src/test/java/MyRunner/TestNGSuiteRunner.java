package MyRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.TestNG;

public class TestNGSuiteRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestNG runnerNG = new TestNG();
		List<String> xmls = new ArrayList<String>();		
		if (args.length != 0) { 
			xmls = Arrays.asList(args);
		}
		else {
			// comment addded in development branch
			//xmls.add("testng.xml");
			xmls.add("src/test/resources/testng.xml");			
		}
		
		runnerNG.setTestSuites(xmls);
		runnerNG.run();
	}

}