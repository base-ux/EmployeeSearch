package projekti.logic.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.Account;
import projekti.domain.ProfilePicture;

public interface ProfilePictureRepository extends JpaRepository<Account, Long> {

    List<ProfilePicture> findByUseralias(String useralias);
}
