package libreria.servicio;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidad.Autor;
import libreria.entidad.Editorial;
import libreria.entidad.Libro;

public class LibroServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

    public void crearLibro() {

        try {
            Libro libro = new Libro();

            System.out.println("Escriba el titulo");
            libro.setTitulo(leer.next());

            System.out.println("Escriba el año de publicacion");
            libro.setAnio(leer.nextInt());

            System.out.println("Escriba la cantidad de ejemplares que se imprimieron");
            libro.setEjemplares(leer.nextInt());

            System.out.println("Escriba la cantidad de ejemplares que se prestaron");
            libro.setEjemplaresPrestados(leer.nextInt());

            libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());

            libro.setAlta(true);

            System.out.println("Escriba el autor de este libro");
            String nombre = leer.next();
            Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
            libro.setAutor(autor);
            
            System.out.println("Escriba la editorial de este libro");
            String ed = leer.next();
            Editorial editorial = (Editorial) em.createQuery("SELECT a FROM Editorial a WHERE a.nombre = :nombre").setParameter("nombre", ed).getSingleResult();
            libro.setEditorial(editorial);

            System.out.println("Escriba el ISBN del libro");
            libro.setIsbn(leer.nextLong());

            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error de sistema");
            System.out.println(e);
        }
    }

    public void consultarLibroPorTitulo() {
        System.out.println("Escriba el nombre del libro que desea consultar");
        String nombre = leer.next();

        try {
            Libro libro = (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.titulo = :nombre").setParameter("nombre", nombre).getSingleResult();
            System.out.println(libro);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void consultarLibroPorISBN() {

        try {
            System.out.println("Escriba el ISBN del libro a buscar");
            long num = leer.nextLong();

            Libro libro = (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.isbn = :num").setParameter("num", num).getSingleResult();
            System.out.println(libro);
        } catch (Exception e) {
        }
    }

    public void consultarLibroPorAutor() {
        System.out.println("Escriba el nombre de autor para buscar el libro");
        String nombre = leer.next();

        Libro libro = (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.autor.nombre = :autor").setParameter("autor", nombre).getSingleResult();
        System.out.println(libro.getTitulo());
    }

    public void consultarLibroPorEditorial() {

        try {
            System.out.println("Escriba la editorial del libro que desea buscar");
            String editorial = leer.next();

            Libro libro = (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.editorial.nombre = :nombre").setParameter("nombre", editorial).getSingleResult();
            System.out.println(libro.getTitulo());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
