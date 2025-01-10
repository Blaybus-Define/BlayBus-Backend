package blaybus.blaybus_backend.domain.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    @GetMapping()
    public String post() {
        return "hello";
    }
}


