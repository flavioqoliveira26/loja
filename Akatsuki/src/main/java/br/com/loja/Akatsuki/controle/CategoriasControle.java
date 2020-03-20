package br.com.loja.Akatsuki.controle;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.loja.Akatsuki.modelos.Categorias;
import br.com.loja.Akatsuki.repositorios.CategoriaRepositorios;


@SpringBootApplication
@Controller
public class CategoriasControle {
	
	@Autowired
	private CategoriaRepositorios categoriaRepositorio;
	
	@GetMapping("/admin/categorias/cadastrar")
	public ModelAndView cadastrar(Categorias categorias) {
		ModelAndView mv = new ModelAndView("admin/categorias/cadastro");
		mv.addObject("Categorias", categorias);	
		return mv;
	}	
	
	@GetMapping("/admin/categorias/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("admin/categorias/lista");
		mv.addObject("listaCategorias",categoriaRepositorio.findAll());		
		return mv;
	}
	
	
	@GetMapping("/admin/categorias/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Categorias> categorias = categoriaRepositorio.findById(id);
		return cadastrar(categorias.get());		
	}
	
	@GetMapping("/admin/categorias/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Categorias> categorias = categoriaRepositorio.findById(id);
		categoriaRepositorio.delete(categorias.get());
		return listar();		
	}
	

	@PostMapping("admin/categorias/salvar")
	public ModelAndView salvar(@Valid Categorias categorias, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("deu ruim");
			return cadastrar(categorias);
		}
		
		categoriaRepositorio.saveAndFlush(categorias);
		System.out.println("deu bom");
		return cadastrar(new Categorias());
	}

}
