package com.PabloNovoa.SistemaGestionTurnos.service;

import com.PabloNovoa.SistemaGestionTurnos.entity.Turno;
import com.PabloNovoa.SistemaGestionTurnos.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    //get

    public List<Turno> buscarTurnos(){
        return turnoRepository.findAll();
    }

    public Optional<Turno> buscarTurno(Long id){
        return turnoRepository.findById(id);
    }

    //post

    public Turno guardarTurno(Turno turno){
        return turnoRepository.save(turno);
    }

    // put

    public void actualizarTurno(Turno turno){
        turnoRepository.save(turno);
    }

    // delete

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }
}
