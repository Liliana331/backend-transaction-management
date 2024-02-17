package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import udemy.mysql.jdbc.MostrarConsulta;

/*
El manejo de transacciones implica ejecutar varias isntrucciones en un mismo bloque, lo que se pretende es que si alguna consulta arroja una
excepcion, entonces no se guarden los cambios en la BD de ninguna consulta hecha en ese grupo.

Para realizar la prueba se requiere que el los try-catch definidos en las sentencias no se ejecuten sino que se propague la excepcion para poder
ver el resultado

la confirmacion del commit se hara externamente, por lo que el cierre de conexion se hara unicamente si no se envia este parametro en la clase.
 
Se debe ademas quitar los bloques catch de las sentencias pq sino se capturan en las consultas y no se capturara aca para hacer el rollback y no hacer los cambios
asi que se deja solo el TRY y FINALLY es las consultas de la BD, el catch se quita 
*/
public class TestManejoPersonas {

    public static void main(String[] args) {

        Connection conexionTransacc = null;

        try {
            conexionTransacc = Conexion.getConnection();
            if (conexionTransacc.getAutoCommit()) {
                conexionTransacc.setAutoCommit(false); //para que no se haga el autocommit
            }

            PersonaDAO personaDao = new PersonaDAO(conexionTransacc); //para aplicar este concepto transaccional

            //consultas en la BD
            List<Persona> listaPersonas = personaDao.seleccionar();
            MostrarConsulta.imprimir(listaPersonas);

            //insertar en la BD
            Persona insertarPersona = new Persona("Diana", "Artunduaga", "diana@email.com", 789654);
            personaDao.insertar(insertarPersona);

            //actualizar en la BD
            //campo con extension mayor al permitido para que arroje error en la BD y ver que se hace rollback y no se ejecuta nada del bloque
//            Persona actualizarPersona = new Persona("Pedro Antio del Perpetuo Socorro", "Zapata", "pedrito@email.com", 789654);
            
            //campo que se ejeucta bien
            Persona actualizarPersona = new Persona("Pedro Antio", "Zapata", "pedrito@email.com", 789654);
            personaDao.actualizar(actualizarPersona, 2);
            
            conexionTransacc.commit();
            System.out.println("Se hace el Commit de las consultas");

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al RollBack");
            try {
                conexionTransacc.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }
    }

}
