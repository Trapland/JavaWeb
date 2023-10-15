<%@ page import="step.learning.dto.models.RegFormModel" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<h2>Реєстрація користувача</h2>
<%
    // Перевіряємо чи є повідомлення попередньої форми, формуємо значення для полів
    RegFormModel model = (RegFormModel) request.getAttribute("reg-model");
    String loginValue = model == null ? "" : model.getLogin();
    String nameValue = model == null ? "" : model.getName();
    String emailValue = model == null ? "" : model.getEmail();
    String birthdateValue = model == null ? "" : model.getBirthdateAsString();
    Map<String,String> errors = model == null ? new HashMap<String,String>() : model.getErrorMessages();
    String nameClass = model == null ? "validate" : (errors.containsKey("name") ? "invalid" : "valid");
    String loginClass = model == null ? "validate" : (errors.containsKey("login") ? "invalid" : "valid");
    String emailClass = model == null ? "validate" : (errors.containsKey("email") ? "invalid" : "valid");
    String dateClass = model == null ? "validate" : (errors.containsKey("birthdate") ? "invalid" : "valid");
    String regMessage = (String) request.getAttribute("reg-message");
    if (regMessage == null) {regMessage = "";}
%>
<p><%=regMessage%></p>
<div class="row">
    <form class="col s12" method="post" action="" enctype="multipart/form-data">
        <div class="row">
            <div class="input-field col s6">
                <i class="material-icons prefix">badge</i>
                <input value="<%=loginValue%>" name="reg-login" id="reg-login" type="text" class="<%=loginClass%>">
                <label for="reg-login">Логін</label>
                <%if(errors.containsKey("login")){%>
                <span class="helper-text" data-error="<%=errors.get("login")%>"></span>
                <%}%>
            </div>
            <div class="input-field col s6">
                <i class="material-icons prefix">person</i>
                <input value="<%=nameValue%>" name="reg-name" id="reg-name" type="text" class="<%=nameClass%>">
                <label for="reg-name">Реальне ім'я</label>
                <%if(errors.containsKey("name")){%>
                <span class="helper-text" data-error="<%=errors.get("name")%>"></span>
                <%}%>
            </div>

        </div>
        <div class="row">
            <div class="input-field col s6">
                <i class="material-icons prefix">lock</i>
                <input value="123" name="reg-password" id="reg-password" type="password" class="validate">
                <label for="reg-password">Пароль</label>
            </div>
            <div class="input-field col s6">
                <i class="material-icons prefix">lock_open</i>
                <input value="123" name="reg-repeat" id="reg-repeat" type="password" class="validate">
                <label for="reg-repeat">Повторіть пароль</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s6">
                <i class="material-icons prefix">email</i>
                <input value="<%=emailValue%>" name="reg-email" id="reg-email" type="email" class="<%=emailClass%>">
                <label for="reg-email">E-mail</label>
                <%if(errors.containsKey("email")){%>
                <span class="helper-text" data-error="<%=errors.get("email")%>"></span>
                <%}%>
            </div>
            <div class="input-field col s6">
                <i class="material-icons prefix">calendar_today</i>
                <input value="<%=birthdateValue%>" name="reg-birthdate" id="reg-birthdate" type="date" class="<%=dateClass%>">
                <label for="reg-birthdate">Дата народження</label>
                <%if(errors.containsKey("birthdate")){%>
                <span class="helper-text" data-error="<%=errors.get("birthdate")%>"></span>
                <%}%>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s6">
                <i class="material-icons prefix">gavel</i>
                <label>
                    <input name="reg-rules" id="reg-rules" type="checkbox" class="filled-in validate">
                    <span>
                        Не буду нічого порушувати
                    </span>
                </label>
            </div>
            <div class="file-field input-field col s6">
                <div class="btn orange">
                    <i class="material-icons">description</i>
                    <input type="file" name="reg-avatar">
                </div>
                <div class="file-path-wrapper">
                    <input class="file-path validate" type="text" placeholder="Зображення аватарка">
                </div>
            </div>

        </div>
        <div class="input-field col s6 right-align">
            <button type="submit" class="waves-effect waves-light btn orange lighten-1"><i class="material-icons right">how_to_reg</i>Реєстрація</button>
        </div>
    </form>
</div>
<p class="gray-text">
    Передача файлів через форми.
    По-перше, передача файлів можлива лише методом POST та з кодуванням
    пакету <code>multipart/form-data</code>(за замовчанням, форма передається
    з іншим кодуванням <code>application/x-www-form-urlencoded</code>).
    Також переконуємось у наявності атрибута name у файловому інпуті.
    <br/>
    По-друге, приймання таких пакетів з боку сервера вимагає окремої обробки.
    Для цього вживаються додаткові модулі-залежності. Наприклад,
    <a href="https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload">Apache Common FileUpload</a>
    В ASP для роботи з файлами є інтерфейс IFormFile, його аналог в обраному
    пакеті - FileItem. У формі, окрім файлів, також передаються інші поля(у
    текстовому вигляді). Відповідно, результат розбору (парсингу) форми є
    дві колекції - файлів та полів. Для повернення єдиного результату (з двох
    колекцій) слід зробити спільний інтерфейс.
</p>