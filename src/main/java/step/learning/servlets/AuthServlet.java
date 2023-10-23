package step.learning.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import step.learning.dao.AuthTokenDao;
import step.learning.dao.UserDao;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class AuthServlet extends HttpServlet {
    private final Gson gson = new GsonBuilder().serializeNulls().create();

    private final AuthTokenDao authTokenDao;

    private final UserDao userDao;
    @Inject
    public AuthServlet(AuthTokenDao authTokenDao, UserDao userDao) {
        this.authTokenDao = authTokenDao;
        this.userDao = userDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(login == null || login.isEmpty() || password == null){
            sendResponse (resp,400,"Missing required parametres: login and/or password");

        }
    }
    private void sendResponse( HttpServletResponse resp, int status,Object body) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(status);
        resp.getWriter().print(gson.toJson(body));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(userDao.install()){
            sendResponse (resp,201,"Created");
        }
        else{
            sendResponse (resp,500,"Error, see server logs");
        }
    }
}
/*
Авторизація та автентифікація
Автентифікація - перевірка логіну/паролю
Авторизація - перевірка прав доступу

Схеми:
За сесіями - сервер зберігає ознаку авторизації механізмом сесій,
    перевіряє її вилученням даного параметру
За токенами - без сесій, клієнт з кожним запитом передає токен,
    який він отримує при авторизації.

Робота з токенами.
Токен (дослівно - жетон, посвідчення) - певні дані, що ідентифікують
користувача. Існують пропозиції стандартизації токенів, зокрема, JWT.
Для формування та перевірки токенів у ЬД як правило створюєтьтся окрема таблиця.
 */
