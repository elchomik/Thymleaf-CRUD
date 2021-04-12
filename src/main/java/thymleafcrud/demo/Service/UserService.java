package thymleafcrud.demo.Service;

import org.springframework.stereotype.Service;
import thymleafcrud.demo.Model.User;
import thymleafcrud.demo.Repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers(){
        return userRepository.findAll();
    }

    public User getSingleUser(Long id){
        return userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid user id"+id));
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
