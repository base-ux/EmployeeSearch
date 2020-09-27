package projekti.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractPersistable<Long> {

    @NotEmpty
    @Size(min = 2, max = 25)
    private String username;

    @NotEmpty
    @Size(min = 8, max = 60)
    private String password;

    @NotEmpty
    @Size(min = 2, max = 25)
    private String realname;

    @NotEmpty
    @Size(min = 2, max = 25)
    private String alias;
}
