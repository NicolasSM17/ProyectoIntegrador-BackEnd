package pe.nico.jwt.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Role {
    @Id
    private String roleName;
    private String roleDescription;
}
