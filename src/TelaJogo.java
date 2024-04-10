import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class TelaJogo {

	private JFrame frame;
	private JButton iniciar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextPane letraTextPane;
	private JButton btnADIVINHAR;
	private JLabel lblNewLabel_2;
	private JLabel dicaTextLabel;
	private ImageIcon icon;
	private JLabel sizeImage;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel acertosTextLabel;
	private JLabel penalidadesTextLabel;
	private JLabel lblNewLabel_5;
	private JLabel plvrAdivinhadalbl;
	private ArrayList<ImageIcon> bancoImagens = new ArrayList<>();
	public int indice = 0;
	private JLabel status;
	private JLabel nomePenalidadeLbl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaJogo window = new TelaJogo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaJogo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Jogo da Forca");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		iniciar = new JButton("INICIAR");
		iniciar.setBounds(166, 11, 89, 23);
		frame.getContentPane().add(iniciar);
		
		lblNewLabel = new JLabel("Tamanho :");
		lblNewLabel.setBounds(28, 211, 95, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Dica :");
		lblNewLabel_1.setBounds(28, 225, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		letraTextPane = new JTextPane();
		letraTextPane.setBounds(310, 176, 114, 20);
		frame.getContentPane().add(letraTextPane);
		
		btnADIVINHAR = new JButton("ADIVINHAR");
		btnADIVINHAR.setBounds(310, 207, 114, 23);
		frame.getContentPane().add(btnADIVINHAR);
		
		lblNewLabel_2 = new JLabel("0");
		lblNewLabel_2.setBounds(92, 211, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		dicaTextLabel = new JLabel("");
		dicaTextLabel.setBounds(67, 225, 246, 14);
		frame.getContentPane().add(dicaTextLabel);
		
		icon = new ImageIcon(getClass().getResource("imagens/0.png"));
		sizeImage = new JLabel(icon);
		sizeImage.setBounds(20, 60, 156, 145);
		frame.getContentPane().add(sizeImage);
		
		for(int i = 1; i < 7; i++) {
			ImageIcon icones = new ImageIcon(getClass().getResource(String.format("imagens/%d.png", i)));
			bancoImagens.add(icones);
		}
		
		
		lblNewLabel_3 = new JLabel("Acertos : ");
		lblNewLabel_3.setBounds(310, 53, 54, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Penalidade : ");
		lblNewLabel_4.setBounds(27, 240, 80, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		acertosTextLabel = new JLabel("0");
		acertosTextLabel.setBounds(400, 53, 46, 14);
		frame.getContentPane().add(acertosTextLabel);
		
		penalidadesTextLabel = new JLabel("0");
		penalidadesTextLabel.setBounds(102, 240, 46, 14);
		frame.getContentPane().add(penalidadesTextLabel);
		
		lblNewLabel_5 = new JLabel("Palavra : ");
		lblNewLabel_5.setBounds(310, 35, 67, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		plvrAdivinhadalbl = new JLabel("");
		plvrAdivinhadalbl.setBounds(360, 35, 64, 14);
		frame.getContentPane().add(plvrAdivinhadalbl);
		
		status = new JLabel("");
		status.setBounds(310, 140, 114, 14);
		frame.getContentPane().add(status);
		
		nomePenalidadeLbl = new JLabel("");
		nomePenalidadeLbl.setBounds(114, 240, 199, 14);
		frame.getContentPane().add(nomePenalidadeLbl);
		
		try {
			  JogoDaForca jogo = new JogoDaForca();
			  

			  iniciar.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			      jogo.adminRESETA_JOGO();
			      jogo.iniciar();
			      lblNewLabel_2.setText(jogo.getTamanho() + "");
			      dicaTextLabel.setText(jogo.getDica());
			      letraTextPane.setText(null);
			      plvrAdivinhadalbl.setText(jogo.getPalavraAdivinhada());
			      acertosTextLabel.setText("0");
			      penalidadesTextLabel.setText("0");
			      indice = 0;
			      sizeImage.setIcon(icon);
			      status.setText(jogo.getResultado());
			      nomePenalidadeLbl.setText(jogo.getNomePenalidade());
			    }
			  });

			  do {
			    btnADIVINHAR.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent ae) {
			        String letra = letraTextPane.getText();
			        ArrayList<Integer> ocorrencias;
			        try {
			          ocorrencias = jogo.getOcorrencias(letra);
			          if (ocorrencias.size() > 0) {
			            JOptionPane.showMessageDialog(null, "Você acertou a letra = " + letra);
			          } else {
			        	  sizeImage.setIcon(bancoImagens.get(indice));
			        	  indice ++;
			        	  nomePenalidadeLbl.setText(jogo.getNomePenalidade());
			              JOptionPane.showMessageDialog(null, "Você errou a letra = " + letra + "\n" + jogo.getNomePenalidade());
			          }

			          int penalidades = jogo.getNumeroPenalidade();
			          int acertos = jogo.getAcertos();

			          penalidadesTextLabel.setText(penalidades + "");
			          acertosTextLabel.setText(acertos + "");
			          plvrAdivinhadalbl.setText(jogo.getPalavraAdivinhada());
			          
			          
			          if(jogo.terminou() == true ) {
			        	  JOptionPane.showMessageDialog(null, jogo.getResultado(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
			        	  status.setText(jogo.getResultado());
			          }

			        } catch (Exception e) {
			          JOptionPane.showMessageDialog(null, e.getMessage());
			        }
			      }
			    });
			    
		
			  } while (jogo.terminou());



			} catch (Exception e) {
			  System.out.println(e.getMessage());
			}

	}
}
