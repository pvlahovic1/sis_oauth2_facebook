package hr.foi.oauth2.facebook.repository;

import hr.foi.oauth2.facebook.model.Data;
import hr.foi.oauth2.facebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DataRepository extends JpaRepository<Data, Integer> {

    Set<Data> findByUser(User user);

}
