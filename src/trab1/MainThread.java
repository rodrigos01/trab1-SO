/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigo
 */
public class MainThread implements Runnable{
    
    AulaMuayThay aula;

    public MainThread(AulaMuayThay aula) {
        this.aula = aula;
    }

    @Override
    public void run() {
        //Inicia a Thread da Aula
        Thread threadAula = new Thread(aula);
        threadAula.start();
        
        //Enquanto a academia estiver aberta, cria novos alunos para frequentar as aulas
        while(!aula.fechada) {
            Thread threadAluno = new Thread(new Aluno(aula));
            threadAluno.start();
            try {
                //Os alunos são criados em um intervalo de 1 à 5 segundos
                Thread.sleep(Randomizer.getInt(1000, 5000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
