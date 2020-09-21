package com.dac20201UFF.calculadora.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CookieValue;

@Controller
public class CalculadoraController {

    @RequestMapping("/")
    public String index(Model modelo, @CookieValue(value = "numeroVisitas", defaultValue = "1") String visitas,
            HttpServletResponse response) {
        System.out.println(visitas);
        Integer numeroVisitas = Integer.parseInt(visitas);
        numeroVisitas = numeroVisitas++;
        visitas = numeroVisitas.toString();
        Cookie cookie = new Cookie("numeroVisitas", visitas);
        response.addCookie(cookie);
        modelo.addAttribute("numeroVisitas", visitas);
        return "index";
    }

    @RequestMapping("/calcular")
    public String calcular(Model modelo, @RequestParam(defaultValue = "somar") String operador,
            @RequestParam(defaultValue = "0.0") Double primeiroNumero,
            @RequestParam(defaultValue = "0.0") Double segundoNumero) {
        Double resultado;
        switch (operador) {
            case "somar":
                resultado = primeiroNumero + segundoNumero;
                break;
            case "dividir":
                if (segundoNumero == 0.0) {
                    modelo.addAttribute("divisaoPorZero", true);
                    resultado = 0.0;
                } else {
                    resultado = primeiroNumero / segundoNumero;
                }
                break;
            case "subtrair":
                resultado = primeiroNumero - segundoNumero;
                break;
            case "multiplicar":
                resultado = primeiroNumero * segundoNumero;
                break;
            default:
                resultado = 0.0;
                break;
        }
        modelo.addAttribute("resultado", resultado);
        return "resultado";
    }

}
