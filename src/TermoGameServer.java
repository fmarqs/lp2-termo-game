import java.net.*;

class TermoGameServer {
    public static void main (String args[ ] ) throws Exception {
        ServerSocket ss=new ServerSocket(3333);
        System.out.println("Iniciando servidor........");
        while(true){
        Socket s=ss.accept();
        System.out.println("Aceitando novo cliente na porta " + s.getPort());

        TermoGame termoGame = new TermoGame(s);
        Thread threadClient = new Thread(termoGame);
        threadClient.start();

        }
    }}