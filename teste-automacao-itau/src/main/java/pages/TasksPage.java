package pages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TasksPage {
	// Driver
	private WebDriver driver;
	List<WebElement> listasCriadas = new ArrayList();
	List<WebElement> listasLivros = new ArrayList();
	
	// Mapeamento dos itens da tela
	private By inputNovaLista = By.cssSelector("input[id*=\"baseAddInput-addList\"]");
	private By inputNovaTarefa = By.cssSelector("input[id*=\"baseAddInput-addTask\"]");
	private By buttonImportante = By.cssSelector("span[class*=\"importanceButton\"]");
	private By checkboxConcluido = By.cssSelector("span[class*=\"checkBox big\"]");
	
	private By checkboxConcluidoTrue = By.cssSelector("span[class*=\"checkBox completed big\"]");
	private By botaoAdicionarTarefa = By.cssSelector("class[id*=\"baseAdd-button\"]");
	private By h2NomeLista = By.cssSelector("h2[class*=\"listTitle\"]");
	private By buttonAdicionarTarefa = By.cssSelector("button[class*=\"baseAdd-icon\"]");
	
	// Mapeamento das listas
	private By listaDeListas = By.cssSelector("div[class*=\"sortable-lists\"]");
	private By listaDeLivros = By.cssSelector("div[class*=\"taskItem-body\"]");
		
	public TasksPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void insereNomeLista(String nome_lista) throws Exception {
		Thread.sleep(1000);
		if(!validaListaCriada(nome_lista)) {
			driver.findElement(inputNovaLista).sendKeys(nome_lista + Keys.ENTER);
		} 
		Thread.sleep(1000);
	}
	
	public void insereLivroLista(String nome_livro) throws Exception {
		Thread.sleep(1000);
		driver.findElement(inputNovaTarefa).sendKeys(nome_livro + Keys.ENTER);
	}
	
	public boolean validaListaCriada(String nome_lista) throws Exception {
		listasCriadas = driver.findElements(listaDeListas);
		for (WebElement element : listasCriadas) {
			if(element.getText().trim().contains(nome_lista.trim())){
				return true;
			}
		}
		return false;
	}
	
	public void marcaClassificacaoLivro(String nome_livro) throws Exception {
		listasLivros = driver.findElements(listaDeLivros);
		for (WebElement element : listasLivros) {
			Thread.sleep(1000);
			if(element.getText().trim().equals(nome_livro.trim())){
				element.findElement(buttonImportante).click();
			}
		}
	}
	
	public void marcarComoConcluida(String nome_livro) throws Exception {
		listasLivros = driver.findElements(listaDeLivros);
		for (WebElement element : listasLivros) {
			Thread.sleep(1000);
			if(element.getText().trim().equals(nome_livro.trim())){
				element.findElement(checkboxConcluido).click();
			}
		}
	}
	
	public void validaInformacoesLista(String nome_lista, List<List<String>> nome_livros) throws Exception {
		listasLivros = driver.findElements(listaDeLivros);
		int index = 0;
		String[] values =  {};
		List<String> line = new ArrayList<>();
		
		if(validaListaCriada(nome_lista)) {
			for (WebElement element : listasLivros) {
				for (List<String> livros : nome_livros) {
					line = livros;
					for (String a : line) {
						values = a.split(",");
						if(element.getText().trim().equals(values[0].trim())){
							index = nome_livros.indexOf(livros);
						}
					}
				}
				if(index >= 0) {
					nome_livros.remove(index);
				}
				index = -1;
			}
			if(nome_livros.size() == 0) {
				System.out.println("*** Todos os livros constam na lista!");
			}
			else {
				System.out.println("*** Lista incompleta! Itens restantes:" + nome_livros);
			}
		} 
	}
	
	public void insereLivroCSV(String arquivo, String nome_lista) throws Exception {
		List<List<String>> records = retornaItensCSV(arquivo);
		for (List<String> element : records) {
			for (String itens : element) {
				String[] livros = itens.split(",");
				insereLivroLista(livros[0]);
				if(livros[1].equals("1")) {
					marcaClassificacaoLivro(livros[0]);
				}
				if(livros[2].equals("1")) {
					marcarComoConcluida(livros[0]);
				}
					
			}
		}
	}
	
	public List<List<String>> retornaItensCSV(String arquivo) throws Exception {
		Thread.sleep(1000);
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(";");
		        records.add(Arrays.asList(values));
		    }
		}
		return records;
	}
}

