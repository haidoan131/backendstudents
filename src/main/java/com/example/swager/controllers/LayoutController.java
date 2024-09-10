package com.example.swager.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/layout")
public class LayoutController {
    @Operation(summary = "get layout1",description = "list product of layout1")
    @GetMapping("/layout1")
    public String index1(){
        return "layout page 1";
    }
    @GetMapping("/layout2")
    public String index2(){
        return "layout2 page 2";
    }
}
