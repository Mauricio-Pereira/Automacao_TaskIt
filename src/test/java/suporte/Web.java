package suporte;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Web {

	public static final String USERNAME ="mauriciopereira_AF6Jl5";
	public static final String AUTOMATE_KEY = "yF8q6cn5HegoEypAkfGZ";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	static String ambiente = "http://www.juliodelima.com.br/taskit/";
	
	public static WebDriver createChrome() {
		// abrir navegador maximizado em segundo plano
		System.setProperty("webdriver.chrome.driver",
				"E:\\Mauricio\\Desktop\\Tudo\\ESTUDOS DEV\\E2E\\Automatizações\\chromedriver");
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("start-maximized");
		
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(ambiente);
		return driver;
	}

	public static WebDriver createBrowserStack() {

		// Add the following capabilities to your test script
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("browserVersion", "latest");

		HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
		browserstackOptions.put("os", "Windows");
		browserstackOptions.put("osVersion", "10"); 
		browserstackOptions.put("resolution", "1280x1024");
		caps.setCapability("bstack:options", browserstackOptions);

		
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new java.net.URL(URL),caps);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(ambiente);
		} catch (Exception e) {
			System.out.println("Houveram problemas com a URL: " + e.getMessage());
		}
		 
		

		return driver;
	}

}
