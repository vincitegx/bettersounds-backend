package com.bettersounds.repository;

import com.bettersounds.domain.CartItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author TEGA
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    
}
