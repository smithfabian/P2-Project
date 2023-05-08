import org.junit.jupiter.api.Test;

import static main.app.models.PasswordManager.generateHash;
import static main.app.models.PasswordManager.isCorrectPassword;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestPasswordManager {

    @Test
    public void testPasswordMatchTrue() {
        String hash = generateHash("password");
        assertTrue(isCorrectPassword("password", hash));
    }

    @Test
    public void testPasswordMatchFalse() {
        String hash = generateHash("password");
        assertFalse(isCorrectPassword("anotherPassword", hash));
    }
}
