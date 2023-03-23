package cason6;

import Controlador.ControladorSocio;
import Modelo.ModeloSocio;
import Vista.VistaSocio;

public class caso6 {

    public static void main(String[] args) {

        VistaSocio vista = new VistaSocio();
        ModeloSocio modelo = new ModeloSocio();

        ControladorSocio control = new ControladorSocio(modelo, vista);
        control.iniciarControl();
    }
}
