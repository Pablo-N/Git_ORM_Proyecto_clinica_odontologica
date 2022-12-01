package com.PabloNovoa.SistemaGestionTurnos.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_odontologo")
    private Odontologo odontologo;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @Column
    private LocalDate fecha;

    // Constructor

    public Turno() {
    }

    public Turno(Odontologo odontologo, Paciente paciente, LocalDate fecha) {
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fecha = fecha;
    }

    public Turno(Long id, Odontologo odontologo, Paciente paciente, LocalDate fecha) {
        this.id = id;
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fecha = fecha;
    }

    // getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
