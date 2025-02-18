package ru.otus.core.sessionmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.concurrent.Callable;
import javax.sql.DataSource;

public class TransactionRunnerJdbc implements TransactionRunner {
    private static final Logger log = LoggerFactory.getLogger(TransactionRunnerJdbc.class);
    private final DataSource dataSource;

    public TransactionRunnerJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public <T> T doInTransaction(TransactionAction<T> action) {
        return wrapException(() -> {
            try (var connection = dataSource.getConnection()) {
                try {
                    var result = action.apply(connection);
                    connection.commit();
                    log.debug("This is doInTransaction result: {}", result);
                    return result;
                } catch (SQLException ex) {
                    connection.rollback();
                    throw new DataBaseOperationException("doInTransaction exception", ex);
                }
            }
        });
    }

    private <T> T wrapException(Callable<T> action) {
        try {
            return action.call();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DataBaseOperationException("exception", ex);
        }
    }
}
