package kz.bitlab.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class BookController {
    @Qualifier("detectiveBook")
    @Autowired
    private Book book;
    @GetMapping
    public String showHome(Model model) {
        model.addAttribute("book", book);
        return "add-book";
    }

    @PostMapping
    public String showHomePost(
            @RequestParam("book-name") String name,
            @RequestParam("book-price") int price,
            @RequestParam("book-amount") int amount) {
        book.setName(name);
        book.setPrice(price);
        book.setAmount(amount);
        return "redirect:/";
    }
}
