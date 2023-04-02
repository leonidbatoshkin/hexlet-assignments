package exercise.connections;

import exercise.TcpConnection;

public class Disconnected implements Connection {

    private final TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void sentData(String data) {
        System.out.println("Error. You have to open connection first");
    }

    @Override
    public void makeConnection() {
        System.out.println("OK, i will open connection");
        this.tcpConnection.setState(new Connected(this.tcpConnection));
    }

    @Override
    public void closeConnection() {
        System.out.println("Error. It's disconnected yet");
    }
}
