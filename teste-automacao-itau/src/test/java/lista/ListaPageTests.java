package lista;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import homepage.HomePageTests;
import pages.TasksPage;

public class ListaPageTests extends BaseTests{

	@Test
	public void insereNovaLista() throws Exception {
		HomePageTests homePageTests = new HomePageTests();	
		homePageTests.realizarLogin();
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.insereNomeLista("Lista");
		tasksPage.validaListaCriada("Lista");
	}
	
	@Test
	public void insereNovoItemLista(String nome_lista) throws Exception {
		
	}
}
