package ciurezu.gheorgheDragos.services;

import ciurezu.gheorgheDragos.models.Account;
import ciurezu.gheorgheDragos.models.Note;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountsService {
    @Test
    void testSaveAccounts(){
        List<Account> accountList = new ArrayList<>();
        Account account = new Account("Dragos Ciurezu",18,
                "dragos",CryptService.encryptString("1234"),"dragos@yahoo.ro");
        Date date = new Date();

        account.addNote(new Note("dadada",date.toString()));
        accountList.add(account);

        try {
            AccountsService.saveAccounts("src/test/java/ciurezu/gheorgheDragos/testedFiles",
                    "test.txt",accountList);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        String readLine;
        List<Account> accountList2 = new ArrayList<>();
        Account account2 = null;

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(
                    "src/test/java/ciurezu/gheorgheDragos/testedFiles/test.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while ((readLine = bufferedReader.readLine()) != null) {
                String[] splitted = readLine.split(" ");

                String username = splitted[0];
                String realName = splitted[1].replace("_", " ");
                String password = splitted[2];
                String email = splitted[3];
                int age = Integer.parseInt(splitted[4]);

                account2 = new Account(realName, age, username, password, email);

                for (int i = 5; i < splitted.length; i = i + 7) {
                    String date2 = splitted[i] + " " + splitted[i + 1] + " " + splitted[i + 2] + " "
                            + splitted[i + 3] + " " + splitted[i + 4] + " " + splitted[i + 5];
                    String message = splitted[i + 6].replace("_", " ");
                    account2.addNote(new Note(message, date2));
                }

                accountList2.add(account2);
            }

            bufferedReader.close();


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        assertEquals(account.getUsername(),account2.getUsername());
        assertEquals(account.getEmail(),account2.getEmail());
        assertEquals(account.getAge(),account2.getAge());
        assertEquals(account.getPassword(),account2.getPassword());
        assertEquals(account.getRealName(),account2.getRealName());
        assertEquals(account.getNoteList().get(0).getText(),account2.getNoteList().get(0).getText());
        assertEquals(account.getNoteList().get(0).getDate(),account2.getNoteList().get(0).getDate());
    }

    @Test
    void testImportAccounts(){

        List<Account> accountList = new ArrayList<>();
        List<Account> accountList2 = new ArrayList<>();
        Account account2 = new Account("Dragos Ciurezu",18,
                "dragos",CryptService.encryptString("1234"),"dragos@yahoo.ro");
        Date date = new Date();

        account2.addNote(new Note("dadada",date.toString()));
        accountList.add(account2);

        try{
            FileWriter fileWriter = new FileWriter(new File(
                    "src/test/java/ciurezu/gheorgheDragos/testedFiles/test2.txt"));

            for (Account account : accountList) {
                fileWriter.write(account.getUsername() + " " + account.getRealName().replace(" ", "_")
                        + " " + account.getPassword() + " " + account.getEmail() + " " + account.getAge() + " ");
                for (Note note : account.getNoteList()) {
                    fileWriter.write(note.getDate() + " " + note.getText().replace(" ", "_") + " ");
                }
                fileWriter.write("\n");
            }

            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            accountList2= AccountsService.importAccounts(
                    "src/test/java/ciurezu/gheorgheDragos/testedFiles","test2.txt");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(accountList.get(0).getRealName(),accountList2.get(0).getRealName());
        assertEquals(accountList.get(0).getUsername(),accountList2.get(0).getUsername());
        assertEquals(accountList.get(0).getEmail(),accountList2.get(0).getEmail());
        assertEquals(accountList.get(0).getAge(),accountList2.get(0).getAge());
        assertEquals(accountList.get(0).getNoteList().get(0).getText(),accountList2.get(0).getNoteList().get(0).getText());
        assertEquals(accountList.get(0).getNoteList().get(0).getDate(),accountList2.get(0).getNoteList().get(0).getDate());
    }
}
