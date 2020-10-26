package projekti.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Lob;
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
public class Comment extends AbstractPersistable<Long> {
    
    private Long postid;

    private String useralias;

    private String postingtime;

    @NotEmpty
    @Size(min = 1, max = 4000)
    @Lob
    private String response;

    @ElementCollection
    private List<String> likers = new ArrayList<>();

    @ElementCollection
    private List<String> readbyusers = new ArrayList<>();
}
