package kz.bitlab.injection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public abstract class Book {
    private Local id;
    private String name;
    private int price;
    private int amount;
    public abstract int calculateTotal();
}
