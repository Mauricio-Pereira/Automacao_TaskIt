package automacao.login;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import suporte.Generator;
import suporte.Screenshot;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuario.csv")

public class InformacoesUsuario {
	private WebDriver driver;
	@Rule
	public TestName test = new TestName();
	
	
	@Before
	public void setUp() {
		// abrir navegador maximizado em segundo plano
		System.setProperty("webdriver.chrome.driver",
				"E:\\Mauricio\\Desktop\\Tudo\\ESTUDOS DEV\\E2E\\Automatizações\\chromedriver");
		String ambiente = "http://www.juliodelima.com.br/taskit/";
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("start-maximized");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(ambiente);

		// clicar no link de texto "sign in"
		driver.findElement(By.xpath("/html/body/nav/div/div/ul[1]/li/a")).click();

		// preencher campo login
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/form/div[2]/div[1]/input")).sendKeys("mauriciopvieira1");
		// preencher campo senha
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/form/div[2]/div[2]/input")).sendKeys("91447165");
		// clicar no botao "sign in"
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/a")).click();
		// clicar no botao "hi mauricio vieira pere"
		driver.findElement(By.className("me")).click();

		// clicar no botao "more data about you"
		driver.findElement(By.xpath("//li[@class='tab col s3']/a[@href='#moredata']")).click();
	}

	@After
	public void fecharNavegador() {
		driver.close();
	}
	
	@Test
	public void AdicionarUmaInformacaoDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato,
			@Param(name="mensagem")String mensagemEsperada){

		// clicar no botao "add more data"
		driver.findElement(By.xpath("//div[@class='row center']/button[@data-target='addmoredata']")).click();

		// clicar na combo de name "Type" e escolhe a opçao "Phone"
		WebElement popupAddMoreData = driver.findElement(By.xpath("//select[@name='type']"));

		// No campo de name "contact" digitar "+5535991993297"
		WebElement campoType = popupAddMoreData.findElement(By.xpath("//select[@name='type']"));
		new Select(campoType).selectByValue(tipo);
		driver.findElement(By.xpath("//input[@name='contact']")).sendKeys(contato);

		// no campo de link de text "SAVE" que está no popup
		driver.findElement(By.xpath("//div[@class='modal-footer']/a")).click();

		// na mensage de id "toast-container" validar que o texto é "Your contact has
		// been added!"
		WebElement mensagem = driver.findElement(By.id("toast-container"));
		assertEquals(mensagemEsperada, mensagem.getText());
		System.out.println("OK");
	}

	
	
	
	@Test
	public void removerUmContatoDeUmUsuario(){
		//clicar no elemento pelo xpath "//span[text()='+5535991993297']/following-sibling::a"
		driver.findElement(By.xpath("//span[text()='+5535991993297']/following-sibling::a")).click();
		
		//confirmar a janela javascript
		driver.switchTo().alert().accept();
		
		//validar que a mensagem "Rest in peace,dear phone!" foi apresentada
		WebElement mensagem = driver.findElement(By.xpath("//div[@id='toast-container']/div[@class='toast rounded']"));
		assertEquals(mensagem.getText(), "Rest in peace, dear phone!");
		
		String screenshotArquivo = "E:\\Mauricio\\Desktop\\Tudo\\ESTUDOS DEV\\E2E\\Automatizações\\Validações\\TaskIt"+Generator.dataHoraParaArquivo() + test.getMethodName()+".png";
		Screenshot.tirar(driver, screenshotArquivo);
		
		//aguardar até 10 segundos para que a janela desapareça
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.stalenessOf(mensagem));
		
		
		//fazer logout
		driver.findElement(By.linkText("Logout")).click();
	}

	
	
}
