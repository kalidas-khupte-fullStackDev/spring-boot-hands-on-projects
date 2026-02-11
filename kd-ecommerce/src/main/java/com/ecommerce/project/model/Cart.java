package com.ecommerce.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    private Double totalPrice = 0.0;

    public void addProduct(CartItem newItem) {
        if (this.cartItems == null) {
            this.cartItems = new ArrayList<>();
        }
        this.cartItems.add(newItem);
        newItem.setCart(this); // Sync the reverse relationship

        // You can even handle price calculation here to keep business logic in the domain!
        this.totalPrice += (newItem.getProductPrice() * newItem.getQuantity());
    }
}
