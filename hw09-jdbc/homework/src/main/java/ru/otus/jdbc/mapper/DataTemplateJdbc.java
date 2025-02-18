package ru.otus.jdbc.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Manager;
import ru.otus.crm.model.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
@SuppressWarnings("java:S1068")
public class DataTemplateJdbc<T> implements DataTemplate<T> {
    private static final Logger log = LoggerFactory.getLogger(DataTemplateJdbc.class);
    private final Class<T> type;
    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, Class<T> type) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.type = type;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        final Optional<T>[] result = new Optional[1];
        result[0] = Optional.empty();
       dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), rs -> {
            try {
                log.debug("TypeName: {}", type.getSimpleName());
                if (rs.next()) {
                    if (type == Client.class) {
                        log.debug("Client findById: {}, {}", rs.getLong("id"), rs.getString("name"));
                        Client client =  new Client(rs.getLong("id"), rs.getString("name"));
                        result[0] = Optional.of((T) client);
                    } else if (type == Manager.class) {
                        log.debug("Manager findById: {}, {}, {}", rs.getLong(1), rs.getString(2), rs.getString(3));
                        Manager manager = new Manager(rs.getLong(1), rs.getString(2), rs.getString(3));
                        result[0] = Optional.of((T) manager);
                    } else {
                        log.warn("There is no such model entity");
                    }
                }
                return result[0];
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            }
        });
        return result[0];
    }

    @Override
    public List<T> findAll(Connection connection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long insert(Connection connection, T client) {
        if (client instanceof Model) {
            log.debug("Parameters: {}", List.of(((Model) client).getName()));
            log.debug("GetInsertSQL is: : {}, {}", entitySQLMetaData.getInsertSql(), List.of(((Model) client).getName()));
            return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(), List.of(((Model) client).getName()));
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Connection connection, T client) {
        throw new UnsupportedOperationException();
    }
}
