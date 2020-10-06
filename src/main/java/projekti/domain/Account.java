package projekti.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import projekti.logic.service.AccountService.ValidPassword;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractPersistable<Long> {

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 60)
    private String username;

    @ValidPassword
    @NotEmpty
    @NotNull
    @Size(min = 8, max = 60)
    private String password;
    
    @NotEmpty
    @NotNull
    @Size(min = 8, max = 60)
    private String confirm;

    @NotEmpty
    @NotNull
    @Size(min = 6, max = 30)
    private String realname;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 30)
    private String alias;
}
