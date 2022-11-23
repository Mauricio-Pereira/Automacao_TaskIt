package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public String CapturarToast() {

		// na mensage de id "toast-container" validar que o texto é "Your contact has been added!"
		return driver.findElement(By.id("toast-container")).getText();
	}
}
