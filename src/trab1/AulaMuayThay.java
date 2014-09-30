package trab1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListModel;

public class AulaMuayThay implements Runnable {

    public boolean fechada = false;
    private Fila fila = new Fila();
    private Fila alunos = new Fila();
    private Fila aleatorios = new Fila();
    private int maxAlunos = 10;
    private int aulas = 0;
    private int maxAulas;
    public Semaphore lotSem = new Semaphore(0),
            aulaSem = new Semaphore(0),
            inscrSem = new Semaphore(1);

    public AulaMuayThay(int aulas) {
        maxAulas = aulas;
    }

    public Fila getFila() {
        return fila;
    }

    public Fila getAlunos() {
        return alunos;
    }

    public Fila getAleatorios() {
        return aleatorios;
    }

    @Override
    public void run() {
        //Enquanto a academia estiver aberta, aguarda ter pelo menos 10 alunos na fila e inicia a aula
        while (!fechada) {
            aguardaLotacao();
            promoveAula();
        }
        //Quando a academia fecha, tira da fila os alunos que ficaram
        Events.add("Última aula finalizada");
        while(!fila.isEmpty()) {
            Aluno a = fila.remove();
            a.aulaSem.release();
        }
    }

    public void inscreveAluno(Aluno aluno) {
        try {
            //Aguarda pelo semáforo de inscrição. Isso serve para que dois 
            //alunos não se inscrevam ao mesmo tempo
            inscrSem.acquire();
            fila.add(aluno);
            //Se a fila tiver pelo menos maxAlunos, libera o semáforo da lotação
            if (fila.size() >= maxAlunos) {
                lotSem.release();
            }
            //Libera o semáforo da inscrição, para que o próximo aluno possa se 
            //inscrever
            inscrSem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void aguardaLotacao() {
        try {
            //Aguarda a fila ter pelo menos 10 alunos
            lotSem.acquire();

            //Remove os 10 primeiros alunos da fila e adiciona à aula.
            for (int i = 0; i < 10; i++) {
                Aluno a = fila.remove();
                alunos.add(a);
            }

            //Quando os 10 alunos tiverem saido da fila e estiverem prontos para
            //a aula, libera o semaforo da aula.
            aulaSem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Essa é uma classe sincronizada. O que significa que Não pode rodar duas 
    //vezes simultaneas na mesma thread. Não podemos ter duas aulas acontecendo 
    //ao mesmo tempo
    public synchronized void promoveAula() {
        try {
            //Aguarda ter 10 alunos prontos para aula
            aulaSem.acquire();
            aulas++;
            Events.add("Aula nº " + aulas + " começa");
            //Se essa for a aula numero maxAula, fecha a academia
            if (aulas >= maxAulas) {
                fechada = true;
            }
            //A Aula terá duração de 10 segundos
            Thread.sleep(10000);

            Events.add("Aula nº " + aulas + " termina");
            //Ao final da aula, remove os alunos da aula e os libera para fazer
            //outras coisas
            while (!alunos.isEmpty()) {
                Aluno a = alunos.remove();
                a.aulaSem.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Adiciona um aluno à lista de alunos fazendo coisas aleatórias
     */
    public void fazerCoisasAleatorias(Aluno a) {
        aleatorios.add(a);
    }
    
    /*
    Remove o aluno da lista de alunos fazendo coisas aleatórias
    */
    public void terminarDeFazerCoisasAleatorias(Aluno a) {
        aleatorios.remove(a);
    }

}
