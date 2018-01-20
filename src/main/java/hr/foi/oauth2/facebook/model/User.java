package hr.foi.oauth2.facebook.model;


import javax.persistence.*;
import java.util.Set;

@lombok.Data
@Entity
@Table(name = "user", schema = "fb_sis")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "fb_id")
    private String fbId;

    @Column(name = "name")
    private String name;

}
