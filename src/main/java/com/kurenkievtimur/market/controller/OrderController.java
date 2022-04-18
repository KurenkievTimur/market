package com.kurenkievtimur.market.controller;

import com.kurenkievtimur.market.DTO.CreateOrderDTO;
import com.kurenkievtimur.market.model.Order;
import com.kurenkievtimur.market.security.UserPrincipal;
import com.kurenkievtimur.market.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public String getOrder(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        List<Order> orders = orderService.findAllByUser(userPrincipal.getUser());

        Double totalSum = orders.stream().map(x -> x.getProduct().getPrice() * x.getCount()).reduce(0.0, Double::sum);

        model.addAttribute("user", userPrincipal);
        model.addAttribute("orders", orders);
        model.addAttribute("totalSum", Math.round(totalSum * 1000.0) / 1000.0);

        if (userPrincipal != null) {
            model.addAttribute("order", orderService.findAllByUser(userPrincipal.getUser()).size());
        } else {
            model.addAttribute("order", null);
        }

        return "orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") int id) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            orderService.delete(order.get());
        }
        return "redirect:/orders";
    }

    @GetMapping("/{id}/{quantity}")
    public String updateOrder(@PathVariable("id") int id, @PathVariable("quantity") int quantity) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            order.get().setCount(quantity);
            orderService.save(order.get());
        }
        return "redirect:/orders";
    }

    @PostMapping("/products/page/{currentPage}/add")
    public String addOrderProducts(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("currentPage") String currentPage,
                                   @RequestParam(value = "orderBy") String orderBy,
                                   @RequestParam(value = "direction") String direction, RedirectAttributes redirectAttributes,
                                   CreateOrderDTO orderDTO) {

        orderService.saveOrUpdate(orderDTO, userPrincipal.getUser());

        redirectAttributes.addAttribute("orderBy", orderBy);
        redirectAttributes.addAttribute("direction", direction);

        return "redirect:/products/page/{currentPage}";
    }

    @PostMapping("/products/{category}/page/{currentPage}/add")
    public String addOrderProductsCategory(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("currentPage") String currentPage,
                                           @PathVariable(value = "category") String category,
                                           @RequestParam(value = "orderBy") String orderBy,
                                           @RequestParam(value = "direction") String direction, RedirectAttributes redirectAttributes,
                                           CreateOrderDTO orderDTO) {

        orderService.saveOrUpdate(orderDTO, userPrincipal.getUser());

        redirectAttributes.addAttribute("orderBy", orderBy);
        redirectAttributes.addAttribute("direction", direction);

        return "redirect:/products/{category}/page/{currentPage}";
    }

    @PostMapping("/products/{category}/{brand}/page/{currentPage}/add")
    public String addOrderProductsCategoryBrand(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("currentPage") String currentPage,
                                                @PathVariable(value = "category") String category, @PathVariable("brand") String brand,
                                                @RequestParam(value = "orderBy") String orderBy,
                                                @RequestParam(value = "direction") String direction, RedirectAttributes redirectAttributes,
                                                CreateOrderDTO orderDTO) {

        orderService.saveOrUpdate(orderDTO, userPrincipal.getUser());

        redirectAttributes.addAttribute("orderBy", orderBy);
        redirectAttributes.addAttribute("direction", direction);
        return "redirect:/products/{category}/{brand}/page/{currentPage}";
    }

    @PostMapping("/products/search/page/{currentPage}/add")
    public String addOrderProductsSearch(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("currentPage") String currentPage,
                                         @RequestParam("orderBy") String orderBy, @RequestParam("direction") String direction,
                                         @RequestParam("search") String search, RedirectAttributes redirectAttributes,
                                         CreateOrderDTO orderDTO) {

        orderService.saveOrUpdate(orderDTO, userPrincipal.getUser());

        redirectAttributes.addAttribute("search", search);
        redirectAttributes.addAttribute("orderBy", orderBy);
        redirectAttributes.addAttribute("direction", direction);

        return "redirect:/products/search/page/{currentPage}";
    }
}
