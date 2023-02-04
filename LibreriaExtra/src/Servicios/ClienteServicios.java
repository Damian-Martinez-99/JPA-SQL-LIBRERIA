package Servicios;

import Entidades.Cliente;
import Persistencia.ClienteDAO;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ClienteServicios {

    Scanner leer = new Scanner(System.in).useDelimiter("\n").useLocale(Locale.US);
    ClienteDAO cl = new ClienteDAO();

    public Cliente crearCliente() {
        Cliente cliente = new Cliente();
        
        do {
            System.out.print("Ingrese el DNI del cliente: ");
            Long dni = leer.nextLong();
            try {
                if (cl.buscarPorDNI(dni) != null) {
                    System.out.println("El DNI ya existe!");
                    dni = 0L;
                }
            } catch (Exception e) {
            }
            cliente.setDocumento(dni);
        } while (cliente.getDocumento() <= 1000000 && cliente.getDocumento() >= 100000000);
        
        do {
            System.out.print("Ingrese el Nombre del cliente: ");
            cliente.setNombre(leer.next());
        } while (cliente.getNombre().isEmpty());
        
        do {
            System.out.print("Ingrese el Apellido del cliente: ");
            cliente.setApellido(leer.next());
        } while (cliente.getApellido().isEmpty());
        
        do {
            System.out.print("Ingrese el Telefono del cliente: ");
            String telefono = leer.next();
            try {
                if (cl.buscarPorTelefono(telefono) != null) {
                    System.out.println("El Telefono ya existe!");
                    telefono = "";
                }
            } catch (Exception e) {
            }
            cliente.setTelefono(telefono);
        } while (cliente.getTelefono().isEmpty());
        
        cl.guardar(cliente);
        
        return cliente;
    }

    public Cliente buscarPorDNI() {
        try {
            Long dni;
            do {
                System.out.print("Ingrese el DNI del cliente que desea buscar: ");
                dni = leer.nextLong();
                return cl.buscarPorDNI(dni);
            } while (dni <= 1000000 && dni >= 100000000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean eliminarPorDNI() {
        try {
            Long dni;
            do {
                System.out.print("Ingrese el DNI del cliente que desea eliminar: ");
                dni = leer.nextLong();
            } while (dni >= 1000000 && dni <= 100000000);
            cl.eliminar(dni);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Cliente> listarClientes() {
        try {
            return cl.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cliente modificarCliente() {
        Cliente cli = new Cliente();
        
        try {
            do {
                System.out.print("Ingrese el DNI del cliente que desea modificar: ");
                cli = cl.buscarPorDNI(leer.nextLong());
            } while (cli.getDocumento() >= 1000000 && cli.getDocumento() <= 100000000);
            
            String opcion;
            System.out.println("Desea modificar el DNI del cliente? S/N");
            opcion = leer.next();
            if (opcion.equalsIgnoreCase("S")) {
                do {
                    System.out.print("Ingrese el DNI del Cliente: ");
                    cli.setDocumento(leer.nextLong());
                } while (cli.getDocumento() >= 1000000 && cli.getDocumento() <= 100000000);
            }
            
            System.out.println("Desea modificar el nombre del cliente? S/N");
            opcion = leer.next();
            if (opcion.equalsIgnoreCase("S")) {
                do {
                    System.out.print("Ingrese el nombre del cliente: ");
                    cli.setNombre(leer.next());
                } while (cli.getNombre().isEmpty());
            }
            
            System.out.println("Desea modificar el apellido del cliente? S/N");
            opcion = leer.next();
            if (opcion.equalsIgnoreCase("S")) {
                do {
                    System.out.print("Ingrese el apellido del cliente: ");
                    cli.setApellido(leer.next());
                } while (cli.getApellido().isEmpty());
            }
            
            System.out.println("Desea modificar el numero telefonico del cliente? S/N");
            opcion = leer.next();
            if (opcion.equalsIgnoreCase("S")) {
                do {
                    System.out.print("Ingrese el numero telefonico del cliente: ");
                    cli.setTelefono(leer.next());
                } while (cli.getTelefono().isEmpty());
            }
            
            cl.editarCliente(cli);
            return cli;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

