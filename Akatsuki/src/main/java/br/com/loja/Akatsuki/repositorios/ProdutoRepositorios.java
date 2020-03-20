package br.com.loja.Akatsuki.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.Akatsuki.modelos.Produtos;

public interface ProdutoRepositorios extends JpaRepository<Produtos, Long>{

}
