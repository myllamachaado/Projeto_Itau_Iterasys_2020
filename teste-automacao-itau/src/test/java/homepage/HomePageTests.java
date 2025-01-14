package homepage;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import pages.HomePage;
import pages.LoginPage;
import pages.TasksPage;

public class HomePageTests extends BaseTests{
	
	@Test
	public void realizarLogin() throws Exception {
		loginPage = homePage.clicarBotaoSignIn();
		loginPage.inserirEmail("teste.itau.automacao@gmail.com");
		loginPage.clicarBotaoProximo();
		loginPage.inserirSenha("iterasys@123");
		Thread.sleep(2000);
		loginPage.clicarBotaoProximo();
		Thread.sleep(2000);
		loginPage.clicarBotaoProximo();
	}

	@Test
	public void validarPaginaInicial() throws Exception {
		Thread.sleep(2000);
		homePage.validaHomePage();
	}
	
	@Test
	public void validarLoginUsuario() throws Exception {
		realizarLogin();
		Thread.sleep(2000);
		loginPage =  homePage.validaUsuarioLogado();
	}
	
	@Test
	public void cadastrarLista() throws Exception {
		Thread.sleep(2000);
		TasksPage tasksPage = new TasksPage(driver);
		String[] CSV = {"resources/csv/livros_estatico.csv", "resources/csv/livros.csv"};
		String[] listas = {"Lista", "Minha Lista"};
		
		realizarLogin();
		for (int i = 0; i< listas.length; i++) {
			List<List<String>> livros = tasksPage.retornaItensCSV(CSV[i]);
			// Cadastra a lista
			tasksPage.insereNomeLista(listas[i]);
			// Cadastra os livros na respectiva lista & já marca livros classificados como "importante"
			tasksPage.insereLivroCSV(CSV[i], listas[i]);
			// Valida os cadastros
			tasksPage.validaInformacoesLista(listas[i], livros);
			livros.clear();
		}
		
	}	
}
