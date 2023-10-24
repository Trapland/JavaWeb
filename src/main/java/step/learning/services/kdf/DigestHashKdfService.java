package step.learning.services.kdf;

import step.learning.services.hash.HashService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Computes derived key using Digest Hash Service
 */
@Singleton
public class DigestHashKdfService implements KdfService {
    private final HashService hashService;
    @Inject
    public DigestHashKdfService(@Named("Digest-Hash") HashService hashService) {
        this.hashService = hashService;
    }

    @Override
    public String getDerivedKey(String password, String salt) {
        return hashService.hash(salt + password + salt);
    }
}
