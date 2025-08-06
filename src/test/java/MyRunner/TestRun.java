package MyRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.bhupesh.AppTest.HomePage;

public class TestRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable r = () -> {
			try {
				Class test = Class.forName("com.bhupesh.AppTest.HomePage");
				HomePage o = (HomePage)test.newInstance();
				Method m = test.getMethod("LaunchTest", null);
				m.invoke(o, null);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		new Thread(r).start();
		new Thread(r).start();		
	}

}
