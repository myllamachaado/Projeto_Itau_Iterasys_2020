package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;

public class LoginPage {

	// Driver
	private WebDriver driver;
	
	//Componentes da Homepage relacionados a Login
	private By inputEmail = By.cssSelector("input[name*=\"loginfmt\"]");
	private By inputSenha = By.cssSelector("input[id*=\"i0118\"]");
	private By botaoProximo = By.cssSelector("input[id*=\"idSIButton9\"]");
	private By botaoProximoSenha = By.cssSelector("input[id*=\"idSIButton9\"]");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inserirEmail(String texto) {
		driver.findElement(inputEmail).sendKeys(texto);
	}
	
	public void inserirSenha(String texto) {
		driver.findElement(inputSenha).sendKeys(texto);
	}
	
	public void clicarBotaoProximo() {
		driver.findElement(botaoProximo).click();
	}
	
	
}
