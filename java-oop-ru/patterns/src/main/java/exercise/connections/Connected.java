package exercise.connections;

import exercise.TcpConnection;

class Connected implements Connection {

    private final TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void closeConnection() {
        System.out.println("OK, i will close connection");
        this.tcpConnection.setState(new Disconnected(this.tcpConnection));
    }

    @Override
    public void makeConnection() {
        System.out.println("Error. It's connected yet");
    }

    @Override
    public void sentData(String data) {
        System.out.println("OK, i will sent " + data);
    }
}
