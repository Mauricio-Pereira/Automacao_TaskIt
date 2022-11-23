package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage {

	public LoginFormPage(WebDriver driver) {
		super(driver);
	}

	public LoginFormPage typeLogin(String login) {
		// preencher campo login
		return this;
	}

	public LoginFormPage typePassword(String password) {
		// preencher campo senha
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/form/div[2]/div[2]/input")).sendKeys(password);
		return this;
	}

	public SecretPage fazerLogin(String login, String password) {
		// preencher campo login
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/form/div[2]/div[1]/input")).sendKeys(login);

		// preencher campo senha
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/form/div[2]/div[2]/input")).sendKeys(password);

		// clicar no botao "sign in"
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/a")).click();
		return new SecretPage(driver);

	}
}
