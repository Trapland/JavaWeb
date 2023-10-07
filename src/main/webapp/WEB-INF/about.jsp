<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String pageBody = (String) request.getAttribute("page-body");
    String context = request.getContextPath();
%>
<head>
    <title>Title</title>
    <title>Title</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!--Import Google Icon Font-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
<nav>
    <div class="nav-wrapper orange lighten-2">
        <a href="<%=context%>" class="brand-logo right">Java 201</a>
        <ul id="nav-mobile">
            <li><a href="<%=context%>/jsp">Sass</a></li>
            <li><a href="badges.html">Components</a></li>
            <li><a href="collapsible.html">JavaScript</a></li>
        </ul>
    </div>
</nav>
<main>
JSP (JavaServer Pages) - це технологія Java для створення динамічних веб-сторінок. Вона надає низку потужних можливостей для розробки веб-додатків. Ось конспект основних можливостей JSP:<br><br>

Інтеграція Java і HTML: JSP дозволяє вбудовувати код Java безпосередньо в HTML-сторінки, що спрощує створення динамічного контенту.<br><br>

Зручність для дизайнерів: Дизайнери можуть працювати з HTML, не турбуючись про код Java, оскільки Java-код в JSP-сторінках зазвичай залишається всередині тегів  або .<br><br>


    Сервлети і JSP: JSP можна використовувати разом із сервлетами. JSP-сторінки компілюються в Java-код сервлета при першому запиті та виконуються на сервері. Це дозволяє створювати складні веб-додатки з вищим рівнем абстракції.<br><br>

    Використання бібліотек тегів: JSP дозволяє створювати власні користувацькі теги або використовувати готові бібліотеки тегів для спрощення та поліпшення розробки веб-додатків.<br><br>

    Обробка форм і вхідних даних: JSP спрощує обробку форм на веб-сторінках за допомогою вбудованих тегів <form> та можливості витягувати та обробляти дані з запитів.<br><br>

    Інтернаціоналізація: JSP надає засоби для локалізації та інтернаціоналізації веб-сторінок, що дозволяє створювати додатки для різних мов і регіонів.<br><br>

    Валідація даних: За допомогою JSP можна виконувати перевірку та валідацію даних, введених користувачами, і виводити повідомлення про помилки на веб-сторінки.<br><br>

    Інтеграція з базами даних: JSP легко інтегруються з базами даних, дозволяючи створювати динамічні веб-додатки, які можуть взаємодіяти з даними.<br><br>

    Керування сесіями: JSP надають засоби для керування користувацькими сесіями, що дозволяє створювати персоналізовані веб-додатки.<br><br>

    Обробка помилок: JSP надають механізми для обробки та відображення помилок, які виникають під час виконання веб-додатка.<br><br>

    Безпека: JSP підтримують механізми для забезпечення безпеки додатків, такі як фільтри та механізми аутентифікації.<br><br>

    Використання EL (Expression Language): EL дозволяє зручно взаємодіяти з даними та об'єктами в JSP, а також виконувати обчислення та операції над даними.<br><br>

    Використання JSTL (JavaServer Pages Standard Tag Library): JSTL надає набір стандартних тегів і функцій для виконання загальних завдань, таких як цикли, умовні оператори та форматування.<br><br>

    Підтримка багатьох форматів виведення: JSP дозволяє легко генерувати різні формати виведення, такі як HTML, XML, JSON та інші.<br><br>

    Інтеграція з фреймворками: JSP може використовуватися разом з різними Java-фреймворками, такими як Spring, для створення потужних веб-додатків.<br><br>

    JSP є потужною та гнучкою технологією для розробки веб-додатків в Java, яка дозволяє створювати динамічні та інтерактивні веб-сайти.<br><br>
</main>
    <footer class="page-footer">
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
</body>

</html>
