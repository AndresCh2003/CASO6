/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ModeloSocio extends Socio {

    ConexionPG conpg = new ConexionPG();

    
    public boolean crearSocio() {
        String sql = "INSERT INTO socio(soc_cedula,soc_nombres,soc_direccion,soc_telefono,soc_disco ,soc_placa ,soc_marca ,soc_estado)VALUES (" + getSoc_cedula() + ", " + getSoc_nombres() + ", " + getSoc_direccion() + ", " + getSoc_telefono() + ", " + getSoc_disco() + ", " + getSoc_placa() + ", " + getSoc_marca() + ", 'A');";

        return conpg.accion(sql);
    }

    public boolean modificarsocio() {
        String sql = "UPDATE socio SET soc_disco = " + getSoc_disco() + ", soc_nombre = '" + getSoc_nombres()+ ", soc_telefono = '" + getSoc_telefono()+  ", soc_direccion = '" + getSoc_direccion() + ", soc_placa = '" + getSoc_placa() + ", soc_marca= '" + getSoc_marca() + "' WHERE soc_codigo = " + getSoc_codigo() + ";";
        return conpg.accion(sql);
    }

    public boolean eliminarsocio(int codigoSoc) {
        String sql = "UPDATE socio SET soc_estado= 'I' where soc_cedula = " + codigoSoc + ";";

        return conpg.accion(sql);
    }

    public List<Socio> listaSocioTabla() {
        try {
            //Me retorna un "List" de "instructor"
            List<Socio> lista = new ArrayList<>();

            String sql = "select * from socio;";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear la instancia de instructor 
                Socio socios = new Socio();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                //Informacion de persona
                socios.setSoc_cedula(rs.getString("soc_cedula"));
                socios.setSoc_nombres(rs.getString("soc_nombres"));
                socios.setSoc_telefono(rs.getString("soc_telefono"));
                socios.setSoc_direccion(rs.getString("soc_direccion"));
                socios.setSoc_disco(rs.getString("soc_disco"));
                socios.setSoc_placa(rs.getString("soc_placa"));
                socios.setSoc_marca(rs.getString("soc_marca"));
                socios.setSoc_estado(rs.getString("soc_estado"));

                lista.add(socios);
            }
            rs.close();
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloSocio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
        public int validarRepetidos(String cedula) { //Metodo que sirve para validar la cantidad de cedulas existentes en la BD
        int cantidad = 0;
        try {

            String sql = "select COUNT(*) from socio where soc_cedula='" + cedula + "'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                cantidad = rs.getInt("COUNT"); //Trae la cantidad de dni repetidos

            }

            //Cierro la conexion a la BD
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ModeloSocio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cantidad;
    }

}
