package tasks;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import homepage.HomePageTests;
import pages.LoginPage;
import pages.TasksPage;

public class TasksPageTests extends BaseTests {
	HomePageTests homePageTest = new HomePageTests();
	LoginPage loginPage;
	TasksPage tasksPage;
	
	@Test
	public void criarNovaLista() throws Exception {
//		// Primeiramente realiza login
//		homePageTest.realizarLogin();
//		//Depois Inserimos o nome na lista
//		tasksPage.insereNomeLista("Lista de Teste haha");;
	}
	
	
}
