package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MePage extends BasePage {

	public MePage(WebDriver driver) {
		super(driver);
	}

	public MePage clicarMoreDataAboutYou() {
		// clicar no botao "more data about you"
		driver.findElement(By.xpath("//li[@class='tab col s3']/a[@href='#moredata']")).click();
		return this;
	}

	public AddContactPage clicarAddMoreDataAboutYou() {
		// clicar no botao "add more data"
		driver.findElement(By.xpath("//div[@class='row center']/button[@data-target='addmoredata']")).click();
		return new AddContactPage(driver);
	}
	
}
