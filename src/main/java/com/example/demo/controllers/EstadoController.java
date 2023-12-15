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
import com.example.demo.entity.Users;
import com.example.demo.entity.Estado;
import com.example.demo.service.EstadoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/forms")
public class EstadoController {

	// INYECTAR VALORES
	//@Value("${texto.indexController.index.titulo}")
	private String nombreApp = "Gestor IZBB";
	
	/****************************************************************
	 *					INYECCION DE DEPENDECIAS					*
	 * **************************************************************/
	@Autowired
	private EstadoService estadoService;
	
	

	
	/********************************************
	 *				FORMULARIOS					*
	 * ******************************************/
	
	@GetMapping("formEstado")
	public String crearEstado(Model model) {
		model.addAttribute("opcion", "agregar");
		model.addAttribute("titulo", "Estados");
		model.addAttribute("subtitulo", "Agregar estado");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("user", sesion());
		
		Estado estado = new Estado();
		model.addAttribute("estado", estado);
		
		return "forms/formEstado";
	}
	
	/********************************************
	 *					LISTAR					*
	 * ******************************************/
	
	@GetMapping("listarEstado")
	public String listarEstados(Model model) {
		List<Estado> estados = estadoService.findAll();
		model.addAttribute("estados", estados);
		model.addAttribute("titulo", "Estados");
		model.addAttribute("subtitulo", "Listar estado");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("user", sesion());
		return "forms/listarEstado";
	}
	
	
	
	/********************************************
	 *					CREAR					*
	 * ******************************************/
	
	@PostMapping("/guardarEstado")
	public String guardarEstado(@Valid Estado estado, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		//INSERTAMOS
		if (result.hasErrors()) {
			model.addAttribute("opcion", "agregar");
			model.addAttribute("titulo", "Estados");
			model.addAttribute("subtitulo", "Agregar estado");
			model.addAttribute("nombreApp", nombreApp);
			model.addAttribute("user", sesion());
			return "forms/formEstado";
		}
		String mensajeFlash = (estado.getId() != null) ? "estado editado con éxito!" : "Estado creado con éxito!";
		estadoService.save(estado);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listarEstado";
		
	}
	
	/********************************************
	 *					ELIMINAR				*
	 * ******************************************/
	
	@RequestMapping(value = "estados/eliminar/{id}")
	public String eliminarEstado(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			estadoService.delete(id);
				flash.addFlashAttribute("success", "Estado eliminado con éxito!");
		}
		return "redirect:/forms/listarEstado";
	}
	
	
	/********************************************
	 *					VER/EDITAR				*
	 * ******************************************/
	
	@RequestMapping(value = "estados/{opcion}/{id}")
	public String detalleEstado(
			@PathVariable(value = "opcion") String opcion,
			@PathVariable(value = "id") Long id,Model model, RedirectAttributes flash) {
		Estado estado = null;
		
		if (id > 0) {
			estado = estadoService.findOne(id);
			if (estado == null) {
				flash.addFlashAttribute("error", "El ID del estado no existe en la BBDD!");
				return "redirect:/forms/listarEstado";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID del estado no puede ser cero!");
			return "redirect:/forms/listarEstado";
		}
		model.addAttribute("opcion", opcion);
		model.addAttribute("estado", estado);
		model.addAttribute("nombreApp", nombreApp);
		if(opcion.equals("editar")) {
			model.addAttribute("subtitulo", "Editar estado");
		}
		else {
			model.addAttribute("subtitulo", "Ver estado");
		}
		model.addAttribute("titulo", "Estados");
		return "forms/formEstado";
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

