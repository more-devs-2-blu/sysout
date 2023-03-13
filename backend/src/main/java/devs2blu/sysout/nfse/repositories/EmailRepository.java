package devs2blu.sysout.nfse.repositories;

import devs2blu.sysout.nfse.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
