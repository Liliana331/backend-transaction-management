
package domain;

/*
Persona: es una clase dominio o entidad, ya que tiene representacion en BD
a la clase que hace las operaciones como: seleccionar, eliminar, insertar o actualizar sobre la clase de entidad se conoce como DAO.
DAO: Data Access Object, clase que realiza las operaciones sobre la clase de entidad, se crea una clase por cada clase entidad

Es la representacion de la BD, los campos utilizados
se hace sobre carga de constructores segun la necesidad ejemplo: 
    * De id pq para borrar o actualizar se usa este campo
    * Los campos: nombre, apellido, email y tel pq al insertar no se envia el id (este es autoincrementable)
    * Todos campos: cuando se necesite modificar un registro
    * Los metodos get y set para cuando se necesite actualizar un campo de manera individual
    * toString(): para enviar a imprimir el estado del objeto en cualquier momento
*/

public class Persona {
    
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private int telefono;

    public Persona() {
    }

    //para borrar o actualizar un campo
    public Persona(int id) {
        this.id = id;
    }

    //para insertar
    public Persona(String nombre, String apellido, String email, int telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    //cuando se necesiten modificar todos los campos de una vez
    public Persona(int id, String nombre, String apellido, String email, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", telefono=" + telefono + '}';
    }
    
    
}
