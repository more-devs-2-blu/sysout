package devs2blu.sysout.nfse.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devs2blu.sysout.nfse.models.NfseModel;

@Repository
public interface NfseRepository extends JpaRepository<NfseModel, UUID> {
    
}
