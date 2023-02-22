package ciurezu.gheorgheDragos.services;

import ciurezu.gheorgheDragos.models.Account;
import ciurezu.gheorgheDragos.models.Note;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class AccountsService {
    public static void saveAccounts(String fileLocation, String fileName, List<Account> accountList) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(fileLocation + "/" + fileName));

        for (Account account : accountList) {
            fileWriter.write(account.getUsername() + " " + account.getRealName().replace(" ", "_")
                    + " " + account.getPassword() + " " + account.getEmail() + " " + account.getAge() + " ");
            for (Note note : account.getNoteList()) {
                fileWriter.write(note.getDate() + " " + note.getText().replace(" ", "_") + " ");
            }
            fileWriter.write("\n");
        }

        fileWriter.close();
    }

    public static List<Account> importAccounts(String fileLocation, String fileName)
            throws IOException, ClassNotFoundException {

        String readLine;
        List<Account> accountList = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileLocation + "/" + fileName)));
        while ((readLine = bufferedReader.readLine()) != null) {
            String[] splitted = readLine.split(" ");

            String username = splitted[0];
            String realName = splitted[1].replace("_", " ");
            String password = splitted[2];
            String email = splitted[3];
            int age = Integer.parseInt(splitted[4]);

            Account account = new Account(realName, age, username, password, email);

            for (int i = 5; i < splitted.length; i = i + 7) {
                String date = splitted[i] + " " + splitted[i + 1] + " " + splitted[i + 2] + " "
                        + splitted[i + 3] + " " + splitted[i + 4] + " " + splitted[i + 5];
                String message = splitted[i + 6].replace("_"," ");
                account.addNote(new Note(message, date));
            }

            accountList.add(account);
        }

        bufferedReader.close();

        return accountList;
    }
}
