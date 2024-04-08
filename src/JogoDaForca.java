import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class JogoDaForca {

    private String[] palavra;
    private ArrayList<String> palavraAdivinhada;
    private String dicaPalavra;
    private int penalidades = 0;
    private int acertos = 0;
    private ArrayList<String> bancoPalavras = new ArrayList<String>();
    private ArrayList<String> bancoDicas = new ArrayList<String>();
    private ArrayList<String> pilhaLetras = new ArrayList<String>();
    private ArrayList<String> nomePenalidades = new ArrayList<String>();

    /////////////////////////////////
    /////////////////////////////////
    public void adminGetPalavra(){
        for(int i = 0; i < this.palavra.length; i++){
            System.out.print(palavra[i]);
        }
        System.out.println();
    }
    //////// TIRAR NO FINAL /////////
    /////////////////////////////////

    public JogoDaForca() throws Exception{

        //Lê cada linha do arquivo palavras.txt e separa as palavras no atributo bancoPalavras
        //e as dicas no atributo bancoDicas.
        InputStream stream = this.getClass().getResourceAsStream("/dados/palavras.txt");
		if (stream == null)
			throw new Exception("Arquivo de palavras inexistente");
        Scanner arquivo = new Scanner(stream);
		String linha;
		while (arquivo.hasNext()) {
			linha = arquivo.nextLine().toUpperCase();
			this.bancoPalavras.add(linha.split(";")[0]);
			this.bancoDicas.add(linha.split(";")[1]);
		}
		arquivo.close();

        //Lê cada linha do arquivo penalidades.txt e separa as palavras no atributo nomePenalidades
        InputStream stream2 = this.getClass().getResourceAsStream("/dados/penalidades.txt");
		if (stream2 == null)
			throw new Exception("Arquivo de palavras inexistente");
        Scanner arquivo2 = new Scanner(stream2);
		String linha2;
		while (arquivo2.hasNext()) {
			linha2 = arquivo2.nextLine().toUpperCase();
			this.nomePenalidades.add(linha2);		
		}
		arquivo2.close();
    }

    public void iniciar(){
        Random random = new Random();
        int indiceRandom = random.nextInt(bancoPalavras.size());
        this.palavra = bancoPalavras.get(indiceRandom).split("");
        this.dicaPalavra = bancoDicas.get(indiceRandom);

        this.palavraAdivinhada = new ArrayList<>(this.palavra.length);
        for(int i = 0; i < this.palavra.length; i++){
            this.palavraAdivinhada.add("*");
        }
    }

    /**
     * Mostra o tamanho da palavra
     */
    public int getTamanho(){
        
        return palavra.length;
    }

    public String getDica(){
        return this.dicaPalavra;
    }

    public ArrayList<Integer> getOcorrencias(String letra) throws Exception{
        ArrayList<Integer> ocorrencias = new ArrayList<>();

        String letraM = letra.toUpperCase();

        if(letraM.length() == 1){
            if(!letraM.isBlank()){
                if (letraM.matches("[A-Z]")){
                    if (this.pilhaLetras.contains(letraM) == false){
                        for(int i = 0; i < this.palavra.length; i++){
                            if(this.palavra[i].equals(letraM)){
                                this.acertos ++;
                                this.palavraAdivinhada.set(i,letraM);
                                ocorrencias.add(i);
                                this.pilhaLetras.add(letraM);
                            }
                        }
                    }   
                    else{ 
                    throw new Exception("Letra já escolhida.");}
                }
                else
                throw new Exception("Digite um caractere do alfabeto");
            }
            else 
            throw new Exception("Não deixe o espaço em branco, digite uma letra.");
        }
        else
        throw new Exception("Você deve digitar somente uma letra.");
        
       
        if(ocorrencias.size() > 0){
            return ocorrencias;
        }else{
            this.penalidades ++;
            return ocorrencias;
        }
    }

    public boolean terminou(){
        return this.penalidades == 6 || this.acertos == this.palavra.length;
    }

    public String getPalavraAdivinhada(){
        String plvrAdvinhada = "";
        for(String s : this.palavraAdivinhada){
            plvrAdvinhada += s;
        }
        return plvrAdvinhada;
    }

    public int getAcertos(){
        return this.acertos;
    }

    public int getNumeroPenalidade(){
        return this.penalidades;
    }

    public String getNomePenalidade(){
        return this.nomePenalidades.get(this.penalidades);
       
    }

    public String getResultado(){
        if(this.acertos == this.palavra.length) return "Você venceu!";
        else if (this.penalidades == 6) return "Você foi enforcado.";     
        else return "Em andamento";
    }
}
