package automacao.login;

import static org.junit.Assert.assertEquals;


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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuario.csv")

public class InformacoesUsuario {
	private WebDriver driver;
	@Rule
	public TestName test = new TestName();
	
	
	@Before
	public void setUp() {
		driver = Web.createChrome();

		
		
	}

	@After
	public void fecharNavegador() {
		driver.close();
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
