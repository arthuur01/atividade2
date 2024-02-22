package atividade2;



public class Principal {
	public static void main(String[] args) {
		XDAO comandos = new XDAO();
		x classe = new x();
		comandos.connect();
		comandos.listar();
		comandos.inserir(classe);
		comandos.alterar(classe);
		Integer id = classe.getId();
		comandos.remover(id);
		comandos.Sair();
		
	}
	
}
