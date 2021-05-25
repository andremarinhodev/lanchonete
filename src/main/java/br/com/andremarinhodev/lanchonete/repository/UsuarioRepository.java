package br.com.andremarinhodev.lanchonete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andremarinhodev.lanchonete.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
