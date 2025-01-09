package blaybus.blaybus_backend.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Member API", description = "Member management API")
public class MemberController {

    @GetMapping("/members")
    @Operation(summary = "Get Member", description = "Retrieve all users")
    public String getUsers(@RequestParam(value = "name", required = false) String name) {
        return "List of members";
    }
}
