public class User {

    private final String userId;
    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String address;

    public User(String userId, String name, String phoneNumber, String email, String address) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
