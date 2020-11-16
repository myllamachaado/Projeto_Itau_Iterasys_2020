@tag
Feature: Lista de livros

		@tag1
		Scenario Outline: Criação de uma lista de dados estáticos
			Given que estou com o navegador aberto na home page do sistema
			 When clico no icone de login no canto superior
			  And insiro o email "teste.itau.automacao@gmail.com" e clico no botao proximo
			  And insiro a senha "iterasys@123" e clico no botao proximo
			  And confirmo que quero continuar conectado
			 Then a pagina de tarefas e exibida
			  And insiro o nome "Lista" como nome da lista e aperto o botao ENTER
			  And insiro os livros "A Arte da Guerra", "A Cauda Longa", "A Torre Negra"
			  And e defino o livro "A Torre Negra" como importante
			 Then a nova lista "Lista" consta cadastrada com todos os livros do arquivo "resources/csv/livros_estatico.csv"
	
	@tag2
	Scenario Outline: Criação de uma lista de dados a partir de CSV
		Given que estou com o navegador aberto na home page do sistema
		 When clico no icone de login no canto superior
		  And insiro o email "teste.itau.automacao@gmail.com" e clico no botao proximo
		  And insiro a senha "iterasys@123" e clico no botao proximo
		  And confirmo que quero continuar conectado
		 Then a pagina de tarefas e exibida
		  And insiro o nome "Minha Lista" como nome da lista e aperto o botao ENTER
		  And insiro os livros presentes no arquivo "resources/csv/livros.csv" na lista "Minha Lista"
		 Then a nova lista "Minha Lista" consta cadastrada com todos os livros do arquivo "resources/csv/livros.csv"
