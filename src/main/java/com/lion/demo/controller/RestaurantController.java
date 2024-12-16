package com.lion.demo.controller;

import com.lion.demo.entity.RestaurantDto;
import com.lion.demo.service.CsvFileReaderService;
import com.lion.demo.service.RestaurantService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private CsvFileReaderService csvFileReaderService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/list")
    public String list(@RequestParam(name = "p", defaultValue = "1") int page,
        @RequestParam(name = "f", defaultValue = "name") String field,
        @RequestParam(name = "q", defaultValue = "") String query,
        HttpSession session, Model model) {

        Page<RestaurantDto> pagedResult = restaurantService.getPagedRestaurants(page, field, query);
        int totalPages = pagedResult.getTotalPages();
        int startPage = (int) Math.ceil((page - 0.5) / RestaurantService.PAGE_SIZE - 1)
            * RestaurantService.PAGE_SIZE + 1;
        int endPage = Math.min(startPage + RestaurantService.PAGE_SIZE - 1, totalPages);
        List<Integer> pageList = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageList.add(i);
        }

        session.setAttribute("menu", "elastic");
        session.setAttribute("currentRestaurantPage", page);
        model.addAttribute("restaurantDtoList", pagedResult.getContent());
        model.addAttribute("field", field);
        model.addAttribute("query", query);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("pageList", pageList);
        return "restaurant/list";
    }

    @GetMapping("/init")
    @ResponseBody
    public String init() {
        csvFileReaderService.restaurantSeoulToElasticSearch();
        return "<h1>Done</h1>";
    }
}