package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

public class TcpConnection {
    String address;
    int port;
    private boolean connected;

    private Connection conn;

    public TcpConnection(String address, int port) {
        this.address = address;
        this.port = port;
        this.conn = new Disconnected(this);
        this.connected = false;
    }

    public String getCurrentState() {
        return connected ? "connected" : "disconnected";
    }

    public void write(String data) {
        List<String> buffer = new ArrayList<>();
        buffer.add(data);
        this.conn.sentData(data);
    }

    public void connect() {
        connected = true;
        this.conn.makeConnection();
    }

    public void disconnect() {
        connected = false;
        this.conn.closeConnection();
    }

    public void setState(Connection conn) {
        this.conn = conn;
    }
}
