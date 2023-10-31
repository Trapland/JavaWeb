package step.learning.dao;

import step.learning.dto.entities.ChatMessage;
import step.learning.services.db.DbProvider;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class ChatDao {
    private final DbProvider dbProvider;
    private final String dbPrefix;
    private final Logger logger;

    @Inject
    public ChatDao(DbProvider dbProvider, @Named("db-prefix") String dbPrefix, Logger logger) {
        this.dbProvider = dbProvider;
        this.dbPrefix = dbPrefix;
        this.logger = logger;
    }

    public boolean install(){
        String sql = "CREATE TABLE " + dbPrefix + "chat (" +
                "user BIGINT UNSIGNED PRIMARY KEY," +
                "message TEXT," +
                "moment DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ") ENGINE = INNODB, DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_unicode_ci";
        try (Statement statement = dbProvider.getConnection().createStatement()){
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getMessage() + "---" + sql);
        }
        return false;
    }
    public void add(ChatMessage chatMessage)
    {
        if(chatMessage == null)
        {
            return;
        }

        String sql = "INSERT INTO " + dbPrefix + "chat (user, message) " +
                     "VALUES (?,?)";
        try(PreparedStatement prep= dbProvider.getConnection().prepareStatement(sql)) {
            prep.setString(1, chatMessage.getUser());
            prep.setString(2, chatMessage.getMessage());
            prep.executeUpdate();
        }
        catch (SQLException ex)
        {
            logger.log(Level.WARNING, ex.getMessage() + "---" + sql);
        }
    }
}
