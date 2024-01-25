package habib_practice.read_simple_json_file.usercost;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userCosts")
public class UserCostController {

    private final UserCostRepository userCostRepository;

    public UserCostController(UserCostRepository userCostRepository) {
        this.userCostRepository = userCostRepository;
    }

    @GetMapping("")
    List<UserCost> findAll() {
        return userCostRepository.findAll();
    }

}
