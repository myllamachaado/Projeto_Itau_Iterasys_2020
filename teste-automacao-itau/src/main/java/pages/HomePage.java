package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	// Driver
	private WebDriver driver;
	//Componentes da Homepage relacionados a Login
	private By iconeSignIn = By.cssSelector("div[id*=\"mectrl_headerPicture\"]");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public LoginPage clicarBotaoSignIn() {
		driver.findElement(iconeSignIn).click();
		return new LoginPage(driver);
	}
	
	public LoginPage validaHomePage() {
		String pagina = driver.getCurrentUrl();
		System.out.println(pagina);
		if (pagina.equals("https://todo.microsoft.com/tasks/")) {
			System.out.println("*** O usuário está na página inicial corretamente");
		}
		else {
			System.out.println("*** O usuário náo está na página inicial");
		}
		return new LoginPage(driver);
	}

	
	public LoginPage validaUsuarioLogado() {
		if ((driver.findElement(By.cssSelector("div[class*=\"_2KqWkae0FcyhdNhWQ-Cp-M\"]")).isDisplayed())) {
			System.out.println("*** O usuário está logado corretamente");
		}
		else {
			System.out.println("*** O usuário não está logado");
		}
		return new LoginPage(driver);
	}

	
}
