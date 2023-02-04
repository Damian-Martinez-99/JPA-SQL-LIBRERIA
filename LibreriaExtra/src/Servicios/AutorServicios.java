package Servicios;

import Entidades.Autor;
import Persistencia.AutorDAO;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AutorServicios {

    Scanner leer = new Scanner(System.in).useDelimiter("\n").useLocale(Locale.US);
    AutorDAO ad = new AutorDAO();

    public Autor crearAutor() {
        Autor autor = new Autor();

        do {
            System.out.print("Ingrese el nombre del autor: ");
            String nombre = leer.next();
            try {
                if (ad.buscarPorNombre(nombre) != null) {
                    System.out.println("El nombre ya existe!");
                    nombre = "";
                }
            } catch (Exception e) {
            }
            autor.setNombre(nombre);
        } while (autor.getNombre().isEmpty());

        autor.setAlta(true);
        ad.guardar(autor);

        return autor;
    }

    public Autor buscarPorNombre() {
        try {
            String nombre;
            do {
                System.out.print("Ingrese el nombre del autor que desea buscar: ");
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
                System.out.print("Ingrese el nombre del autor que desea eliminar: ");
                nombre = leer.next();
                ad.eliminar(nombre);
            } while (nombre.isEmpty());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Autor> listarAutores() {
        try {
            return ad.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Autor modificarAutor() {
        try {
            Autor au = new Autor();
            
            do {
                System.out.print("Ingrese el nombre del autor que desea modificar: ");
                au = ad.buscarPorNombre(leer.next());
            } while (au.getNombre().isEmpty());
            
            do {
                System.out.print("Ingrese el nuevo nombre del autor: ");
                au.setNombre(leer.next());
                ad.editarAutor(au);
            } while (au.getNombre().isEmpty());
            
            return au;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
