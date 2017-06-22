package database.example.demo;

import javax.validation.Valid;

import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import database.example.demo.User;
import database.example.demo.UserRepository;

@Controller    // This means that this class is a Controller
//@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	private String food;
	private String name;
	
	@GetMapping(path="/list") // Map ONLY GET Requests
	public String addNewUser (Model userModel) {
		userModel.addAttribute("list", new User());
		//userModel.addAttribute(new User());
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		/*User n = new User();
		n.setName(name);
		n.setFood(food);
		userRepository.save(n);*/
		return "list";
	}
	/*THe below is for passing the object WITHOUT validation */
	/*
	@PostMapping("/list")
	public String usersAdded(@ModelAttribute User user){
		userRepository.save(user);
		return "result";
	}
	/***********************************************************/
	
	
	@PostMapping("/list")
	public String usersAdded(@Valid User user, BindingResult bindingResult){
		
		if (bindingResult.hasErrors()){
			return "list";
		}
		userRepository.save(user);
		return "result";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
}