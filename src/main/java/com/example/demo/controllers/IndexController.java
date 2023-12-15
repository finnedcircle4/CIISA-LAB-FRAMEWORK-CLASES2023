package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Producto;
import com.example.demo.entity.Categoria;
import com.example.demo.entity.Estado;
import com.example.demo.entity.Users;
//import com.example.demo.models.Producto;
import com.example.demo.service.ProductoService;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.EstadoService;
import com.example.demo.service.UserService;


@Controller
public class IndexController {
	
	/****************************************************************
	 *					INYECCION DE DEPENDECIAS					*
	 * **************************************************************/
	@Autowired
	private ProductoService productoService;
	@Autowired
	private UserService userService;
	@Autowired
	private EstadoService estadoService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
		//@Value("${texto.indexController.index.titulo}")
		private String nombreApp = "Gestor IZBB";
		
	@GetMapping({"/home","/","/index"})
	public String home(Model model) {
	
		List<Producto> productos = productoService.findAll();
		model.addAttribute("productos", productos);
		
		List<Users> users = userService.findAll();
		model.addAttribute("users", users);
		
		List<Categoria> categorias = categoriaService.findAll();
		model.addAttribute("categorias", categorias);
		
		List<Estado> estados = estadoService.findAll();
		model.addAttribute("estados", estados);
		
		 /*PROBRAR TEXTO ENCRIPTADO
		String textoPlano = "Ciisa123";
        String textoEncriptado = passwordEncoder.encode(textoPlano);
        System.out.println("Texto encriptado: " + textoEncriptado);
        
        /*textoPlano = "admin123";
        textoEncriptado = passwordEncoder.encode(textoPlano);
        System.out.println("Texto encriptado: " + textoEncriptado);*/
		
		model.addAttribute("nombreApp",nombreApp);
		model.addAttribute("titulo", "Página Principal");
		model.addAttribute("subtitulo", "Home");
		return "home";
	}
	
	
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("titulo", "Formulario de registro");
		model.addAttribute("nombreApp", nombreApp);
		return "register";
	}
}