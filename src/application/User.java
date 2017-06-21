package application;

class User {
    private String firstName;
    private final String lastName;
//    private final String userEmail;
//    private final String userName;
//    private final String password;

    User(String firstName, String lastName, String userEmail, String userName, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
//        this.userEmail = userEmail;
//        this.userName = userName;
//        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
