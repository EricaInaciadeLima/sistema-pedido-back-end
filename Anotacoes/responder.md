Em inglês:
1- What are the advantages and disadvantages of performing Dependency Injection (DI) through a constructor versus using
@Autowired?
1- Qual é a vantagem e a desvantagem de fazer a Injeção de Dependência (Dependency Injection - DI) via construtor ou
utilizando @Autowired?
RESPOSTA

2- What is the role of a constructor method — whether with parameters or empty — in a Java class within the context of
Spring?
2- Qual é o papel do método construtor, seja com variáveis (parâmetros) ou vazio, em uma classe Java dentro do contexto
do Spring?
RESPOSTA

3- What is the responsibility of using final and what is its relevance in Java and Spring?
3- Qual é a responsabilidade de utilizar final e qual a sua relevância em Java e Spring?
RESPOSTA

4- qual razão para criar métodos construtores com parametros e vazios nas entidades?

5- Por que de criar metodos getters e setters dentro das classes de entidade? Existe alguma depencia que simplifica?Sim,
qual sua vantagem e desvantagem ao utilizar uma depencia?

6- explique sobre arquitetura mvc, é uma arquitetura de camadas(distribuindo responsabilidades)

7- explique sobre a camada model/entity, sua responsabilidade, depencia que utiliza, annotations do spring boot, etc.

8- explique sobre INVARIANTE.

9- explique sobre a camada repository, sua responsabilidade, depencia que utiliza, annotations do spring boot, etc.

10- @ManyToOne//muista imagens pertence a unico produto
@JoinColumn(name = "produto_id")//diz qual atributo da entidade db, representa essa relação(fk)
@com.fasterxml.jackson.annotation.JsonIgnore//Transforma o objeto em JSON, o que é a biblioteca Jackson?

11- biblioteca java.util

12- java colletion framework

13- Por que utilizar Serializable nas entidades do java?

---
Banco de dados

Flyway é uma ferramenta que tras qual facilidade/praticiade no desenvolvimento?

Explique quais tabelas que existe no projeto e seus relacionamentos:









----


Tests, TDD, Principios e conceitos

Quem popularizou desenvolvimento orientado a teste(TDD) e sua relevancia?

O que é ciclo do TDD e discriminar cada etapa?

Como construir um teste seguindo boas praticas?

//INVARIANTE --> É UMA REGRA DE CONSISTENCIA DO ESTADO INTERNO DO OBJETO
//MVC-CAMADAS

----
rascunho
@Test
@DisplayName("Deve persistir produto com categoria sem imagens com sucesso.")
void devePersistirProdutoComCategoriaEsemImagensComSucesso() {
//Arrange/Given
var categoriaId = UUID.randomUUID();
CategoriaEntity categoria = new CategoriaEntity();
categoria.setId(categoriaId);
categoria.setNome("Computadores");

        var produtoId = UUID.randomUUID();
        ProdutoEntity request = new ProdutoEntity();
        request.setNome("Headset");
        request.setDescricao("Modelo Pro-Max");
        request.setPreco(120.99);
        request.setQuantidadeEstoque(10);
        request.setCategorias(Set.of(categoria));

        when(categoriaRepository.findAllById(anyCollection())).thenReturn(List.of(categoria));
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(request);


        //Act/When -> Executar
        ProdutoEntity response = produtoService.criar(request);

        //Assert/Then
        assertEquals(response.getNome(), request.getNome());
        assertEquals(response.getDescricao(), request.getDescricao());
        assertEquals(response.getPreco(), request.getPreco());
        assertEquals(response.getQuantidadeEstoque(), request.getQuantidadeEstoque());
        assertEquals(response.getCategorias(), request.getCategorias());

        //Validar comportamento dos métodos mockado
        var verificar = verify(produtoRepository).save(any(ProdutoEntity.class));//verificar se metodo save fez seu papel
        System.out.println("verificar: " + verificar);
    }