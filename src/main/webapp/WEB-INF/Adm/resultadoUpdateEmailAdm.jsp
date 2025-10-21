<%--
  Created by IntelliJ IDEA.
  User: matheusraimundo-ieg
  Date: 13/10/2025
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Adm/atualizarAdm.css">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/LOGO ZETA - 5.png" type="image/x-icon">
  <title>Atualizar ADM</title>
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
        <label for="Home"><a href="${pageContext.request.contextPath}/index.html">Início</a></label>
      </div>
    </nav>
  </div>
</header>
<main>
  <div class="atualizarADM">
    <h1>${mensagem}</h1>
  </div>
  <div class="sidebar">
    <div class="icon active">
      <a href="${pageContext.request.contextPath}/index.html">
        <img src="${pageContext.request.contextPath}/assets/icon1BarADM.svg" alt="Ícone 1">
      </a>
    </div>
    <div class="icon">
      <img src="${pageContext.request.contextPath}/assets/icon2BarADM.svg" alt="Ícone 2">
    </div>
    <div class="icon">
      <img src="${pageContext.request.contextPath}/assets/icon3BarADM.svg" alt="Ícone 3">
    </div>
    <div class="icon">
      <img src="${pageContext.request.contextPath}/assets/icon4BarADM.svg" alt="Ícone 4">
    </div>
    <div class="icon">
      <img src="${pageContext.request.contextPath}/assets/icon5BarADM.svg" alt="Ícone 5">
    </div>
    <div class="icon">
      <img src="${pageContext.request.contextPath}/assets/icon6BarADM.svg" alt="Ícone 6">
    </div>
  </div>
</main>
</body>
</html>
