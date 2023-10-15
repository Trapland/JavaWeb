package step.learning.services.db;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Objects;

public class PlanetDbProvider implements DbProvider{

    @Override
    public Connection getConnection() {
        String server = "";
        String login = "";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException( e);
        }

        try (InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("db_config.json")) {
            JsonArray jsonArray = JsonParser.parseReader(new InputStreamReader(Objects.requireNonNull(resourceStream))).getAsJsonArray();
            server = jsonArray.get(0).getAsString();
            login = jsonArray.get(1).getAsString();
            password = jsonArray.get(2).getAsString();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(server, login, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
