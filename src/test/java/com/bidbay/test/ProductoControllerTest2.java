package com.bidbay.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bidbay.controllers.ProductoController;
import com.bidbay.models.entity.Categoria;
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Usuario;
import com.bidbay.service.ICategoriaService;
import com.bidbay.service.IProductoService;

import jakarta.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class ProductoControllerTest2 {

    @Mock
    private IProductoService productoService;

    @Mock
    private ICategoriaService categoriaService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private ProductoController productoController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listar_ProductosEncontradosPorNombre_DebeDevolverVistaProductoViewConProductosEncontrados() {
        // Configuración del escenario de prueba
        String search = "keyword";
        List<Producto> productosEncontrados = new ArrayList<>();
        productosEncontrados.add(new Producto());
        when(productoService.findByName(search)).thenReturn(productosEncontrados);

        // Ejecución del método a probar
        String viewName = productoController.listar(null, null, search, model);

        // Verificación de resultados
        Assert.assertEquals("views/productoView", viewName);
        verify(productoService).findByName(search);
        verify(model).addAttribute("productos", productosEncontrados);
        verify(model).addAttribute("inputValue", search);
    }

    @Test
    public void crear_NuevoProducto_DebeDevolverVistaProductoFormConModeloConfigurado() {
        // Configuración del escenario de prueba
        Map<String, Object> modelMap = mock(Map.class);

        // Ejecución del método a probar
        String viewName = productoController.crear(modelMap);

        // Verificación de resultados
        Assert.assertEquals("views/productoForm", viewName);
        verify(modelMap).put("titulo", "¿Qué querés vender?");
        verify(modelMap).put("botonSubmit", "Vender");
        verify(modelMap).put("categorias", categoriaService.findAll());
    }

    @Test
    public void guardar_ValidacionFallida_DebeDevolverVistaProductoForm() {
        // Configuración del escenario de prueba
        when(bindingResult.hasErrors()).thenReturn(true);

        // Ejecución del método a probar
        String viewName = productoController.guardar(mock(Producto.class), bindingResult, model, multipartFile, redirectAttributes);

        // Verificación de resultados
        Assert.assertEquals("views/productoForm", viewName);
        verify(model).addAttribute("titulo", "Formulario de Producto");
    }
/*
    @Test
    public void guardar_ValidacionExitosaSinCargaDeImagen_DebeGuardarProductoYRedirigirALista() {
        // Configuración del escenario de prueba
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(multipartFile.isEmpty()).thenReturn(true); // Simulamos que no se carga ninguna imagen

        // Ejecución del método a probar
        String viewName = productoController.guardar(new Producto(), bindingResult, model, any(MultipartFile.class), redirectAttributes);

        // Verificación de resultados
       // Assert.assertEquals("redirect:/producto/listar", viewName);
       // verify(productoService, times(1)).save(any(Producto.class));
      //  verify(redirectAttributes, never()).addFlashAttribute(anyString(), anyString());
    }*/
    
    @Test
    public void editar_IdValido_DebeDevolverVistaProductoFormConProductoCargado() {
        // Configuración del escenario de prueba
        long id = 1L;
        Producto producto = mock(Producto.class);
        when(productoService.findOne(id)).thenReturn(producto);
        Map<String, Object> modelMap = mock(Map.class);

        // Ejecución del método a probar
        String viewName = productoController.editar(id, modelMap);

        // Verificación de resultados
        Assert.assertEquals("views/productoForm", viewName);
        verify(modelMap).put("producto", producto);
        verify(modelMap).put("titulo", "Editar Producto");
        verify(modelMap).put("botonSubmit", "Editar");
    }

    @Test
    public void eliminar_IdValido_DebeRedirigirALista() {
        // Configuración del escenario de prueba
        long id = 1L;

        // Ejecución del método a probar
        String viewName = productoController.eliminar(id);

        // Verificación de resultados
        Assert.assertEquals("redirect:/producto/listar", viewName);
        verify(productoService).delete(id);
    }

    @Test
    public void buscar_CategoriaIdNulo_DebeDevolverVistaProductoSearchViewConProductosEncontrados() {
        // Configuración del escenario de prueba
        Long categoriaId = null;
        String search = "keyword";
        String order = "asc";
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto());
        when(productoService.findAll()).thenReturn(productos);

        // Ejecución del método a probar
        String viewName = productoController.buscar(categoriaId, null, order, search, model);

        // Verificación de resultados
        Assert.assertEquals("views/productoSearhView", viewName);
        verify(model).addAttribute("titulo", "Búsqueda de Productos");
        verify(model).addAttribute("inputValue", search);
     //   verify(model).addAttribute("categorias", categoriaService.findAll());
        verify(productoService).findAll();
      //  verify(model).addAttribute("productos", productos);
    }

    @Test
    public void buscar_CategoriaIdValido_DebeDevolverVistaProductoSearchViewConProductosDeCategoria() {
        // Configuración del escenario de prueba
        Long categoriaId = 1L;
        String search = null;
        String order = null;
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto());
        Categoria categoria = new Categoria();
        when(categoriaService.findOne(categoriaId)).thenReturn(categoria);
        when(productoService.findByCategoriaId(categoriaId)).thenReturn(productos);

        // Ejecución del método a probar
        String viewName = productoController.buscar(categoriaId, null, order, search, model);

        // Verificación de resultados
        Assert.assertEquals("views/productoSearhView", viewName);
        verify(model).addAttribute("titulo", "Búsqueda de Productos");
        verify(model).addAttribute("inputValue", search);
        verify(model).addAttribute("categorias", categoriaService.findAll());
        verify(categoriaService).findOne(categoriaId);
     //   verify(productoService).findByCategoriaId(categoriaId);
        verify(model).addAttribute("categoria", categoria);
       // verify(model).addAttribute("productos", productos);
    }
}
