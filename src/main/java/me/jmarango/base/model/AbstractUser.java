package me.jmarango.base.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractUser extends AbstractPersistable<Long> {

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
