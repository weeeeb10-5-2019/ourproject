package control;

import java.io.IOException;
import java.text.ParseException;

import bean.User;

public class Authentication {
    // if(!isAuthentic) user = null;
    // A cookie is suggested to show if the user has logged in or not
    // it seems a little redundant to have getters or setters here
    // but more functions could be added if necessary
    private User user;

    public Authentication() {
    }

    // the function of authentication can be realized using
    // User.get(username, password)
    // which will return null if is not authentic
    public Authentication(String username, String password) throws IOException, ParseException {
        this.user = User.get(username, password);
    }

    public boolean isAuthentic(String username, String password) throws IOException, ParseException {
        setUser(User.get(username, password));
        if (this.user.equals(null))
            return false;
        else
            return true;
    }

    public boolean isAuthentic() {
        if (this.user.equals(null))
            return false;
        else
            return true;
    }

    public boolean isLoggedIn() {
        if (this.user.equals(null)) {
            return false;
        } else
            return true;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}