// Pacote que armazena classe Login
package gui;

// Bibliotecas usadas
import banco.Conexao;
import dados.Usuario;
import dao.UsuarioDAO;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    // Variáveis de conexão
    Conexao conexao;
    Connection connection;
    
    // Método que inicializa os componentes da tela
    public Login() {
        initComponents();
    }

    // Métodos de Conexão
    private void abrirConexao()
    {
        conexao = new Conexao("LEANDRO-VAIO\\SQLEXPRESS", "BD2", "admin", "sqlpassword");
        connection = conexao.connect();
    }
    
    private void fecharConexao()
    {
        connection = conexao.disconnect();
    }
    
    private boolean consistirCamposClassificacao()
    {
        boolean consistir = true;
        String CodClassificacao = txtLogin.getText();
        String Classificacao = new String(txtSenha.getPassword());     
        
        // Verifica se existe campo vazio
        String[] Fila = new String[]{CodClassificacao, Classificacao};
        String[] FilaNome = new String[]{"Usuário", "Senha"};      
        for (int i = 0; i < Fila.length; i++)
        {
            if(Fila[i].equals(""))
            {
                // Abre a janela de Aviso
                JOptionPane.showMessageDialog(null, "O Campo " + FilaNome[i] + " deve ser preenchido!", "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
                
                // Da o foco no campo requerido
                if(i == 0){txtLogin.requestFocus();}
                if(i == 1){txtSenha.requestFocus();}
                consistir = false;
                break;
            }
        }    
        return consistir;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuario = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setBackground(new java.awt.Color(3, 188, 123));

        lblUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblUsuario.setText("Usuário :");

        lblSenha.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblSenha.setText("Senha :");

        txtLogin.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtSenha.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        btnEntrar.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSenha)
                            .addComponent(lblUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(335, 335, 335))
                            .addComponent(txtLogin))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEntrar)
                    .addComponent(btnSair))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        // Abrir conexão com o banco sql server
        abrirConexao();     
        
        // Variáveis com informação da tela
        String login = txtLogin.getText();
        String senha = new String(txtSenha.getPassword());
        
        // Instância da classe DAO 
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if(consistirCamposClassificacao())
        {
             // Realiza chamada DAO
            if (usuarioDAO.verificarUsuario(connection, new Usuario(0, login, senha, "", "")))
            {
                this.dispose();
                Principal principal = new Principal();
                principal.setTitle("Gerenciamento de Senha");
                principal.setLocationRelativeTo(null);
                new Principal().setVisible(true);

            }
            else
            {
                JOptionPane.showMessageDialog(this, "Usuário não Cadastrado!");
            }
        }    
        
        // Fechar conexão com o banco sql server
        //fecharConexao();
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // Fecha aplicativo
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login login = new Login();
                login.setSize(640, 480);
                login.setTitle("Login");
                login.setLocationRelativeTo(null);
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
