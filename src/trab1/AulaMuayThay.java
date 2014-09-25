package trab1;

public class AulaMuayThay implements Runnable {

	public boolean fechada = false;
	private int alunos = 0;
	private int maxAlunos = 10;
	private boolean lotado = false;
	private int aulas = 0;
	private int maxAulas;
	
	public AulaMuayThay(int aulas) {
		maxAulas = aulas;
	}
	
	@Override
	public void run() {
		aguardaLotacaoMaxima();
		promoveAula();
		
	}
	
	public void inscreveParaAula() {
		alunos++;
		if(alunos == maxAlunos) {
			alunos = 0;
			lotado = true;
		}
	}
	
	public synchronized void aguardaAulaIniciar() {
		
	}
	
	public synchronized void aguardaLotacaoMaxima() {
		System.out.println("aguardando lotação");
		while(!lotado) {
			System.out.println("Alunos inscritos: "+alunos);
		}
		System.out.println("Aula lotou");
	}
	
	public synchronized void promoveAula() {
		
		System.out.println("Aula começa");
		
		aulas++;
		if(aulas >= maxAulas) {
			fechada = true;
		}
	}

}
