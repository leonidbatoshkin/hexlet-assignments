package exercise.connections;

public interface Connection {
    void sentData(String data);

    void makeConnection();

    void closeConnection();
}
