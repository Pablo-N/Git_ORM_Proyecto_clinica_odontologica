package com.PabloNovoa.SistemaGestionTurnos.service;

import com.PabloNovoa.SistemaGestionTurnos.entity.Paciente;
import com.PabloNovoa.SistemaGestionTurnos.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }



    // get

    public List<Paciente> buscarPacientes(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPaciente(Long id){
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> buscarPacientePorCorreo(String correo){
        return pacienteRepository.findByEmail(correo);
    }

    //post

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    // put

    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
    }

    // delete

    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }


}
