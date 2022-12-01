package com.PabloNovoa.SistemaGestionTurnos.service;

import com.PabloNovoa.SistemaGestionTurnos.entity.Odontologo;
import com.PabloNovoa.SistemaGestionTurnos.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    // get

    public List<Odontologo> buscarOdontologos(){
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> buscarOdontologo(Long id){
        return odontologoRepository.findById(id);
    }


    //post

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    // put

    public void actualizarOdontologo(Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }

    // delete

    public void eliminarOdontologo(Long id){
        odontologoRepository.deleteById(id);
    }



}
