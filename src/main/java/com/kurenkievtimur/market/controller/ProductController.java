package com.kurenkievtimur.market.controller;

import com.kurenkievtimur.market.DTO.CreateProductDTO;
import com.kurenkievtimur.market.model.Product;
import com.kurenkievtimur.market.security.UserPrincipal;
import com.kurenkievtimur.market.service.BrandService;
import com.kurenkievtimur.market.service.CategoryService;
import com.kurenkievtimur.market.service.OrderService;
import com.kurenkievtimur.market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final OrderService orderService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/page/{currentPage}")
    public String getProducts(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model, @PathVariable("currentPage")
            int currentPage, @RequestParam(value = "orderBy", defaultValue = "price") String orderBy,
                              @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Product> products = productService.findAll(PageRequest.of(currentPage - 1, 6,
                Sort.Direction.fromString(direction), orderBy));

        model.addAttribute("user", userPrincipal);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("direction", direction);

        if (userPrincipal != null) {
            model.addAttribute("order", orderService.findAllByUser(userPrincipal.getUser()).size());
        } else {
            model.addAttribute("order", null);
        }

        return "products";
    }

    @GetMapping("/{category}/page/{currentPage}")
    public String getProductsCategory(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("currentPage") int currentPage,
                                      @PathVariable("category") String name, @RequestParam(value = "orderBy", defaultValue = "price") String orderBy,
                                      @RequestParam(value = "direction", defaultValue = "ASC") String direction, Model model) {

        Page<Product> products = productService.findAllByCategoryName(name, PageRequest.of(currentPage - 1, 6,
                Sort.Direction.fromString(direction), orderBy));

        model.addAttribute("user", userPrincipal);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("direction", direction);
        model.addAttribute("category", name);

        if (userPrincipal != null) {
            model.addAttribute("order", orderService.findAllByUser(userPrincipal.getUser()).size());
        } else {
            model.addAttribute("order", null);
        }

        return "productsCategory";
    }

    @GetMapping("/{category}/{brand}/page/{currentPage}")
    public String getProductsCategoryBrand(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("currentPage") int currentPage,
                                           @PathVariable("category") String category, @PathVariable("brand") String brand,
                                           @RequestParam(value = "orderBy", defaultValue = "price") String orderBy,
                                           @RequestParam(value = "direction", defaultValue = "ASC") String direction, Model model) {

        Page<Product> products = productService.findAllByCategoryNameAndBrandName(category, brand, PageRequest.of(currentPage - 1, 6,
                Sort.Direction.fromString(direction), orderBy));

        model.addAttribute("user", userPrincipal);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("direction", direction);
        model.addAttribute("category", category);
        model.addAttribute("brand", brand);

        if (userPrincipal != null) {
            model.addAttribute("order", orderService.findAllByUser(userPrincipal.getUser()).size());
        } else {
            model.addAttribute("order", null);
        }

        return "productsCategoryBrand";
    }

    @GetMapping("/search/page/{currentPage}")
    public String searchProducts(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("currentPage") int currentPage,
                                 @RequestParam("search") String search,
                                 @RequestParam(value = "orderBy", defaultValue = "price") String orderBy,
                                 @RequestParam(value = "direction", defaultValue = "ASC") String direction, Model model) {

        Page<Product> products = productService.findAllByNameContaining(search, PageRequest.of(currentPage - 1, 6,
                Sort.Direction.fromString(direction), orderBy));

        model.addAttribute("user", userPrincipal);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("search", search);
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("direction", direction);

        if (userPrincipal != null) {
            model.addAttribute("order", orderService.findAllByUser(userPrincipal.getUser()).size());
        } else {
            model.addAttribute("order", null);
        }

        return "searchProducts";
    }

    @GetMapping("/add")
    public String getProduct(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("user", userPrincipal);
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("categories", categoryService.findAll());

        if (userPrincipal != null) {
            model.addAttribute("order", orderService.findAllByUser(userPrincipal.getUser()).size());
        } else {
            model.addAttribute("order", null);
        }

        return "addProduct";
    }

    @PostMapping("/add")
    public String addProduct(CreateProductDTO productDTO) throws IOException {
        if (productDTO.getImage() != null && !productDTO.getImage().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            productDTO.getImage().transferTo(new File(uploadPath + "/" + productDTO.getImage().getOriginalFilename()));
        }
        productService.save(productDTO);
        return "redirect:/products/page/{1}";
    }
}
