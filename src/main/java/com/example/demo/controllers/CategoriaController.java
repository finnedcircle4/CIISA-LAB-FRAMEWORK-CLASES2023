package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.entity.Categoria;
import com.example.demo.entity.Users;
import com.example.demo.service.CategoriaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/forms")
public class CategoriaController {

	// INYECTAR VALORES
	//@Value("${texto.indexController.index.titulo}")
	private String nombreApp = "Gestor IZBB";
	
	/****************************************************************
	 *					INYECCION DE DEPENDECIAS					*
	 * **************************************************************/
	@Autowired
	private CategoriaService categoriaService;
	
	

	
	/********************************************
	 *				FORMULARIOS					*
	 * ******************************************/
	
	@GetMapping("formCategoria")
	public String crearCategoria(Model model) {
		model.addAttribute("opcion", "agregar");
		model.addAttribute("titulo", "Categorias");
		model.addAttribute("subtitulo", "Agregar categoria");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("user", sesion());
		
		Categoria categoria = new Categoria();
		model.addAttribute("categoria", categoria);
		
		return "forms/formCategoria";
	}
	
	/********************************************
	 *					LISTAR					*
	 * ******************************************/
	
	@GetMapping("listarCategoria")
	public String listarCategorias(Model model) {
		List<Categoria> categorias = categoriaService.findAll();
		model.addAttribute("categorias", categorias);
		model.addAttribute("titulo", "Categorias");
		model.addAttribute("subtitulo", "Listar categorias");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("user", sesion());
		return "forms/listarCategoria";
	}
	
	
	
	/********************************************
	 *					CREAR					*
	 * ******************************************/
	
	@PostMapping("/guardarCategoria")
	public String guardarCategoria(@Valid Categoria categoria, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		//INSERTAMOS
		if (result.hasErrors()) {
			model.addAttribute("opcion", "agregar");
			model.addAttribute("titulo", "Categorias");
			model.addAttribute("subtitulo", "Agregar categoria");
			model.addAttribute("nombreApp", nombreApp);
			model.addAttribute("user", sesion());
			return "forms/formCategoria";
		}
		String mensajeFlash = (categoria.getId() != null) ? "Categoría editada con éxito!" : "Categoría creada con éxito!";
		categoriaService.save(categoria);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listarCategoria";
		
	}
	
	/********************************************
	 *					ELIMINAR				*
	 * ******************************************/
	
	@RequestMapping(value = "categorias/eliminar/{id}")
	public String eliminarCategoria(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			categoriaService.delete(id);
				flash.addFlashAttribute("success", "categoria eliminada con éxito!");
		}
		return "redirect:/forms/listarCategoria";
	}
	
	
	/********************************************
	 *					VER/EDITAR				*
	 * ******************************************/
	
	@RequestMapping(value = "categorias/{opcion}/{id}")
	public String detalleCategoria(
			@PathVariable(value = "opcion") String opcion,
			@PathVariable(value = "id") Long id,Model model, RedirectAttributes flash) {
		Categoria categoria = null;
		
		if (id > 0) {
			categoria = categoriaService.findOne(id);
			if (categoria == null) {
				flash.addFlashAttribute("error", "El ID de categoria no existe en la BBDD!");
				return "redirect:/forms/listarCategoria";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID de categoria no puede ser cero!");
			return "redirect:/forms/listarCategoria";
		}
		model.addAttribute("opcion", opcion);
		model.addAttribute("categoria", categoria);
		model.addAttribute("nombreApp", nombreApp);
		if(opcion.equals("editar")) {
			model.addAttribute("subtitulo", "Editar Categoria");
		}
		else {
			model.addAttribute("subtitulo", "Ver Categoria");
		}
		model.addAttribute("titulo", "Categorias");
		return "forms/formCategoria";
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