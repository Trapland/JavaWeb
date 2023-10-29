<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>SPA</h1>
<p>
    Автентифікація та авторизація за допомогою токенів здійснюється
    наступним чином<br/>
    - користувач вводить логін та пароль, формується асинхронний запит
    до API авторизації, у відповідь отримується токен.<br/>
    - токен перевіряється на цілісність та зберігається у локальному
    сховищі. Подальші запити включають одержаний токен до
    заголовків.
</p>
<p>
    Наявність токену на сторінці: <b id="spa-token-status"></b>
</p>
<auth-part></auth-part>
<button id="spa-log-out" class="btn orange lighten-1"><i class="material-icons">logout</i></button>
<button id="spa-get-data" class="btn orange lighten-2"><i class="material-icons">folder</i></button>
<button id="spa-get-data2" class="btn orange lighten-2"><i class="material-icons">folder</i></button>
<button id="spa-get-data3" class="btn orange lighten-2"><i class="material-icons">folder</i></button>
<button id="spa-get-data4" class="btn orange lighten-2"><i class="material-icons">folder</i></button>
