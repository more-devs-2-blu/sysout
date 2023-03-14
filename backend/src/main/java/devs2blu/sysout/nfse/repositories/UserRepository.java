package devs2blu.sysout.nfse.repositories;

import devs2blu.sysout.nfse.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
	Optional<UserModel> findByUserDocIdentification(String userDocIdentification);

	Optional<UserModel> findByEmail(String email);
}
