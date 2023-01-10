import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;
import javax.naming.AuthenticationException;

public class App {

    static ArrayList<Processos> lista = new ArrayList<Processos>();
    static ArrayList<Processos> escalonamento = new ArrayList<Processos>();
    static long TempoTotal;
    static boolean interrompido = false;
    static int contador = 0;

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.println("Quantos processos tem?");
        int quantidade = input.nextInt();
        int index = 1;

        do {

            System.out.println("Digite o tempo do processo " + index);
            int tempo = input.nextInt();

            System.out.println("Qual momento da interrupção?");
            int momentoInterrupcao = input.nextInt();

            System.out.println("Por quanto tempo o processo sera interrompido?");
            int tempoInterrupcao = input.nextInt();

            System.out.println("Qual a prioridade do processo?");
            int prioridade = input.nextInt();

            Processos P = new Processos(index, tempo, momentoInterrupcao, tempoInterrupcao, prioridade,
                    Integer.MIN_VALUE);
            lista.add(P);

            index++;

        } while (index < quantidade + 1);

        System.out.println("Escolha 1 para FCFS" + "\n" +
                "Escolha 2 para SJF" + "\n" +
                "Escolha 3 para SRT" + "\n" +
                "Escolha 4 para DULING" + "\n" +
                "Escolha 5 para ROUND ROLIN" + "\n" +
                "Escolha 6 para PARA SAIR" + "\n");

        int escolha = input.nextInt();

        switch (escolha) {
            case 1:
                fcfs(lista);
                return;

            case 2:
                sjf(lista);

            case 3:
                srt(lista);

            case 4:
                duling(lista);

            case 5:
                // roundRolin(lista4);

            case 6:
                break;
        }
    }

    public static void executaProcesso(Processos processo) {

        if (processo.getMomentoInterrupcao() == 0) { // se processo não tiver interrupção
            if (processo.getTempoPercorrido() + processo.getTempoInterrupcao() <= TempoTotal) { // verifica se o processo esta interrompido
                                                                                                
                 System.out.println("Processo " + processo.getIndex() + " startado.");       
                                                                                         
                try {
                    Thread.sleep(processo.getTempoProcesso() * 1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("----------------------------------");

                TempoTotal = TempoTotal + processo.getTempoProcesso();
                processo.setTempoPercorrido(TempoTotal);
                processo.setTempoProcesso(0);

                System.out.println("Processo " + processo.getIndex() + " finalizado.");
            } else {

                contador++; // incrementa um até o contador ter o mesmo valor que tamanho da lista

            }

        } else { // se processo tiver interrupção
            
            System.out.println("Processo " + processo.getIndex() + " startado.");

            try {
                Thread.sleep(processo.getMomentoInterrupcao() * 1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------------------------------");

            TempoTotal = TempoTotal + processo.getMomentoInterrupcao();
            processo.setTempoPercorrido(TempoTotal);
            processo.setTempoProcesso(processo.getTempoProcesso() - processo.getMomentoInterrupcao());      // Atualiza tempo do processo
            processo.setMomentoInterrupcao(0); // interrompição ja iniciada
            
            System.out.println("Processo " + processo.getIndex() + " interrompido.");
        }

    }

    public static void fcfs(ArrayList<Processos> lista) throws InterruptedException, CloneNotSupportedException {

        TempoTotal = System.currentTimeMillis();

        ArrayList<Processos> listaCopiada = new ArrayList<Processos>();

        for (Processos copy : lista)
            listaCopiada.add((Processos) copy.clone()); // copia a lista

        while (listaCopiada.size() != 0) {
            contador = 0;
            for (int indice = 0; indice < listaCopiada.size(); indice++) { // percorre a lista pegando o indice
                executaProcesso(listaCopiada.get(indice));
            }
            if (contador == listaCopiada.size()) {

                System.out.println("Aguardando processo ");
                Thread.sleep(1000);
                TempoTotal++;
            }
            for(int i = 0; i < listaCopiada.size(); i++){
                if(listaCopiada.get(i).getTempoProcesso() == 0){
                    listaCopiada.remove(i);
                }
            }
        }
    }

    public static void sjf(ArrayList<Processos> lista) throws InterruptedException, CloneNotSupportedException {
    
        TempoTotal = System.currentTimeMillis();

        ArrayList<Processos> listaCopiada = new ArrayList<Processos>();

        for (Processos copy : lista)
            listaCopiada.add((Processos) copy.clone()); // copia a lista

        Collections.sort(listaCopiada);

        while (listaCopiada.size() != 0) {
            contador = 0;
            for (int indice = 0; indice < listaCopiada.size(); indice++) { // percorre a lista pegando o indice
                executaProcesso(listaCopiada.get(indice));
            }
            if (contador == listaCopiada.size()) {

                System.out.println("Aguardando processo ");
                Thread.sleep(1000);
                TempoTotal++;
            }
            for(int i = 0; i < listaCopiada.size(); i++){
                if(listaCopiada.get(i).getTempoProcesso() == 0){
                    listaCopiada.remove(i);
                }
            }
        }
    }

    public static void duling(ArrayList<Processos> lista) throws InterruptedException, CloneNotSupportedException {

        TempoTotal = System.currentTimeMillis();

        ArrayList<Processos> listaCopiada = new ArrayList<Processos>();

        for (Processos copy : lista)
            listaCopiada.add((Processos) copy.clone()); // copia a lista

        Collections.sort(listaCopiada);

        while (listaCopiada.size() != 0) {
            contador = 0;
            for (int indice = 0; indice < listaCopiada.size(); indice++) { // percorre a lista pegando o indice
                if(listaCopiada.get(indice).getTempoProcesso() == listaCopiada.get(indice + 1).getTempoProcesso()){
                    if(listaCopiada.get(indice).getPrioridade() > listaCopiada.get(indice + 1).getPrioridade()){
         
                        Processos aux = listaCopiada.get(indice + 1);                        
                        Processos aux1 = listaCopiada.get(indice);
                        listaCopiada.set(indice + 1, aux1);
                        listaCopiada.set(indice, aux);
                        
                    }
                }
                executaProcesso(listaCopiada.get(indice));
                
            }
            if (contador == listaCopiada.size()) {

                System.out.println("Aguardando processo ");
                Thread.sleep(1000);
                TempoTotal++;
            }
            for(int i = 0; i < listaCopiada.size(); i++){
                if(listaCopiada.get(i).getTempoProcesso() == 0){
                    listaCopiada.remove(i);
                }
            }
        }
    }

    public static void srt(ArrayList<Processos> lista) throws InterruptedException, CloneNotSupportedException {

    }
}