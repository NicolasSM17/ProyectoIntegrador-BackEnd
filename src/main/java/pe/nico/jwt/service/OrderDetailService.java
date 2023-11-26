package pe.nico.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.nico.jwt.configuration.JwtRequestFilter;
import pe.nico.jwt.dao.CartRepository;
import pe.nico.jwt.dao.OrderDetailRepository;
import pe.nico.jwt.dao.ProductRepository;
import pe.nico.jwt.dao.UserRepository;
import pe.nico.jwt.entity.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {
    private static final String ORDER_PLACED = "Ordenado";

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    public List<OrderDetail> getAllOrderDetails(String status){
        List<OrderDetail> orderDetails = new ArrayList<>();

        if(status.equals("Todo")){
            orderDetailRepository.findAll().forEach(x -> orderDetails.add(x));
        } else {
            orderDetailRepository.findByOrderStatus(status).forEach(x -> orderDetails.add(x));
        }

        return orderDetails;
    }

    public List<OrderDetail> getOrderDetails(){
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userRepository.findById(currentUser).get();

        return orderDetailRepository.findByUser(user);
    }

    public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout){
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for(OrderProductQuantity o: productQuantityList){
            Product product = productRepository.findById(o.getProductId()).get();
            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = userRepository.findById(currentUser).get();

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getProductDiscountedPrice() * o.getQuantity(),
                    product,
                    user
            );

            // empty the cart
            if(!isSingleProductCheckout){
                List<Cart> carts = cartRepository.findByUser(user);
                carts.stream().forEach(x -> cartRepository.deleteById(x.getCartId()));
            }

            orderDetailRepository.save(orderDetail);
        }
    }

    public void markOrderAsDelivered(Integer orderId){
        OrderDetail orderDetail = orderDetailRepository.findById(orderId).get();

        if(orderDetail != null){
            orderDetail.setOrderStatus("Entregado");
            orderDetailRepository.save(orderDetail);
        }
    }
}
