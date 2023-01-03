package survey.backend.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "login", unique = true, nullable = false)
    private String userLogin;

    @Column(name = "password", nullable = false)
    private String userPassword;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> roles = new HashSet<>();
}
