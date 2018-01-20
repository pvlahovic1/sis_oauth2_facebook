package hr.foi.oauth2.facebook.repository;

import hr.foi.oauth2.facebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer> {

    User findByFbId(String fbId);
    User findByName(String name);

}
