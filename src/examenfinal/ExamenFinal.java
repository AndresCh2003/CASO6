/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenfinal;

import Controlador.ControladorPrincipal;
import Vista.VistaPrin;

/**
 *
 * @author Usuario
 */
public class ExamenFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         VistaPrin vistaPrincipal = new VistaPrin();

        ControladorPrincipal control = new ControladorPrincipal(vistaPrincipal);
        control.iniciarControl();
    }
    
}
