package core;

import play.data.validation.Constraints;

public class Login {
    
    @Constraints.Required
    public String emailAddress;

    @Constraints.Required
    public String password;
    
}
