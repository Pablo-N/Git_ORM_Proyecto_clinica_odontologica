package com.PabloNovoa.SistemaGestionTurnos;

import com.PabloNovoa.SistemaGestionTurnos.entity.Paciente;
import com.PabloNovoa.SistemaGestionTurnos.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class SistemaGestionTurnosApplicationTests {

	@Autowired
	private PacienteService pacienteService;

	@Test
	void contextLoads() {
	}


	@Test
	public void buscarPacienteId1(){
		//PacienteService pacienteService= new PacienteService();
		Optional<Paciente> pacienteBuscado=pacienteService.buscarPaciente(1l);

		System.out.println(pacienteBuscado.get().getApellido());

		Assertions.assertEquals("N",pacienteBuscado.get().getApellido());
	}

}
