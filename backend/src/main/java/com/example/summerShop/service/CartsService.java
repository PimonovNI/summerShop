package com.example.summerShop.service;

import com.example.summerShop.dto.CartGetDto;
import com.example.summerShop.dto.CartReadDto;
import com.example.summerShop.model.Cart;
import com.example.summerShop.model.Image;
import com.example.summerShop.model.Order;
import com.example.summerShop.repository.*;
import com.example.summerShop.util.UrlUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CartsService {

    @Value("${spring.site.url}")
    private String domain;

    private final CartsRepository cartsRepository;
    private final OrdersRepository ordersRepository;
    private final ProductsRepository productsRepository;
    private final UsersRepository usersRepository;
    private final BrandsRepository brandsRepository;
    private final CategoriesRepository categoriesRepository;
    private final SizesRepository sizesRepository;
    private final ImageRepository imageRepository;

    public CartsService(CartsRepository cartsRepository, OrdersRepository ordersRepository,
                        ProductsRepository productsRepository, UsersRepository usersRepository,
                        BrandsRepository brandsRepository, CategoriesRepository categoriesRepository,
                        SizesRepository sizesRepository, ImageRepository imageRepository) {
        this.cartsRepository = cartsRepository;
        this.ordersRepository = ordersRepository;
        this.productsRepository = productsRepository;
        this.usersRepository = usersRepository;
        this.brandsRepository = brandsRepository;
        this.categoriesRepository = categoriesRepository;
        this.sizesRepository = sizesRepository;
        this.imageRepository = imageRepository;
    }

    @Transactional
    public void save(CartReadDto dto, Long userId) {
        Optional<Cart> temp = cartsRepository.findCartByUserAndProductAndSize(
                usersRepository.getReferenceById(userId),
                productsRepository.getReferenceById(dto.getProduct()),
                sizesRepository.getReferenceById(dto.getSize())
        );

        if (temp.isPresent()) {
            Cart cart = temp.get();
            cart.setCount(cart.getCount() + dto.getCount());
            cartsRepository.save(cart);
            return;
        }

        Cart cart = Cart.builder()
                .user(usersRepository.getReferenceById(userId))
                .product(productsRepository.getReferenceById(dto.getProduct()))
                .size(sizesRepository.getReferenceById(dto.getSize()))
                .count(dto.getCount())
                .build();
        cartsRepository.save(cart);
    }

    @Transactional
    public void delete(Long cartId, Long userId) {
        Cart cart = cartsRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id"));
        if (!cart.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Invalid user id");
        }
        cartsRepository.delete(cart);
    }

    @Transactional
    public void buy(Long userId) {
        List<Cart> carts = cartsRepository.findAllByUserFetchProduct(usersRepository.getReferenceById(userId));
        List<Order> orders = carts.stream()
                .map(c -> Order.builder()
                        .name(c.getProduct().getName())
                        .price(c.getProduct().getPrice())
                        .gender(c.getProduct().getGender())
                        .brand(c.getProduct().getBrand())
                        .category(c.getProduct().getCategory())
                        .size(c.getSize())
                        .user(c.getUser())
                        .createdAt(Instant.now())
                        .build())
                .toList();

        ordersRepository.saveAll(orders);
        cartsRepository.deleteAll(carts);
    }

    public List<CartGetDto> findAllByUserId(Long userId) {
        return cartsRepository.findAllByUserFetchProductAndSize(usersRepository.getReferenceById(userId))
                .stream()
                .map(this::mapFrom)
                .toList();
    }

    private CartGetDto mapFrom(Cart cart) {
        return new CartGetDto(
                cart.getId(),
                cart.getProduct().getName(),
                /*UrlUtils.imgProductPath(domain, cart.getProduct().getPhoto()),*/
                UrlUtils.imgProductGooglePath(imageRepository.findById(cart.getProduct().getPhoto())
                        .orElse(new Image("", "")).getUrl()),
                cart.getProduct().getPrice(),
                cart.getProduct().getBrand().getName(),
                cart.getProduct().getCategory().getName(),
                cart.getSize().getName(),
                cart.getCount()
        );
    }
}
