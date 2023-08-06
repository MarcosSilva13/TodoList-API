# Api de lista de tarefas
<p>
  A API permite criar uma tarefa, listar as tarefas em pendentes e concluídas, atualizar e deletar.
  Permite realizar o cadastro de usuários, cada um deles terá sua lista de tarefas.
</p>

<h2> Tecnologias utilizadas </h2>
<p>
  🔹<strong> Java 17 </strong> <br>
  🔹<strong> Spring Boot </strong> <br>
  🔹<strong> Spring Data JPA </strong> <br>
  🔹<strong> Spring Security </strong> <br>
  🔹<strong> JWT </strong> <br>
  🔹<strong> MySQL </strong> <br>
  🔹<strong> Maven </strong><br>
</p>

<h2> Endpoints - Autenticação </h2>

<h3>🔹POST🔹</h3>

```bash
/auth/register ==> (Cadastra um novo usuário).

/auth/login ==> (Retorna o token se o usuário for autenticado corretamente).
```
<h2> Endpoints - Usuário </h2>
obs: Necessário estar autenticado.

<h3>🔹GET🔹</h3>

```bash
/users ==> (Retorna as informações do usuário).
```

<h2> Endpoints - Tarefas </h2>
obs: Necessário estar autenticado.

<h3>🔹GET🔹</h3>

```bash
/todos/{status} ==> (Lista todas as tarefas de um usuário por status. pending(tarefas pendentes) ou completed(tarefas concluídas)).
```

<h3>🔹POST🔹</h3>

```bash
/todos ==> (Cadastra uma nova tarefa).
```

<h3>🔹PUT🔹</h3>

```bash
/todos/{id} ==> (Atualiza os dados de uma tarefa pelo id).

/todos/complete/{id} ==> (Atualiza o status de uma tarefa de pendente para concluída pelo seu id).
```

<h3>🔹DELETE🔹</h3>

```bash
/todos/{id} ==> (Deleta uma tarefa pelo seu id).
```
