package Controlador;

import Modelo.ModeloSocio;
import Modelo.Socios;
import Vista.VistaSocio;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ControladorSocio {

    private ModeloSocio modelo;
    private VistaSocio vista;
    private JFileChooser jfc;

    public ControladorSocio(ModeloSocio modelo, VistaSocio vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
    }

    public void iniciarControl() {
        cargarTabla();

        vista.getBtnCrear().addActionListener(l -> abrirJDlCrear());
        vista.getBtnExaminar().addActionListener(l -> seleccionarFoto());
        vista.getBtnGuardar().addActionListener(l -> crearEditar());
        vista.getBtnActualizar().addActionListener(l -> cargarTabla());
        vista.getBtnEliminar().addActionListener(l -> eliminar());
    }

    public void abrirJDlCrear() {

        vista.getjDlgSocio().setVisible(true);
        vista.getjDlgSocio().setSize(608, 520);
        vista.getjDlgSocio().setLocationRelativeTo(null);
        vista.getjDlgSocio().setName("Ingresar datos");
        vista.getjDlgSocio().setTitle("Ingresar datos");

    }

    public void seleccionarFoto() {

        vista.getLblFoto().setIcon(null);
        jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = jfc.showOpenDialog(null);

        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                Image imagen = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(vista.getLblFoto().getWidth(), vista.getLblFoto().getHeight(), Image.SCALE_DEFAULT);
                vista.getLblFoto().setIcon(new ImageIcon(imagen));
                vista.getLblFoto().updateUI();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(vista, "Error: " + ex);
            }
        }
    }

    public void cargarTabla() {
        vista.getTblSocio().setDefaultRenderer(Object.class, new ImagenTabla());//La manera de renderizar la tabla.
        vista.getTblSocio().setRowHeight(100);

        //Enlazar el modelo de tabla con mi controlador.
        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) vista.getTblSocio().getModel();
        tblModel.setNumRows(0);//limpio filas de la tabla.

        List<Socios> listap = modelo.listaSociosTabla();//Enlazo al Modelo y obtengo los datos
        Holder<Integer> i = new Holder<>(0);//Contador para las filas. 'i' funciona dentro de una expresion lambda

        listap.stream().forEach(s -> {

            tblModel.addRow(new Object[9]);//Creo una fila vacia
            vista.getTblSocio().setValueAt(s.getPer_cedula(), i.value, 0);
            vista.getTblSocio().setValueAt(s.getNombre(), i.value, 1);
            vista.getTblSocio().setValueAt(s.getDireccion(), i.value, 2);
            vista.getTblSocio().setValueAt(s.getTelefono(), i.value, 3);
            vista.getTblSocio().setValueAt(s.getDiscoTaxi(), i.value, 4);
            vista.getTblSocio().setValueAt(s.getPlacaTaxi(), i.value, 5);
            vista.getTblSocio().setValueAt(s.getMarcaTaxi(), i.value, 6);

            Image foto = s.getImagen();
            if (foto != null) {

                Image nimg = foto.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon icono = new ImageIcon(nimg);
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setIcon(icono);
                vista.getTblSocio().setValueAt(new JLabel(icono), i.value, 7);

            } else {
                vista.getTblSocio().setValueAt(null, i.value, 7);
            }

            i.value++;
        });
    }

    private void crearEditar() {
        if ("Ingresar datos".equals(vista.getjDlgSocio().getName())) {

            //INSERTAR
            modelo.setPer_cedula(vista.getTxtCedula().getText());
            modelo.setNombre(vista.getTxtNombre().getText());
            modelo.setDireccion(vista.getTxtDireccion().getText());
            modelo.setTelefono(vista.getTxtTelefono().getText());
            modelo.setDiscoTaxi(vista.getTxtDisco().getText());
            modelo.setPlacaTaxi(vista.getTxtPlaca().getText());
            modelo.setMarcaTaxi(vista.getTxtMarca().getText());

            //Foto
            try {

                FileInputStream foto = new FileInputStream(jfc.getSelectedFile());
                int longitud = (int) jfc.getSelectedFile().length();

                modelo.setFoto(foto);
                modelo.setLongitud(longitud);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (modelo.crearSocio()) {

                JOptionPane.showMessageDialog(null, "Creado satisfactoriamente");
                vista.getjDlgSocio().setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo crear");
            }

        } else {

//            //EDITAR
//            modelo.setMed_codigo(Integer.parseInt(vista.getTxtCodigo().getText()));
//            modelo.setMed_nomcom(vista.getTxtNomCom().getText());
//            modelo.setMed_nomgen(vista.getTxtNomGen().getText());
//
//            java.sql.Date fechaEl = new java.sql.Date(vista.getFechaElaboracion().getDate().getTime());
//            modelo.setMed_fechaela(fechaEl);
//
//            java.sql.Date fechaEx = new java.sql.Date(vista.getFechaExpiracion().getDate().getTime());
//            modelo.setMed_fechaexp(fechaEx);
//
//            modelo.setMed_costo(Double.parseDouble(vista.getSpinnerCosto().getValue().toString()));
//            modelo.setMed_pvp(Double.parseDouble(vista.getSpinnerPVP().getValue().toString()));
//            //Foto
//            try {
//
//                FileInputStream foto = new FileInputStream(jfc.getSelectedFile());
//                int longitud = (int) jfc.getSelectedFile().length();
//
//                modelo.setFoto(foto);
//                modelo.setLongitud(longitud);
//
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            if (modelo.modificarMedicamento()) {
//
//                JOptionPane.showMessageDialog(null, "Medicamento modificado satisfactoriamente");
//                vista.getjDlgMedicamento().setVisible(false);
//            } else {
//                JOptionPane.showMessageDialog(vista, "No se pudo modificar el medicamento");
//            }
//
        }

        cargarTabla();
    }

    public void eliminar() {

        int fila = vista.getTblSocio().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                String cedula;
                cedula = vista.getTblSocio().getValueAt(fila, 0).toString();

                if (modelo.eliminar(cedula)) {
                    JOptionPane.showMessageDialog(null, "Se elimino satisfactoriamente");
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar");
                }
            }
        }
    }
}
