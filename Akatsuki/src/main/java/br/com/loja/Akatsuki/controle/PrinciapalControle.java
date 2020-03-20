package br.com.loja.Akatsuki.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrinciapalControle {
	
	@GetMapping("/admin")
	public String acessarPrincipal() {
		return "admin/home";
	}

}
