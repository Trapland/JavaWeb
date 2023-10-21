<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String connectionStatus = (String) request.getAttribute("connectionStatus");
%>
<h1>Робота з базами даних</h1>
<h2>JDBC</h2>
<p>
    <strong>JDBC</strong> - Java DataBase Connectivity - технологія
    доступу до даних, аналогічна подібним ADO, PDO тощо.
    Головна ідея - створення підключення, формування запиту та передача
    його до СУБД, одержання результатів виконання запиту та даних, що
    ним повертаються.
</p>
<p>
    Технологія надає універсальний інтерфейс доступу до даних(однаковий
    для різних СУБД), конкретна реалізація здійснюється шляхом підключення
    драйвер відповідної БД (які також називають конекторами).
    Налаштування підключення здійснюється шляхом реєстрації драйвера
    та надсилання запиту до СУБД щодо підключення (автентифікаціії).
    Нагадуємо, що паролі до БД слід зберігати в окремому файлі (конфігурації)
    який вилучено з репозиторію (у .gitignore)
</p>
<p>
    Оскільки дані про підключення можуть знадобитись у різних частинах
    проєкту найбільш доцільно створювати його у вигляді окремого сервісу.
</p>
<p>
    Статус підключення: <strong><%=connectionStatus%></strong>
</p>
<h2>Управління даними</h2>
<p>
    <button id="db-create-button" class="waves-effect waves-light btn orange lighten-2"><i class="material-icons right">cloud</i>create</button>
    <input name="user-name" placeholder="Ім'я">
    <input name="user-phone" placeholder="Телефон">
    <button id="db-insert-button" class="waves-effect waves-light btn orange lighten-2"><i class="material-icons right">phone_iphone</i>Замовити дзвінок</button>
    <br/>
    <u id="out"></u>
</p>
<div class="row">
    <button id="db-read-button" class="waves-effect waves-light btn orange lighten-2"><i class="material-icons right">view_list</i>Переглянути замовлення</button>
    <button id="db-show-all-button" class="waves-effect waves-light btn orange lighten-2"><i class="material-icons right">view_list</i>Переглянути усі замовлення</button>
</div>
<div id="calls-container">

</div>