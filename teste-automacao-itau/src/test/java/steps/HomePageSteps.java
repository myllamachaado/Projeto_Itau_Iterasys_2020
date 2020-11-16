package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import pages.TasksPage;

public class HomePageSteps{  
	
	private static WebDriver driver;
	LoginPage loginPage = new LoginPage(driver);
	
	@Before
	public static void iniciar() {
		System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver\\86\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://todo.microsoft.com");
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}
	
	@After
	public static void finalizar() throws Exception {
		driver.quit();	
	}
	
	
	
	
	
	@Given("que estou com o navegador aberto na home page do sistema")
	public void que_estou_com_o_navegador_aberto_na_home_page_do_sistema() {
		HomePage homePage = new HomePage(driver);
		homePage.validaHomePage();
	}

	@When("clico no icone de login no canto superior")
	public void clico_no_icone_de_login_no_canto_superior() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.clicarBotaoSignIn();
	}

	@And("insiro o email {string} e clico no botao proximo")
	public void insiro_o_email_e_clico_no_botao_proximo(String email) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		loginPage.inserirEmail(email);
		loginPage.clicarBotaoProximo();
	}

	@And("insiro a senha {string} e clico no botao proximo")
	public void insiro_a_senha_e_clico_no_botao_proximo(String senha) throws Exception {
		Thread.sleep(1000);
		loginPage.inserirSenha(senha);
		Thread.sleep(1000);
		loginPage.clicarBotaoProximo();
	}

	@And("confirmo que quero continuar conectado")
	public void confirmo_que_quero_continuar_conectado() throws Exception {
		Thread.sleep(2000);
		loginPage.clicarBotaoProximo();
	}

	@Then("a pagina de tarefas e exibida")
	public void a_pagina_de_tarefas_e_exibida() throws Exception {
		HomePage homePage = new HomePage(driver);
		Thread.sleep(5000);
		loginPage = homePage.validaUsuarioLogado();
	}
	
	@And("insiro o nome {string} como nome da lista e aperto o botao ENTER")
	public void insiro_o_nome_como_nome_da_lista_e_aperto_o_botao_enter(String nome_lista) throws Exception {
		TasksPage tasksPage = new TasksPage(driver);
		Thread.sleep(1000);
		tasksPage.insereNomeLista(nome_lista);
	}
	
	@Then("insiro os livros {string}, {string}, {string}")
	public void insiro_os_livros(String livro1, String livro2, String livro3) throws Exception {
		TasksPage tasksPage = new TasksPage(driver);
		Thread.sleep(3000);
		tasksPage.insereLivroLista(livro1);
		tasksPage.insereLivroLista(livro2);
		tasksPage.insereLivroLista(livro3);
	}

	@Then("e defino o livro {string} como importante")
	public void e_defino_o_livro_como_importante(String nome_livro) throws Exception {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.marcaClassificacaoLivro(nome_livro);
	}
	
	@And("insiro os livros presentes no arquivo {string} na lista {string}")
	public void insiro_os_livros_presentes_no_arquivo_na_lista(String arquivo, String nome_lista) throws Exception {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.insereLivroCSV(arquivo, nome_lista);
	}
	
	
	@Then("a nova lista {string} consta cadastrada com todos os livros do arquivo {string}")
	public void a_nova_lista_consta_cadastrada_com_todos_os_livros_do_arquivo(String nome_lista, String arquivo) throws Exception {
		List<List<String>> nome_livros = new ArrayList<>();
		TasksPage tasksPage = new TasksPage(driver);
		nome_livros = tasksPage.retornaItensCSV(arquivo);
		
		tasksPage.validaInformacoesLista(nome_lista, nome_livros);
	}
	
}
	
