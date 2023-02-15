package com.bettersounds.repository;

import com.bettersounds.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author TEGA
 */
public interface CartRepository extends JpaRepository<Cart, Long>{
    
}
