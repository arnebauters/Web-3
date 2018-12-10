package domain.db;

import domain.model.Fiets;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class databaseFiets implements FietsDb {
    private Properties properties;
    private String url;

    public databaseFiets(Properties properties) {
        this.properties = properties;
        try{
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        }catch (ClassNotFoundException e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void voegFietsToe(Fiets fiets) {
        if (fiets == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = "INSERT INTO product (name, brand, price) VALUES (?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, fiets.getNaam());
            statement.setString(2, fiets.getMerk());
            statement.setDouble(3, fiets.getPrijs());
            statement.execute();
        } catch (Exception e) {
            throw new DbException(e);
        }
    }

    @Override
    public Fiets getFiets(int productId) {
        Fiets fiets = new Fiets();
        String sql = "Select * FROM product WHERE product_id = " + productId;
        fiets = setupFiets(fiets, sql);
        return fiets;
            }

    private Fiets setupFiets(Fiets fiets, String sql) {
        try(Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()){
                int Id = Integer.parseInt(result.getString("product_id"));
                String name = result.getString("name");
                String brand = result.getString("brand");
                double price = Double.parseDouble(result.getString("price"));
                fiets = new Fiets(Id,name, brand,price);

            }
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
        return fiets;
    }

    @Override
    public Fiets duurste() {
        Fiets fiets = new Fiets();
        String sql = "Select * FROM product ORDER BY price DESC LIMIT 1";
        fiets = setupFiets(fiets, sql);
        return fiets;
    }


    @Override
    public List<Fiets> fietsen() {
        List<Fiets> products = new ArrayList<>();
        String sql = "SELECT *FROM product ORDER BY product_id";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int Id = Integer.parseInt(result.getString("product_id"));
                String name = result.getString("name");
                String brand = result.getString("brand");
                double price = Double.parseDouble(result.getString("price"));
                Fiets fiets = new Fiets(Id,name, brand,price);
                products.add(fiets);
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
        return products;
    }

    @Override
    public void verwijder(int productId) {
        if (productId <=0) {
            throw new DbException("Nothing to delete.");
        }
        String sql = "DELETE FROM product WHERE product_id = ?";

        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,productId);
            statement.execute();
        } catch (Exception e) {
            throw new DbException(e);
        }
    }

    @Override
    public void update(Fiets fiets) {
        if (fiets == null) {
            throw new DbException("Nothing to update.");
        }
        String sql = "UPDATE product SET name = ?, brand = ?, price = ? WHERE product_id = ?";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, fiets.getNaam());
            statement.setString(2, fiets.getMerk());
            statement.setDouble(3, fiets.getPrijs());
            statement.setInt(4, fiets.getProductId());
            statement.execute();
        } catch (Exception e) {
            throw new DbException(e);
        }

    }
}
