package Cliente;

import java.io.*;
import java.net.*;

public class Cliente
{
    public static void main (String[] args) throws Exception {

        Socket conexao = new Socket ("localhost",12345);

        BufferedReader receptor = new BufferedReader(
                new InputStreamReader(
                        conexao.getInputStream()));

        PrintWriter transmissor = new PrintWriter(
                conexao.getOutputStream());

        BufferedReader teclado = new BufferedReader(
                new InputStreamReader(
                        System.in));

        for(;;)
        {
            String comando = "";
            try
            {
                comando = teclado.readLine();
            }
            catch(Exception erro)
            {}

            if (comando.equals("ATUAL"))
            {
                transmissor.println(comando);
                transmissor.flush();
                System.out.println(receptor.readLine());
            }
            else if (comando.equals("FIM"))
            {
                transmissor.println(comando);
                transmissor.flush();
                break;
            }
            else
                System.err.println("Comando invalido");
        }

        receptor.close();
        transmissor.close();
    }
}