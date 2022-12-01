package com.PabloNovoa.SistemaGestionTurnos.controller;


import com.PabloNovoa.SistemaGestionTurnos.entity.Odontologo;
import com.PabloNovoa.SistemaGestionTurnos.entity.Turno;
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

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    // get

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        return ResponseEntity.ok(turnoService.buscarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable Long id){
        // confirmamos si el Turno existe

        Optional<Turno> turnoBuscado = turnoService.buscarTurno(id);

        if (turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    //post

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){

        return ResponseEntity.ok(turnoService.guardarTurno(turno));

    }

    //put

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) {

        //preguntar si existe el Turno

        Optional<Turno> turnoBuscado = turnoService.buscarTurno(turno.getId());

        if (turnoBuscado.isPresent()) {

            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Se actualizó al Turno con id= " + turno.getId());

        } else {
            return ResponseEntity.badRequest().body("No se pudo actualizar al Turno con id= " + turno.getId() +
                    " ya que el mismo no existe en la base de datos. :(");
        }
    }


    //delete

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){

        Optional<Turno> turnoBuscado = turnoService.buscarTurno(id);

        if (turnoBuscado.isPresent()){
            //existe el id con un Turno registrado

            turnoService.eliminarTurno(id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se elimino el Turno" +
                    " con id= "+id);
        }
        else{
            //no existe
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No existe" +
                    " el ID= "+id+" asociado a un Turno en la base de datos.");
        }
    }


}
