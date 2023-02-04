package Persistencia;

import Entidades.Cliente;
import java.util.List;

public final class ClienteDAO extends DAO<Cliente>{

    @Override
    public void guardar(Cliente cliente) {
        super.guardar(cliente);
    }

    public void eliminar(Long documento) throws Exception {
        Cliente cliente = buscarPorDNI(documento);
        super.eliminar(cliente);
    }

    public Cliente buscarPorDNI(Long documento) throws Exception {
        conectar();
        Cliente cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.documento LIKE :documento").setParameter("documento", documento).getSingleResult();
        desconectar();
        return cliente;
    }
    
    public Cliente buscarPorTelefono(String telefono) throws Exception {
        conectar();
        Cliente cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.telefono LIKE :telefono").setParameter("telefono", telefono).getSingleResult();
        desconectar();
        return cliente;
    }

    public void editarCliente(Cliente cliente) throws Exception {
        super.editar(cliente);
    }

    public List<Cliente> listarTodos() throws Exception {
        conectar();
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c").getResultList();
        desconectar();
        return clientes;
    }
}
