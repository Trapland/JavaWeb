package step.learning.dao;

import step.learning.services.db.DbProvider;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
@Singleton
public class AuthTokenDao {
    private final DbProvider dbProvider;
    private final String dbPrefix;

    private final Logger logger;

    @Inject
    public AuthTokenDao(DbProvider dbProvider, @Named("db-prefix")String dbPrefix, Logger logger) {
        this.dbProvider = dbProvider;
        this.dbPrefix = dbPrefix;
        this.logger = logger;
    }
    public boolean install(){
        String sql = "CREATE TABLE " + dbPrefix +"auth_tokens (" +
                "jti BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID()))," +
                "sub BIGINT UNSIGNED NOT NULL COMMENT 'user-id', " +
                "exp DATETIME NOT NULL," +
                "iat DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                ") ENGINE = INNODB, DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_unicode_ci";
        try (Statement statement = dbProvider.getConnection().createStatement()){
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getMessage() + "---" + sql);
        }
        return false;
    }
}
