

## Anotações e Atributos

### Pontos que preciso entender

### ProdutoEntity

**Indagação:**
Por que, ao referenciar a tabela de junção (ou intermediária/associativa) como `name = "tbl_categoria"`, considerando que o relacionamento entre as tabelas produto e categoria é do tipo N:N?
O correto, nesse caso, seria utilizar na propriedade `name` a tabela `"tbl_produto_categoria"`? (confirmar).

---

## Relacionamento ManyToMany e JoinTable

Em relação às anotações e atributos, utiliza-se `@ManyToMany` em conjunto com `@JoinTable` quando existe uma tabela intermediária (associativa/de junção), mas não se deseja tratá-la como uma entidade no código, principalmente por não possuir atributos próprios.

A anotação `@JoinTable` indica justamente que existe uma tabela intermediária responsável por ligar as tabelas de produto e categoria.

Dentro de `@JoinTable`, definimos o nome da tabela de junção e as colunas que referenciam as respectivas tabelas. O atributo `name` define o nome da tabela intermediária (associativa), enquanto:

* `joinColumns = @JoinColumn(name = "produto_id")` indica que, na tabela de junção, a coluna que representa a entidade **Produto** é `produto_id`.
* `inverseJoinColumns = @JoinColumn(name = "categoria_id")` indica que a coluna que representa a entidade **Categoria** é `categoria_id`.

---

## Adendo sobre o papel do JPA

Quando definimos na entidade Java a anotação `@JoinTable`, estamos dizendo que existe uma tabela de junção, mas que não queremos representá-la diretamente como uma entidade no Java.

Em vez disso, delegamos ao JPA a responsabilidade de gerenciar e armazenar os dados nessa tabela de forma transparente, “por debaixo dos panos”.

---

## Papel da tabela associativa no SQL

No SQL, por ser uma linguagem declarativa, o banco de dados não suporta diretamente relacionamentos do tipo N:N (muitos para muitos). Por essa razão, é necessário criar uma tabela associativa para resolver esse problema.

Ou seja, o principal motivo da existência da tabela associativa é permitir que o banco consiga representar corretamente esse relacionamento de muitos para muitos.

Geralmente, essa tabela contém como atributos os IDs das duas tabelas relacionadas.

---

## Controle de duplicidade no banco

Além disso, esse tipo de relacionamento pode gerar problemas de duplicidade de dados se não houver controle adequado. Para evitar isso, a tabela associativa também aplica uma regra de integridade, geralmente utilizando uma chave primária composta.

Essa chave primária composta é formada pelas chaves estrangeiras das duas tabelas relacionadas (por exemplo, `produto_id` e `categoria_id`). Dessa forma, o banco de dados garante que a mesma combinação não seja inserida mais de uma vez, evitando registros duplicados.

Assim, a tabela associativa tem dois papéis principais:

* Permitir a implementação do relacionamento N:N no banco de dados
* Garantir a integridade dos dados, evitando duplicidades

---

## Uso de Set e HashSet no Java

No atributo:

```java
private Set<CategoriaEntity> categorias = new HashSet<>();
```

utiliza-se a interface `Set`, implementada pela classe `HashSet`.

O `Set` evita duplicidade de dados em memória (no Java), antes mesmo de chegar ao JPA. No entanto, o banco de dados continua sendo o responsável final por garantir a integridade dessas informações, validando-as conforme as regras definidas na tabela de junção (como chave primária composta).

Além disso, o `HashSet` não garante a ordem em que os dados serão armazenados.

A prevenção de duplicidade no `Set` depende diretamente da implementação correta dos métodos `equals()` e `hashCode()`.

---

## Visão Banco vs Java

No banco de dados, a tabela intermediária é apenas um detalhe de implementação, funcionando como uma ponte:

```
Produto ←→ tbl_produto_categoria ←→ Categoria
```

Já no Java, essa complexidade é abstraída, e enxergamos apenas:

```
Produto ←→ Categoria
```

---

## Papel do JPA no relacionamento

O JPA cuida automaticamente da tabela intermediária. Através das anotações `@ManyToMany` e `@JoinTable`, ele entende o relacionamento como:

```
Produto → tbl_produto_categoria → Categoria
```

No fluxo de persistência:

* O JPA tenta inserir os dados (montando o `INSERT`)
* O banco de dados decide se a operação é válida ou não, com base nas constraints definidas (como chave primária e restrições de integridade)

---
