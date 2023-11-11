package interface_adapeters;

public class UserCreationFailed extends RuntimeException {
    public UserCreationFailed(String error) {
        super(error);
    }
}
