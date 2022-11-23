package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage  extends BasePage{
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public LoginFormPage clickSignIn() {
		// clicar no link de texto "sign in"
				driver.findElement(By.xpath("/html/body/nav/div/div/ul[1]/li/a")).click();
				return new LoginFormPage(driver);
	}
}
