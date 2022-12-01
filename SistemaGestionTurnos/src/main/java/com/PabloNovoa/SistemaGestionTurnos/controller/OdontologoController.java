package com.PabloNovoa.SistemaGestionTurnos.controller;

import com.PabloNovoa.SistemaGestionTurnos.entity.Odontologo;
import com.PabloNovoa.SistemaGestionTurnos.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    // get

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        System.out.println(odontologoService.buscarOdontologos());
        return ResponseEntity.ok(odontologoService.buscarOdontologos());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id){
        // confirmamos si el Odontologo existe

        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);

        if (odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    //post

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){

            return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));

    }

    //put

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {

        //preguntar si existe el odontologo

        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(odontologo.getId());

        if (odontologoBuscado.isPresent()) {

            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Se actualizó al odontologo con id= " + odontologo.getId());

        } else {
            return ResponseEntity.badRequest().body("No se pudo actualizar al Odontologo con id= " + odontologo.getId() +
                    " ya que el mismo no existe en la base de datos. :(");
        }
    }


    //delete

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){

        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);

        if (odontologoBuscado.isPresent()){
            //existe el id con un odontologo registrado

            odontologoService.eliminarOdontologo(id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se elimino el odontologo" +
                    " con id= "+id);
        }
        else{
            //no existe
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No existe" +
                    " el ID= "+id+" asociado a un odontologo en la base de datos.");
        }
    }




}
