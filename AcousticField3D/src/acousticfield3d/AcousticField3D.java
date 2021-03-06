/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acousticfield3d;

import acousticfield3d.gui.MainForm;
import acousticfield3d.utils.FileUtils;
import acousticfield3d.utils.Parse;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asier
 */
public class AcousticField3D {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AcousticField3D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AcousticField3D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AcousticField3D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AcousticField3D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //try to load config file
        Config config = new Config();
        
        try {
            config = (Config) FileUtils.readObject( new File( MainForm.CONFIG_PATH ));
        } catch (Exception ex) {
        }
        
        MainForm t = new MainForm(config);
        t.setVisible(true);
        t.setLocationRelativeTo(null);
        
        if (args.length > 0){
            for(String s : args){
                char firstChar = s.charAt(0);
                s = s.substring(1);
                
                if(firstChar == 's'){ //autoload simulation s file
                    t.loadSimulation( s );
                }else if(firstChar == 'c'){ //auto connect c number
                    final int portNumber = Parse.toInt( s );
                    t.transControlPanel.initComm(portNumber);
                }else if(firstChar == 'b'){ //autoselect first bead b
                    t.movePanel.selectFirstBead();
                }
               
            }
        }
    }
    
}
