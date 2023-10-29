package pe.nico.jwt.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", joinColumns = {@JoinColumn(name = "USER_ID")},
               inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> role;
}
