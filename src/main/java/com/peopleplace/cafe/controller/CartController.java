package com.peopleplace.cafe.controller;

import com.peopleplace.cafe.dto.cart.AddToCartDto;
import com.peopleplace.cafe.dto.cart.CartDto;
import com.peopleplace.cafe.exceptions.AuthenticationFailException;
import com.peopleplace.cafe.exceptions.CartItemNotExistException;
import com.peopleplace.cafe.exceptions.ProductNotExistException;
import com.peopleplace.cafe.model.Product;
import com.peopleplace.cafe.model.User;
import com.peopleplace.cafe.service.AuthenticationService;
import com.peopleplace.cafe.service.CartService;
import com.peopleplace.cafe.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * @param addToCartDto
     * @param token
     * @return
     * @throws AuthenticationFailException
     * @throws ProductNotExistException
     */
    @PostMapping("/add")
    public ResponseEntity<CartDto> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestHeader("token") String token) throws AuthenticationFailException, ProductNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productService.getProductById(addToCartDto.getProductId());
        logger.info("product to add : " + product.getName());
        if (null != addToCartDto.getSubProductId()) {
            Product subProduct = productService.getProductById(addToCartDto.getSubProductId());
            product.setSubProduct(subProduct);
            logger.info("subproduct to add : " + subProduct.getName());
        }
        cartService.addToCart(addToCartDto, product, user);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    /**
     * @param token
     * @return
     * @throws AuthenticationFailException
     */
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestHeader("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    /**
     * @param cartDto
     * @param token
     * @return
     * @throws AuthenticationFailException
     * @throws ProductNotExistException
     */
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<CartDto> updateCartItem(@RequestBody AddToCartDto cartDto,
                                                  @RequestHeader("token") String token) throws AuthenticationFailException, ProductNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productService.getProductById(cartDto.getProductId());
        cartService.updateCartItem(cartDto, user, product);
        CartDto cartLDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartLDto, HttpStatus.OK);
    }

    /**
     * @param itemID
     * @param token
     * @return
     * @throws AuthenticationFailException
     * @throws CartItemNotExistException
     */
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<CartDto> deleteCartItem(@PathVariable("cartItemId") Long itemID, @RequestHeader("token") String token) throws AuthenticationFailException, CartItemNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        cartService.deleteCartItem(itemID, user.getId());
        //return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
        CartDto cartLDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartLDto, HttpStatus.OK);
    }

}
