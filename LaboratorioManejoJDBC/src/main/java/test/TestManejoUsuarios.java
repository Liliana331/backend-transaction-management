package test;

import datos.Conexion;
import datos.UsuarioDAO;
import domain.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import udemy.mysql.jdbc.MostrarConsulta;

public class TestManejoUsuarios {
    
    public static void main(String[] args) {
        
        Connection transaccional = null;
        
        try {
            transaccional = Conexion.getConnection();
            if (transaccional.getAutoCommit()) {
                transaccional.setAutoCommit(false);
            }
            //crea objeto para hacer las consultas
            UsuarioDAO usuarioDao = new UsuarioDAO(transaccional);

            //insertar usuarios
            //Usuario usuarioNuevo = new Usuario("miguel@email.com", "miguelito5");
            Usuario usuarioNuevo = new Usuario("Margareth@email.com", "magola45");
            int registrosInsertados = usuarioDao.insertar(usuarioNuevo);
            System.out.println("registrosInsertados = " + registrosInsertados);

            //actualizar usuarios
            
            //Dato con mas caracteres para que genere fallo en la actualizacion
            //Usuario actualizarUsuario = new Usuario(1, "manuel1235445454847848494545121545415152@email.com", "manu78");
            //Dato correcto
            Usuario actualizarUsuario = new Usuario(1, "manuelito2@email.com", "manu78");
            int registrosActualizados = usuarioDao.actualizar(actualizarUsuario);
            System.out.println("registrosActualizados = " + registrosActualizados);

            //eliminar usuarios
            Usuario eliminarUsuario = new Usuario(54);
            int usuariosEliminados = usuarioDao.eliminar(eliminarUsuario);
            System.out.println("usuariosEliminados = " + usuariosEliminados);

            //retorna lista de la consulta y se imprime
            List<Usuario> listaUsuarios = usuarioDao.seleccionar();
            MostrarConsulta.imprimir(listaUsuarios);
            
            transaccional.commit();
            System.out.println("Se realizó el Commit");
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                transaccional.rollback();
                System.out.println("Se realizó el RollBack");
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }
        
    }
}
