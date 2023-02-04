package Servicios;

import java.util.Locale;
import java.util.Scanner;

public class MenuServicios {

    EditorialServicios editorial = new EditorialServicios();
    AutorServicios autor = new AutorServicios();
    LibroServicios libro = new LibroServicios();
    ClienteServicios cliente = new ClienteServicios();
    PrestamoServicios prestamo = new PrestamoServicios();
    Scanner leer = new Scanner(System.in).useDelimiter("\n").useLocale(Locale.US);

    public void menu() throws Exception {
        int opcion = 0;
        do {
            opcion = 0;
            System.out.println("");
            System.out.println("Ingrese una opción:");
            System.out.println("1-Opciones de la Editorial.");
            System.out.println("2-Opciones de un Autor.");
            System.out.println("3-Opciones de un Libro.");
            System.out.println("4-Opciones de un Cliente.");
            System.out.println("5-Opciones de un Prestamo.");
            System.out.println("6-Salir!");
            System.out.print("La opcion ingresada es: ");
            opcion = leer.nextInt();
            System.out.println("");
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese una opción:");
                    System.out.println("1-Crear Editorial.");
                    System.out.println("2-Buscar por nombre.");
                    System.out.println("3-Eliminar por nombre.");
                    System.out.println("4-Listar Editoriales.");
                    System.out.println("5-Modificar Editorial.");
                    System.out.print("La opcion ingresada es: ");
                    opcion = leer.nextInt();
                    switch (opcion) {
                        case 1:
                            System.out.println(editorial.crearEditorial());
                            break;
                        case 2:
                            System.out.println(editorial.buscarPorNombre());
                            break;
                        case 3:
                            System.out.println(editorial.eliminarPorNombre());
                            break;
                        case 4:
                            System.out.println(editorial.listarEditorial());
                            break;
                        case 5:
                            System.out.println(editorial.modificarEditorial());
                            break;
                        default:
                            System.out.println("Ingrese una opcion valida en el menu.");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Ingrese una opción:");
                    System.out.println("1-Crear Autor.");
                    System.out.println("2-Buscar por nombre.");
                    System.out.println("3-Eliminar por nombre.");
                    System.out.println("4-Listar Autores.");
                    System.out.println("5-Modificar Autores.");
                    System.out.print("La opcion ingresada es: ");
                    opcion = leer.nextInt();
                    switch (opcion) {
                        case 1:
                            System.out.println(autor.crearAutor());
                            break;
                        case 2:
                            System.out.println(autor.buscarPorNombre());
                            break;
                        case 3:
                            System.out.println(autor.eliminarPorNombre());
                            break;
                        case 4:
                            System.out.println(autor.listarAutores());
                            break;
                        case 5:
                            System.out.println(autor.modificarAutor());
                            break;
                        default:
                            System.out.println("Ingrese una opcion valida en el menu.");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Ingrese una opción:");
                    System.out.println("1-Crear libro.");
                    System.out.println("2-Buscar por titulo.");
                    System.out.println("3-Buscar por ISBN.");
                    System.out.println("4-Eliminar por ISBN.");
                    System.out.println("5-Listar libros.");
                    System.out.println("6-Modificar libros.");
                    System.out.println("7-Buscar libros por Autor.");
                    System.out.println("8-Buscar libros por Editorial.");
                    System.out.print("La opcion ingresada es: ");
                    opcion = leer.nextInt();
                    switch (opcion) {
                        case 1:
                            System.out.println(libro.crearLibro());
                            break;
                        case 2:
                            System.out.println(libro.buscarPorTitulo());
                            break;
                        case 3:
                            System.out.println(libro.buscarPorISBN());
                            break;
                        case 4:
                            System.out.println(libro.eliminarPorISBN());
                            break;
                        case 5:
                            System.out.println(libro.listarLibros());
                            break;
                        case 6:
                            System.out.println(libro.modificarLibro());
                            break;
                        case 7:
                            System.out.println(libro.buscarLibrosPorAutor());
                            break;
                        case 8:
                            System.out.println(libro.buscarLibrosPorEditorial());
                            break;
                        default:
                            System.out.println("Ingrese una opcion valida en el menu.");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Ingrese una opción:");
                    System.out.println("1-Crear cliente.");
                    System.out.println("2-Buscar por DNI.");
                    System.out.println("3-Eliminar por DNI.");
                    System.out.println("4-Listar clientes.");
                    System.out.println("5-Modificar clientes.");
                    System.out.print("La opcion ingresada es: ");
                    opcion = leer.nextInt();
                    switch (opcion) {
                        case 1:
                            System.out.println(cliente.crearCliente());
                            break;
                        case 2:
                            System.out.println(cliente.buscarPorDNI());
                            break;
                        case 3:
                             System.out.println(cliente.eliminarPorDNI());
                            break;
                        case 4:
                            System.out.println(cliente.listarClientes());
                            break;
                        case 5:
                            System.out.println(cliente.modificarCliente());
                            break;
                        default:
                            System.out.println("Ingrese una opcion valida en el menu.");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("Ingrese una opción:");
                    System.out.println("1-Crear prestamo.");
                    System.out.println("2-Listar prestamo por cliente.");
                    System.out.println("3-Buscar prestamo por cliente e ID del prestamo");
                    System.out.println("4-Devolucion de un libro.");
                    System.out.print("La opcion ingresada es: ");
                    opcion = leer.nextInt();
                    switch (opcion) {
                        case 1:
                            System.out.println(prestamo.crearPrestamo());
                            break;
                        case 2:
                            prestamo.listarPrestamoPorCliente();
                            break;
                        case 3:
                            System.out.println(prestamo.buscarUnPrestamoPorClientePorID());
                            break;
                        case 4:
                            prestamo.devolucion();
                            break;
                        default:
                            System.out.println("Ingrese una opcion valida en el menu.");
                            break;
                    }
                    break;
                case 6:
                    System.out.println("Ha salido de la libreria, Blas les desea un buen dia!");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida en el menu.");
                    break;
            }
        } while (opcion != 6);
    }
}
