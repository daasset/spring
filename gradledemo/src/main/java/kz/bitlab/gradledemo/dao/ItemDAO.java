package kz.bitlab.gradledemo.dao;

import kz.bitlab.gradledemo.model.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public static boolean create(Item item) {
        int row = 0;

        try {
            PreparedStatement stmt = Application.INSTANCE.connection().prepareStatement(
                    "insert into item (name, description, price) values (?, ?, ?)");
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setDouble(3, item.getPrice());

            row = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row > 0;
    }

    public static List<Item> findAll() {
        List<Item> items = new ArrayList<>();

        try {
            PreparedStatement stmt = Application.INSTANCE.connection().prepareStatement(
                    "select * from item order by id desc");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Item item = new Item(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"));
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }
}
