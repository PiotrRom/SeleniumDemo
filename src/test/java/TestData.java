import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Piotr on 2017-03-20.
 */
public class TestData {
    private String admpass = "sumXQQ72$L";
    private String admmail = "administrator@testarena.pl";
    private String urlTestArena = "http://demo.testarena.pl";
    private String urlMailinator = "https://www.mailinator.com/";
    private String mail ="";
    private String newUserPass = "Test123";

    public String getAdmpass() {
        return admpass;
    }

    public String getAdmmail() {
        return admmail;
    }

    public String getUrlTestArena() {
        return urlTestArena;
    }

    public String getUrlMailinator() {
        return urlMailinator;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNewUserPass() {
        return newUserPass;
    }

    public String generateRandomString(int length){
        return RandomStringUtils.randomAlphabetic(length);
    }

    public String generateRandomNumber(int length){
        return RandomStringUtils.randomNumeric(length);
    }

}
