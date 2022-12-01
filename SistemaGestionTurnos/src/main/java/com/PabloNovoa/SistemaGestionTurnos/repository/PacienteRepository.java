package com.PabloNovoa.SistemaGestionTurnos.repository;

import com.PabloNovoa.SistemaGestionTurnos.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    Optional<Paciente> findByEmail(String email);
}
