package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
            EntityManager em = emf.createEntityManager();
            
            try {
                
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }
    
}
