package com.shopp.store.service.impl;

import com.shopp.store.JWT.JwtAuthenticationFilter;
import com.shopp.store.JWT.JwtService;
import com.shopp.store.customException.ResourceNotFoundException;
import com.shopp.store.dto.CartDTO;
import com.shopp.store.entity.Cart;
import com.shopp.store.entity.Product;
import com.shopp.store.entity.User;
import com.shopp.store.repository.CartRepository;
import com.shopp.store.repository.UserRepository;
import com.shopp.store.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartDAO;

    @Autowired
    private JwtService jwtService;

    @Autowired
    HttpServletRequest httpRequest;

    @Autowired
    UserRepository userDAO;

    @Override
    public CartDTO addToCart(CartDTO cartRequest) {

        String jwt = httpRequest.getHeader("Authorization").substring(7);
        String userName = jwtService.extractUsername(jwt);
        User customer = userDAO.findByEmail(userName)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
//        List<Cart> carts = customer.getCarts();
        // If product already available in cart update cart
        long productId = cartRequest.getProduct().getProductId();
        Cart cart = cartDAO.findCartByProductID(productId);
        if(cart!=null){
            CartDTO cartResponse = updateCartItem(cart.getItemId(),cartRequest);
            return cartResponse;
        }
        else{
            cart = Cart.builder()
                    .product(cartRequest.getProduct())
                    .quantity(cartRequest.getQuantity())
                    .itemTotal(cartRequest.getProduct().getPrice()* cartRequest.getQuantity())
                    .updateDate(LocalDateTime.now())
                    .customer(customer)
                    .build();

            cart = cartDAO.save(cart);

            CartDTO cartResponse = CartDTO.builder()
                    .product(cart.getProduct())
                    .itemId(cart.getItemId())
                    .quantity(cart.getQuantity())
                    .itemTotal(cart.getItemTotal())
                    .updateDate(cart.getUpdateDate())
                    .build();

            return cartResponse;
        }
    }

    @Override
    public List<CartDTO> getCartItems() {
        return null;
    }

    @Override
    public CartDTO updateCartItem(long cartId, CartDTO cartRequest) {
        Cart cart = cartDAO.findById(cartId)
                .orElseThrow(()-> new ResourceNotFoundException("cart item not found"));
        cart = Cart.builder()
                .itemId(cartId)
                .product(cartRequest.getProduct())
                .updateDate(LocalDateTime.now())
                .quantity(cart.getQuantity()+cartRequest.getQuantity())
                .itemTotal((cart.getQuantity()+cartRequest.getQuantity())*cartRequest.getProduct().getPrice())
                .build();
        cart = cartDAO.save(cart);
        CartDTO cartResponse = CartDTO.builder()
                .itemId(cart.getItemId())
                .product(cart.getProduct())
                .updateDate(cart.getUpdateDate())
                .quantity(cart.getQuantity())
                .itemTotal(cart.getItemTotal())
                .build();
        return cartResponse;
    }

    @Override
    public String deleteCartItem(long cartId) {
        return null;
    }
}
