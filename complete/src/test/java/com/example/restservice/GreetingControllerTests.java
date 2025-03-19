/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice;

// Imports para simular requisições HTTP no teste
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Imports para testes unitários com JUnit 5
import org.junit.jupiter.api.Test;// Indica que um método é um teste unitário do JUnit

import org.springframework.beans.factory.annotation.Autowired; // Injeta automaticamente dependências gerenciadas pelo Spring
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; // Configura automaticamente o MockMvc para testes
import org.springframework.boot.test.context.SpringBootTest; // Carrega o contexto do Spring Boot para testes
import org.springframework.test.web.servlet.MockMvc; // Classe usada para simular requisições HTTP e validar respostas nos testes

@SpringBootTest // Indica que o teste rodará dentro do contexto do Spring Boot



@AutoConfigureMockMvc // Configura automaticamente o MockMvc para testar controllers sem um servidor real
public class GreetingControllerTests {

    @Autowired // Injeta automaticamente uma instância de MockMvc
	private MockMvc mockMvc;
	/**
	 * Teste unitário para verificar o comportamento do endpoint /greeting 
	 * quando nenhum parâmetro é fornecido. 
	 * Espera-se que a resposta contenha a mensagem padrão "Hello, World!" 
	 * e retorne o status HTTP 200 (OK).
	 */
	@Test
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, World!"));
	}
	/**
	 * Teste unitário para validar o comportamento do endpoint /greeting 
	 * quando um parâmetro "name" é passado na requisição.
	 * Espera-se que a resposta contenha a mensagem "Hello, {name}!" 
	 * substituindo "{name}" pelo valor passado no parâmetro.
	 * Neste caso, o nome fornecido é "Spring Community", 
	 * então a resposta esperada é "Hello, Spring Community!".
	 */

	@Test
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {

		this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
	}

}
