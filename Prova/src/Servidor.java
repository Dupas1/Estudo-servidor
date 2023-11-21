import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {

    public static void main(String[] args) throws Exception {

        ServerSocket pedido = new ServerSocket(12345);

        ArrayList<Integer> num = new ArrayList<Integer>();
        num.add(0);//add numero 0

        for(;;){
            Socket conexao = pedido.accept();
            new TratadoraDeConexao (conexao,num).start();
        }
    }
}
