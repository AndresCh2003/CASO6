/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloSocio;
import Modelo.Socio;
import Vista.VistaSocio;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Usuario
 */
public class ControladorSocio {

    ModeloSocio modelo;
    VistaSocio vista;
//    VistaPrincipal p = new VistaPrincipal();

    public ControladorSocio(ModeloSocio modelo, VistaSocio vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
//        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
//        cargarTablaDeNutricionista();
    }

    public void iniciarControl() {
        vista.getBtnCrear().addActionListener(l -> abrirJDlgSocio());
        vista.getBtnGuardar().addActionListener(l -> crearModificarSocio());
       vista.getBtnActualizar().addActionListener(l -> cargarTablaDeSocios());
        vista.getBtnModificar().addActionListener(l -> cargarDatosSocioEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarSocio());
        vista.getBtnCancelar().addActionListener(l -> cancelar());
//        vista.getBtnImprimir().addActionListener(l-> imprimirPersona());
//
       buscarRegistros();
    }

    public void abrirJDlgSocio() {
        vista.getJdlgCrearSocio().setVisible(true);
        vista.getJdlgCrearSocio().setSize(684, 475);
        vista.getJdlgCrearSocio().setLocationRelativeTo(null);
        vista.getJdlgCrearSocio().setName("Crear nuevo socio");
        vista.getJdlgCrearSocio().setTitle("Crear nuevo socio");
        cargarTablaDeSocios();
        //QUITAR VISIBILIDAD DEL CODIGO DEL Socio
        vista.getTxtCodigoSocio().setVisible(false);
//        LimpiarCampos();
    }

    public void cargarTablaDeSocios() {
        DefaultTableModel tabla = (DefaultTableModel) vista.getjTblSocios().getModel();
        tabla.setNumRows(0);

        List<Socio> socios = modelo.listaSocioTabla();
        socios.stream().forEach(s -> {
            Object[] datos = {String.valueOf(s.getSoc_cedula()), s.getSoc_nombres(), s.getSoc_telefono(), s.getSoc_placa(), s.getSoc_marca()};
            tabla.addRow(datos);
        });
    }


    public void crearModificarSocio() {

        if ("Crear nuevo socio".equals(vista.getJdlgCrearSocio().getName())) {

            //Validar datos
            ModeloSocio socio = new ModeloSocio();
            if (socio.validarRepetidos(vista.getTxtCedula().getText()) == 0) {

                socio.setSoc_cedula(vista.getTxtCedula().getText());
                socio.setSoc_nombres(vista.getTxtNombres().getText());
                socio.setSoc_telefono(vista.getTxtTelefono().getText());
                socio.setSoc_direccion(vista.getTxtDireccion().getText());


                socio.setSoc_disco(vista.getTxtDisco().getText());
               socio.setSoc_placa(vista.getTxtPlaca().getText());
                socio.setSoc_marca(vista.getTxtMarca().getText());

                    if (modelo.crearSocio()) {
                        JOptionPane.showMessageDialog(null, "Se creo exitosamente");
                        vista.getJdlgCrearSocio().dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo registrar");
                    }

            } else {
                JOptionPane.showMessageDialog(null, "El numero de cedula ya se encuentra registrado en la base de datos");
            }
        } else {
            ///Validar Datos
            ModeloSocio socio = new ModeloSocio();
                socio.setSoc_cedula(vista.getTxtCedula().getText());
                socio.setSoc_nombres(vista.getTxtNombres().getText());
                socio.setSoc_telefono(vista.getTxtTelefono().getText());
                socio.setSoc_direccion(vista.getTxtDireccion().getText());


                socio.setSoc_disco(vista.getTxtDisco().getText());
               socio.setSoc_placa(vista.getTxtPlaca().getText());
                socio.setSoc_marca(vista.getTxtMarca().getText());

            if (socio.modificarsocio()) {
                System.out.println("Socio modificado");
                modelo.setSoc_codigo(Integer.parseInt(vista.getTxtCodigoSocio().getText()));

                if (modelo.modificarsocio()) {
                    JOptionPane.showMessageDialog(null, "La información se modificó satisfactoriamente");
                    vista.getJdlgCrearSocio().setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo modificar la información");
                }
            } else {
                System.out.println("Error modificando persona");
            }
        }
        cargarTablaDeSocios();
    }
    
    
    
    public void cargarDatosSocioEnTXT() {
        int fila = vista.getjTblSocios().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            //Abrir jDialog de campos de Docente
            vista.getJdlgCrearSocio().setVisible(true);
            vista.getJdlgCrearSocio().setName("Modificar socio");
            vista.getJdlgCrearSocio().setSize(820, 562);
            vista.getJdlgCrearSocio().setLocationRelativeTo(null);
            vista.getJdlgCrearSocio().setTitle("Modificar socio");

            //Quitar visibilidad
            vista.getTxtCodigoSocio().setVisible(false);

            List<Socio> listan = modelo.listaSocioTabla();

            listan.stream().forEach(socios -> {

                if (socios.getSoc_cedula().equals(vista.getjTblSocios().getValueAt(fila, 1).toString())) {
                    vista.getTxtCedula().setText(socios.getSoc_cedula());
                    vista.getTxtNombres().setText(socios.getSoc_nombres());
                    vista.getTxtTelefono().setText(socios.getSoc_telefono());
                    vista.getTxtDireccion().setText(socios.getSoc_direccion());
                    vista.getTxtDisco().setText(socios.getSoc_disco());
                    vista.getTxtPlaca().setText(socios.getSoc_placa());
                    vista.getTxtMarca().setText(socios.getSoc_marca());

                }
            });
        }
    }
    
    
    public void eliminarSocio() {
        int fila = vista.getjTblSocios().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigoSocio;
                codigoSocio = Integer.parseInt(vista.getjTblSocios().getValueAt(fila, 0).toString());

                if (modelo.eliminarsocio(codigoSocio)) {
                    JOptionPane.showMessageDialog(null, "El registro se elimino satisfactoriamente");
                    cargarTablaDeSocios();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "El registro no se pudo eliminar");
                }
            }
        }
    }
    
        public void buscarRegistros() {

        KeyListener eventoTeclado = new KeyListener() {//Crear un objeto de tipo keyListener(Es una interface) por lo tanto se debe implementar sus metodos abstractos

            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {

                //CODIGO PARA FILTRAR LOS DATOS DIRECTAMENTE DE LA TABLA. NO ELIMINAR. SI FUNCIONA. ES MUY IMPORTANTE
                TableRowSorter<DefaultTableModel> filtrar;

                DefaultTableModel tabla = (DefaultTableModel) vista.getjTblSocios().getModel();

                //vista.getTablaconduccion().setAutoCreateRowSorter(true);
                filtrar = new TableRowSorter<>(tabla);
                vista.getjTblSocios().setRowSorter(filtrar);

                try {

                    filtrar.setRowFilter(RowFilter.regexFilter(vista.getTxtBuscar().getText())); //Se pasa como parametro el campo de donde se va a obtener la informacion y el (3) es la columna con la cual va a buscar las coincidencias
                } catch (Exception ex) {
                    System.out.println("Error: " + ex);
                }
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }
    public void cancelar (){
        vista.getJdlgCrearSocio().setVisible(false);
    }

    
        public void bloquearcampos() {
        vista.getTxtCedula().setEditable(false);
    }
}
