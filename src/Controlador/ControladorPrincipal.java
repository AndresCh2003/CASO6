/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloSocio;
import Vista.VistaPrin;
import Vista.VistaSocio;

/**
 *
 * @author Usuario
 */
public class ControladorPrincipal {
        VistaPrin vistaPrincipal;

    public ControladorPrincipal(VistaPrin vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        vistaPrincipal.setVisible(true);
    }
     public void iniciarControl() {
        vistaPrincipal.getBtnSocios().addActionListener(l -> crudSocio());
    }

         public void crudSocio() {
        //Instancio las clases del Modelo y la Vista.
        VistaSocio vista = new VistaSocio();
        ModeloSocio modelo = new ModeloSocio();

        //Agregar Vista Personas al Desktop Pane.
                vistaPrincipal.getEscritorioPrincipal().add(vista);
        ControladorSocio control = new ControladorSocio(modelo, vista);
        control.iniciarControl();//Empezamos las escuchas a los eventos de la vista, Listeners.
    }
}
