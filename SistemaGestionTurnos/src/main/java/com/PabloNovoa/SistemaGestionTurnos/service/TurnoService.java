package com.PabloNovoa.SistemaGestionTurnos.service;

import com.PabloNovoa.SistemaGestionTurnos.dto.TurnoDTO;
import com.PabloNovoa.SistemaGestionTurnos.entity.Odontologo;
import com.PabloNovoa.SistemaGestionTurnos.entity.Paciente;
import com.PabloNovoa.SistemaGestionTurnos.entity.Turno;
import com.PabloNovoa.SistemaGestionTurnos.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

   private TurnoRepository turnoRepository;



    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
        //convertir el turno a un turnoDTO
        TurnoDTO respuesta= new TurnoDTO();
        //cargar la información de turno al turno DTO
        respuesta.setId(turno.getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setFecha(turno.getFecha());
        //devolución
        return respuesta;
    }

    private Turno turnoDTOATurno(TurnoDTO turnodto){
        Turno respuesta= new Turno();
        //cargar la información de turno DTO al turno
        Odontologo odontologo= new Odontologo();
        Paciente paciente= new Paciente();
        odontologo.setId(turnodto.getOdontologoId());
        paciente.setId(turnodto.getPacienteId());
        respuesta.setFecha(turnodto.getFecha());
        respuesta.setId(turnodto.getId());
        //no debemos olvidarnos de agregar ambos objetos
        respuesta.setOdontologo(odontologo);
        respuesta.setPaciente(paciente);
        //salida
        return respuesta;
    }



    //get

    public List<TurnoDTO> buscarTurnos(){
        List<Turno> turnosEncontrados = turnoRepository.findAll();
        // recorremos la lista para convertir cada elemento

        List<TurnoDTO> respuesta = new ArrayList<>();

        for (Turno turno : turnosEncontrados) {

            respuesta.add(turnoATurnoDTO(turno));

        }

        return respuesta;
    }

    public Optional<TurnoDTO> buscarTurno(Long id){
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            //existe el turno
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }
        else{
            //no existe turno, es nulo
            return Optional.empty();
        }
    }

    //post

    public TurnoDTO guardarTurno(TurnoDTO turnodto){
        Turno turnoGuardado=turnoRepository.save(turnoDTOATurno(turnodto));
        return turnoATurnoDTO(turnoGuardado);
    }

    // put

    public void actualizarTurno(TurnoDTO turnodto){
        turnoRepository.save(turnoDTOATurno(turnodto));
    }

    // delete

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }
}
