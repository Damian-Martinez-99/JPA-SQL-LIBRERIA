package Persistencia;

import Entidades.Autor;
import Entidades.Editorial;
import Entidades.Libro;
import java.util.List;

public final class LibroDAO extends DAO<Libro> {

    @Override
    public void guardar(Libro libro) {
        super.guardar(libro);
    }

    public void eliminar(Long ISBN) {
        Libro libro = buscarPorISBN(ISBN);
        super.eliminar(libro);
    }

    public void editarLibro(Libro libro) throws Exception {
        super.editar(libro);
    }

    public List<Libro> listarTodos() {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l").getResultList();
        desconectar();
        return libros;
    }

    public Libro buscarPorISBN(Long ISBN) {
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.ISBN LIKE :ISBN").setParameter("ISBN", ISBN).getSingleResult();
        desconectar();
        return libro;
    }

    public Libro buscarPorTitulo(String titulo) {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo").setParameter("titulo", titulo).getResultList();
        desconectar();
        return libros.get(0);
    }

    /**
     * Revisar esta pagina para aprender mas de los JOIN en JPQL
     * https://www.baeldung.com/jpa-join-types
     */
    public List<Libro> buscarPorTituloYAutor(String titulo, String nombre) {
        conectar();
        //Opcion 1 sin JOIN
        //        List<Persona> personas = em.createQuery("SELECT p FROM Persona p WHERE p.direccion.pais LIKE :pais AND p.direccion.provincia LIKE :provincia ")
        //                .setParameter("pais", pais).setParameter("provincia", provincia).getResultList();
        //Opcion 2 con JOIN
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre LIKE :nombre AND l.titulo LIKE :titulo ").setParameter("titulo", titulo).setParameter("autor", nombre).getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> buscarLibrosPorAutor(Autor autor) {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.autor.id = :autor").setParameter("autor", autor.getId()).getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> buscarLibrosPorEditorial(Editorial editorial) {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.editorial.id LIKE :editorial").setParameter("editorial", editorial.getId()).getResultList();
        desconectar();
        return libros;
    }
}
