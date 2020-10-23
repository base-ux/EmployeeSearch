package projekti.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfilePicture extends AbstractPersistable<Long> {

    private Long profilePictureSize;
    private String name;
    private String mediaType;
    private String useralias;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] profilePicture;
}
