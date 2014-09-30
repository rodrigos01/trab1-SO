package trab1;

import java.util.concurrent.Semaphore;

public class Aluno implements Runnable {

    private AulaMuayThay aula;
    public int cod;
    private static int count = 0;

    public Semaphore aulaSem = new Semaphore(0),
            aleatSem = new Semaphore(0);

    public Aluno(AulaMuayThay aula) {
        this.aula = aula;
        cod = ++count;
    }

    @Override
    public void run() {
        //Enquanto a academia não estiver fechada, inscreve na aula de muay thay
        //e depois, faz coisas aleatórias
        while (!aula.fechada) {

            aula.inscreveAluno(this);

            aguardaAula();

            fazerCoisasAleatorias();

        }
        //O Aluno vai embora
    }

    //A Thread ficará parada nesse métodoenquanto o aluno estiver em aula.
    public void aguardaAula() {
        try {
            aulaSem.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void fazerCoisasAleatorias() {
        try {
            //Adiciona o aluno à lista de alunos fazendo coisas aleatórias
            aula.fazerCoisasAleatorias(this);
            //O Aluno ficará fazendo coisas aleatórias por um período entre
            //10 e 30 segundos
            Thread.sleep(Randomizer.getInt(10000, 30000));
            //Tira o Aluno dessa lista.
            aula.terminarDeFazerCoisasAleatorias(this);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Aluno " + cod;
    }

}
