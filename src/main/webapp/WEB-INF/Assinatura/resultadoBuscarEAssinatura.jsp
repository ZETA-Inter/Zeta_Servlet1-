<%@ page import="com.zeta_servlet.model.Adm" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link
          href="https://fonts.googleapis.com/css2?family=Poppins&display=swap"
          rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Adm/consultarAdm.css">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/LOGO ZETA - 5.png"
        type="image/x-icon">
  <title>Consultar ADM</title>
</head>
<body>
<header class="header">
  <div class="container">
    <div class="logo">
      <img src="${pageContext.request.contextPath}/assets/Group 12229.svg" alt="Logo ZETA">
      <span class="logo-text">ZETA</span>
    </div>
    <nav class="nav">
      <div class="menu">
        <input type="radio" name="link" id="Home">
        <label for="Home"><a
                href="${pageContext.request.contextPath}/index.html">Início</a></label>
      </div>
    </nav>
  </div>
</header>

<main>
  <div class="consultarADM">
    <h1>Consultar ADM</h1>
    <h2>Procure pelos seguintes campos</h2>
  </div>

  <div class="sidebar">
    <div class="icon active">
      <a href="${pageContext.request.contextPath}/index.html"><img
              src="${pageContext.request.contextPath}/assets/icon1BarADM.svg"
              alt="Ícone 1"></a>
    </div>
    <div class="icon"><img src="${pageContext.request.contextPath}/assets/icon2BarADM.svg"
                           alt="Ícone 2"></div>
    <div class="icon"><img src="${pageContext.request.contextPath}/assets/icon3BarADM.svg"
                           alt="Ícone 3"></div>
    <div class="icon"><img src="${pageContext.request.contextPath}/assets/icon4BarADM.svg"
                           alt="Ícone 4"></div>
    <div class="icon"><img src="${pageContext.request.contextPath}/assets/icon5BarADM.svg"
                           alt="Ícone 5"></div>
    <div class="icon"><img src="${pageContext.request.contextPath}/assets/icon6BarADM.svg"
                           alt="Ícone 6"></div>
  </div>

  <div class="campos">
    <form id="admForm" action="/ZETA_Servlet/AdmServlet"
          method="get" onsubmit="return validarFormulario()">
      <label for="inputBusca">Buscar</label>

      <div class="input-wrapper">
        <div class="selection-indicator"
             id="selectionIndicator">ID</div>
        <input type="text" id="inputBusca" name="valor"
               placeholder="Digite o ID (apenas números)">
        <span class="arrow"
              onclick="abrirPopup()">&#9662;</span>
        <input type="hidden" id="action" name="action"
               value="buscarPorId">
        <div class="error-message" id="errorMessage"></div>
      </div>

      <button type="submit">Buscar</button>
    </form>

    <!-- pop-up com opções -->
    <div class="popup-opcoes" id="popupOpcoes">
      <p
              onclick="selecionarOpcao('buscarPorId', 'Digite o ID (apenas números)', 'ID')">Buscar
        por ID</p>
      <p
              onclick="selecionarOpcao('buscarPorEmail', 'Digite o email completo', 'Email')">Buscar
        por Email</p>
    </div>
  </div>

  </div>
</main>

<script>
  const popup = document.getElementById("popupOpcoes");
  const actionInput = document.getElementById("action");
  const inputBusca = document.getElementById("inputBusca");
  const errorMessage = document.getElementById("errorMessage");
  const selectionIndicator = document.getElementById("selectionIndicator");

  // Configurações de validação para cada tipo de busca
  const configs = {
    'buscarPorId': {
      placeholder: 'Digite o ID (apenas números)',
      pattern: '^[0-9]+$',
      title: 'Por favor, digite apenas números para o ID',
      error: 'ID deve conter apenas números',
      indicator: 'ID'
    },
    'buscarPorEmail': {
      placeholder: 'Digite o email completo',
      pattern: '^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$',
      title: 'Por favor, digite um email válido',
      error: 'Por favor, digite um email válido',
      indicator: 'Email'
    }
  };

  function abrirPopup() {
    popup.style.display = popup.style.display === "block" ? "none" : "block";
  }

  function selecionarOpcao(acao, placeholder, indicatorText) {
    const config = configs[acao];

    // Atualizar o campo hidden
    actionInput.value = acao;

    // Atualizar o placeholder e configurações do input
    inputBusca.placeholder = config.placeholder;
    inputBusca.title = config.title;

    // Atualizar indicador visual
    selectionIndicator.textContent = config.indicator;
    selectionIndicator.style.display = 'block';

    // Ajustar padding do input para o indicador
    inputBusca.style.paddingLeft = '50px';

    // Limpar campo e mensagens de erro
    inputBusca.value = '';
    inputBusca.classList.remove('input-error');
    errorMessage.style.display = 'none';

    // Fechar popup
    popup.style.display = "none";

    // Remover seleção anterior e marcar a nova
    const opcoes = popup.querySelectorAll('p');
    opcoes.forEach(opcao => opcao.classList.remove('selected-option'));

    // Encontrar e marcar a opção selecionada
    const opcaoSelecionada = Array.from(opcoes).find(opcao =>
            opcao.getAttribute('onclick').includes(acao)
    );
    if (opcaoSelecionada) {
      opcaoSelecionada.classList.add('selected-option');
    }
  }

  function validarFormulario() {
    const valor = inputBusca.value.trim();
    const acao = actionInput.value;
    const config = configs[acao];

    // Validar se o campo está vazio
    if (!valor) {
      mostrarErro(`Por favor, digite um ${config.indicator.toLowerCase()}`);
      return false;
    }

    // Validar formato com regex
    const regex = new RegExp(config.pattern);
    if (!regex.test(valor)) {
      mostrarErro(config.error);
      return false;
    }

    // Se passou em todas as validações
    return true;
  }

  function mostrarErro(mensagem) {
    errorMessage.textContent = mensagem;
    errorMessage.style.display = 'block';
    inputBusca.classList.add('input-error');

    // Auto-esconder mensagem de erro após 3 segundos
    setTimeout(() => {
      errorMessage.style.display = 'none';
      inputBusca.classList.remove('input-error');
    }, 3000);
  }

  // Fechar popup ao clicar fora
  document.addEventListener("click", (e) => {
    const wrapper = document.querySelector(".input-wrapper");
    if (!wrapper.contains(e.target)) {
      popup.style.display = "none";
    }
  });

  // Inicializar com a opção padrão (ID)
  document.addEventListener("DOMContentLoaded", function() {
    selecionarOpcao('buscarPorId', 'Digite o ID (apenas números)', 'ID');
  });

  // Limpar erro quando o usuário começar a digitar
  inputBusca.addEventListener('input', function() {
    if (this.classList.contains('input-error')) {
      this.classList.remove('input-error');
      errorMessage.style.display = 'none';
    }
  });
</script>
<div class="tabela">
  <table>
    <thead>
    <tr>
      <td>ID</td>
      <td>Email</td>
      <td>Senha</td>
    </tr>
    </thead>
    <tbody>
    <%
      @SuppressWarnings("unchecked")
      List<Adm> adms = (List<Adm>) request.getAttribute("adms");
      if (adms != null && !adms.isEmpty()) {
        for (Adm adm : adms) {
    %>
    <tr>
      <td><%= adm.getId() %></td>
      <td><%= adm.getEmail() %></td>
      <td><%= adm.getSenha() %></td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="3">Nenhum administrador encontrado</td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
</div>