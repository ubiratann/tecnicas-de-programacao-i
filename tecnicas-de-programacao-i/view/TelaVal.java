
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.BadLocationException;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Component;

 
public class TelaVal extends javax.swing.JFrame {
    
    /**
     * Creates new form TelaVal
     */
    public TelaVal() {
        initComponents();
        initalEnabledComponents();
        this.setTitle("Calculadora de Valora\u00E7\u00F5es L\u00F3gicas");
        this.setResizable(false);
        this.setLocationRelativeTo(this);
    }


    private void habilita(){
        setVariaveis(true);
        setConectivos(true);
        pAb.setEnabled(true);
        pFc.setEnabled(true);
        
        
    }

   void setVariaveis(boolean valor){
        pAb.setEnabled(valor);
        btA.setEnabled(valor);
        btB.setEnabled(valor);
        btC.setEnabled(valor);
        btD.setEnabled(valor);
        btE.setEnabled(valor);
        btNeg.setEnabled(valor); 
  //      System.out.println(verParent());
        if(verParent()){
            pFc.setEnabled(false);
            calcular.setEnabled(true);  
        }else
            pFc.setEnabled(true);
    }
    
    private void setConectivos(boolean valor){
        btAnd.setEnabled(valor);
        btOr.setEnabled(valor);
        btNeg.setEnabled(valor);
        pFc.setEnabled(valor);
        if(verParent())
            calcular.setEnabled(false);  
       
           
    }
    
    private void tratamento(String tipo){
        String key = tipo;
     
        habilita();
        switch (key){
            case "'":
                setConectivos(true);
                setVariaveis(false);
               
                break;
            case "(":              
                btNeg.setEnabled(false);  
                pFc.setEnabled(false);
                btAnd.setEnabled(false);
                btOr.setEnabled(false);                
                calcular.setEnabled(false);
                break;
            case ")":
                pAb.setEnabled(false);
                setVariaveis(false);
                if(verParent())
                    calcular.setEnabled(true);
                else
                    calcular.setEnabled(false);
                 btNeg.setEnabled(true); 
                break;
            case "+":
                setVariaveis(true);
                setConectivos(false);
                break;
            case ".":             
                setVariaveis(true);
                setConectivos(false);
                break;
            case "A":        
                setConectivos(true);
                setVariaveis(false);
                break;
            case "B":
                              
                setConectivos(true);
                setVariaveis(false);
                break;
            case "C":
             
                setConectivos(true);
                setVariaveis(false);
                break;
            case "D":
                setConectivos(true);
                setVariaveis(false);
                break;
            case "E":               
                setConectivos(true);
                setVariaveis(false);
                break;
        }
    }
    
    private void initalEnabledComponents(){
        setVariaveis(true);
        pAb.setEnabled(true);
        setConectivos(false);
    }
    private void initComponents() {
        cxTxt = new javax.swing.JTextField();
        cxTxt.setToolTipText("");
        cxTxt.setEnabled(false);
        btA = new javax.swing.JButton();
        btB = new javax.swing.JButton();
        btC = new javax.swing.JButton();
        btD = new javax.swing.JButton();
        btE = new javax.swing.JButton();
        calcular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        cxTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cxTxtKeyTyped(evt);
            }
        });

        btA.setText("A");
        btA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAActionPerformed(evt);
            }
        });

        btB.setText("B");
        btB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBActionPerformed(evt);
            }
        });

        btC.setText("C");
        btC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCActionPerformed(evt);
            }
        });

        btD.setText("D");
        btD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDActionPerformed(evt);
            }
        });

        btE.setText("E");
        btE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEActionPerformed(evt);
            }
        });

        calcular.setText("calcular");
        calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularActionPerformed(evt);
            }
        });
        
        JButton apagar = new JButton("apagar");
        apagar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		 
        		try {
					if(!cxTxt.getText().isEmpty())
                        if(cxTxt.getText().length() == 1)
                        {    
                          initalEnabledComponents();
                          String aux = cxTxt.getText(0, cxTxt.getText().length()-1);
                          aux = aux.toUpperCase();
                          cxTxt.setText(aux);
                        }
                        else{
                            String aux1 = cxTxt.getText().charAt(cxTxt.getText().length()-2)+"";
                            aux1 = aux1.toUpperCase();  
                            tratamento(cxTxt.getText().charAt(cxTxt.getText().length()-2)+"");
                            cxTxt.setText(cxTxt.getText(0, cxTxt.getText().length()-1));
				        }
        		} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  
        		
        	}
        });
        
        
        //************************************************************
        
        
        JPanel options = new JPanel();
        /*
        options.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("TESTESSSS");
				}
				
			}
		});*/
        
        options.setBackground(Color.WHITE);
        
        lblFormulaLogica = new JLabel("Fórmulas Lógicas");
        pAb = new javax.swing.JButton();
        
                pAb.setText("(");
                pAb.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        pAbActionPerformed(evt);
                    }
                });
        pFc = new javax.swing.JButton();
        
                pFc.setText(")");
                pFc.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        pFcActionPerformed(evt);
                    }
                });
        btOr = new javax.swing.JButton();
        
                btOr.setText("+");
                btOr.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btOrActionPerformed(evt);
                    }
                });
        btAnd = new javax.swing.JButton();
        
                btAnd.setText(".");
                btAnd.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btAndActionPerformed(evt);
                    }
                });
        btNeg = new javax.swing.JButton();
        
                btNeg.setText("'");
                btNeg.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btNegActionPerformed(evt);
                    }
                });
        
         lblIcone = new JLabel("");
         lblIcone.setIcon(new ImageIcon(TelaVal.class.getResource("/imagens/logo.png")));
        
        
        
        //************************************************************    

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(options);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(53)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(lblFormulaLogica, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 214, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(btA)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btB)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btC)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btD)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btE))
        						.addComponent(cxTxt, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(pAb)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(pFc)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btOr)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btAnd)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btNeg, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(apagar)
        						.addComponent(calcular))))
        			.addGap(45))
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(128, Short.MAX_VALUE)
        			.addComponent( lblIcone)
        			.addGap(114))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent( lblIcone)
        			.addGap(29)
        			.addComponent(lblFormulaLogica)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(cxTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(apagar))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(22)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(btA)
        						.addComponent(btB)
        						.addComponent(btC)
        						.addComponent(btD)
        						.addComponent(btE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(pAb)
        						.addComponent(pFc)
        						.addComponent(btOr)
        						.addComponent(btAnd)
        						.addComponent(btNeg)))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(18)
        					.addComponent(calcular, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        			.addContainerGap(185, Short.MAX_VALUE))
        );
        
     
 //************************************************************
        
        JPanel mainLayout = new JPanel();
        mainLayout.setLayout(new GridLayout(0, 1, 0, 0));
        
      
        options.setLayout(layout);
        
        mainLayout.add(options);
        getContentPane().add(mainLayout);
        
        
        //************************************************************

        
        
    
        pack();
    }
    private boolean verParent(){
        int contF = 0;
        int contA = 0;
        String[] camp = cxTxt.getText().split(""); 
        for(String a : camp) {
            if( a.equals("("))
                contA++;
            if(a.equals(")"))
                contF++;
        }
        
        if(contA > contF)
            return false;
        return true;
    }
    
    
    
    private void btOrActionPerformed(java.awt.event.ActionEvent evt) {
       tratamento(btOr.getText());
       cxTxt.setText(cxTxt.getText() + btOr.getText());
       
    }
   
    private void btAActionPerformed(java.awt.event.ActionEvent evt) {
        cxTxt.setText(cxTxt.getText() + btA.getText());
        tratamento(btA.getText());
        Atomica a  = new Atomica("A");

    }

    private void btBActionPerformed(java.awt.event.ActionEvent evt) {
        tratamento(btB.getText());
        cxTxt.setText(cxTxt.getText() + btB.getText());
        Atomica b = new Atomica("B");

      
    }

    private void btCActionPerformed(java.awt.event.ActionEvent evt) {
        cxTxt.setText(cxTxt.getText() + btC.getText());
        tratamento(btC.getText());
        Atomica c = new Atomica("C");
      
    }

    private void btDActionPerformed(java.awt.event.ActionEvent evt) {
        cxTxt.setText(cxTxt.getText() + btD.getText());
        tratamento(btD.getText());
      
    }

    private void btEActionPerformed(java.awt.event.ActionEvent evt) {
        cxTxt.setText(cxTxt.getText() + btE.getText());
        tratamento(btE.getText());        
       
    }

    private void calcularActionPerformed(java.awt.event.ActionEvent evt) {
      
           String phi = cxTxt.getText(); 
           Leitor l = new Leitor(phi);
            String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

           String date = simpleDateFormat.format(new Date()); 
           TelaTabela t = new TelaTabela(l.lerFormula(), l.obterAtomicas(), l.stringFormula(), date);
           t.setVisible(true);
 
    }

    private void btAndActionPerformed(java.awt.event.ActionEvent evt) {
       cxTxt.setText(cxTxt.getText() + btAnd.getText());
        tratamento(btAnd.getText());

    }

    private void btNegActionPerformed(java.awt.event.ActionEvent evt) {
       cxTxt.setText(cxTxt.getText() + btNeg.getText());
        tratamento(btNeg.getText());

    }

    private void pAbActionPerformed(java.awt.event.ActionEvent evt) {
       cxTxt.setText(cxTxt.getText() + pAb.getText()); 
        tratamento(pAb.getText());
          
    }

    private void pFcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pFcActionPerformed
       
       cxTxt.setText(cxTxt.getText() + pFc.getText());
       tratamento(pFc.getText());
    }

    private void cxTxtKeyTyped(java.awt.event.KeyEvent evt) {
        
    }
    
   
    private javax.swing.JButton btA;
    private javax.swing.JButton btAnd;
    private javax.swing.JButton btB;
    private javax.swing.JButton btC;
    private javax.swing.JButton btD;
    private javax.swing.JButton btE;
    private javax.swing.JButton btNeg;
    private javax.swing.JButton btOr;
    private javax.swing.JButton calcular;
    private javax.swing.JTextField cxTxt;
    private javax.swing.JButton pAb;
    private javax.swing.JButton pFc;
    private JLabel lblFormulaLogica;
    private JLabel  lblIcone;
}
