
public class AddressManager {
    public static void addAddress(User user , Address address){
        user.getAddresses().add(address);
    }

    public static void deleteAddress(User user , Address address){
        user.getAddresses().remove(address);
    }
}
