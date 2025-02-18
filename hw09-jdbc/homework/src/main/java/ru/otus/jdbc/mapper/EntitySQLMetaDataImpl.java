package ru.otus.jdbc.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {
    private static final Logger log = LoggerFactory.getLogger(EntitySQLMetaDataImpl.class);
    private final EntityClassMetaData<T> entityClassMetaDataClient;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaDataClient) {
        this.entityClassMetaDataClient = entityClassMetaDataClient;
    }

    @Override
    public String getSelectAllSql() {
        String query = "select * from " + entityClassMetaDataClient.getName();
        log.debug("getSelectAllSql query is: {}" + query);
        return query;
    }

    @Override
    public String getSelectByIdSql() {
        String query = "select * from " + entityClassMetaDataClient.getName() + " where id = ?";
        log.debug("getSelectByIdSql query is: {}", query);
        return query;
    }

    @Override
    public String getInsertSql() {
        log.debug("Get Name is: {}", entityClassMetaDataClient.getName());
        String query = "insert into " + entityClassMetaDataClient.getName() + " (name) values (?)";
        log.debug("getInsertSql query is: {}", query);
        return query;
    }

    @Override
    public String getUpdateSql() {
        String query = "update  " + entityClassMetaDataClient.getName() + " set name = ?";
        log.debug("getUpdateSql query is: {}", query);
        return null;
    }
}
