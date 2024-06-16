package com.ra.projectspringboot.service.impl;

import com.ra.projectspringboot.dto.request.CartItemRequest;
import com.ra.projectspringboot.dto.request.CheckoutRequest;
import com.ra.projectspringboot.dto.response.CartItemResponse;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.*;
import com.ra.projectspringboot.repository.*;
import com.ra.projectspringboot.service.ICartService;
import com.ra.projectspringboot.service.IProductService;
import com.ra.projectspringboot.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
    private final IShoppingCartRepository shoppingCartRepository;
    private final IUserRepository userRepository;
    private final IOrderRepository orderRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IProductRepository productRepository;
    private final IProductService productService;
    private final IUserService userService;

    @Override
    public List<CartItemResponse> findAllCartItem() {
        return shoppingCartRepository.findAllByUserId(userService.getCurrentUser().getId()).stream().map(this::toCartItemResponse).toList();
    }

    @Override
    public CartItemResponse addNewCartItem(CartItemRequest cartItemRequest) throws CustomException {
        Optional<ShopingCart> optionalShopingCart = shoppingCartRepository.findByUserIdAndProductId(userService.getCurrentUser().getId(), cartItemRequest.getProductId());
        if (optionalShopingCart.isPresent()) {
            ShopingCart shopingCart = optionalShopingCart.get();
            if ((shopingCart.getOrderQuantity() + cartItemRequest.getQuantity()) <= shopingCart.getProduct().getStockQuantity()) {
                shopingCart.setOrderQuantity(shopingCart.getOrderQuantity() + cartItemRequest.getQuantity());
                return toCartItemResponse(shoppingCartRepository.save(shopingCart));
            }
            throw new CustomException("not enough stock quantity", HttpStatus.BAD_REQUEST);
        } else {
            Product product = productService.findProductById(cartItemRequest.getProductId());
            if (cartItemRequest.getQuantity() <= product.getStockQuantity()) {
                ShopingCart shopingCart = ShopingCart.builder()
                        .user(userService.findUserById(userService.getCurrentUser().getId()))
                        .product(product)
                        .orderQuantity(cartItemRequest.getQuantity())
                        .build();
                return toCartItemResponse(shoppingCartRepository.save(shopingCart));
            }
            throw new CustomException("not enough stock quantity", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public CartItemResponse changeQuantity(Long cartItemId, Integer quantity) throws CustomException {
        ShopingCart shopingCart = shoppingCartRepository.findById(cartItemId).orElseThrow(() -> new CustomException("cartItem not found", HttpStatus.NOT_FOUND));
        if (shopingCart.getProduct().getStockQuantity() >= quantity) {
            shopingCart.setOrderQuantity(quantity);
            return toCartItemResponse(shoppingCartRepository.save(shopingCart));
        }
        throw new CustomException("not enough stock quantity", HttpStatus.BAD_REQUEST);
    }

    @Override
    public void deleteCartItemById(Long cartItemId) throws CustomException {
        if (shoppingCartRepository.existsById(cartItemId)) {
            shoppingCartRepository.deleteById(cartItemId);
        }
        throw new CustomException("cartItem not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteAllCartItem() {
        shoppingCartRepository.deleteAllByUserId(userService.getCurrentUser().getId());
    }

    @Override
    public void checkout(CheckoutRequest checkoutRequest) throws CustomException {
        // 1. thực hiện lấy tất cả CartItem trong giỏ hàng
        List<ShopingCart> shopingCarts = shoppingCartRepository.findAllByUserId(userService.getCurrentUser().getId());

        Double totalPrice = shopingCarts.stream().map(cartItem -> cartItem.getProduct().getUnitPrice() * cartItem.getOrderQuantity()).reduce(0.0, Double::sum);

        User user = userRepository.findById(userService.getCurrentUser().getId()).orElseThrow(() -> new CustomException("user not found", HttpStatus.NOT_FOUND));

        // 2. Tạo đối tượng order và lưu vào database
        // receiveAt là 4 ngày giao hàng so với ngày đặt createAt
        Order order = Order.builder()
                .serialNumber(UUID.randomUUID().toString())
                .user(user)
                .totalPrice(totalPrice)
                .status(OrderStatus.WAITING)
                .note(checkoutRequest.getNote())
                .receiveName(checkoutRequest.getReceiveName())
                .receiveAddress(checkoutRequest.getReceiveAddress())
                .receivePhone(checkoutRequest.getReceivePhone())
                .createdAt(new Date())
                .receivedAt(new Date(new Date().getTime() + 345600000))
                .build();
        Order newOrder = orderRepository.save(order);
        // 3. thực hiện tạo orderDetail và lưu vào database
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (ShopingCart cart : shopingCarts) {
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(newOrder)
                    .product(cart.getProduct())
                    .name(cart.getProduct().getProductName())
                    .unitPrice(cart.getProduct().getUnitPrice())
                    .orderQuantity(cart.getOrderQuantity())
                    .build();
            orderDetails.add(orderDetail);
        }
        orderDetailRepository.saveAll(orderDetails);
        // 4. thực hiện trừ số lượng sản phẩm trong database
        for (ShopingCart shopingCart : shopingCarts) {
            Product product = shopingCart.getProduct();
            product.setStockQuantity(product.getStockQuantity() - shopingCart.getOrderQuantity());
            productRepository.save(product);
        }
        // 5. thực hiện xóa sản phần trong giỏ hàng
        deleteAllCartItem();
    }

    public CartItemResponse toCartItemResponse(ShopingCart shopingCart) {
        return CartItemResponse.builder()
                .id(shopingCart.getId())
                .productName(shopingCart.getProduct().getProductName())
                .unitPrice(shopingCart.getProduct().getUnitPrice())
                .quantity(shopingCart.getOrderQuantity())
                .build();
    }
}
