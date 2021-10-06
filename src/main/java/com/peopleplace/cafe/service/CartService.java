package com.peopleplace.cafe.service;

import com.peopleplace.cafe.dto.cart.AddToCartDto;
import com.peopleplace.cafe.dto.cart.CartDto;
import com.peopleplace.cafe.dto.cart.CartItemDto;
import com.peopleplace.cafe.exceptions.CartItemNotExistException;
import com.peopleplace.cafe.exceptions.ProductNotExistException;
import com.peopleplace.cafe.model.Cart;
import com.peopleplace.cafe.model.Product;
import com.peopleplace.cafe.model.User;
import com.peopleplace.cafe.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private CartRepository cartRepository;

    public CartService() {
    }

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public static CartItemDto getDtoFromCart(Cart cart) {
        CartItemDto cartItemDto = new CartItemDto(cart);
        return cartItemDto;
    }

    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cart.setId(sequenceGeneratorService.generateSequence(Cart.SEQUENCE_NAME));
        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0;
        double totalCostAllCarts = 0;
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);

            totalCost += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
            logger.info("totalCost for product: " + totalCost);
            if (null != cartItemDto.getProduct().getSubProduct()) {
                Product subProduct = cartItemDto.getProduct().getSubProduct();
                totalCost += (subProduct.getPrice());
                logger.info("totalCost after subProduct: " + totalCost);
            }
            totalCostAllCarts += totalCost;
            cart.setTotalCost(totalCost);
            cartRepository.save(cart);
        }

        CartDto cartDto = new CartDto(cartItems, totalCostAllCarts);
        return cartDto;
    }

    public Cart getCartById(Long cartId) throws ProductNotExistException {
        Optional<Cart> optionalProduct = cartRepository.findById(cartId);
        if (!optionalProduct.isPresent())
            throw new CartItemNotExistException("Cart id is invalid " + cartId);
        return optionalProduct.get();
    }

    public void updateCartItem(AddToCartDto cartDto, User user, Product product) {
        Cart cart = this.getCartById(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    public void deleteCartItem(Long id, Long userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);
    }

    public void deleteCartItems(Long userId) {
        cartRepository.deleteAll();
    }

    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }
}