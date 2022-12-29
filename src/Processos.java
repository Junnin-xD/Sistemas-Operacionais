public class Processos implements Comparable<Processos>{

    private int valor;
    private int tempoProcesso;
    private boolean interrupcao;

    public Processos(int valor, int tempo, boolean interrupcao){
        this.setValor(valor);
        this.setTempoProcesso(tempo);
        this.setInterrupcao(interrupcao);
    }

    public int getTempoProcesso() {
        return tempoProcesso;
    }

    public void setTempoProcesso(int tempoProcesso) {
        this.tempoProcesso = tempoProcesso;
    }

    public boolean isInterrupcao() {
        return interrupcao;
    }

    public void setInterrupcao(boolean interrupcao) {
        this.interrupcao = interrupcao;
    }

    @Override
    public String toString() {
        return "Processos [tempoProcesso=" + tempoProcesso + ", interrupcao=" + interrupcao + "]";
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public int compareTo(Processos o) {
        return (this.tempoProcesso - o.getTempoProcesso());
    }    
}
