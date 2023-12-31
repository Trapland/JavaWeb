<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String pageBody = (String) request.getAttribute("page-body");
    String context = request.getContextPath();
%>
<!doctype html>
<html>
<head>
    <title>Title</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!--Import Google Icon Font-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    <link rel="stylesheet" href="<%=context%>/CSS/site.css?time=<%= new Date().getTime()%>">

</head>
<body>
<nav>
    <div class="nav-wrapper orange lighten-2">
        <!-- Modal Trigger -->
        <a class="right modal-trigger auth-icon" href="#auth-modal"><i class="material-icons">exit_to_app</i></a>
        <a href="<%=context%>/" class="right site-logo">Java 201</a>
        <ul id="nav-mobile">
            <li><a href="<%=context%>/jsp">JSP</a></li>
            <li><a href="<%=context%>/filters">Filters</a></li>
            <li><a href="<%=context%>/ioc">IoC</a></li>
            <li><a href="<%=context%>/db">DB</a></li>
            <li><a href="<%=context%>/spa">SPA</a></li>
            <li><a href="<%=context%>/ws">WS</a></li>

        </ul>
    </div>
</nav>
<div class="container">
    <jsp:include page="<%=pageBody%>"/>
</div>
<footer class="page-footer orange lighten-2">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Footer Content</h5>
                <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer content.</p>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            © 2023 Copyright Text
            <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
        </div>
    </div>
</footer>


<!-- Modal Structure -->
<div id="auth-modal" class="modal">
    <div class="modal-content">
        <h4>Автентифікація на сайті</h4>
        <div class="input-field col s6">
            <i class="material-icons prefix">badge</i>
            <input id="auth-login" type="text">
            <label for="auth-login">Логін</label>
        </div>
        <div class="input-field col s6">
            <i class="material-icons prefix">lock</i>
            <input id="auth-password" type="password" class="validate">
            <label for="auth-password">Пароль</label>
        </div>
    </div>
    <div class="modal-footer">
        <b id="auth-message"></b>
        <a href="<%=context%>/signup" class="modal-close waves-effect waves-green btn-flat orange lighten-1">Реєстрація</a>
        <button id="auth-sign-in" class="modal-close waves-effect waves-green btn-flat orange lighten-2">Вхід</button>
    </div>
</div>
</body>

<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<!-- Site JS -->
<script src="<%=context%>/js/site.js"></script>
<script src="<%=context%>/js/spa.js?time=<%= new Date().getTime()%>"></script>

</html>
