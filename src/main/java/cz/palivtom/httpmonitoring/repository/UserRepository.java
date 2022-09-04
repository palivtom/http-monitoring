package cz.palivtom.httpmonitoring.repository;

import cz.palivtom.httpmonitoring.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}