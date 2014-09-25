package trab1;

public class Trab1 {

	public static void main(String[] args) {
		
		AulaMuayThay aula = new AulaMuayThay(10);
		Thread threadAula = new Thread(aula);
		threadAula.start();
		
//		while(!aula.fechada) {
//			Thread threadAluno = new Thread(new Aluno(aula));
//			threadAluno.start();
//		}

	}

}
