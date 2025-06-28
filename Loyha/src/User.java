public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String role;
    private Long markedId;

    public User(Long id, String firstName, String lastName, String phoneNumber, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getMarkedId() {
        return markedId;
    }

    public void setMarkedId(Long markedId) {
        this.markedId = markedId;
    }


    public void setMarketId(Long marketId) {
    }

    public Object getMarketId() {
        return null;
    }
}
