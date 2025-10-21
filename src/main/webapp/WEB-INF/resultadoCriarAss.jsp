<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="criarAssinatura.css">
    <link rel="shortcut icon" href="./assets/LOGO ZETA - 5.png" type="image/x-icon">
    <title>Criar Assinatura</title>
</head>
<body>
<header class="header">
    <div class="container">
        <div class="logo">
            <img src="./assets/Group 12229.svg" alt="Logo ZETA">
            <span class="logo-text">ZETA</span>
        </div>
        <nav class="nav">
            <div class="menu">
                <input type="radio" name="link" id="Home">
                <label for="Home"><a href="admin.html">Início</a></label>
            </div>
        </nav>
    </div>
</header>
<main>
    <div class="inserirAssinatura">
        <h1>Criar Assinatura</h1>
        <%
            int option = (int) request.getAttribute("option");
            if(option>0){


        %>
        <h2 style="color: green;">Assinatura inserida com sucesso</h2>
        <%
            }else{
        %>
        <h2 style="color: red">Erro ao inserir assinatura</h2>
        <%
            }%>
    </div>
    <div class="sidebar">
        <div class="icon active">
            <a href="menuAdm.html">
                <img src="./assets/icon1BarADM.svg" alt="Ícone 1">
            </a>
        </div>
        <div class="icon">
            <a href="assinatura.html">
                <img src="./assets/icon2BarADM.svg" alt="Ícone 2">
            </a>

        </div>
        <div class="icon">
            <img src="./assets/icon3BarADM.svg" alt="Ícone 3">
        </div>
        <div class="icon">
            <img src="./assets/icon4BarADM.svg" alt="Ícone 4">
        </div>
        <div class="icon">
            <img src="./assets/icon5BarADM.svg" alt="Ícone 5">
        </div>
        <div class="icon">
            <img src="./assets/icon6BarADM.svg" alt="Ícone 6">
        </div>
    </div>
    <form action="criarAss" method="get">
        <div class="campos1">
            <input type="text" placeholder="Plano" name="plano" id="plano" required>
            <input type="text" placeholder="beneficiarios" name="beneficiarios" id="beneficiarios" required>
            <input type="text" placeholder="Beneficio Descrição" name="beneficiariosDesc" id="beneficiariosDesc" required>

        </div>
        <div class="campos2">
            <input type="text" placeholder="Preço Fixo" name="precoFixo" id="precoFixo" required>
            <input type="text" placeholder="Preço Produtor" name="precoProdutor" id="precoProdutor" required>
            <button type="submit">Inserir</button>
        </div>
    </form>
</main>
</body>
</html>