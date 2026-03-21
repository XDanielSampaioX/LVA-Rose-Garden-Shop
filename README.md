# LVA-Rose-Garden-Shop

Projeto de loja para venda de rosas do deserto, com frontend em Next.js, backend em Spring Boot e script SQL para estrutura inicial do banco.

## Estrutura atual

- `front`: aplicação Next.js responsável pela vitrine e listagem de produtos.
- `back`: API Spring Boot com módulos de produtos, usuários, autenticação e carrinho.
- `database`: script SQL base para criação do banco.

## Arquitetura

- Backend: arquitetura em camadas com `controller`, `service`, `repository`, `dto`, `domain`, `mapper`, `validator` e `security`.
- Frontend: organização por camadas leves com `app`, `components`, `domains`, `services`, `types` e utilitários compartilhados.
- O pacote `core` foi removido do backend e do frontend. Os DTOs ativos do backend agora ficam em `back/src/main/java/com/LVA_Rose_Garden_Shop/dto`.
- O catálogo possui seed inicial com imagens locais em Base64 e atualização pública semanal via automação Java.
- O domínio principal continua usando entidades mais ricas e objetos de valor em `domain/user/valueobject` e `domain/product/valueobject`.

## Tecnologias usadas

- Frontend: Next.js 15, React 19, TypeScript e Tailwind CSS 4.
- Backend: Java 21, Spring Boot 3, Spring Data JPA, Validation, MapStruct e JWT.
- Banco: MySQL.

## Como rodar o projeto

### Backend

1. Configure uma instância MySQL.
2. O banco foi pensado para ser recriado do zero com um schema limpo e sem aproveitar estruturas antigas incompatíveis. A configuração atual do backend assume isso no primeiro start.
3. O arquivo [`application.properties`](/C:/Projects/LVA-Rose-Garden-Shop/back/src/main/resources/application.properties) já foi configurado com valores padrão de desenvolvimento:
   `jdbc:mysql://localhost:3306/LVA_Rose_Garden_Shop`, usuário `root`, senha `1234` e porta `8080`.
4. As credenciais OAuth do Google são carregadas automaticamente do arquivo `client_secret_*.json` em `back/src/main/resources`.
5. Ajuste esses valores conforme seu ambiente se necessário.
6. Execute o backend a partir da pasta [`back`](/C:/Projects/LVA-Rose-Garden-Shop/back).
7. Ao subir com o banco vazio, o backend recria o schema e popula a tabela `produto` com o catálogo inicial definido em [`produtos-publicos.json`](/C:/Projects/LVA-Rose-Garden-Shop/back/src/main/resources/seeds/produtos-publicos.json).
8. A sincronização semanal do catálogo pode ser controlada por `app.catalog-sync.enabled`, `app.catalog-sync.cron` e `app.catalog-sync.zone` em [`application.properties`](/C:/Projects/LVA-Rose-Garden-Shop/back/src/main/resources/application.properties).
9. Os scripts Python antigos de raspagem foram removidos; a automação ativa do catálogo agora fica integralmente no backend Java.

### Frontend

1. Instale as dependências na pasta [`front`](/C:/Projects/LVA-Rose-Garden-Shop/front).
2. Se necessário, defina `NEXT_PUBLIC_API_URL` apontando para a API. O padrão atual é `http://localhost:8080`.
3. Execute o frontend e acesse a aplicação no navegador.
4. A rota inicial é o catálogo público em `/`.
5. O login fica em `/login` e só é necessário quando o usuário quiser comprar ou acessar a conta.

## Endpoints já existentes

- `POST /usuarios`: cria usuário.
- `PUT /usuarios/{id}`: edita usuário existente.
- `GET /usuarios/{id}`: busca usuário por id.
- `POST /autenticacao/login`: autentica usuário e retorna token JWT.
- `GET /autenticacao/google`: inicia o fluxo de autenticação com Google.
- `POST /autenticacao/logout`: remove o cookie de sessão da aplicação.
- `POST /assistente/perguntar`: responde dúvidas do cliente e sugere produtos da loja.
- `GET /produtos/listar`: lista produtos com paginação e filtros.
- `GET /produtos/{id}`: busca produto por id.
- `POST /produtos`: cria produto.
- `PUT /produtos/{id}`: edita produto.
- `GET /carrinho/{idUsuario}`: lista carrinho do usuário.
- `POST /carrinho`: adiciona item ao carrinho.
- `DELETE /carrinho/{idUsuario}/remover`: remove registros do carrinho do usuário.
- `DELETE /carrinho/{idUsuario}/limpar`: limpa o carrinho do usuário.

## Ajustes realizados nesta revisão

- Implementado login com Google usando as credenciais salvas em `resources`.
- A aplicação passou a emitir e validar JWT próprio, em vez de usar o token bruto do Google como sessão.
- O usuário autenticado pelo Google passa a ser persistido no banco.
- O banco foi tratado para partir de um schema limpo, evitando incompatibilidades herdadas.
- O backend usa `spring.jpa.hibernate.ddl-auto=update` e mantém a evolução do schema alinhada com as entidades ativas.
- O catálogo recebeu suporte a imagem persistida no banco (`imagemBase64`, `imagemMimeType`) e referência pública de origem.
- Foi adicionada uma carga automática inicial com produtos inspirados em vitrines públicas de Fortaleza.
- Foi criada uma automação Java semanal para revisitar as fontes públicas, atualizar metadados e ignorar imagens duplicadas por hash.
- A página inicial passou a ser o catálogo público e o login foi isolado em `/login`.
- Adicionado middleware no frontend para observar o cookie do JWT e limpar a sessão quando expirar.
- Reorganizada a estrutura do frontend com componentes reutilizáveis de vitrine, banner, botões, badges, header e footer.
- O front foi remodelado com linguagem visual mais próxima de lojas de rosas do deserto de Fortaleza, usando a referência visual importada do arquivo `Desert Rose Login - Lovable.html`.
- O assistente da loja foi exposto no backend e no frontend para responder dúvidas de compra, cultivo e seleção de produtos.
- Foram removidos arquivos padrão, scripts Python legados e artefatos de referência que não participavam mais da aplicação ativa.

## Automação Semanal

- A sincronização em Java fica em [`CatalogoPublicoScheduler.java`](/C:/Projects/LVA-Rose-Garden-Shop/back/src/main/java/com/LVA_Rose_Garden_Shop/service/catalogsync/CatalogoPublicoScheduler.java), [`CatalogoPublicoSyncService.java`](/C:/Projects/LVA-Rose-Garden-Shop/back/src/main/java/com/LVA_Rose_Garden_Shop/service/catalogsync/CatalogoPublicoSyncService.java) e [`CatalogoPublicoCrawler.java`](/C:/Projects/LVA-Rose-Garden-Shop/back/src/main/java/com/LVA_Rose_Garden_Shop/service/catalogsync/CatalogoPublicoCrawler.java).
- O job roda por padrão toda segunda-feira às 03:00 em `America/Fortaleza`.
- A deduplicação usa hash SHA-256 da imagem. Se a imagem nova repetir uma já conhecida, ela é ignorada.
- Se a imagem nova parecer branded, a automação preserva a imagem atual do produto e atualiza apenas os metadados públicos.

## Maven

- O backend agora inclui [`back/.mvn/maven.config`](/C:/Projects/LVA-Rose-Garden-Shop/back/.mvn/maven.config) e [`back/maven-settings.xml`](/C:/Projects/LVA-Rose-Garden-Shop/back/maven-settings.xml) para forçar um repositório Maven local ao projeto e evitar o bloqueio causado pelo `.m2` global do ambiente.

## Testes

- Backend: `mvn test` validado com sucesso.
- Frontend: `next lint` validado com sucesso.
- Frontend: `tsc --noEmit` validado com sucesso.
- O `next build` ainda pode falhar nesta sandbox por restrição de processo (`spawn EPERM`), então a checagem principal de front ficou em lint e TypeScript.
