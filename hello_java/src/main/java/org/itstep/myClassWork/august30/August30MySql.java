package org.itstep.myClassWork.august30;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class August30MySql implements Runnable
{
    private Connection connection;

    @Override
    public void run() {
        ConnectToDbByProps();
    }


    /**
     * В этом случае мы храним настройки подключения в файле database.properties
     */
    private void ConnectToDbByProps() {
        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("database.properties"))){
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String jdbcUrl = "jdbc:mysql://localhost:30121/" + props.getProperty("MYSQL_DATABASE");
        String MYSQL_USER = props.getProperty("MYSQL_USER");
        String MYSQL_PASSWORD = props.getProperty("MYSQL_PASSWORD");

        try {
            this.connection = DriverManager.getConnection(jdbcUrl, MYSQL_USER, MYSQL_PASSWORD);
            System.out.println("Connected to the database!");
            // connection.close(); // Не забудьте закрыть соединение, когда оно больше не нужно

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * В данном случае - мы храним настройки подключения в файле .env
     */
    private void ConnectToDb() {
        // Лучше читать данные с .env
        Dotenv dotenv = Dotenv.load();
        String jdbcUrl = "jdbc:mysql://localhost:30121/" + dotenv.get("MYSQL_DATABASE");
        String MYSQL_USER = dotenv.get("MYSQL_USER");
        String MYSQL_PASSWORD = dotenv.get("MYSQL_PASSWORD");

        try {
            this.connection = DriverManager.getConnection(jdbcUrl, MYSQL_USER, MYSQL_PASSWORD);
            System.out.println("Connected to the database!");
            // connection.close(); // Не забудьте закрыть соединение, когда оно больше не нужно

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
