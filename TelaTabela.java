
import java.util.ArrayList;
import java.io.*;
import java.text.*;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ubiratan
 */
public class TelaTabela  extends javax.swing.JFrame {
	String stringPhi;
	Formula phi;
	Atomica[] atomica;
    String date;
    File file;
        
   	TelaTabela (Formula form, Atomica[] at, String nome , String data) {
   		setTitle(nome);
		initComponents();
        phi = form;
		atomica = at;
		stringPhi = nome;
        this.date= data; 
        del.setEnabled(false);
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
        inicializar();
                gerarTabela();
                
	} 
	
	void inicializar() {
		if (atomica != null) for (int i = 0; i < atomica.length; i++) atomica[i].escolherBitPos(i); // a posicao da prop at no array corresponde a sua pos no num bin
	}

	void mostrarCabecalho() {
		if (atomica != null)
		{
       
			for (int i = atomica.length-1; i >= 0; i--) areaTxt.setText( areaTxt.getText()+atomica[i]+"\t"); // 0 corresponde ao LBS (menos sig); atomica.length-1 ao MBS (mais sig)
			areaTxt.setText(areaTxt.getText() + stringPhi + "\n");
		}
	}
	// mostra os val verd das prop atomicas e da formula 
	void mostrarLinha() {
          
		if (atomica != null)
		{
			for (int i = atomica.length-1; i >= 0; i--) areaTxt.setText(areaTxt.getText()+ atomica[i].obterValor()+"\t");
			areaTxt.setText(areaTxt.getText() +phi.obterValor()+ "\n");
		}
	}

	void incrementarValoresAt() {
		if (atomica != null) for (Atomica prop : atomica) prop.incrementar(); // incrementa o contador das prop at, uma por uma
	}

	void verificarValoresAt() {
		if (atomica != null)
			for (Atomica prop : atomica)
			{
				if (prop.obterContador() == prop.obterContMax()) // troca os valores das prop at cujo esteja com o contador no limite
				{
					prop.zerarCont();
					prop.trocarValor();
				}
			}
	}

	void gerarTabela() {
		int linha, qtdTotalLinhas;
			
		linha = 1;
		qtdTotalLinhas = 2 * atomica[ atomica.length-1 ].obterContMax(); // o total de linhas da tab verd corresponde a 2 elevado a pos do MSB;
		
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

        jScrollPane1 = new javax.swing.JScrollPane();
        areaTxt = new javax.swing.JTextArea();
        save = new javax.swing.JButton();
        del = new javax.swing.JButton();
        voltar = new javax.swing.JButton();

        del.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
                del.setEnabled(false);
        		//areaTxt.setText("");
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
        areaTxt.setEnabled(false);
        areaTxt.setPreferredSize(new java.awt.Dimension(353, 230));
        jScrollPane1.setViewportView(areaTxt);

        save.setText("Salvar Tabela");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        del.setText("Excluir Tabela");

        voltar.setText("voltar");
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

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed

        if(!areaTxt.getText().isEmpty()){

            file = new File("./Tabela gerada em "+date+ ".txt");
            try{
                file.createNewFile();
            }
            catch(IOException io){
                io.printStackTrace();
            }
            
            try{
                FileWriter fw  = new FileWriter(file.getAbsoluteFile());
                BufferedWriter wr = new BufferedWriter(fw);
                String str[] = this.areaTxt.getText().split("\\n");
                for(String linha : str){
                    wr.write(linha+"\n");
                }
                
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
