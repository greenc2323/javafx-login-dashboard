package edu.utsa.cs3443.gfj947_lab4.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class User extends Person {

    private String username;
    private String password; // plain-text for this lab

    public User(String username, String password, String firstName, String lastName) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "User{" + username + ", " + super.toString() + "}";
    }

    /**
     * Authenticate using users.csv in resources/dat

     * CSV format in columns :
     *   0 = firstName
     *   1 = lastName

     *   2 = emai
     *   3 = ID

     *   4 = usernae
     *   5 = Password
     */
    public static User authenticate(String username, String password) {
        String resourcePath = "/data/users.csv";

        try (InputStream is = User.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                System.err.println("users.csv not found at " + resourcePath);
                return null;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue;
                    }

                    String[] parts = line.split(",");

                    if (parts.length < 6) {
                        System.err.println("Bad line in users.csv: " + line);
                        continue;
                    }

                    String firstName = parts[0].trim();
                    String lastName  = parts[1].trim();
                    String fileUser  = parts[4].trim(); // username column
                    String filePass  = parts[5].trim(); // password column

                    if (fileUser.equals(username) && filePass.equals(password)) {
                        return new User(fileUser, filePass, firstName, lastName);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users.csv: " + e.getMessage());
        }

        return null;
    }
}


