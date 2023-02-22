package ciurezu.gheorgheDragos.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCryptService {
    @Test
    void testCryptString(){
        String name="abc";
        String cryptName= "ÇÉË";
        assertEquals(cryptName,CryptService.encryptString(name));
    }

    @Test
    void testDecryptString(){
        String decryptName = "abc";
        String name = "ÇÉË";
        assertEquals(decryptName,CryptService.decryptString(name));
    }
}
