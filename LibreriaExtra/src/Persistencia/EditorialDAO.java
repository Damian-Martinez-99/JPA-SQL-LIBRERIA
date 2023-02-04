package Persistencia;

import Entidades.Editorial;
import java.util.List;

public final class EditorialDAO extends DAO<Editorial> {

    @Override
    public void guardar(Editorial editorial) {
        super.guardar(editorial);
    }

    public void eliminar(String nombre) throws Exception {
        Editorial editorial = buscarPorNombre(nombre);
        super.eliminar(editorial);
    }
    
      public void editarAutor(Editorial editorial) throws Exception {
        super.editar(editorial);
    }

    public Editorial buscarPorNombre(String nombre) throws Exception {
        conectar();
        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre").setParameter("nombre", nombre).getResultList();desconectar();
        return editoriales.get(0);
    }

    public List<Editorial> listarTodos() throws Exception {
        conectar();
        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e").getResultList();
        desconectar();
        return editoriales;
    }
}
