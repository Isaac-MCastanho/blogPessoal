package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {

		usuarioRepository.deleteAll();

		usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@email.com.br", "12345678",
				"https://i.pinimg.com/236x/1d/ac/ac/1dacac805f7c1f8364b8e604ae882f64.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "manuela@email.com.br", "12345678",
				"https://i.pinimg.com/236x/2d/f2/c4/2df2c4d2a14f55fdc84c034f5e37d257.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriana@email.com.br", "12345678",
				"https://i.pinimg.com/236x/21/3d/16/213d160e24e48d10cc9d27c306e1a41d.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Paulo Antunes", "paulo@email.com.br", "12345678",
				"https://i.pinimg.com/236x/35/2f/15/352f15e55b4c410ecdc9b648af6c0b39.jpg"));
	}

	@Test
	@DisplayName("Retorna 1 usuário")
	public void deveRetornarUmUsuarios() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
	}

	@Test
	@DisplayName("Retorna 3 usuários")
	public void deveRetornarTresUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("silva");
		assertEquals(3, listaDeUsuarios.size());

		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));
	}

	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}

}
