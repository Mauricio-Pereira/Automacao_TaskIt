package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddContactPage extends BasePage {

	public AddContactPage(WebDriver driver) {
		super(driver);
	}

	public AddContactPage escolherTipoDeContato(String tipo) {
		// clicar na combo de name "Type" e escolhe a opçao "Phone"
		WebElement campoType = driver.findElement(By.xpath("//select[@name='type']"));
		new Select(campoType).selectByValue(tipo);
		return this;
	}

	public AddContactPage digitarContato(String contato) {
		// No campo de name "contact" digitar "+5535991993297"
		driver.findElement(By.xpath("//select[@name='type']")).findElement(By.xpath("//input[@name='contact']")).sendKeys(contato);
		return this;
	}

	public MePage clicarSalvar() {
		// no campo de link de text "SAVE" que está no popup
		driver.findElement(By.xpath("//div[@class='modal-footer']/a")).click();
		return new MePage(driver);
	}


	public MePage adcionarContato(String tipo, String contato) {
		escolherTipoDeContato(tipo);
		digitarContato(contato);
		clicarSalvar();
		return new MePage(driver);
	}
}
