package thymleafcrud.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thymleafcrud.demo.Model.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
