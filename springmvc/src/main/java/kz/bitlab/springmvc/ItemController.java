package kz.bitlab.springmvc;

import kz.bitlab.springmvc.dao.ItemDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/item")
public class ItemController {
    @GetMapping(value = "/add-item")
    public String addItem() {
        return "item/add-item";
    }

    @PostMapping(value = "/add-item")
    public String addItemPost(
            @RequestParam(name = "item-name") String name,
            @RequestParam(name = "item-price") int price,
            @RequestParam(name = "item-amount") int amount) {
        String redirect = "/item/add-item?error";

        String level = price >= 500_000? "expensive"
                : (price >= 300_000 ? "normal" : "cheap");

        Item item = new Item(null, name, price, amount, level);
        if (ItemDAO.addItem(item)) {
            redirect = "/item/add-item?success";
        }

        return "redirect:" + redirect;
    }

    @GetMapping(value = "/home")
    public String openHome(Model model) {
        List<Item> items = ItemDAO.getAllItems();
        model.addAttribute("items", items);
        return "item/home";
    }
}
