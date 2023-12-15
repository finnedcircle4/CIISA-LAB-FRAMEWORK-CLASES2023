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
import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;
import com.example.demo.entity.ProductosCategorias;
import com.example.demo.entity.Estado;
import com.example.demo.service.ProductosCategoriasService;
import com.example.demo.service.EstadoService;
import com.example.demo.service.ProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/forms")
public class ProductoController {

	// INYECTAR VALORES
	//@Value("${texto.indexController.index.titulo}")
	private String nombreApp = "Gestor IZBB";
	
	/****************************************************************
	 *					INYECCION DE DEPENDECIAS					*
	 * **************************************************************/
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private ProductosCategoriasService productosCategoriasService;
	
	@Autowired
	private EstadoService estadoService;
	
	

	
	/********************************************
	 *				FORMULARIOS					*
	 * ******************************************/
	
	@GetMapping("formProducto")
	public String crearCoctel(Model model) {
		model.addAttribute("titulo", "Productos");
		model.addAttribute("opcion", "agregar");
		model.addAttribute("subtitulo", "Agregar producto");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("usuario", sesion());
		
		List<Estado> estados = estadoService.findAll();
		//System.out.println(medidas.size());
		Producto producto = new Producto();
		model.addAttribute("producto", producto);
		model.addAttribute("medidas", estados);
		
		return "forms/formProducto";
	}
	
	/********************************************
	 *					LISTAR					*
	 * ******************************************/
	
	@GetMapping("listarProducto")
	public String listarCocteles(Model model) {
		List<Producto> productos = productoService.findAll();
		model.addAttribute("productos", productos);
		
		model.addAttribute("titulo", "productos");
		model.addAttribute("subtitulo", "Listar productos");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("user", sesion());
		return "forms/listarProducto";
	}
	
	
	
	/********************************************
	 *					CREAR					*
	 * ******************************************/
	
	@PostMapping("/guardarProducto")
	public String guardarProducto(@Valid Producto producto, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		//INSERTAMOS
		if (result.hasErrors()) {
			model.addAttribute("opcion", "agregar");
			model.addAttribute("titulo", "Productos");
			model.addAttribute("subtitulo", "Agregar Producto");
			model.addAttribute("nombreApp", nombreApp);
			model.addAttribute("user", sesion());
			return "forms/formProducto";
		}
		String mensajeFlash = (producto.getId() != null) ? "Producto editado con éxito!" : "Producto creado con éxito!";
		productoService.save(producto);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listarProducto";
		
	}
	
	/********************************************
	 *					ELIMINAR				*
	 * ******************************************/
	@RequestMapping(value = "productos/eliminar/{id}")
	public String eliminarProducto(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			//OBTENEMOS LOS COCTELES_INGREDIENTES VINCULADOS A ESTE COCTEL
			List<ProductosCategorias> productosCategorias = productoService.findProductoCatById(id);
			for (ProductosCategorias ProdCat : productosCategorias) {
				productosCategoriasService.delete(ProdCat.getId());	
			}
			//OBTENEMOS LOS DETALLES_PEDIDO VINCULADOS A ESTE COCTEL
		}
		return "redirect:/forms/listarProducto";
	}
	
	
	/********************************************
	 *					VER/EDITAR				*
	 * ******************************************/
	
	@RequestMapping(value = "productos/{opcion}/{id}")
	public String detalleProducto(
			@PathVariable(value = "opcion") String opcion,
			@PathVariable(value = "id") Long id,Model model, RedirectAttributes flash) {
		Producto producto = null;
		
		if (id > 0) {
			producto = productoService.findOne(id);
			if (producto == null) {
				flash.addFlashAttribute("error", "El ID del Producto no existe en la BBDD!");
				return "redirect:/forms/listarProducto";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID del Producto no puede ser cero!");
			return "redirect:/forms/listarProducto";
		}
		model.addAttribute("opcion", opcion);
		model.addAttribute("producto", producto);
		model.addAttribute("nombreApp", nombreApp);
		if(opcion.equals("editar")) {
			model.addAttribute("subtitulo", "Editar Producto");
		}
		else {
			model.addAttribute("subtitulo", "Ver Producto");
		}
		List<ProductosCategorias> productosCategorias = productoService.findProductoCatById(id);
		model.addAttribute("productosCategorias", productosCategorias);
		model.addAttribute("titulo", "Productos");
		return "forms/formProducto";
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

