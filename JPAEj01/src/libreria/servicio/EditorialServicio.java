package libreria.servicio;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidad.Editorial;

public class EditorialServicio {

    Scanner leer = new Scanner(System.in);
    EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

    public void crearEditorial() {

        try {
            System.out.println("Escriba el nombre de la editorial a crear");
            String nombre = leer.next();

            Editorial editorial = new Editorial();
            editorial.setNombre(nombre);
            editorial.setAlta(true);

            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void bajaEditorial() {

        try {
            System.out.println("Que editorial desea dar de baja?");
            String nombre = leer.next();

            Editorial editorial = (Editorial) em.createQuery("SELECT a FROM Editorial a WHERE a.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
            editorial.setAlta(false);
            em.getTransaction().begin();
            em.merge(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
