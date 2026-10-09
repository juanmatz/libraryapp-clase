package com.library.libraryapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryappApplicationTests {

	@Test
	void contextLoads() {
		Calcular clc = new Calcular();
		double resultado = clc.sumar(5.0,5.0);
		assertEquals(10.0,resultado);
	}
	

}
