import java.net.*;
import java.io.*;

class PalindromoServer {
    public static void main (String args[ ] ) throws Exception {
        ServerSocket ss=new ServerSocket(3333);
        System.out.println("Server started........");
        while(true){
        Socket s=ss.accept();
        System.out.println("Aceitando novo cliente na porta " + s.getPort());

        IsPalindrome isPalindrome = new IsPalindrome(s);
        Thread threadClient = new Thread(isPalindrome);
        threadClient.start();

        }
    }}