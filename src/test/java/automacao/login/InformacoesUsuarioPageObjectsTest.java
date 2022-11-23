package automacao.login;

import static org.junit.Assert.assertEquals;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import pages.LoginPage;
import suporte.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuario.csv")

public class InformacoesUsuarioPageObjectsTest {
	private WebDriver driver;

	@Before
	public void SetUp() {
		driver = Web.createBrowserStack();
	}

	@Test
	public void AdicionarUmaInformacaoDoUsuario(@Param(name="login")String login,
			@Param(name="senha")String senha,
			@Param(name="tipo")String tipo,
			@Param(name="contato")String contato,
			@Param(name="mensagemEsperada")String mensagemEsperada) {
		String textoToast = new LoginPage(driver)
		.clickSignIn()
		.fazerLogin(login,senha)
		.clicarEmMe()
		.clicarMoreDataAboutYou()
		.clicarAddMoreDataAboutYou()
		.adcionarContato(tipo, contato)
		.CapturarToast();
		
		assertEquals(mensagemEsperada, textoToast);
		
	}

	@After
	public void ShutDown() {
		driver.close();

	}
}
