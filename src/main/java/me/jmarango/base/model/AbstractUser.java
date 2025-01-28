package me.jmarango.base.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.userdetails.UserDetails;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractUser extends AbstractPersistable<Long> implements UserDetails {

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    public AbstractUser(Long id, String username, String password) {
        super();
        super.setId(id);
        this.username = username;
        this.password = password;
    }
}
