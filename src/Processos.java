public class Processos implements Comparable<Processos>, Cloneable{

    private int index;
    private int tempoProcesso;
    private int tempoInterrupcao;
    private int momentoInterrupcao;
    private int prioridade;
    private Long tempoPercorrido;

    public Processos(int index, int tempo, int momentoInterrupcao, int tempoInterrupcao, int prioridade, long tempoPercorrido) {
        this.setIndex(index);
        this.setTempoProcesso(tempo);
        this.setTempoInterrupcao(tempoInterrupcao);
        this.setMomentoInterrupcao(momentoInterrupcao);
        this.setPrioridade(prioridade);
        this.setTempoPercorrido(tempoPercorrido);
    }
    

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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


    public Long getTempoPercorrido() {
        return tempoPercorrido;
    }


    public void setTempoPercorrido(Long tempoPercorrido) {
        this.tempoPercorrido = tempoPercorrido;
    }
}