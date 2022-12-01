package com.PabloNovoa.SistemaGestionTurnos.exceptions;

public class BadRequestException extends Exception{

    public BadRequestException(String mensaje){
        super(mensaje);
    }
}
