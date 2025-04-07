package kg.alatoo.corp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kg.alatoo.corp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findById(long id);
}
