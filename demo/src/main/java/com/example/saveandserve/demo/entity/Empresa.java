package com.example.saveandserve.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "empresa")
public class Empresa {
    @jakarta.persistence.Id
    public String idEmpresa;
    public String nombreEmpresa;
    public String tipoEmpresa;
    public String telefonoEmpresa;
    public String emailEmpresa;
    public String suscripcion;
    public String cifEmpresa;
    public String direccionEmpresa;
    public String ciudadEmpresa;
    public String contraseñaEmpresa;
}

