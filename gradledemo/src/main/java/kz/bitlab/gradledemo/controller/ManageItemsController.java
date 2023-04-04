package kz.bitlab.gradledemo.controller;

import kz.bitlab.gradledemo.dao.ItemDAO;
import kz.bitlab.gradledemo.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/manage")
public class ManageItemsController {
    @GetMapping("/add-item")
    public String addItem() {
        return "add-item";
    }

    @PostMapping("/add-item")
    public String addItemPost(
            @RequestParam(name = "item-name") String name,
            @RequestParam(name = "item-description") String description,
            @RequestParam(name = "item-price") double price) {
        String redirectStr = "/manage/add-item?error";

        Item item = new Item(null, name, description, price);
        if (ItemDAO.create(item)) {
            redirectStr = "/";
        }

        return "redirect:" + redirectStr;
    }
}
