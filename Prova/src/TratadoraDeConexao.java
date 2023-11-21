import java.io.*;
import java.net.*;
import java.util.*;

public class TratadoraDeConexao extends Thread{

    private Socket conexao;
    private ArrayList<Integer> num;

    public TratadoraDeConexao(Socket cnx, ArrayList<Integer> num)throws Exception{

        if (cnx==null) throw new Exception ("Conexao nao fornecida");
        if (num==null) throw new Exception ("Numero nao fornecido");

        this.conexao=cnx;
        this.num=num;
    }

    public void run(){
        try {
            BufferedReader receptor = new BufferedReader(new InputStreamReader(this.conexao.getInputStream()));

            PrintWriter transmissor = new PrintWriter(this.conexao.getOutputStream());

            for(;;) {
                String comando = receptor.readLine().toUpperCase();

                if (comando.equals("ATUAL")) {
                    int num;
                    synchronized (this.num) {
                        num=this.num.get(0);
                        this.num.remove(0);
                        this.num.add(num+1);
                    }
                    transmissor.println(num);
                    transmissor.flush();
                }
                else if (comando.equals("FIM")) {
                    break;
                }
                receptor.close();
                transmissor.close();
                this.conexao.close();
            }
        }catch (Exception error){}


    }
}
//