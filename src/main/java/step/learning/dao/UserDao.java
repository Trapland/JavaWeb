package step.learning.dao;

import step.learning.dto.models.RegFormModel;
import step.learning.services.db.DbProvider;
import step.learning.services.hash.HashService;
import step.learning.services.random.RandomService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class UserDao {
    private final DbProvider dbProvider;
    private final String dbPrefix;

    private final Logger logger;

    private final RandomService randomService;
    private final HashService hashService;

    @Inject
    public UserDao(DbProvider dbProvider, @Named("db-prefix")String dbPrefix, Logger logger, RandomService randomService,@Named("Digest-Hash") HashService hashService) {
        this.dbProvider = dbProvider;
        this.dbPrefix = dbPrefix;
        this.logger = logger;
        this.randomService = randomService;
        this.hashService = hashService;
    }
    public boolean install(){
        String sql = "CREATE TABLE " + dbPrefix +"users (" +
                "`id`           BIGINT UNSIGNED PRIMARY KEY DEFAULT (UUID_SHORT())," +
                "`name`         VARCHAR(64)         NOT NULL, " +
                "`salt`         CHAR(16)            NOT NULL COMMENT 'RFC 2898 -- Salt', " +
                "`pass_dk`      CHAR(32)            NOT NULL COMMENT 'RFC 2898 -- DK: Derived key', " +
                "`email`        VARCHAR(96)         NOT NULL, " +
                "`email_code`   CHAR(6)             NOT NULL, " +
                "`avatar_url`   VARCHAR(64)         NULL, " +
                "`birthdate`    DATE                NULL," +
                "`reg_at`       DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "`del_at`       DATETIME            NULL" +
                ") ENGINE = INNODB, DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_unicode_ci";
        try (Statement statement = dbProvider.getConnection().createStatement()){
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getMessage() + "---" + sql);
        }
        return false;
    }
    public boolean addFromForm(RegFormModel model){
        String salt = randomService.randomHex(16);
        String passDk = hashService.hash(salt + model.getPassword() + salt);
        String emailCode = randomService.randomHex(6);
        String sql = "INSERT INTO " + dbPrefix + "users(" +
                "`name`,`salt`,`pass_dk`,`email`,`email_code`,`avatar_url`,`birthdate`) " +
                "VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement prep = dbProvider.getConnection().prepareStatement(sql)){
            prep.setString(1,model.getName());
            prep.setString(2,salt);
            prep.setString(3,passDk);
            prep.setString(4,model.getEmail());
            prep.setString(5,emailCode);
            prep.setString(6,model.getAvatar());
            prep.setString(7,model.getBirthdateAsString());
            prep.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getMessage() + "---" + sql);
        }
        return false;
    }
}
