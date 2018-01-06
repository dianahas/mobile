package hotels.resource;

import hotels.model.User;
import hotels.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
public class UserResource {

    private static final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "api/user/{email:.+}", method = RequestMethod.GET)
    public User getUserById(@Valid @PathVariable String email) {

        log.info("Get user with email:  " + email);
        return userRepository.getUserByEmail(email);
    }


    @PostMapping(value = "api/user")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        user.setId(null);
        userRepository.addUser(user);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/login")
    ResponseEntity<?> login(@RequestBody User loginUser) {
        User user = userRepository.getUserByEmail(loginUser.getEmail());

        if (user != null) {
            if (user.getEmail().equals(loginUser.getEmail()) && user.getPassword().equals(loginUser.getPassword())) {
                return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
