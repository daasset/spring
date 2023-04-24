package kz.bitlab.injection;

import org.springframework.stereotype.Component;

@Component
public class NovelBook extends Book {
    @Override
    public int calculateTotal() {
        return getPrice() * getAmount();
    }
}
