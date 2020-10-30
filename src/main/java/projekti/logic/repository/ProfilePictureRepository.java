package projekti.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.Account;
import projekti.domain.ProfilePicture;

public interface ProfilePictureRepository extends JpaRepository<Account, Long> {

    ProfilePicture findByUseralias(String useralias);
}
