package co.omise;

import co.omise.models.Account;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.*;

public class SerializableTest {
    private String filename = "file.ser";

    private Account account = new Account(
            "account",
            "omise",
            "id",
            true,
            DateTime.now(),
            false,
            "a@b.com",
            "thb"
    );

    @Test
    public void testSerialize() {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(account);
            out.close();
            file.close();

            System.out.println("Object has been serialized");
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    @Test
    public void testDeserialize() {
        Account account;

        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            account = (Account) in.readObject();
            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            System.out.println("Email = " + account.getEmail());
            System.out.println("Currency = " + account.getCurrency());

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }
}
