package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.entity.Users;
import com.example.demo.entity.Usuario;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/forms")
public class UserController {

	// INYECTAR VALORES
	//@Value("${texto.indexController.index.titulo}")
	private String nombreApp = "Gestor IZBB";
	
	/****************************************************************
	 *					INYECCION DE DEPENDECIAS					*
	 * **************************************************************/
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	

	
	/********************************************
	 *				FORMULARIOS					*
	 * ******************************************/
	
	@GetMapping("formUsers")
	public String crearUsuario(Model model) {
		model.addAttribute("opcion", "agregar");
		model.addAttribute("titulo", "Usuarios");
		model.addAttribute("subtitulo", "Agregar usuario");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("user", sesion());
		
		Users user = new Users();
		model.addAttribute("user", user);
		
		return "forms/formUsers";
	}
	
	/********************************************
	 *					LISTAR					*
	 * ******************************************/
	
	@GetMapping("listarUsers")
	public String listarUsers(Model model) {
		List<Users> users = userService.findAll();
		model.addAttribute("users", users);
		model.addAttribute("titulo", "Usuarios");
		model.addAttribute("subtitulo", "Listar usuarios");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("user", sesion());
		return "forms/listarUsers";
	}
	
	
	
	/********************************************
	 *					CREAR					*
	 * ******************************************/
	
	@PostMapping("/guardarUser")
	public String guardarUsuario(@Valid Users user, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		//INSERTAMOS
		if (result.hasErrors()) {
			model.addAttribute("opcion", "agregar");
			model.addAttribute("titulo", "Usuarios");
			model.addAttribute("subtitulo", "Agregar usuario");
			model.addAttribute("nombreApp", nombreApp);
			model.addAttribute("user", sesion());
			return "forms/formUsers";
		}
		String mensajeFlash = (user.getId() != null) ? "Usuario editado con éxito!" : "Usuario creada con éxito!";
		userService.save(user);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listarUsers";	
	}
	
	@PostMapping("/registrarUsuario")
	public String guardarUser(
			@RequestParam("nombre") String nombre, 
			@RequestParam("apellido") String apellido,
			@RequestParam("email") String email,
			@RequestParam("password") String password, 
			Model model, RedirectAttributes flash,
			SessionStatus status) {
		Users user = new Users();
		user.setNombre(nombre);
		user.setApellido(apellido);
		user.setEmail(email);
		System.out.println("Clave ingresada: " + password);
		user.setPassword(passwordEncoder.encode(password));
		System.out.println("Clave encriptada: " + passwordEncoder.encode(password));
		user.setRol("USER");
		userService.save(user);
		String mensajeFlash = (user.getId() != null) ? "Usuario editado con éxito!" : "Usuario creado con éxito!";
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:login";

	}
	
	/********************************************
	 *					ELIMINAR				*
	 * ******************************************/
	
	@RequestMapping(value = "users/eliminar/{id}")
	public String eliminarUsuario(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
				userService.delete(id);
				flash.addFlashAttribute("success", "Usuario eliminado con éxito!");
		}
		return "redirect:/forms/listarUsers";
	}
	
	
	/********************************************
	 *					VER/EDITAR				*
	 * ******************************************/
	
	@RequestMapping(value = "users/{opcion}/{id}")
	public String detalleUsuario(
			@PathVariable(value = "opcion") String opcion,
			@PathVariable(value = "id") Long id,Model model, RedirectAttributes flash) {
		Users user = null;
		
		if (id > 0) {
			user = userService.findOne(id);
			if (user == null) {
				flash.addFlashAttribute("error", "El ID del usuario no existe en la BBDD!");
				return "redirect:/forms/listarUsers";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID del usuario no puede ser cero!");
			return "redirect:/forms/listarUsers";
		}
		model.addAttribute("opcion", opcion);
		model.addAttribute("user", user);
		model.addAttribute("nombreApp", nombreApp);
		if(opcion.equals("editar")) {
			model.addAttribute("subtitulo", "Editar Usuario");
		}
		else {
			model.addAttribute("subtitulo", "Ver Usuario");
		}
		model.addAttribute("titulo", "Users");
		return "forms/formUsers";
	}
	
	/************************************************
	 *				MODEL ATRIBUTES					*
	 * **********************************************/
	@ModelAttribute("user")
	public Users sesion() {
		Users user = new Users();
		user.setNombre("Ignacio");
		user.setApellido("Zuñiga");
		user.setRol("ADMIN");
		user.setEmail("izuniga@ciisa.cl");
		return user;

	}

}
