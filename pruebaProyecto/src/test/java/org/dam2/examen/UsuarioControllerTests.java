package org.dam2.examen;

import java.time.LocalDate;

import org.dam2.appEmt.App;
import org.dam2.appEmt.configuration.cifrado.MD5;
import org.dam2.appEmt.login.controladores.UsuarioController;
import org.dam2.appEmt.login.modelPeticion.UsuarioRequest;
import org.dam2.appEmt.login.modelo.Sexo;
import org.dam2.appEmt.login.servicios.IUsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UsuarioController.class)
@ContextConfiguration(classes = {App.class})
class UsuarioControllerTests {

    @Mock
    private IUsuarioService usuarioService;

    /*
    @Autowired
    private WebApplicationContext webApplicationContext;
    */
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testIntroducirUsuario() throws Exception {
        //FIXME: No coge el contexto de la seguridad 
        
        UsuarioRequest request = UsuarioRequest.builder()
        .correo("usuario@usuario.com")
        .clave(MD5.encriptar("Autobus2022?"))
        .nombre("Usuario")
        .apellidos("Usuario")
        .fechaNacimiento(LocalDate.of(2020, 12, 20))
        .sexo(Sexo.HOMBRE)
        .build();
    

        Mockito.when(usuarioService.existsById("usuario@usuario.com")).thenReturn(true);
        
		this.mockMvc.perform( 
			MockMvcRequestBuilders
				.post("/usuario/insertar")
				.content(objectMapper.writeValueAsString(request))
				.accept(MediaType.APPLICATION_JSON)
            )
			.andExpect(MockMvcResultMatchers.status().isCreated()
		);

        Mockito.verify(usuarioService).existsById("usuario@usuario.com");


	}

}
