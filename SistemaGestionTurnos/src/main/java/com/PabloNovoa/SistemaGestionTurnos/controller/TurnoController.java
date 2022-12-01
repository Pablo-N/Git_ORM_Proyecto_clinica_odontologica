package com.PabloNovoa.SistemaGestionTurnos.controller;


import com.PabloNovoa.SistemaGestionTurnos.dto.TurnoDTO;
import com.PabloNovoa.SistemaGestionTurnos.entity.Odontologo;
import com.PabloNovoa.SistemaGestionTurnos.entity.Paciente;
import com.PabloNovoa.SistemaGestionTurnos.entity.Turno;
import com.PabloNovoa.SistemaGestionTurnos.exceptions.BadRequestException;
import com.PabloNovoa.SistemaGestionTurnos.exceptions.ResourceNotFoundException;
import com.PabloNovoa.SistemaGestionTurnos.service.OdontologoService;
import com.PabloNovoa.SistemaGestionTurnos.service.PacienteService;
import com.PabloNovoa.SistemaGestionTurnos.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;

    private PacienteService pacienteService;

    private OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }



    // get

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos(){
        return ResponseEntity.ok(turnoService.buscarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id){
        // confirmamos si el Turno existe

        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);

        if (turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    //post

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws BadRequestException{

        //si el odontologo o paciente no existe error

        if(odontologoService.buscarOdontologo(turno.getOdontologoId()).isPresent()
                &&pacienteService.buscarPaciente(turno.getPacienteId()).isPresent()){
            //ambos existen, puedo guardar el turno
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        else{
            throw new BadRequestException("El paciente o el odontologo no existen en la base de datos");
        }

    }

    //put

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno) {

        //Podemos encontrar un problema con el id del turno
        //Podemos encontrar un problema con el id del odontologo y/o paciente

        Optional<TurnoDTO> turnoBuscado=turnoService.buscarTurno(turno.getId());
        if (turnoBuscado.isPresent()){
            //el turno existe, podemos verificar el resto
            //PacienteService pacienteService= new PacienteService();
            if(odontologoService.buscarOdontologo(turno.getOdontologoId()).isPresent()
                    &&pacienteService.buscarPaciente(turno.getPacienteId()).isPresent()){
                //ambos existen, puedo guardar el turno
                turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("Se actualizó el turno con id= "+turno.getId());
            }
            else{
                return ResponseEntity.badRequest().body("No se puede actualizar el turno con" +
                        " id= "+turno.getId()+" ya que existe un error con el odontologo y/o " +
                        "paciente");
            }
        }
        else {
            //no existe el turno
            return ResponseEntity.badRequest().body("No encontramos el turnos que " +
                    "se quiere modificar.");
        }
    }


    //delete

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException{

        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);

        if (turnoBuscado.isPresent()){
            //existe el id con un Turno registrado

            turnoService.eliminarTurno(id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se elimino el Turno" +
                    " con id= "+id);
        }
        else{
            //no existe
            throw new ResourceNotFoundException("No se puede eliminar el turno" +
                    " con id= "+id);
        }
    }


}
