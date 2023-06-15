package com.bidbay.test;

import static org.mockito.Mockito.verify;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.bidbay.controllers.HomeController;
import com.bidbay.service.IProductoService;
import com.bidbay.service.IUsuarioService;

public class HomeControllerTest {
    @Mock
    private IProductoService productoService;

    @Mock
    private Model model;

    @Mock
    private Map<String, Object> model2;

    @Mock
    private BindingResult bindingResult;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Captor
    private ArgumentCaptor<Object> objectArgumentCaptor;

    @InjectMocks
    private HomeController homeController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void inicioTest() {
        String result = homeController.listar(model);
        Assert.assertTrue("index".equals(result));
    }
}