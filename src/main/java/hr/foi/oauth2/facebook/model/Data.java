package hr.foi.oauth2.facebook.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@lombok.Data
@Entity
@Table(name = "data", schema = "fb_sis")
public class Data {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "title")
    private String title;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

}
