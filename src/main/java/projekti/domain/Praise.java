package projekti.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
public class Praise extends AbstractPersistable<Long> {

    private String useralias;

    @NotEmpty
    @Size(min = 1, max = 100)
    private String praisetext;

    @ManyToOne
    private Ability ability;
}
