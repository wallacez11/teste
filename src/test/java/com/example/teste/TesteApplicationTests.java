package com.example.teste;

import com.example.teste.config.JwtService;
import com.example.teste.dto.EnderecoResponse;
import com.example.teste.dto.UsersResponse;
import com.example.teste.model.Address;
import com.example.teste.model.StatusRegistro;
import com.example.teste.model.User;
import com.example.teste.repository.UserRepository;
import com.example.teste.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

	@InjectMocks
	private UsuarioServiceImpl usuarioService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private JwtService jwtService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
	void testAddUser() {
		Address address = new Address("Rua XYZ", "123", "Complemento", "Bairro", "Cidade", "Estado", "CEP");

		User newUser = new User("123456789",
				"John Doe",
				LocalDate.of(1990, 1, 1),
				address);


		// Configurar o comportamento esperado para returnUsername()
		when(jwtService.returnUsername()).thenReturn("newUser");
		;
		when(userRepository.save(newUser)).thenReturn(newUser);

		usuarioService.addUser(newUser);

		verify(userRepository, times(1)).save(newUser);
	}

	@Test
	void testGetUsers() {
		Address address1 = new Address("Rua XYZ", "123", "Complemento", "Bairro", "Cidade", "Estado", "CEP");
		Address address2 = new Address("Avenida ABC", "456", null, "Bairro2", "Cidade2", "Estado2", "CEP2");

		UsersResponse user1 = new UsersResponse("123456789", "John Doe", LocalDate.of(1990, 1, 1), address1);
		UsersResponse user2 = new UsersResponse("987654321", "Jane Doe", LocalDate.of(1985, 5, 10), address2);

		when(userRepository.findByStatus(StatusRegistro.ATIVO)).thenReturn(Arrays.asList(user1, user2));

		List<UsersResponse> userList = usuarioService.getUsers();

		assertEquals(2, userList.size());
		assertEquals(user1, userList.get(0));
		assertEquals(user2, userList.get(1));
	}

	@Test
	void testGetUser() {
		Address address = new Address("Rua XYZ", "123", "Complemento", "Bairro", "Cidade", "Estado", "CEP");
		User existingUser = new User("123456789", "John Doe", LocalDate.of(1990, 1, 1), address);

		when(userRepository.findByCpf("123456789")).thenReturn(existingUser);

		User user = usuarioService.getUser("123456789");

		assertEquals(existingUser, user);
	}

	@Test
	void testUpdateUser() {
		Address address = new Address("Rua XYZ", "123", "Complemento", "Bairro", "Cidade", "Estado", "CEP");
		User existingUser = new User("123456789", "John Doe", LocalDate.of(1990, 1, 1),address);
		User updatedUser = new User("123456789", "John Updated", LocalDate.of(1985, 5, 10), address);
		when(jwtService.returnUsername()).thenReturn("newUser");
		when(userRepository.findByCpf("123456789")).thenReturn(existingUser);

		usuarioService.updateUser("123456789", updatedUser);

		verify(userRepository, times(1)).save(existingUser);
		assertEquals("John Updated", existingUser.getNome());
		assertEquals(LocalDate.of(1985, 5, 10), existingUser.getDataNascimento());
	}

	@Test
	void testUpdateUserNotFound() {
		when(userRepository.findByCpf("123456789")).thenReturn(null);

		assertThrows(ResponseStatusException.class, () -> usuarioService.updateUser("123456789", new User()));
	}

	@Test
	void testDeleteUser() {
		Address address = new Address("Rua XYZ", "123", "Complemento", "Bairro", "Cidade", "Estado", "CEP");
		User existingUser = new User("123456789", "John Doe", LocalDate.of(1990, 1, 1), address);
		when(jwtService.returnUsername()).thenReturn("newUser");
		when(userRepository.findByCpf("123456789")).thenReturn(existingUser);

		usuarioService.deleteUser("123456789");

		verify(userRepository, times(1)).save(existingUser);
		assertEquals(StatusRegistro.REMOVIDO, existingUser.getStatus());
	}

	@Test
	void testDeleteUserNotFound() {
		when(userRepository.findByCpf("123456789")).thenReturn(null);

		assertThrows(ResponseStatusException.class, () -> usuarioService.deleteUser("123456789"));
	}
}