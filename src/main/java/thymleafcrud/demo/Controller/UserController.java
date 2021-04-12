package thymleafcrud.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import thymleafcrud.demo.Model.User;
import thymleafcrud.demo.Repository.UserRepository;
import thymleafcrud.demo.Service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {


    private final UserService userService;

    public UserController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/createUser")
    public String showCreateForm(User user){
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model){
        if(result.hasErrors())
            return "add-user";
        userService.addUser(user);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showAllUsers(Model model){
        model.addAttribute("users",userService.allUsers());
        return "index";
    }

    @GetMapping("/edited/{id}")
    public String showUpdateForm(@PathVariable("id") long id,Model model){
        User user=userService.getSingleUser(id);
        model.addAttribute("user",user);
        return "update-user";
    }

   @PostMapping("/updated/{id}")
   public String updateUser(@PathVariable("id") long id,@Valid User user,
                            BindingResult result,Model model){
        if(result.hasErrors()){
            user.setId(id);
            return "update-user";
        }
        userService.addUser(user);
        return "redirect:/index";

   }

   @GetMapping("/deleted/{id}")
    public String deleteUser(@PathVariable("id") long id,Model model){

       User user=userService.getSingleUser(id);
       userService.deleteUser(user.getId());
        return "redirect:/index";
   }

}
