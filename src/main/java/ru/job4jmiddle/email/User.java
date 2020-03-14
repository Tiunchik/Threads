/**
 * Package ru.job4jmiddle.email for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.email;


/**
 * Class User - model of data.
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 14.03.2020
 */
public class User {

    /**
     * username.
     */
    private String username;

    /**
     * user email.
     */
    private String email;

    /**
     * constructor for creating user.
     *
     * @param username set username
     * @param email ser email
     */
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    /**
     * getter for username.
     *
     * @return get username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setter for username.
     *
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter for username.
     *
     * @return get username
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for email.
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
