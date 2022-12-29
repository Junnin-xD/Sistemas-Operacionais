import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App {

    static ArrayList<Processos> lista = new ArrayList<Processos>();
    public static void main(String[] args) throws Exception {
        
        Scanner input = new Scanner(System.in);
        
        
        System.out.println("Quantos processos tem?");
        int quantidade = input.nextInt();
        int index = 1;

        do{
            boolean interrupcao = false;

            System.out.println("Digite o tempo do processo:");
            int tempo = input.nextInt();

            System.out.println("O processo tem interrupção? (s) para sim (n) para não");
            String opcao = input.next();
            
            if(opcao.equals("s")){
                interrupcao = true;
            } 

            Processos P = new Processos(index, tempo, interrupcao);
            lista.add(P);

            index++;

        }while(index < quantidade + 1);

        System.out.println("Escolha 1 para FCFS" + "\n" +
                            "Escolha 2 para SJF" + "\n" +
                            "Escolha 3 para SRT" + "\n" +
                            "Escolha 4 para DULING" + "\n" +
                            "Escolha 5 para ROUND ROLIN" + "\n" +
                            "Escolha 6 para PARA SAIR" + "\n");
                            
            int escolha = input.nextInt();
        
            switch(escolha){
                case 1: 
                fcfs(lista);
                return;

                case 2:
                sjf(lista);

                case 3:
                //srt(lista);

                case 4:
                //duling(lista);

                case 5:
                //roundRolin(lista);
                
                case 6:
                break;
            }

        /*for(Processos i: lista){
            System.out.println(i);
        }*/
    }

    public static void fcfs(ArrayList<Processos> lista) throws InterruptedException{

        for(int i = 0; i < lista.size(); i++){
            
            System.out.println("Processo " + lista.get(i).getValor() + " startado");
            Thread.sleep(lista.get(i).getTempoProcesso() * 1000);
            System.out.println("Processo " + lista.get(i).getValor() + " finalizado");
        
        }
    }

    public static void sjf(ArrayList<Processos> lista) throws InterruptedException{

        Collections.sort(lista);

        for(int i = 0; i < lista.size(); i++){
            
            System.out.println("Processo " + lista.get(i).getValor() + " startado");
            Thread.sleep(lista.get(i).getTempoProcesso() * 1000);
            System.out.println("Processo " + lista.get(i).getValor() + " finalizado");
        
        }
    }

    /*public static void srt(ArrayList<Processos> lista) throws InterruptedException{

        for(int i = 0; i < lista.size(); i++){
            
            System.out.println("Processo " + i + " startado");
            Thread.sleep(lista.get(i).getTempoProcesso() * 1000);
            System.out.println("Processo " + i + " finalizado");
        
        }
    }

    public static void duling(ArrayList<Processos> lista) throws InterruptedException{RoundRolin

        for(int i = 0; i < lista.size(); i++){
            
            System.out.println("Processo " + i + " startado");
            Thread.sleep(lista.get(i).getTempoProcesso() * 1000);
            System.out.println("Processo " + i + " finalizado");
        
        }
    }

    public static void roundRolin(ArrayList<Processos> lista) throws InterruptedException{

        for(int i = 0; i < lista.size(); i++){
            
            System.out.println("Processo " + i + " startado");
            Thread.sleep(lista.get(i).getTempoProcesso() * 1000);
            System.out.println("Processo " + i + " finalizado");
        
        }
    }*/
}
