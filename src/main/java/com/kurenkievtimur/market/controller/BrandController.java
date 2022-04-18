package com.kurenkievtimur.market.controller;

import com.kurenkievtimur.market.DTO.CreateBrandDTO;
import com.kurenkievtimur.market.model.Brand;
import com.kurenkievtimur.market.security.UserPrincipal;
import com.kurenkievtimur.market.service.BrandService;
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
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;
    private final OrderService orderService;

    @GetMapping()
    public String getBrand(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("user", userPrincipal);

        if (userPrincipal != null) {
            model.addAttribute("order", orderService.findAllByUser(userPrincipal.getUser()).size());
        } else {
            model.addAttribute("order", null);
        }

        return "addBrand";
    }

    @PostMapping("/add")
    public String addBrand(CreateBrandDTO brandDTO) {
        Optional<Brand> brand = brandService.findFirstByNameIgnoreCase(brandDTO.getBrand());
        if (!brand.isPresent()) {
            brandService.save(brandDTO);
        }
        return "redirect:/brands";
    }
}
