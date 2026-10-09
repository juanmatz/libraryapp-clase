package com.library.libraryapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.library.libraryapp.service.Calcular;

@SpringBootTest
class LibraryappApplicationTests {

	@Test
	void contextLoads() {
		Calcular clc = new Calcular();
		double resultado = clc.sumar(5.0,5.0);
		assertEquals(100.0,resultado);
	}
	

}
