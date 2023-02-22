package ciurezu.gheorgheDragos.services;


public final class CryptService {
    public static String encryptString(String string) {
        String encrypted = "";

        for (int i = 0; i < string.length(); i++) {
            int intValue = string.charAt(i);
            intValue = intValue * 2 + 5;
            encrypted += (char) (intValue);
        }
        return encrypted;
    }


    public static String decryptString(String string) {
        String decrypted = "";

        for (int i = 0; i < string.length(); i++) {
            int intValue = string.charAt(i);
            intValue = (intValue - 5) / 2;
            decrypted += (char) (intValue);
        }
        return decrypted;
    }
}
