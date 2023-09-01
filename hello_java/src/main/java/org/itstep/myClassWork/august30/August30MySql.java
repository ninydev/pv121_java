package org.itstep.myClassWork.august30;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;


public class August30MySql implements Runnable
{
    private Connection connection;

    @Override
    public void run() {
        ConnectToDbByProps();
        migrate(); // Создаем необходимые для работы таблицы
        seed();
    }

    private void seed(){
        String sqlCreateAdmin = "INSERT INTO `users` " +
                "(`id`, `email`, `password`, `created_at`) " +
                "VALUES " +
                "(NULL, 'admin@admin.com', MD5('QweAsdZxc!23'), CURRENT_TIMESTAMP)";

        // Пример запроса, построенного ORM системой, который приводит к краху прилложения.
//        sqlCreateAdmin = "" +
//                "SELECT count(DISTINCT(`Nft`.`id`)) AS `count` FROM `nfts` AS `Nft` " +
//                "INNER JOIN `nft_collections` AS `NftCollection` ON `Nft`.`collectionId` = `NftCollection`.`id` AND `NftCollection`.`isPublic` = true " +
//                "INNER JOIN `nft_owners` AS `NftCollection->NftOwner` ON `NftCollection`.`ownerId` = `NftCollection->NftOwner`.`id` " +
//                "LEFT OUTER JOIN ( `favorite_points` AS `Favorites->FavoritePoint` " +
//                "INNER JOIN `favorites` AS `Favorites` ON `Favorites`.`id` = `Favorites->FavoritePoint`.`favoriteId`) ON `Nft`.`id` = `Favorites->FavoritePoint`.`nftId` AND `Favorites`.`userId` = '3cb67218-2bf7-4cf6-8810-6907952c86d2' " +
//                "INNER JOIN `nfts_nft_custom_collections` AS `NftNftCustomCollections` ON `Nft`.`id` = `NftNftCustomCollections`.`nftId` AND `NftNftCustomCollections`.`collectionId` = '52b1e929-28c0-47e1-adf6-a0247abf44dd' " +
//                "INNER JOIN ( `nfts_nft_custom_collections` AS `NftCustomCollections->NftNftCustomCollection` " +
//                "INNER JOIN `nft_custom_collections` AS `NftCustomCollections` ON `NftCustomCollections`.`id` = `NftCustomCollections->NftNftCustomCollection`.`collectionId`) ON `Nft`.`id` = `NftCustomCollections->NftNftCustomCollection`.`nftId` AND `NftCustomCollections`.`id` = '52b1e929-28c0-47e1-adf6-a0247abf44dd' " +
//                "LEFT OUTER JOIN `nft_selects` AS `NftSelects` ON `Nft`.`id` = `NftSelects`.`nftId` AND `NftSelects`.`userId` = '3cb67218-2bf7-4cf6-8810-6907952c86d2' AND `NftSelects`.`customCollectionId` = '52b1e929-28c0-47e1-adf6-a0247abf44dd' " +
//                "WHERE `Nft`.`isPublic` = true;";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCreateAdmin);
        }
        // Анализируем повторение сущности ( за счет уникальности поля email)
        catch (SQLIntegrityConstraintViolationException e) {
            // Если такая сущность уже в базе есть - значит просто продолжаем работу
            System.out.println(" Admin уже создан в базе");
        }
        catch (SQLException e) {
            // Если что то другое пошло не так - то выходим из приложения по RuntimeException
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void migrate() {
        String sqlCrateTable = "" +
                "CREATE TABLE IF NOT EXISTS `pv121`.`users` " +
                "(" +
                "`id` INT UNSIGNED NOT NULL AUTO_INCREMENT , " +
                "`email` VARCHAR(64) NOT NULL , " +
                "`password` VARCHAR(255) NOT NULL , " +
                "`created_at` DATETIME on update CURRENT_TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP , " +
                "PRIMARY KEY (`id`), UNIQUE (`email`)) ENGINE = InnoDB;";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCrateTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

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
