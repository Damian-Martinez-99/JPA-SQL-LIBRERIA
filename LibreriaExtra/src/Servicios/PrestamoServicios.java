package Servicios;

import Entidades.Cliente;
import Entidades.Prestamo;
import Persistencia.PrestamoDAO;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PrestamoServicios {

    Scanner leer = new Scanner(System.in).useDelimiter("\n").useLocale(Locale.US);
    LibroServicios libro = new LibroServicios();
    ClienteServicios cliente = new ClienteServicios();
    PrestamoDAO presta = new PrestamoDAO();

    public Prestamo crearPrestamo() throws Exception {
        Prestamo prestamo = new Prestamo();

        prestamo.setLibro(libro.prestarmoLibro());

        Date fechaActual = new Date();
        int dia;
        int mes;
        int anio;
        do {
            System.out.print("Ingrese un año: ");
            do {
                anio = leer.nextInt();
            } while (anio > 2023);

            System.out.print("Ingrese un mes: ");
            do {
                mes = leer.nextInt();
            } while (mes > 12 && mes < 1);

            if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                do {
                    System.out.print("Ingrese un dia entre 1 y 31: ");
                    dia = leer.nextInt();
                } while (dia > 31 && dia < 1);

            } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                do {
                    System.out.print("Ingrese un dia entre 1 y 30: ");
                    dia = leer.nextInt();
                } while (dia > 30 && dia < 1);

            } else {
                if (anio % 4 == 0) {
                    do {
                        System.out.print("Ingrese un dia entre 1 y 29: ");
                        dia = leer.nextInt();
                    } while (dia > 29 && dia < 1);

                } else {
                    do {
                        System.out.print("Ingrese un dia entre 1 y 28: ");
                        dia = leer.nextInt();
                    } while (dia > 28 && dia < 1);
                }
            }

            prestamo.setFechaPrestamo(new Date(anio - 1900, mes - 1, dia));
            if (prestamo.getFechaPrestamo().getTime() > fechaActual.getTime()) {
                System.out.println("Ingrese una fecha valida.");
            }
        } while (prestamo.getFechaPrestamo().getTime() > fechaActual.getTime());

        System.out.println("La inicial fecha del prestamo es: " + prestamo.getFechaPrestamo());
        //FECHA DEL PRESTAMO HASTA ACA

        int dias;
        do {
            System.out.print("Ingrese la cantidad de dias que desea tener el libro (maximo 31 dias): ");
            try {
                dias = leer.nextInt();
            } catch (Exception e) {
                dias = 0;
            }
        } while (dias >= 31 && dias <= 0);

        Calendar calendario = new GregorianCalendar();
        calendario.setTime(prestamo.getFechaPrestamo());
        calendario.add(Calendar.DATE, dias);
        prestamo.setFechaDevolucion(calendario.getTime());
        System.out.println("La fecha de devolucion es: " + prestamo.getFechaDevolucion());
        //FECHA DE DEVOLUCION HASTA ACA

        Cliente persona = new Cliente();
        try {
            persona = cliente.buscarPorDNI();
        } catch (Exception e) {
            persona = null;
        }

        if (persona == null) {
            prestamo.setCliente(cliente.crearCliente());
        } else {
            prestamo.setCliente(persona);
        }

        presta.guardar(prestamo);

        return prestamo;
    }

    public void listarPrestamoPorCliente() {
        Cliente persona = cliente.buscarPorDNI();
        List<Prestamo> prestamos = presta.buscarPorCliente(persona);
        for (Prestamo prestamo : prestamos) {
            System.out.println("Cliente: " + prestamo.getCliente().getNombre() + ", ID: " + prestamo.getCliente().getId());
            System.out.println("Nombre del Libro: " + prestamo.getLibro().getTitulo() + ", ID: " + prestamo.getLibro().getId());
            System.out.println("ID del Prestamo: " + prestamo.getId() + ", Fecha del prestamo: " + prestamo.getFechaPrestamo() + ", Fecha de devolucion: " + prestamo.getFechaDevolucion());
        }
    }

    public Prestamo buscarUnPrestamoPorClientePorID() throws Exception {
        try {
            Cliente persona = new Cliente();
            do {
                persona = cliente.buscarPorDNI();
            } while (persona == null);

            Prestamo prestamo = new Prestamo();
            do {
                System.out.print("Ingrese el ID del prestamo: ");
                try {
                    prestamo = presta.buscarPorID(leer.nextInt());
                } catch (Exception e) {
                }
            } while (prestamo == null);

            return presta.buscarPorClientePorID(persona, prestamo.getId());

        } catch (Exception e) {
            System.out.println("No se pudo encontrar el prestamo.");
            return null;
        }
    }

    public void devolucion() throws Exception {
        Prestamo prestamo = new Prestamo();

        do {
            prestamo = buscarUnPrestamoPorClientePorID();
        } while (prestamo == null);

        Date fechaActual = new Date();
        Date fechaDevolucion;
        int dia;
        int mes;
        int anio;
        do {
            System.out.print("Ingrese un año: ");
            do {
                anio = leer.nextInt();
            } while (anio > 2023);

            System.out.print("Ingrese un mes: ");
            do {
                mes = leer.nextInt();
            } while (mes > 12 && mes < 1);

            if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                do {
                    System.out.print("Ingrese un dia entre 1 y 31: ");
                    dia = leer.nextInt();
                } while (dia > 31 && dia < 1);

            } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                do {
                    System.out.print("Ingrese un dia entre 1 y 30: ");
                    dia = leer.nextInt();
                } while (dia > 30 && dia < 1);

            } else {
                if (anio % 4 == 0) {
                    do {
                        System.out.print("Ingrese un dia entre 1 y 29: ");
                        dia = leer.nextInt();
                    } while (dia > 29 && dia < 1);

                } else {
                    do {
                        System.out.print("Ingrese un dia entre 1 y 28: ");
                        dia = leer.nextInt();
                    } while (dia > 28 && dia < 1);
                }
            }
            fechaDevolucion = new Date (anio-1900, mes-1, dia);
        } while (prestamo.getFechaPrestamo().getTime() > fechaActual.getTime());

        if (fechaDevolucion.getTime() > prestamo.getFechaDevolucion().getTime()) {
            System.out.println("Se excedio de la fecha de devolucion del prestamo.");
            System.out.println("La fecha de devolucion acordada es: " + prestamo.getFechaDevolucion());
            System.out.println("La fecha en la cual voy a devolver el libro es: " + fechaDevolucion);
        } else {
            System.out.println("El prestamo se entrego en el tiempo correspondiente.");
            System.out.println("La fecha de devolucion acordada es: " + prestamo.getFechaDevolucion());
            System.out.println("La fecha en la cual voy a devolver el libro es: " + fechaDevolucion);
        }

        libro.devolucionLibro(prestamo.getLibro());

        presta.eliminar(prestamo.getId());
    }
}

