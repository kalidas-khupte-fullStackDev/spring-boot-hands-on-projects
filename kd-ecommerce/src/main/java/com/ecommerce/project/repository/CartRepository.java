package com.ecommerce.project.repository;

import com.ecommerce.project.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("SELECT c from Cart c WHERE c.user.email = ?1")
    Optional<Cart> findCartByEmailId(String email);

    @Query("SELECT c from Cart c WHERE c.id=?1 AND c.user.email = ?2")
    Optional<Cart> findCartByEmailIdAndCartId(Long cartId, String email);
}
