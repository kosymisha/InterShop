package com.someshop.intershop.controller;

import com.someshop.intershop.model.Product;
import com.someshop.intershop.model.User;
import com.someshop.intershop.service.ProductService;
import com.someshop.intershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/products")
    public String getProducts (Model model) {
        model.addAttribute("products", productService.findAll());
        return "parts/products";
    }

    @GetMapping("/products/{product}/form")
    public String getProductForm (@PathVariable Product product, Model model,
                                  @RequestParam String userId) {
        model.addAttribute("product", product);
        model.addAttribute("shops", userService.findById(userId).getShops());
        return "parts/productForm";
    }

    @GetMapping("/products/form")
    public String getProductsForm (Model model) {
        model.addAttribute("products", productService.findAll());
        return "parts/productFormAll";
    }
}
