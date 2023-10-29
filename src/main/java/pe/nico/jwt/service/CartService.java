package pe.nico.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.nico.jwt.configuration.JwtRequestFilter;
import pe.nico.jwt.dao.CartRepository;
import pe.nico.jwt.dao.ProductRepository;
import pe.nico.jwt.dao.UserRepository;
import pe.nico.jwt.entity.Cart;
import pe.nico.jwt.entity.Product;
import pe.nico.jwt.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public void deleteCartItem(Integer cartId){
        cartRepository.deleteById(cartId);
    }

    public Cart addToCart(Integer productId){
        Product product = productRepository.findById(productId).get();
        String username = JwtRequestFilter.CURRENT_USER;
        User user = null;

        if(username != null){
            user = userRepository.findById(username).get();
        }

        List<Cart> cartList = cartRepository.findByUser(user);
        List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getProductId() == productId).collect(Collectors.toList());

        if(filteredList.size() > 0){
            return null;
        }

        if(product != null && user != null){
            Cart cart = new Cart(product, user);

            return cartRepository.save(cart);
        }

        return null;
    }

    public List<Cart> getCartDetails(){
        String username = JwtRequestFilter.CURRENT_USER;
        User user = userRepository.findById(username).get();

        return cartRepository.findByUser(user);
    }
}
