package co.omise;

/**
 * Client Exception object contains information about errors during client initialization.
 */
public class ClientException extends Exception {
    public ClientException(Exception cause) {
        super("Client initialization failure.", cause);
    }
}
