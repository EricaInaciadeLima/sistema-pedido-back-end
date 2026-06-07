Fluxo de implementação

GET /usuarios/{id} -> Obtem usuario autenticado(obs.: refazer a tabela de cliente irá conter dados de endereço)
POST /usuarios/criar -> criar a conta(não precisa devolver nada)OK


manipular dado de maneira segura
jaxrs ?
ACID - Banco de dados
@Transactional -> pesquisar
commit e begin -> pesquisar

BEGIN. E COMMIT
acid
tarefas:
implementar as services de cupom, categorias
estudar sobre ACID e TRANSACOES
completar  a maioria dos testes unitarios

controller mockmvc para simular o comportamento do spring (200 e 500)
services precisam testar o "comportamento" de negocio

testes de unidade -> controller e service

GET /categorias?page=0&size=10&sorte=name ->find all
GET /categorias/{id} -> find by id -> sort, size, search 

GET /cupom -> busca uma lista de tudo e paginação find all
GET /cupom/{id} -> buscar com filtragem find by id
GET /cupom/{code} -> buscar com filtragem find by code
PUT /cupom/{id}/toogle -> desativar
POST /cupom/criar -> 

GET /produtos?page=0&size=10&sorte=name -> listagem de produtos
PATCH /produtos/{id}/imagens -> Upload de imagens(devolver payload id)
POST /produtos -> criar produto(obs.:sem o campo de umgUrl, body dessa requisição passar id_umgUrl)


GET /pedidos?page=0&size=10&sorte=name -> busca uma lista de pedidos e paginação
GET /pedidos/{id} -> listar um pedido

POST /carrinhos/usuarios/{id}/itens -> adicionar produto ao carrinho
PUT /carrinhos/usuarios/{id}/itens/{produto-id} -> atualiza quantidade de produto

POST /checkout ->  converter carrinho por pedido,
validar todos os itens dentro do estoque,
aplicar cupom de desconto,
resolve o endereço de entrega,
decrementa estoque,

POST /pagamentos/processar -> processar (ENUM: CREDITO_CARD, DEBITO_CARD, PIX, BOLETO)*

POST /auth/autenticar -> Autenticação vai retornar um jwt com a role de "admin" ou "user"*

POST /cupons -> criar cupom para uma lista de usuarios, a rota é protegida pelo admin*
------------

validar Exception
boas?
CategoriaNaoEncontradaException
ProdutoIdException
PedidoIdException
ImagemProdutoNaoEncontradaException
ProdutoSemCategoriaException

exagero?
EmailException
CPFNPJException
NomeProdutoException
DescricaoProdutoMuitoLongaException
PrecoProdutoInvalidoException
QuantidadeEstoqueInvalidaException
CidadeIdException
EnderecoIdException

que podem serem validados por annotations @NotBlank
@Size, etc...
-----------------




@Test
@DisplayName("Deve persistit produto com categoria e imagens com sucesso.")
void devePersistirProdutoComCategoriaEimagensComSucesso() {
//ArRange/Given(Preparação-> criar os inputs/requests necessários para poder testar o comportamento do método)
var categoriaId = UUID.randomUUID();
CategoriaEntity categoria = new CategoriaEntity( categoriaId, "Computadores");

        ImagemProdutoEntity imagemPrimaria = new ImagemProdutoEntity(
                UUID.randomUUID(),//pode ser null
                "https://exemplo.com/imagemprimaria.png",
                "imagemPrimaria.png",
                true,
                Instant.now(),
                null
        );
        ImagemProdutoEntity imagemSecundaria = new ImagemProdutoEntity(
                null,
                "https://exemplo.com/imagemsecundaria.png",
                "imagemPrimaria.png",
                true,
                Instant.now(),
                null
        );

        var produtoId = UUID.randomUUID();
        ProdutoEntity request = new ProdutoEntity();
        request.setNome("Notebook Lenovo");
        request.setDescricao("Modelo com placa dedicada RSXP");
        request.setPreco(Double.valueOf(1458.00));
        request.setQuantidadeEstoque(Integer.valueOf(1));

// request.setCategoriasId(Set.of(categoriaId));

        when(categoriaRepository.findAllById(anyCollection())).thenReturn(List.of(categoria));

        when(imagemProdutoRepository.findByIdIn(anyCollection())).thenReturn(List.of(imagemPrimaria, imagemSecundaria));

        when(produtoRepository.save(any(ProdutoEntity.class))).thenAnswer(invocationOnMock -> {
            ProdutoEntity produto = invocationOnMock.getArgument(0);
            if (produto.getId() == null) {
                produto.setId(produtoId);
            }
            return produto;
        });
        when(imagemProdutoRepository.saveAll(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

//When(Quando)/Act(Executar/Agir)->Executar os comportamentos
ProdutoEntity response = produtoService.criar(request);

//Then(Quando)/Assert(Verificar/Validar) -> Válidar as informaçoes que esperar, ou seja, comprarar request com response
assertNotNull(response.getId());
assertEquals(response.getNome(), request.getNome());
assertEquals(response.getDescricao(), request.getDescricao());
assertEquals(response.getPreco(), request.getPreco());
assertEquals(response.getQuantidadeEstoque(), request.getQuantidadeEstoque());
assertEquals(response.getId(), imagemPrimaria.getProduto().getId());
assertEquals(response.getId(), imagemSecundaria.getProduto().getId());
assertEquals(Set.of(produtoId), response.getCategorias().stream().map(CategoriaEntity::getId).collect(
Collectors.toSet()));
}


Function<String, String> = () -> IO.print()

Function<String, String> toUpper =
String::toUpperCase;
toUpper()



List<String> upper = names.stream()
.map(name -> name.toUpperCase())
.toList();

| name, indice, array |name, indice, array |name, indice, array | name, indice, array

List<String> upper = names.stream()
.map(toUpper)
.toList();

function processarPedido(pedido, callback) {

    console.log("Processando pedido:", pedido);

    callback(pedido);
}

processarPedido(
"Notebook Gamer",

    function(pedido) {
        console.log("Pedido finalizado:", pedido);
    }
);


@FunctionalInterface
public interface Soma {

    int somar(int a, int b);
}
@Compenents
public class SomaImpl implements Soma {

    @Override
    public int somar(int a, int b) {

        return a + b;
    }
}

public class Main {

    public static void main(String[] args) {

        Soma soma = new SomaImpl();

        int resultado = soma.somar(10, 20);

        System.out.println(resultado);
    }
}

public void executar(
int a,
int b,
Soma soma // passa lambda ou metodo e é instacia a classe que implementa a classe
) {

        int resultado = soma.somar(a, b);

        System.out.println(resultado);
    }


------------
@OneToMany(fetch = FetchType.EAGER)

padrao
@OneToMany(fetch = FetchType.LAZY)

hibernate 
@jsonignore -> evitar loop
Product → Category → Product → Category

SELECT DISTINCT p
FROM Product p
LEFT JOIN FETCH p.categories
LEFT JOIN FETCH p.images
WHERE p.id = :id 

---

primeiro se vai buscar o produto por produtoid com findbyid
fazer upload da foto, chamando o s3service.upload
chamar o produtorepository.save do produto com id da iimagem gerada no s3service.upload
String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
String key = keyPrefix + fileName;
String keyPrefix = "products/" + productId + "/"
extrair o id da url da imagem

tratar as exceptions -> cenarios que podem falhar o carregamento da imagem

criar service s3 -> fazer upload da foto, chamando o s3service.upload
chamar o produtorepository.save do produto com id da iimagem gerada no s3service.upload(nome randomico)
String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename(); -> nome da imagem
service de produto -> primeiro se vai buscar o produto por produtoid com findbyid



try (InputStream inputStream = file.getInputStream()) {
PutObjectRequest putRequest = PutObjectRequest.builder()
.bucket(bucketName)
.key(key)
.contentType(file.getContentType())
.contentLength(file.getSize())
.build();

            s3Client.putObject(putRequest, RequestBody.fromInputStream(inputStream, file.getSize()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload image to S3: " + e.getMessage(), e);
        }