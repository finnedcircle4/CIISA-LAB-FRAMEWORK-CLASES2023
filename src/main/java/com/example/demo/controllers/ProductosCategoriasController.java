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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.entity.Producto;
import com.example.demo.entity.ProductosCategorias;
import com.example.demo.entity.Categoria;
import com.example.demo.entity.Estado;
import com.example.demo.entity.Users;
import com.example.demo.service.ProductoService;
import com.example.demo.service.ProductosCategoriasService;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.EstadoService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/forms")
public class ProductosCategoriasController {

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
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	
	/********************************************
	 *				FORMULARIOS					*
	 * ******************************************/
	
	@RequestMapping("formProductosCategorias/{id}")
	public String crearProductosCategorias(@PathVariable(value = "id") Long id,Model model) {
		model.addAttribute("titulo", "Productos");
		model.addAttribute("subtitulo", "Agregar Categorias a Producto");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("user", sesion());
		
		List<Estado> estados = estadoService.findAll();
		List<Categoria> categorias = categoriaService.findAll();
		List<ProductosCategorias> listproductosCategorias = productosCategoriasService.findByProductoId(id);
		Producto producto = productoService.findOne(id);
		System.out.println(producto.toString());
		
		ProductosCategorias productosCategorias = new ProductosCategorias();
		model.addAttribute("productosCategorias", productosCategorias);
		model.addAttribute("listProductosCategorias", listproductosCategorias);
		model.addAttribute("Categorias", categorias);
		model.addAttribute("Producto", producto);
		model.addAttribute("Estados", estados);
		
		return "forms/formProductosCategorias";
	}
	
	/********************************************
	 *					LISTAR					*
	 * ******************************************/
	

	
	
	/********************************************
	 *					CREAR					*
	 * ******************************************/
	@PostMapping("/guardarProductosCategorias")
	public String guardarProductosCategorias(
			@RequestParam("estado_id") int estado_id,
            @RequestParam("categoria_id") int categoria_id,
            @RequestParam("producto_id") int producto_id,
            @RequestParam("stock") int stock,Model model,
			RedirectAttributes flash, SessionStatus status) {
		
		ProductosCategorias productoCategorias = new ProductosCategorias();
		productoCategorias.setStock(stock);
		productoCategorias.setProducto(productoService.findOne((long) producto_id));
		productoCategorias.setCategoria(categoriaService.findOne((long) categoria_id));
		productoCategorias.setEstado(estadoService.findOne((long) estado_id));
		
		System.out.println(productoCategorias);
		
		String mensajeFlash = (productoCategorias.getId() != null) ? "Categoria editado con éxito!" : "Categoria agregado con éxito!";
		productosCategoriasService.save(productoCategorias);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:formProductosCategorias/"+producto_id;
		
	}
	

	/********************************************
	 *					ELIMINAR				*
	 * ******************************************/
	@RequestMapping(value = "/categoriasproducto/eliminar/{idProducto}/{id}")
	public String eliminarProductoCategoria(
			@PathVariable(value = "idProducto") Long producto_id,
			@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			//OBTENEMOS LOS Productos_Categorias VINCULADOS A ESTE Producto
			productosCategoriasService.delete(id);
			flash.addFlashAttribute("success", "Categoria eliminada con éxito!");

		}
		return "redirect:/forms/formProductosCategorias/"+producto_id;
	}
	
	/********************************************
	 *					VER/EDITAR				*
	 * ******************************************/
	

	
	/************************************************
	 *				MODEL ATRIBUTES					*
	 * **********************************************/
	@ModelAttribute("user")
	public Users sesion() {
		Users user = new Users();
		user.setNombre("Ignacio");
		user.setApellido("Zuñiga");
		user.setEmail("izuniga@ciisa.cl");
		return user;

	}

}
