package Persistencia;

import Entidades.Cliente;
import Entidades.Prestamo;
import java.util.List;

public final class PrestamoDAO extends DAO<Prestamo> {

    @Override
    public void guardar(Prestamo prestamo) {
        super.guardar(prestamo);
    }

    public void eliminar(Integer id) throws Exception {
        Prestamo prestamo = buscarPorID(id);
        super.eliminar(prestamo);
    }
    
    public List<Prestamo> buscarPorCliente(Cliente cliente) {
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p WHERE p.cliente.id = :id").setParameter("id", cliente.getId()).getResultList();
        desconectar();
        return prestamos;
    }
    
    public Prestamo buscarPorID(int id) throws Exception {
        conectar();
        Prestamo prestamo = em.find(Prestamo.class, id);
        desconectar();
        return prestamo;
    }
    
    public Prestamo buscarPorClientePorID(Cliente cliente, Integer id) {
        conectar();
        Prestamo p = (Prestamo) em.createQuery("SELECT p FROM Prestamo p WHERE p.cliente.id = :idCliente AND p.id = :id").setParameter("id", id).setParameter("idCliente", cliente.getId()).getSingleResult();
        desconectar();
        return  p;
    }
    
    public void editarPrestamo(Prestamo prestamo) throws Exception {
        super.editar(prestamo);
    }

    public List<Prestamo> listarTodos() throws Exception {
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p").getResultList();
        desconectar();
        return prestamos;
    }
}
