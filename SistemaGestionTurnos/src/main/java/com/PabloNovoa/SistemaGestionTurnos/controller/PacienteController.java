package com.PabloNovoa.SistemaGestionTurnos.controller;

import com.PabloNovoa.SistemaGestionTurnos.entity.Paciente;
import com.PabloNovoa.SistemaGestionTurnos.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // get

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.buscarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){
        // confirmamos si el paciente existe

        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);

        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/buscar/correo/{email}")
    public ResponseEntity<Paciente> buscarPacientePorCorreo(@PathVariable String email){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorCorreo(email);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //post
    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        // verifico que el correo no exista

        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorCorreo(paciente.getEmail());

        if (pacienteBuscado.isPresent()){
            // si existe un paciente con el email enviado

            return ResponseEntity.badRequest().build();

        }
        else {
            return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
        }
    }

    //put

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        //preguntar si existe el paciente
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizó al paciente con id= " + paciente.getId());

        } else {
            return ResponseEntity.badRequest().body("No se pudo actualizar al paciente con id= " + paciente.getId() +
                    " ya que el mismo no existe en la base de datos. :(");
        }
    }


    //delete

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()){
            //existe el id con un paciente registrado
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se elimino el paciente" +
                    " con id= "+id);
        }
        else{
            //no existe

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No existe" +
                    " el ID= "+id+" asociado a un paciente en la base de datos.");
        }
    }


}
