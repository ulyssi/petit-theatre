package utils;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;

/**
 * Example from Chapter 3
 * 
 * Simple object to prompt for user id/password.
 * 
 * @author Jeff Heaton
 * @version 1.0
 */
public class UserCategorieAddDialog extends javax.swing.JDialog {
  public UserCategorieAddDialog(Frame parent) {
    super(parent, true);

    //{{INIT_CONTROLS
    setTitle("Categorie ADD");
    getContentPane().setLayout(null);
    setSize(403, 129);
    setVisible(false);
    JLabel1.setText("Categorie:");
    getContentPane().add(JLabel1);
    JLabel1.setBounds(12, 12, 72, 24);
    JLabel2.setText("Prix:");
    getContentPane().add(JLabel2);
    JLabel2.setBounds(12, 48, 72, 24);
    getContentPane().add(_categorie);
    _categorie.setBounds(84, 12, 312, 24);
    _ok.setText("OK");
    getContentPane().add(_ok);
    _ok.setBounds(60, 84, 84, 24);
    getContentPane().add(_prix);
    _prix.setBounds(84, 48, 312, 24);
    _cancel.setText("Cancel");
    getContentPane().add(_cancel);
    _cancel.setBounds(264, 84, 84, 24);
    //}}

    //{{REGISTER_LISTENERS
    SymAction lSymAction = new SymAction();
    _ok.addActionListener(lSymAction);
    _cancel.addActionListener(lSymAction);
    //}}
  }

  public void setVisible(boolean b) {
    if (b)
      setLocation(50, 50);
    super.setVisible(b);
  }

  public void addNotify() {
    // Record the size of the window prior to calling parents addNotify.
    Dimension size = getSize();

    super.addNotify();

    if (frameSizeAdjusted)
      return;
    frameSizeAdjusted = true;

    // Adjust size of frame according to the insets
    Insets insets = getInsets();
    setSize(insets.left + insets.right + size.width, insets.top
        + insets.bottom + size.height);
  }

  // Used by addNotify
  boolean frameSizeAdjusted = false;

  //{{DECLARE_CONTROLS
  javax.swing.JLabel JLabel1 = new javax.swing.JLabel();

  javax.swing.JLabel JLabel2 = new javax.swing.JLabel();

  /**
   * The user ID entered.
   */
  javax.swing.JTextField _categorie = new javax.swing.JTextField();

  /**
   */
  javax.swing.JButton _ok = new javax.swing.JButton();

  /**
   * The password is entered.
   */
  javax.swing.JTextField _prix = new javax.swing.JTextField();

  javax.swing.JButton _cancel = new javax.swing.JButton();
  
  int getPrix() {
      return Integer.parseInt(_prix.getText());
  }
  
  String getNom() {
	  return _categorie.getText();
  }

  //}}

  class SymAction implements java.awt.event.ActionListener {
    public void actionPerformed(java.awt.event.ActionEvent event) {
      Object object = event.getSource();
      if (object == _ok)
        Ok_actionPerformed(event);
      else if (object == _cancel)
        Cancel_actionPerformed(event);
    }
  }

  /**
   * Called when ok is clicked.
   * 
   * @param event
   */
  void Ok_actionPerformed(java.awt.event.ActionEvent event) {
    setVisible(false);
  }

  /**
   * Called when cancel is clicked.
   * 
   * @param event
   */
  void Cancel_actionPerformed(java.awt.event.ActionEvent event) {
    _categorie.setText("");
    _prix.setText("");
    setVisible(false);
  }
}
