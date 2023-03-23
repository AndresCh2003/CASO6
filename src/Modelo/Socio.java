/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Image;
import java.io.FileInputStream;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Socio {
    private int soc_codigo;
    private String soc_cedula;
    private String soc_nombres;
    private String soc_direccion;
    private String soc_telefono;
    private String soc_disco;
    private String soc_placa;
    private String soc_marca;  
    private String soc_estado;

    public Socio() {
    }

    
    public Socio(int soc_codigo, String soc_cedula, String soc_nombres, String soc_direccion, String soc_telefono, String soc_disco, String soc_placa, String soc_marca, String soc_estado) {
        this.soc_codigo = soc_codigo;
        this.soc_cedula = soc_cedula;
        this.soc_nombres = soc_nombres;
        this.soc_direccion = soc_direccion;
        this.soc_telefono = soc_telefono;
        this.soc_disco = soc_disco;
        this.soc_placa = soc_placa;
        this.soc_marca = soc_marca;
        this.soc_estado = soc_estado;
    }


    public String getSoc_nombres() {
        return soc_nombres;
    }

    public void setSoc_nombres(String soc_nombres) {
        this.soc_nombres = soc_nombres;
    }


    public int getSoc_codigo() {
        return soc_codigo;
    }

    public void setSoc_codigo(int soc_codigo) {
        this.soc_codigo = soc_codigo;
    }

    public String getSoc_cedula() {
        return soc_cedula;
    }

    public void setSoc_cedula(String soc_cedula) {
        this.soc_cedula = soc_cedula;
    }

    public String getSoc_direccion() {
        return soc_direccion;
    }

    public void setSoc_direccion(String soc_direccion) {
        this.soc_direccion = soc_direccion;
    }

    public String getSoc_telefono() {
        return soc_telefono;
    }

    public void setSoc_telefono(String soc_telefono) {
        this.soc_telefono = soc_telefono;
    }

    public String getSoc_disco() {
        return soc_disco;
    }

    public void setSoc_disco(String soc_disco) {
        this.soc_disco = soc_disco;
    }

    public String getSoc_placa() {
        return soc_placa;
    }

    public void setSoc_placa(String soc_placa) {
        this.soc_placa = soc_placa;
    }

    public String getSoc_marca() {
        return soc_marca;
    }

    public void setSoc_marca(String soc_marca) {
        this.soc_marca = soc_marca;
    }

    public String getSoc_estado() {
        return soc_estado;
    }

    public void setSoc_estado(String soc_estado) {
        this.soc_estado = soc_estado;
    }

    
}
