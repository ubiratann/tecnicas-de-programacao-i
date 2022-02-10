package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

import model.Atomica;
import model.Formula;


public class TelaTabela  extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	String stringPhi;
	Formula phi;
	Atomica[] atomica;
    String date;
    File file; //arquivo declarado como universal para que as classes consigam
                // criar e excluir sempre a mesma instância de arquivo
        
   	public TelaTabela (Formula form, Atomica[] at, String nome , String data) {
   		setTitle(nome);
		initComponents(); //metodo que faz a instancia de todos o componentes da interface gráfica
        phi = form; //formula recebida pelo leitor
		atomica = at; //conjunto de formulas atômicas
		stringPhi = nome; //
        this.date= data; //data que vai para o nome do arquivo
        del.setEnabled(false); //botao del só é habilitado se salvar o arquivo antes
        this.setLocationRelativeTo(this); //posicao da janela
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//a janela apenas some sem encerrar o aplicativo
		
        inicializar();
        gerarTabela();
                
	} 
	
	void inicializar() {
		if (atomica != null) for (int i = 0; i < atomica.length; i++) atomica[i].escolherBitPos(i);
		// a posicao da prop at no array corresponde a sua pos no num bin
	}

	void mostrarCabecalho() {
		if (atomica != null)
		{
       
			for (int i = atomica.length-1; i >= 0; i--) areaTxt.setText( areaTxt.getText()+atomica[i]+"\t");
			// 0 corresponde ao LSB (menos sig); atomica.length-1 ao MSB (mais sig)
			areaTxt.setText(areaTxt.getText() + stringPhi + "\n");
		}
	}
	// mostra os val verd das prop atomicas e da formula 
	void mostrarLinha() {
          
		if (atomica != null)
		{
			for (int i = atomica.length-1; i >= 0; i--) areaTxt.setText(areaTxt.getText()+ atomica[i].getValor()+"\t");
			areaTxt.setText(areaTxt.getText() +phi.getValor()+ "\n");
		}
	}

	void incrementarValoresAt() {
		if (atomica != null) for (Atomica prop : atomica) prop.incrementarContador();
		// incrementa o contador das prop at, uma por uma
	}

	void verificarValoresAt() {
		if (atomica != null)
			for (Atomica prop : atomica)
			{
				if (prop.getContador() == prop.getContadorMax())
				    // troca os valores das prop at cujo esteja com o contador no limite
				{
					prop.zerarContador();
					prop.trocarValor();
				}
			}
	}

	void gerarTabela() {
		int linha, qtdTotalLinhas;
			
		linha = 1;
		qtdTotalLinhas = 2 * atomica[ atomica.length-1 ].getContadorMax();
		// o total de linhas da tab verd corresponde a 2 elevado a pos do MSB;
		
		mostrarCabecalho();

		while (linha <= qtdTotalLinhas)
		{
			mostrarLinha();
			
			incrementarValoresAt();
			
			verificarValoresAt();
			
			linha++;
		}
	} 
    
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane(); //painel onde ficam os componentes
        areaTxt = new javax.swing.JTextArea(); //area de texto que a tabela vai aparecer
        save = new javax.swing.JButton(); //botao de salvar
        del = new javax.swing.JButton(); //botao deletar
        voltar = new javax.swing.JButton(); //botao voltar


        del.setText("Excluir Tabela");
        //evento de deletar arquivo de tabela já gerada
        del.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
                del.setEnabled(false);

                try{
                    file.delete();
                }catch(Exception io){
                    io.printStackTrace();
                }
        	}
        	
        });
       

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        areaTxt.setEditable(false);
        areaTxt.setColumns(20);
        areaTxt.setRows(5);
        areaTxt.setPreferredSize(new java.awt.Dimension(353, 230));
        jScrollPane1.setViewportView(areaTxt);

        //evento que chama o metodo de salvar um arquivo
        save.setText("Salvar Tabela");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        //evento que chama o metodo de voltar para a calculadora
        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(del)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(save)
        					.addPreferredGap(ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
        					.addComponent(voltar))
        				.addComponent(jScrollPane1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addGap(19)
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(del)
        				.addComponent(voltar)
        				.addComponent(save))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }

    //clase que cria e salva um arquivo com a data em que a tabela foi gerada
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed

        if(!areaTxt.getText().isEmpty()){

            file = new File("./Tabela gerada em "+date+ ".txt");
            try{
                file.createNewFile();
            }
            catch(IOException io){
                io.printStackTrace();
            }
                //tratamento para criacao do arquivo
            try{
                FileWriter fw  = new FileWriter(file.getAbsoluteFile());
                BufferedWriter wr = new BufferedWriter(fw);
                String str[] = this.areaTxt.getText().split("\\n");

                for(String linha : str){
                    wr.write(linha+"\n");
                } //escrevendo linhas da tabela mostrada no arquivo
                
                wr.close();
                del.setEnabled(true);
            }catch(IOException io){
                io.printStackTrace();
            }
        }else{
                save.setEnabled(false);
            }
    }

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
    	 dispose();
    }
    private javax.swing.JTextArea areaTxt;
    private javax.swing.JButton del;
    private javax.swing.JButton voltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton save;
}
