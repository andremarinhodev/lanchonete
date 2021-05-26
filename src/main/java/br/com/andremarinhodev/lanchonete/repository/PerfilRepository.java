package br.com.andremarinhodev.lanchonete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andremarinhodev.lanchonete.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{

	Optional<Perfil> findByNome(String nome);

}
