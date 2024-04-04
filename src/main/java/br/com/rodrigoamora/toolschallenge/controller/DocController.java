package br.com.rodrigoamora.toolschallenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocController {
	@GetMapping("/redoc")
	public String redoc() {
		return "static/redoc.html";
	}
}
