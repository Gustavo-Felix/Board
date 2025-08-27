package com.dio.gustavo;

import com.dio.gustavo.persistence.migration.MigrationStrategy;
import com.dio.gustavo.ui.MainMenu;

import java.sql.SQLException;

import static com.dio.gustavo.persistence.config.ConnectionConfig.getConnection;

public class Main {

    public static void main(String[] args) throws SQLException {
        try(var connection = getConnection()){
            new MigrationStrategy(connection).executeMigration();
        }
        new MainMenu().execute();
    }

}