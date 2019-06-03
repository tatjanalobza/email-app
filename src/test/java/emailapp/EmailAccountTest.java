package emailapp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class EmailAccountTest {

    private static final Set<String> AVAILABLE_CHARACTERS = "ABSSCDEFGHIJKLMNOPQRSTUWXYZ0123456789!@#$"
            .chars().mapToObj(String::valueOf).collect(toSet());

    EmailAccount account = new EmailAccount("TestFirstName", "TestLastName", 3, "private.email@gmail.com");
    String password = account.getPassword();

    @Test
    public void testPasswordGeneration() {
        Assert.assertNotNull(password);
    }

    @Test
    public void testPasswordCharacters() {
        Assert.assertTrue(AVAILABLE_CHARACTERS.containsAll(password.chars().mapToObj(String::valueOf).collect(toSet())));
    }
    @Test
    public void testPasswordLength() {
        Assert.assertEquals(10, password.length());
    }
    @Test
    public void testEmailGeneration() {
        Assert.assertTrue(account.getCompanyEmail().matches("testfirstname\\.testlastname@acct\\.anycompany\\.com"));
    }

}
