<%@ page contentType="text/html;charset=UTF-8" %>

<h1>Java web. Вступ.</h1>
<p>
    Новий проєкт - архетип webapp.<br>
    Для запуску проєкту потрібен веб-сервер. Варіанти:<br>
    Tomcat (8), Glassfish (4-5), WildFly (22) та інші.<br>
    Ключовий момент - обираємо версію з підтримкою javax.<br>
    Будь-який з них завантажується архівом та просто розпаковується.<br>
</p>
<%  // Razor - @{ }
    String str = "Hello";
    str += " world";
    int x = 10;
%>
<p>
    str = <%= str %>, x + 5 = <%= x + 5 %>
</p>
<ul>
    <% for (int i = 0; i < 5; i++) { %>
    <li>
        Item No <%= i + 1 %>
    </li>
    <% } %>
</ul>
<jsp:include page="fragment.jsp"/>
<h2>Сервлети</h2>
<p>
    Сервлети - це класи Java, призначені для роботи з мережними задачами.
    Для роботи з ними треба встановити Servlet API (за допомогою Maven).
    Після створення класу сервлету його треба включити в маршрутизацію.
</p>
<ul>
    <li>За допомогою Web.xml</li>
    <li>За допомогою</li>
    <li>За допомогою</li>
</ul>