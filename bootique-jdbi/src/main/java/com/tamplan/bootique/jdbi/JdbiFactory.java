package com.tamplan.bootique.jdbi;

import io.bootique.jdbc.DataSourceFactory;
import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;
import java.util.Objects;

public class JdbiFactory {

    private final Map<String, JdbiProperties> configs;
    private DataSourceFactory dataSourceFactory;

    public JdbiFactory(DataSourceFactory dataSourceFactory, Map<String, JdbiProperties> configs) {
        Objects.requireNonNull(dataSourceFactory, "Jdbc data source factory is null");
        Objects.requireNonNull(configs);

        this.dataSourceFactory = dataSourceFactory;
        this.configs = configs;
    }

    public Jdbi getJdbi(final String dbName) {
        Objects.requireNonNull(dbName, "Database name is null");

        final DataSource dataSource = dataSourceFactory.forName(dbName);

        if ( dataSource == null ){
            throw new IllegalStateException("No data source found by name=" + dbName);
        }

        return Jdbi.create(() -> {
            Connection conn = dataSource.getConnection();

            if ( configs.containsKey(dbName) ) {
                JdbiProperties props = configs.get(dbName);

                conn.setAutoCommit(props.isAutoCommit());
            }
            return conn;
        });
    }
}
