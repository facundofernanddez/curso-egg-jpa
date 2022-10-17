package libreria.servicio;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidad.Autor;

public class AutorServicio {

    Scanner leer = new Scanner(System.in);
    EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

    public void crearAutor() {

        try {
            Autor autor = new Autor();

            System.out.println("Escriba el nombre del autor");
            String nombre = leer.next();

            autor.setNombre(nombre);
            autor.setAlta(true);

            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void bajaAutor() {

        try {
            System.out.println("Escriba el nombre del autor que desea eliminar");
            String nombre = leer.next();

            Autor autorConsulta = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
            autorConsulta.setAlta(false);
            em.getTransaction().begin();
            em.merge(autorConsulta);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void buscarAutorPorNombre() {

        try {
            System.out.println("Escriba el nombre del autor que desea buscar");
            String nombre = leer.next();

            Autor autorConsulta = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
            System.out.println(autorConsulta);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
