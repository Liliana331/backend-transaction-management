package datos;

import domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection conexionTransaccional;

    public static final String SQL_SELECT = "SELECT id_usuario, usuario, contrasenia FROM udemy.usuario";
    public static final String SQL_INSERT = "INSERT INTO udemy.usuario (usuario, contrasenia) VALUES (?, ?)";
    public static final String SQL_UPDATE = "UPDATE udemy.usuario SET usuario = ?, contrasenia = ? WHERE id_usuario = ?";
    public static final String SQL_DELETE = "DELETE FROM udemy.usuario WHERE id_usuario = ?";

    public UsuarioDAO() {
    }

    public UsuarioDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<Usuario> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>(); //lista de usuarios
        Usuario usuario = null;

        try {
            //se establece conexion con BD
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            //PreparedStatement se utiliza para representar y manejar consultas SQL parametrizadas. Permite precompilar una consulta SQL con parámetros y luego ejecutarla varias veces con diferentes valores para esos parámetros
            pstm = conn.prepareStatement(SQL_SELECT);
            //ejecuta la consulta
            rs = pstm.executeQuery();

            //se evalua cada resultado de la BD y se crea un objeto Usuario con los campos obtenidos
            //se recorrido por cada fila de la BD
            while (rs.next()) {
                //se extrae cada valor de la BD y se asigna a un campo en especifico
                int idUsuario = rs.getInt("id_usuario");
                String user = rs.getString("usuario");
                String contrasenia = rs.getString("contrasenia");

                //se toman esos valores de la BD para construir un usuario puntual
                usuario = new Usuario(idUsuario, user, contrasenia);

                //finalmente se adiciona ese usuario a la lista Usuarios para almacenar toda la informacion
                usuarios.add(usuario);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(pstm);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }

        }
        return usuarios;
    }

    public int insertar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;
        int registrosInsertados = 0;

        try {
            //se establece conexion con BD
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_INSERT);

            //del objeto usuario que llega como argumento se extraen los valores y se establecen para relacionarlos con los de la BD
            pstm.setString(1, usuario.getUsuario());
            pstm.setString(2, usuario.getPassword());

            //finalmente se ejecuta la consulta con executeUpdate pq actualiza información en BD
            registrosInsertados = pstm.executeUpdate();
        } finally {
            Conexion.close(pstm);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }

        }
        return registrosInsertados;
    }

    public int actualizar(Usuario usuario) throws SQLException {
        int registrosActualizados = 0;
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_UPDATE);

            pstm.setString(1, usuario.getUsuario());
            pstm.setString(2, usuario.getPassword());
            pstm.setInt(3, usuario.getIdUsuario());

            registrosActualizados = pstm.executeUpdate();
        } finally {
            Conexion.close(pstm);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }

        }

        return registrosActualizados;
    }

    public int eliminar(Usuario usuario) throws SQLException {
        int registrosEliminados = 0;
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            pstm = conn.prepareStatement(SQL_DELETE);

            pstm.setInt(1, usuario.getIdUsuario());

            registrosEliminados = pstm.executeUpdate();
        } finally {
            Conexion.close(pstm);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return registrosEliminados;
    }

}
