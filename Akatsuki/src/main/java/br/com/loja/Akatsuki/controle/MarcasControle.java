package br.com.loja.Akatsuki.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarcasControle {
	
	@GetMapping("/admin/marcas/cadastrar")
	public String cadastrar() {
		return "admin/marcas/cadastro";
	}
	
	@GetMapping("/admin/marcas/listar")
	public String listar() {
		return "admin/marcas/lista";
	}

}
