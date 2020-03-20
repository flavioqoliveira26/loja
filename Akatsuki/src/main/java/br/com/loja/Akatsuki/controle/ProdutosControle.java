package br.com.loja.Akatsuki.controle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.loja.Akatsuki.modelos.Categorias;
import br.com.loja.Akatsuki.modelos.Produtos;
import br.com.loja.Akatsuki.repositorios.ProdutoRepositorios;



@SpringBootApplication
@Controller
public class ProdutosControle {
	
	
	
	@Autowired
	private ProdutoRepositorios produtoRepositorios;
	
	@GetMapping("/admin/produtos/cadastrar")
	public ModelAndView cadastrar(Produtos produtos) {
		ModelAndView mv =  new ModelAndView("/admin/produtos/cadastro");
		mv.addObject("produtos", produtos);
		return mv;
	}
	
	@GetMapping("/admin/produtos/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/admin/produtos/lista");
		mv.addObject("listaProdutos", produtoRepositorios.findAll());
		return mv;
	}
	
	@GetMapping("/admin/produtos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Produtos> produtos = produtoRepositorios.findById(id);
		return cadastrar(produtos.get());		
	}
	
	@GetMapping("/admin/produtos/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Produtos> produtos = produtoRepositorios.findById(id);
		produtoRepositorios.delete(produtos.get());
		return listar();		
	}
	
	@PostMapping("/admin/produtos/salvar")
	public ModelAndView salvar(@Valid Produtos produtos, BindingResult result,
			@RequestParam("file")MultipartFile arquivo)  {	
		
		if(result.hasErrors()) {
			return cadastrar(produtos);
		}
			
		produtoRepositorios.saveAndFlush(produtos);
		return cadastrar(new Produtos());			
	}

}
