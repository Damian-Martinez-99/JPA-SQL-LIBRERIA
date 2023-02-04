package Persistencia;

import Entidades.Autor;
import java.util.List;

public final class AutorDAO extends DAO<Autor> {

    @Override
    public void guardar(Autor autor) {
        super.guardar(autor);
    }

    public void eliminar(String nombre) throws Exception {
        Autor autor = buscarPorNombre(nombre);
        super.eliminar(autor);
    }

    public Autor buscarPorNombre(String nombre) throws Exception {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre").setParameter("nombre", nombre).getResultList();
        desconectar();
        return autores.get(0);
    }

    public void editarAutor(Autor autor) throws Exception {
        super.editar(autor);
    }

    public List<Autor> listarTodos() throws Exception {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a").getResultList();
        desconectar();
        return autores;
    }
}
