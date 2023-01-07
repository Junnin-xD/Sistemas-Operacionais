import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;
import javax.naming.AuthenticationException;

public class App {

    static LinkedList<Processos> lista = new LinkedList<Processos>();
    static LinkedList<Processos> escalonamento = new LinkedList<Processos>();

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

            Processos P = new Processos(index, tempo, momentoInterrupcao, tempoInterrupcao, prioridade);
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
                // sjf(lista);

            case 3:
                // srt(lista);

            case 4:
                // duling(lista);

            case 5:
                // roundRolin(lista);

            case 6:
                break;
        }
    }

    public static void fcfs(LinkedList<Processos> lista) throws InterruptedException {

        int contadorInterrupcao = 0;

        for (int i = 0; i < lista.size(); i++) {                                                //Percorre vetor selecionando o processo

            if (lista.get(i).getMomentoInterrupcao() != 0) {                                    //Verifica se o processo será interrompido

                System.out.println("Processo " + lista.get(i).getIndex() + " startado");

                int contaTempoProcesso = 0;

                while (contaTempoProcesso < lista.get(i).getMomentoInterrupcao()){              //looping até o tempo do processo chegar no momento da interrupção

                    if(escalonamento.size() > 0){                                               //Verifica se tem algum processo a espera na lista de escalonamento e executa o processo
                        lista.get(i).run();
                        System.out.println("---");

                        int aux = lista.get(i).getTempoProcesso();

                        lista.get(i).setTempoProcesso(aux - 1);

                        contadorInterrupcao++;
                        contaTempoProcesso++;
                    }else{                                                                      //Executa o processo com a fila de escalonamento vazia

                        lista.get(i).run();
                        System.out.println("---");

                        int aux = lista.get(i).getTempoProcesso();

                        lista.get(i).setTempoProcesso(aux - 1);

                        contaTempoProcesso++;

                    }
                }

                System.out.println("Processo " + lista.get(i).getIndex() + " Interrompido");

                escalonamento.add(lista.get(i));

            } else {                                                                            //Executa o processo quando não há interrupção

                System.out.println("Processo " + lista.get(i).getIndex() + " startado");

                do {
                    if(escalonamento.size() > 0){
                        lista.get(i).run();
                        System.out.println("---");
                        int aux = lista.get(i).getTempoProcesso();

                        lista.get(i).setTempoProcesso(aux - 1);
                        
                        contadorInterrupcao++;   
                    }                 

                } while (lista.get(i).getTempoProcesso() != 0);

                System.out.println("Processo " + lista.get(i).getIndex() + " finalizado");

            }

        }

        if(escalonamento.size() != 0){                                                                       //Verifica se tem processos na lista de escalonados

            for (int j = 0; j < escalonamento.size(); j++) {                                                 //Percorre a lista pegando o index

                int contaTempo = escalonamento.get(j).getTempoInterrupcao();
                escalonamento.get(j).setTempoInterrupcao(contaTempo - contadorInterrupcao);

                if (escalonamento.get(j).getTempoInterrupcao() > 0) {                                                                       //Aguarda tempo de interrupção
                    System.out.println("Finalizando tempo de Interrupção do processo " + escalonamento.get(j).getIndex());
                    do {
                        escalonamento.get(j).run();
                        System.out.println("---");
                        int aux = escalonamento.get(j).getTempoInterrupcao();

                        escalonamento.get(j).setTempoInterrupcao(aux - 1);

                    } while (escalonamento.get(j).getTempoInterrupcao() > 0);

                    System.out.println("Tempo de interrupção do processo " + escalonamento.get(j).getIndex() + " finalizado");
                    System.out.println("Processo " + escalonamento.get(j).getIndex() + " startado");

                    while(escalonamento.get(j).getTempoProcesso() > 0){
                       
                        escalonamento.get(j).run();
                        System.out.println("---");
                        int aux = escalonamento.get(j).getTempoProcesso();

                        escalonamento.get(j).setTempoProcesso(aux - 1);
                    }

                    System.out.println("Processo " + escalonamento.get(j).getIndex() + " finalizado.");

                } else {                                                                                      //FALHA TERMINA AQUI

                    System.out.println("Processo " + escalonamento.get(j).getIndex() + " startado");

                    do {
                        escalonamento.get(j).run();
                        System.out.println("---");
                        int aux = escalonamento.get(j).getTempoProcesso();

                        escalonamento.get(j).setTempoProcesso(aux - 1);

                    } while (escalonamento.get(j).getTempoProcesso() != 0);
                    System.out.println("Processo " + escalonamento.get(j).getIndex() + " finalizado");
                }
            }
        }
    }
}