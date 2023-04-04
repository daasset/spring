package kz.bitlab.springmvc.dao;

import kz.bitlab.springmvc.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public static boolean addItem(Item item) {
        int rows = 0;

        try {
            PreparedStatement stmt = Application.INSTANCE.connection().prepareStatement(
                "insert into items(name, price, amount, level) values(?, ?, ?, ?)"
            );
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getPrice());
            stmt.setInt(3, item.getAmount());
            stmt.setString(4, item.getLevel());

            rows = stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }

    public static List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();

        try {
            PreparedStatement stmt = Application.INSTANCE.connection().prepareStatement(
                    "select * from items order by id desc");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Item item = new Item(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("amount"),
                        rs.getString("level"));
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }
}
