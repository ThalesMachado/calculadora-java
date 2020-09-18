package com.dac20201UFF.calculadora.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalculadoraController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
}
