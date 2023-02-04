package Servicios;

import Entidades.Editorial;
import Persistencia.EditorialDAO;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class EditorialServicios {

    Scanner leer = new Scanner(System.in).useDelimiter("\n").useLocale(Locale.US);
    EditorialDAO ad = new EditorialDAO();

    public Editorial crearEditorial() {
        Editorial editorial = new Editorial();
        
        do {
            System.out.print("Ingrese el nombre de la editorial: ");
            String nombre = leer.next();
            try {
                if (ad.buscarPorNombre(nombre) != null) {
                    System.out.println("El nombre ya existe!");
                    nombre = "";
                }
            } catch (Exception e) {
            }
            editorial.setNombre(nombre);
        } while (editorial.getNombre().isEmpty());
        
        editorial.setAlta(true);
        ad.guardar(editorial);
        
        return editorial;
    }

    public Editorial buscarPorNombre() {
        try {
            String nombre;
            do {
                System.out.print("Ingrese el nombre de la editorial que desea buscar: ");
                nombre = leer.next();
                return ad.buscarPorNombre(nombre);
            } while (nombre.isEmpty());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean eliminarPorNombre() {
        try {
            String nombre;
            do {
                System.out.print("Ingrese el nombre de la editorial que desea eliminar: ");
                nombre = leer.next();
                ad.eliminar(nombre);
            } while (nombre.isEmpty());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Editorial> listarEditorial() {
        try {
            return ad.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Editorial modificarEditorial() {
        try {
            Editorial ed = new Editorial();
            do {
                System.out.print("Ingrese el nombre de la editorial que desea modificar: ");
                ed = ad.buscarPorNombre(leer.next());
            } while (ed.getNombre().isEmpty());
            do {
                System.out.print("Ingrese el nuevo nombre de la editorial: ");
                ed.setNombre(leer.next());
                ad.editarAutor(ed);
            } while (ed.getNombre().isEmpty());
            return ed;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
