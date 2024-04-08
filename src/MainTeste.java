public class MainTeste {
    public static void main(String[] args) {
        try {
			JogoDaForca jogo = new JogoDaForca();
			jogo.iniciar();
			jogo.adminGetPalavra();
			System.out.println(jogo.getDica());
            System.out.println(jogo.getTamanho());
            System.out.println(jogo.getPalavraAdivinhada());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

    }
}
