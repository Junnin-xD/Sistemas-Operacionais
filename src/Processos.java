public class Processos implements Comparable<Processos>, Runnable {

    private int index;
    private int tempoProcesso;
    private int tempoInterrupcao;
    private int momentoInterrupcao;
    private int prioridade;

    public Processos(int index, int tempo, int momentoInterrupcao, int tempoInterrupcao, int prioridade) {
        this.setIndex(index);
        this.setTempoProcesso(tempo);
        this.setTempoInterrupcao(tempoInterrupcao);
        this.setMomentoInterrupcao(momentoInterrupcao);
        this.setPrioridade(prioridade);
        Thread t = new Thread(this);
    }

    public int getTempoInterrupcao() {
        return tempoInterrupcao;
    }

    public void setTempoInterrupcao(int tempoInterrupcao) {
        this.tempoInterrupcao = tempoInterrupcao;
    }

    public int getTempoProcesso() {
        return tempoProcesso;
    }

    public void setTempoProcesso(int tempoProcesso) {
        this.tempoProcesso = tempoProcesso;
    }

    public int getMomentoInterrupcao() {
        return momentoInterrupcao;
    }

    public void setMomentoInterrupcao(int momentoInterrupcao) {
        this.momentoInterrupcao = momentoInterrupcao;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(Processos o) {
        return (this.tempoProcesso - o.getTempoProcesso());
    }

    @Override
    public String toString() {
        return "Processos [tempoProcesso=" + tempoProcesso + ", momentoInterrupcao=" + momentoInterrupcao + "]";
    }

    @Override
    public void run() {                                                 

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}