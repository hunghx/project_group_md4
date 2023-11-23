package ra.academy.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class FormRegister { // lớp POJO là lớp bình thường trong java
   @NotBlank(message = "Not empty")
    private String fullName;
    @NotBlank(message = "Not empty")
   @Pattern(regexp = "^(.+)@(\\S+)$",message = "Email is invalid !")
    private String email;
    @NotBlank(message = "Not empty")
    private String password;
    @NotBlank(message = "Not empty")
    private String rePassword;
    @NotBlank(message = "Not empty")
    @Pattern(regexp = "^0[0-9]{9,10}$",message = "Phone is invalid!")
    private String phone;
    @NotBlank(message = "Not empty")
    private String address;

    public FormRegister(String fullName, String email, String password, String rePassword, String phone, String address) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.phone = phone;
        this.address = address;
    }

    public FormRegister() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
