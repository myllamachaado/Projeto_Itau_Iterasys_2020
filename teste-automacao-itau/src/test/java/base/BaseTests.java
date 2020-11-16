package base;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.HomePage;
import pages.LoginPage;
import pages.TasksPage;

public class BaseTests {
	public static WebDriver driver;
	
	protected static HomePage homePage;
	protected LoginPage loginPage;
	protected TasksPage tasksPage;
	
	@BeforeAll
	public static void iniciar() {
		System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver\\86\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeEach
	public void iniciaPaginaInicial() {
		driver.get("https://todo.microsoft.com");
		homePage = new HomePage(driver);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}
	
	@AfterAll
	public static void finalizar() {
		driver.quit();
	}
}
