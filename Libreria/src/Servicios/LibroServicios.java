package Servicios;

import Entidades.Editorial;
import Entidades.Autor;
import Entidades.Libro;
import Persistencia.LibroDAO;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class LibroServicios {

    Scanner leer = new Scanner(System.in).useDelimiter("\n").useLocale(Locale.US);
    LibroDAO li = new LibroDAO();
    AutorServicios au = new AutorServicios();
    EditorialServicios ed = new EditorialServicios();

    public Libro crearLibro() {
        Libro libro = new Libro();
        try {
            do {
                System.out.print("Ingrese el titulo del libro: ");
                String titulo = leer.next();
                try {
                    if (li.buscarPorTitulo(titulo) != null) {
                        System.out.println("El titulo ya existe!");
                        titulo = "";
                    }
                } catch (Exception e) {
                }
                libro.setTitulo(titulo);
            } while (libro.getTitulo().isEmpty());
            do {
                System.out.print("Ingrese el ISBN del libro: ");
                Long ISBN = leer.nextLong();
                try {
                    if (li.buscarPorISBN(ISBN) != null) {
                        System.out.println("El ISBN ya existe!");
                        ISBN = 0L;
                    }
                } catch (Exception e) {
                }
                libro.setISBN(ISBN);
            } while (libro.getISBN() == 0);
            do {
                System.out.print("Ingrese el año del libro: ");
                libro.setAnio(leer.nextInt());
            } while (libro.getAnio() == 0);
            do {
                System.out.print("Ingrese la cantidad de ejemplares: ");
                libro.setEjemplares(leer.nextInt());
            } while (libro.getEjemplares() < 0);
            libro.setEjemplaresPrestados(0);
            libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
            libro.setAlta(true);
            Autor autorLibro = null;
            do {
                autorLibro = au.buscarPorNombre();
                if (autorLibro == null) {
                    System.out.println("El autor no fue encontrado, por favor genere uno para el libro: ");
                    autorLibro = au.crearAutor();
                }
            } while (autorLibro == null);
            libro.setAutor(autorLibro);
            Editorial editorialLibro = null;
            do {
                editorialLibro = ed.buscarPorNombre();
                if (editorialLibro == null) {
                    System.out.println("La editorial no fue encontrada, por favor genere una para el libro: ");
                    editorialLibro = ed.crearEditorial();
                }
            } while (editorialLibro == null);
            libro.setEditorial(editorialLibro);
            li.guardar(libro);
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Libro buscarPorTitulo() {
        try {
            String nombre;
            do {
                System.out.print("Ingrese el titulo del libro que desea buscar: ");
                nombre = leer.next();
                return li.buscarPorTitulo(nombre);
            } while (nombre.isEmpty());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Libro buscarPorISBN() {
        try {
            Long nombre;
            do {
                System.out.print("Ingrese el ISBN del libro que desea buscar: ");
                nombre = leer.nextLong();
                return li.buscarPorISBN(nombre);
            } while (nombre == 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean eliminarPorISBN() {
        try {
            Long ISBN;
            do {
                System.out.print("Ingrese el titulo del libro que desea eliminar: ");
                ISBN = leer.nextLong();
                li.eliminar(ISBN);
            } while (ISBN == 0);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Libro> listarLibros() {
        try {
            return li.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Libro modificarLibro() {
        Libro libro = new Libro();
        try {
            do {
                System.out.print("Ingrese el ISBN del libro que desea modificar: ");
                libro = li.buscarPorISBN(leer.nextLong());
            } while (libro.getISBN() == 0);
            String opcion;
            System.out.println("Desea modificar el titulo del libro? S/N");
            opcion = leer.next();
            if (opcion.equalsIgnoreCase("S")) {
                do {
                    System.out.print("Ingrese el titulo del libro: ");
                    libro.setTitulo(leer.next());
                } while (libro.getTitulo().isEmpty());
            }
            System.out.println("Desea modificar el ISBN del libro? S/N");
            opcion = leer.next();
            if (opcion.equalsIgnoreCase("S")) {
                do {
                    System.out.print("Ingrese el ISBN del libro: ");
                    libro.setISBN(leer.nextLong());
                } while (libro.getISBN() == 0);
            }
            System.out.println("Desea modificar el año del libro? S/N");
            opcion = leer.next();
            if (opcion.equalsIgnoreCase("S")) {
                do {
                    System.out.print("Ingrese el año del libro: ");
                    libro.setAnio(leer.nextInt());
                } while (libro.getAnio() == 0);
            }
            System.out.println("Desea modificar la cantidad de ejemplares del libro? S/N");
            opcion = leer.next();
            if (opcion.equalsIgnoreCase("S")) {
                do {
                    System.out.print("Ingrese la cantidad de ejemplares: ");
                    libro.setEjemplares(leer.nextInt());
                } while (libro.getEjemplares() < 0);
            }
            System.out.println("Desea modificar el estado del libro? S/N");
            opcion = leer.next();
            if (opcion.equalsIgnoreCase("S")) {
                System.out.println("Ingrese la letra A para dar de Alta:");
                System.out.println("Ingrese la letra B para dar de Baja:");
                do {
                    opcion = leer.next();
                } while (!opcion.equalsIgnoreCase("A") && !opcion.equalsIgnoreCase("B"));
            }
            if (opcion.equalsIgnoreCase("A")) {
                libro.setAlta(true);
            } else {
                libro.setAlta(false);
            }
            Autor autorLibro = null;
            System.out.println("Desea modificar el nombre del Autor? S/N");
            opcion = leer.next();
            if (opcion.equalsIgnoreCase("S")) {
                do {
                    autorLibro = au.buscarPorNombre();
                    if (autorLibro == null) {
                        System.out.println("El autor no fue encontrado, por favor genere uno para el libro: ");
                        autorLibro = au.crearAutor();
                    }
                } while (autorLibro == null);
                libro.setAutor(autorLibro);
            }
            Editorial editorialLibro = null;
            System.out.println("Desea modificar el nombre de la Editorial? S/N");
            opcion = leer.next();
            if (opcion.equalsIgnoreCase("S")) {
                do {
                    editorialLibro = ed.buscarPorNombre();
                    if (editorialLibro == null) {
                        System.out.println("La editorial no fue encontrada, por favor genere una para el libro: ");
                        editorialLibro = ed.crearEditorial();
                    }
                } while (editorialLibro == null);
                libro.setEditorial(editorialLibro);
            }
            li.EditarLibro(libro);
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Libro> buscarLibrosPorAutor() {
        Autor autorLibro = null;
        do {
            autorLibro = au.buscarPorNombre();
            if (autorLibro == null) {
                System.out.println("El autor no fue encontrado, por favor ingrese un autor que se encuentre disponible: ");
            }
        } while (autorLibro == null);
        return li.buscarLibrosPorAutor(autorLibro);
    }

    public List<Libro> buscarLibrosPorEditorial() {
        Editorial editorialLibro = null;
        do {
            editorialLibro = ed.buscarPorNombre();
            if (editorialLibro == null) {
                System.out.println("El autor no fue encontrado, por favor ingrese un autor que se encuentre disponible: ");
            }
        } while (editorialLibro == null);
        return li.buscarLibrosPorEditorial(editorialLibro);
    }
}
