package step.learning.dao;

import step.learning.dto.entities.CallMe;
import step.learning.services.db.DbProvider;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class CallMeDao {
    private final DbProvider dbProvider;
    private final String dbPrefix;

    private final Logger logger;

    @Inject
    public CallMeDao(DbProvider dbProvider, @Named("db-prefix")String dbPrefix, Logger logger) {
        this.dbProvider = dbProvider;
        this.dbPrefix = dbPrefix;
        this.logger = logger;
    }


    public List<CallMe> getAll() {
        List<CallMe> ret = new ArrayList<>();
        String sql = "SELECT C.* FROM "
                + dbPrefix + "call_me C";
        try(Statement statement = dbProvider.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()){
                ret.add(new CallMe(resultSet));
            }
        }
        catch (SQLException ex){
            logger.log(Level.WARNING,ex.getMessage() + "----" + sql);
        }
        return ret;
    }
}
/*
DAO - Data Access Object - елементи DAL (Layer) - об'єкти призначені
для роботи із сутностями, переведення роботи з БД до об'єктів Java та
їх колекцій.
 */