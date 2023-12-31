package pe.nico.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.nico.jwt.dto.ProductDto;
import pe.nico.jwt.entity.ImageModel;
import pe.nico.jwt.entity.Product;
import pe.nico.jwt.service.ProductService;

import java.io.IOException;
import java.util.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = {"/addNewProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart("product") Product product,
                                 @RequestPart("imageFile")MultipartFile[] file){
        try {
            Set<ImageModel> images = uploadImage(file);
            product.setProductImages(images);

            return productService.addNewProduct(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException{
        Set<ImageModel> imageModels = new HashSet<>();

        for(MultipartFile file : multipartFiles){
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }

        return imageModels;
    }

    @GetMapping({"/getAllProducts"})
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber,
                                        @RequestParam(defaultValue =  "") String searchKey){
        List<Product> result = productService.getAllProducts(pageNumber, searchKey);
        System.out.println("Result size is " + result.size());

        return result;
    }

    @GetMapping({"/getProductDetailsById/{productId}"})
    public Product getProductDetailsById(@PathVariable("productId") Integer productId){
        return productService.getProductDetailsById(productId);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping({"/deleteProductDetails/{productId}"})
    public void deleteProductDetails(@PathVariable("productId") Integer productId){
        productService.deleteProductDetails(productId);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getProductDetails/{isSingleProductCheckout}/{productId}"})
    public List<Product> getProductDetails(@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
                                  @PathVariable(name = "productId") Integer productId){

        return productService.getProductDetails(isSingleProductCheckout, productId);
    }

    //Endpoint del bot
    @GetMapping({"/getAllNameProducts"})
    public List<ProductDto> getAllNameProducts(){

        return productService.getAllNameProducts();
    }

    //private final List<ProductDto> productos;
    /*public ProductController(List<ProductDto> productos) {
        this.productos = productos;
    }*/

    @GetMapping("/formato-nuevo")
    public Map<String, String> obtenerFormatoNuevo() {
        List<ProductDto> productos = productService.getAllNameProducts();
        Map<String, String> nuevoFormato = new HashMap<>();

        for (int i = 0; i < productos.size(); i++) {
            String nombre = "nombre" + (i + 1);
            String productName = productos.get(i).getProductName();
            nuevoFormato.put(nombre, productName);
        }

        return nuevoFormato;
    }
}
