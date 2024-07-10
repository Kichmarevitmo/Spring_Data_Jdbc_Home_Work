package ru.easyum;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.easyum.dao.InMemoryUserDao;
import ru.easyum.dao.UserDao;
import ru.easyum.server.UsersWebServer;
import ru.easyum.server.UsersWebServerWithFilterBasedSecurity;
import ru.easyum.services.TemplateProcessor;
import ru.easyum.services.TemplateProcessorImpl;
import ru.easyum.services.UserAuthService;
import ru.easyum.services.UserAuthServiceImpl;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/users

    // REST сервис
    http://localhost:8080/api/user/3
*/
public class WebServerWithFilterBasedSecurityDemo {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        UserDao userDao = new InMemoryUserDao();
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(userDao);

        UsersWebServer usersWebServer = new UsersWebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                authService, userDao, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
