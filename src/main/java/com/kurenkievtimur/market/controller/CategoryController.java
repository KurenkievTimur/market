package com.kurenkievtimur.market.controller;

import com.kurenkievtimur.market.DTO.CreateCategoryDTO;
import com.kurenkievtimur.market.model.Category;
import com.kurenkievtimur.market.security.UserPrincipal;
import com.kurenkievtimur.market.service.CategoryService;
import com.kurenkievtimur.market.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final OrderService orderService;

    @GetMapping()
    public String getCategory(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("user", userPrincipal);

        if (userPrincipal != null) {
            model.addAttribute("order", orderService.findAllByUser(userPrincipal.getUser()).size());
        } else {
            model.addAttribute("order", null);
        }

        return "addCategory";
    }

    @PostMapping("/add")
    public String addCategory(CreateCategoryDTO categoryDTO) {
        Optional<Category> category = categoryService.findFirstByNameIgnoreCase(categoryDTO.getCategory());
        if (!category.isPresent()) {
            categoryService.save(categoryDTO);
        }
        return "redirect:/categories";
    }
}
