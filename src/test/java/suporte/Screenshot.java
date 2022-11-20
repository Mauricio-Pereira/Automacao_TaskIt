package suporte;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	public static void tirar(WebDriver driver, String arquivo) {
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(screenshot, new File(arquivo));
		}
		catch (Exception e) {
			System.out.println("Houveram erros ao copiar o arquivo para a pasta" + e.getMessage());
			
		}
	}

}
 