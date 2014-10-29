/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import sk.uniza.fri.duracik2.entity.Hrana;
import sk.uniza.fri.duracik2.entity.Uzol;
import sk.uniza.fri.duracik2.grafy.MaticaVzdalenosti;
import sk.uniza.fri.duracik2.map.Mapa;

/**
 *
 * @author Unlink
 */
public class Tabulka extends javax.swing.JFrame {

	private File aData;
	private FileParser fp;

	/**
	 * Creates new form Tabulka
	 */
	public Tabulka() {
		initComponents();
		setLocationRelativeTo(null);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Matica vzdialeností");

        jButton1.setText("Generuj");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Okresy");

        jButton2.setText("Load Datadir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("No directory loaded");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTabbedPane1.addTab("Tabuľka", jScrollPane1);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jTabbedPane1.addTab("Text", jScrollPane2);

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jTabbedPane1.addTab("Mosel", jScrollPane3);

        jScrollPane4.setViewportView(jTextPane1);

        jTabbedPane1.addTab("Subor1", jScrollPane4);

        jScrollPane5.setViewportView(jTextPane2);

        jTabbedPane1.addTab("Subor2", jScrollPane5);

        jLabel2.setText("Limit");

        jTextField2.setText("50");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jMenu2.setText("Tools");

        jMenuItem1.setText("Mapa SR");
        jMenuItem1.setEnabled(false);
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		jButton1.setEnabled(false);
		new SwingWorker<Object, Double>() {
			
			String[][] tbldata;
			Object[] head;
			String bnt = jButton1.getText();
			StringBuilder sb = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			StringBuilder sb3 = new StringBuilder();
			
			StringBuilder sb4 = new StringBuilder();
			StringBuilder sb5 = new StringBuilder();
			
			@Override
			protected Object doInBackground() throws Exception {
				if (fp != null) {
					publish(0.0);
					MaticaVzdalenosti maticaVzdalenosti = new MaticaVzdalenosti(fp);
					maticaVzdalenosti.addProgressListnerer(new MaticaVzdalenosti.ProgessListenerer() {
						@Override
						public void stateChanged(double paPercentage) {
							publish(paPercentage);
						}
					});
					ArrayList<Uzol> uzly = new ArrayList<>(fp.najdiUzly(fp.najdiOkresy(jTextField1.getText()), false));
					int pocObyvatelov = 0;
					int celkovoDedin = uzly.size();
					for (Uzol u : uzly) {
						pocObyvatelov+=u.getPocObv();
					}
					Collections.sort(uzly, new Comparator<Uzol>() {
						@Override
						public int compare(Uzol paO1, Uzol paO2) {
							return Integer.compare(paO2.getPocObv(), paO1.getPocObv());
						}
					});
					int limit = Integer.parseInt(jTextField2.getText());
					for (int i=limit; i<uzly.size(); ) {
						uzly.remove(uzly.size()-1);
					}
					
					maticaVzdalenosti.riesDjikstraPre(uzly);
				
					head = new Object[uzly.size() + 1];
					tbldata = new String[uzly.size()][uzly.size() + 1];					
					double[][] matica1 = maticaVzdalenosti.precitajRiesenie(uzly);
					sb.append(String.format("%25s", ""));
					sb2.append("celkovoObyvatelov::").append(pocObyvatelov).append("\n");
					sb2.append("celkovoDedin::").append(celkovoDedin).append("\n");
					sb2.append("mesta::[");
					sb3.append("obyvatelia::[");
					for (int i = 0; i < uzly.size(); i++) {
						head[i+1] = uzly.get(i).getNazov();
						tbldata[i][0] = uzly.get(i).getNazov();
						sb.append(String.format("%25s", uzly.get(i).getNazov()));
						sb2.append(String.format("\"%s\", ", uzly.get(i).getNazov()));
						sb3.append(String.format("%d, ", uzly.get(i).getPocObv()));
					}
					sb.append("\n");
					sb2.delete(sb2.length()-2, sb2.length());
					sb2.append("]\n");
					sb3.delete(sb3.length()-2, sb3.length());
					sb3.append("]\n");
					sb2.append(sb3);
					
					sb2.append("matica::[");
					for (int i = 0; i < matica1.length; i++) {
						sb.append(String.format("%25s", uzly.get(i).getNazov()));
						for (int j = 0; j < matica1[0].length; j++) {
							tbldata[i][j + 1] = String.format("%.3f", matica1[i][j]);
							sb.append(String.format("%25s", String.format("%.3f", matica1[i][j])));
							sb2.append(String.format(Locale.US, "%.3f, ", matica1[i][j]));
						}
						sb.append("\n");
						sb2.delete(sb2.length()-1, sb2.length());
						sb2.append("\n");
					}
					sb2.delete(sb2.length()-2, sb2.length());
					sb2.append("]\n");
					
					for (Uzol u : fp.najdiUzly(fp.najdiOkresy(jTextField1.getText()), true)) {
						sb4.append(u.getId());
						sb4.append("\t").append(u.getNazov());
						sb4.append("\t").append(u.getX());
						sb4.append("\t").append(u.getY()).append("\n");
					}
					
					for (Hrana h : fp.najdiHrany(fp.najdiUzly(fp.najdiOkresy(jTextField1.getText()), true))) {
						sb5.append(h.getId());
						sb5.append("\t").append(h.getU1().getId());
						sb5.append("\t").append(h.getU2().getId());
						sb5.append("\t").append(h.getDlzka()).append("\n");
					}
					
					return 1;
				}
				tbldata = new String[0][0];
				head = new Object[0];
				return 1;
			}

			@Override
			protected void done() {
				try {
					jTable1.setModel(new DefaultTableModel(tbldata, head));
					jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					jTextArea1.setText(sb.toString());
					jTextArea2.setText(sb2.toString());
					
					jTextPane1.setText(sb4.toString());
					jTextPane2.setText(sb5.toString());
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				finally {
					jButton1.setEnabled(true);
					jButton1.setText(bnt);
				}
			}

			@Override
			protected void process(List<Double> paChunks) {
				jButton1.setText(String.format("%.1f%s", paChunks.get(paChunks.size()-1)*100, "% hotovo"));
			}
			
			
		}.execute();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			aData = jfc.getSelectedFile();
			jLabel3.setText(aData.getAbsolutePath());
			jButton2.setEnabled(false);
			new SwingWorker<Object, Object> () {

				@Override
				protected Object doInBackground() throws Exception {
					fp = new FileParser(aData);
					fp.nahrajHrany();//Nacita data do pamate
					return fp;
				}

				@Override
				protected void done() {
					jButton1.setEnabled(true);
					jButton2.setEnabled(true);
					jMenuItem1.setEnabled(true);
				}
				
			}.execute();
		}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new Mapa(this, fp).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Tabulka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Tabulka().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables
}
