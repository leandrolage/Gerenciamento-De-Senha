// Pacote que armazena classe Principal
package gui;

// Bibliotecas usadas
import banco.Conexao;
import dados.Aplicacao;
import dados.Classificacao;
import dados.Relacao;
import dados.Usuario;
import dao.AplicacaoDAO;
import dao.ClassificacaoDAO;
import dao.RelacaoDAO;
import dao.UsuarioDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.sql.Connection;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicPanelUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Principal extends javax.swing.JFrame {

    // Variáveis de conexão
    Conexao conexao;
    Connection connection;
    
    // Variáveis de fluxo do código
    public boolean IncluirClicado = false;
    public boolean AlterarClicado = false;
    public boolean GravarClicado = false;
    
    // Classe usada para criação do gradiente do Panel
    public class TestPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = new Color(3,188,123);
        Color color2 = new Color(0,169,157);
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
 }
    
    // Método que inicializa os componentes da tela
    public Principal() {
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
       
    // Métodos que formatam as tabelas da tela
    private void formatarTabelaAplicacao()
    {
        // Escolhe o tamanho da coluna na tabela
        tblAplicacao.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblAplicacao.getColumnModel().getColumn(1).setPreferredWidth(395);
        tblAplicacao.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblAplicacao.getColumnModel().getColumn(3).setPreferredWidth(90);
        tblAplicacao.getColumnModel().getColumn(4).setPreferredWidth(395);
        tblAplicacao.getColumnModel().getColumn(5).setPreferredWidth(500);
        
        // Escolhe tamanho minimo e maximo de cada coluna da tabela 
        tblAplicacao.getColumnModel().getColumn(0).setMinWidth(80);
        tblAplicacao.getColumnModel().getColumn(0).setMaxWidth(110);
        tblAplicacao.getColumnModel().getColumn(1).setMinWidth(395);
        tblAplicacao.getColumnModel().getColumn(1).setMaxWidth(415);
        tblAplicacao.getColumnModel().getColumn(2).setMinWidth(40);
        tblAplicacao.getColumnModel().getColumn(2).setMaxWidth(50);
        tblAplicacao.getColumnModel().getColumn(3).setMinWidth(90);
        tblAplicacao.getColumnModel().getColumn(3).setMaxWidth(110);
        tblAplicacao.getColumnModel().getColumn(4).setMinWidth(395);
        tblAplicacao.getColumnModel().getColumn(4).setMaxWidth(415);
        tblAplicacao.getColumnModel().getColumn(5).setMinWidth(500);
        tblAplicacao.getColumnModel().getColumn(5).setMaxWidth(510);
    }
    
    private void formatarTabelaClassificacao()
    {
        // Escolhe o tamanho da coluna na tabela
        tblClassificacao.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblClassificacao.getColumnModel().getColumn(1).setPreferredWidth(1200);
        
        // Escolhe tamanho minimo e maximo de cada coluna da tabela 
        tblClassificacao.getColumnModel().getColumn(0).setMinWidth(90);
        tblClassificacao.getColumnModel().getColumn(0).setMaxWidth(100);
        tblClassificacao.getColumnModel().getColumn(1).setMinWidth(1200);
        tblClassificacao.getColumnModel().getColumn(1).setMaxWidth(1220);
    }
      
    private void formatarTabelaUsuario()
    {
        // Escolhe o tamanho da coluna na tabela
        tblUsuario.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblUsuario.getColumnModel().getColumn(1).setPreferredWidth(720);
        tblUsuario.getColumnModel().getColumn(2).setPreferredWidth(180);
        tblUsuario.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblUsuario.getColumnModel().getColumn(4).setPreferredWidth(80);
        
        // Escolhe tamanho minimo e maximo de cada coluna da tabela 
        tblUsuario.getColumnModel().getColumn(0).setMinWidth(90);
        tblUsuario.getColumnModel().getColumn(0).setMaxWidth(100);
        tblUsuario.getColumnModel().getColumn(1).setMinWidth(720);
        tblUsuario.getColumnModel().getColumn(1).setMaxWidth(775);
        tblUsuario.getColumnModel().getColumn(2).setMinWidth(180);
        tblUsuario.getColumnModel().getColumn(2).setMaxWidth(200);
        tblUsuario.getColumnModel().getColumn(3).setMinWidth(80);
        tblUsuario.getColumnModel().getColumn(3).setMaxWidth(100);
        tblUsuario.getColumnModel().getColumn(4).setMinWidth(80);
        tblUsuario.getColumnModel().getColumn(4).setMaxWidth(100);
    }
    
    private void formatarTabelaRelacao()
    {
        // Escolhe o tamanho da coluna na tabela
        tblRelacao.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblRelacao.getColumnModel().getColumn(1).setPreferredWidth(325);
        tblRelacao.getColumnModel().getColumn(2).setPreferredWidth(90);
        tblRelacao.getColumnModel().getColumn(3).setPreferredWidth(415);
        tblRelacao.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblRelacao.getColumnModel().getColumn(5).setPreferredWidth(70);
        tblRelacao.getColumnModel().getColumn(6).setPreferredWidth(70);
        tblRelacao.getColumnModel().getColumn(7).setPreferredWidth(70);
        tblRelacao.getColumnModel().getColumn(8).setPreferredWidth(150);
        
        // Escolhe tamanho minimo e maximo de cada coluna da tabela 
        tblRelacao.getColumnModel().getColumn(0).setMinWidth(90);
        tblRelacao.getColumnModel().getColumn(0).setMaxWidth(100);
        tblRelacao.getColumnModel().getColumn(1).setMinWidth(325);
        tblRelacao.getColumnModel().getColumn(1).setMaxWidth(330);
        tblRelacao.getColumnModel().getColumn(2).setMinWidth(90);
        tblRelacao.getColumnModel().getColumn(2).setMaxWidth(100);
        tblRelacao.getColumnModel().getColumn(3).setMinWidth(415);
        tblRelacao.getColumnModel().getColumn(3).setMaxWidth(430);
        tblRelacao.getColumnModel().getColumn(4).setMinWidth(70);
        tblRelacao.getColumnModel().getColumn(4).setMaxWidth(90);
        tblRelacao.getColumnModel().getColumn(5).setMinWidth(70);
        tblRelacao.getColumnModel().getColumn(5).setMaxWidth(90);
        tblRelacao.getColumnModel().getColumn(6).setMinWidth(70);
        tblRelacao.getColumnModel().getColumn(6).setMaxWidth(90);
        tblRelacao.getColumnModel().getColumn(7).setMinWidth(70);
        tblRelacao.getColumnModel().getColumn(7).setMaxWidth(90);
        tblRelacao.getColumnModel().getColumn(8).setMinWidth(130);
        tblRelacao.getColumnModel().getColumn(8).setMaxWidth(150);
    }
        
    private void formatarTabelaClassificacaoUsuario()
    {
        // Escolhe o tamanho da coluna na tabela
        tblClassificacaoUsuario.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblClassificacaoUsuario.getColumnModel().getColumn(1).setPreferredWidth(300);
        
        // Escolhe tamanho minimo e maximo de cada coluna da tabela 
        tblClassificacaoUsuario.getColumnModel().getColumn(0).setMinWidth(90);
        tblClassificacaoUsuario.getColumnModel().getColumn(0).setMaxWidth(100);
        tblClassificacaoUsuario.getColumnModel().getColumn(1).setMinWidth(300);
        tblClassificacaoUsuario.getColumnModel().getColumn(1).setMaxWidth(320);
    }
    
    private void formatarTabelaClassificacaoAplicacaoUsuario()
    {
        // Escolhe o tamanho da coluna na tabela
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(1).setPreferredWidth(415);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(3).setPreferredWidth(70);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(5).setPreferredWidth(70);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(6).setPreferredWidth(150);
        
       // Escolhe tamanho minimo e maximo de cada coluna da tabela 
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(0).setMinWidth(90);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(0).setMaxWidth(100);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(1).setMinWidth(415);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(1).setMaxWidth(430);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(2).setMinWidth(70);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(2).setMaxWidth(90);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(3).setMinWidth(70);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(3).setMaxWidth(90);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(4).setMinWidth(70);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(4).setMaxWidth(90);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(5).setMinWidth(70);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(5).setMaxWidth(90);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(6).setMinWidth(70);
        tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(6).setMaxWidth(90);
    }
     
    // Métodos que consistem os campos da tela
    private boolean consistirCamposAplicacao()
    {
        boolean consistir = true;
        String CodAplicacao = txtCodAplicacaoAplicacao.getText();
        String Nome = txtNomeAplicacao.getText();
        String Nivel = txtNivelAplicacao.getText();
        String CodClassificacao = txtCodClassificacaoAplicacao.getText();
        
       // Verifica se existe campo vazio
        String[] Fila = new String[]{CodAplicacao, Nome, Nivel, CodClassificacao};
        String[] FilaNome = new String[]{"CodAplicação", "Nome", "Nivel", "CodClassificação"};      
        for (int i = 0; i < Fila.length; i++)
        {
            if(Fila[i].equals(""))
            {
                // Abre a janela de Aviso
                JOptionPane.showMessageDialog(null, "O Campo " + FilaNome[i] + " deve ser preenchido!", "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
                
                // Da o foco no campo requerido
                if(i == 0){txtCodAplicacaoAplicacao.requestFocus();}
                if(i == 1){txtNomeAplicacao.requestFocus();}
                if(i == 2){txtNivelAplicacao.requestFocus();}
                if(i == 3){txtCodClassificacaoAplicacao.requestFocus();}
                consistir = false;
                break;
            }
        }    
        return consistir;
    }
    
    private boolean consistirCamposClassificacao()
    {
        boolean consistir = true;
        String CodClassificacao = txtCodClassificacaoClassificacao.getText();
        String Classificacao = txtClassificacaoClassificacao.getText();
        
        // Verifica se existe campo vazio
        String[] Fila = new String[]{CodClassificacao, Classificacao};
        String[] FilaNome = new String[]{"CodClassificação", "Classificação"};      
        for (int i = 0; i < Fila.length; i++)
        {
            if(Fila[i].equals(""))
            {
                // Abre a janela de Aviso
                JOptionPane.showMessageDialog(null, "O Campo " + FilaNome[i] + " deve ser preenchido!", "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
                
                // Da o foco no campo requerido
                if(i == 0){txtCodClassificacaoClassificacao.requestFocus();}
                if(i == 1){txtClassificacaoClassificacao.requestFocus();}
                consistir = false;
                break;
            }
        }    
        return consistir;
    }
        
    private boolean consistirCamposUsuario()
    {
        boolean consistir = true;
        String CodUsuario = txtCodUsuarioUsuario.getText();
        String Usuario = txtUsuarioUsuario.getText();
        String Senha = txtSenhaUsuario.getText();
        String Nivel = txtNivelUsuario.getText();
        String Ativo = txtAtivoUsuario.getText(); 

        // Verifica se existe campo vazio
        String[] Fila = new String[]{CodUsuario, Usuario, Senha, Nivel, Ativo};
        String[] FilaNome = new String[]{"CodUsuário", "Usuário", "Senha", "Nivel", "Ativo"};      
        for (int i = 0; i < Fila.length; i++)
        {
            if(Fila[i].equals(""))
            {
                // Abre a janela de Aviso
                JOptionPane.showMessageDialog(null, "O Campo " + FilaNome[i] + " deve ser preenchido!", "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
                
                // Da o foco no campo requerido
                if(i == 0){txtCodUsuarioUsuario.requestFocus();}
                if(i == 1){txtUsuarioUsuario.requestFocus();}
                if(i == 2){txtSenhaUsuario.requestFocus();}
                if(i == 3){txtNivelUsuario.requestFocus();}
                if(i == 4){txtAtivoUsuario.requestFocus();}
                consistir = false;
                break;
            }
        }    
        return consistir;
    }
    
    // Método que limita a quantidade máxima de caracteres para os textFields
    public class MaxLengthTextDocument extends PlainDocument {
    //Armazena o número máximo de caracteres para o texto.
    private int maxChars;
 
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {
            if(str != null && (getLength() + str.length() < maxChars)){
                    super.insertString(offs, str, a);
            }
    }
 
     //getter e setter omitidos
    private void setMaxChars(int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
 }
    
    public void paint (Graphics g) {
        super.paint (g);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSair = new javax.swing.JButton();
        tabGerenciadorSenha = new javax.swing.JTabbedPane();
        pnlClassificacao = new javax.swing.JPanel();
        lblCodClassificacaoClassificacao = new javax.swing.JLabel();
        lblClassificacaoClassificacao = new javax.swing.JLabel();
        txtCodClassificacaoClassificacao = new javax.swing.JTextField();
        txtClassificacaoClassificacao = new javax.swing.JTextField();
        btnIncluirClassificacao = new javax.swing.JButton();
        btnAlterarClassificacao = new javax.swing.JButton();
        btnExcluirClassificacao = new javax.swing.JButton();
        btnGravarClassificacao = new javax.swing.JButton();
        btnCancelarClassificacao = new javax.swing.JButton();
        slpClassificacao = new javax.swing.JScrollPane();
        tblClassificacao = new javax.swing.JTable();
        pnlAplicacao = new javax.swing.JPanel();
        lblCodAplicacaoAplicacao = new javax.swing.JLabel();
        lblNomeAplicacao = new javax.swing.JLabel();
        lblComentarioAplicacao = new javax.swing.JLabel();
        lblNivelAplicacao = new javax.swing.JLabel();
        lblCodClassificacaoAplicacao = new javax.swing.JLabel();
        txtCodAplicacaoAplicacao = new javax.swing.JTextField();
        txtAplicacaoClassificacao = new javax.swing.JTextField();
        txtNomeAplicacao = new javax.swing.JTextField();
        txtComentarioAplicacao = new javax.swing.JTextField();
        txtNivelAplicacao = new javax.swing.JTextField();
        txtCodClassificacaoAplicacao = new javax.swing.JTextField();
        btnIncluirAplicacao = new javax.swing.JButton();
        btnAlterarAplicacao = new javax.swing.JButton();
        btnExcluirAplicacao = new javax.swing.JButton();
        btnGravarAplicacao = new javax.swing.JButton();
        btnCancelarAplicacao = new javax.swing.JButton();
        slpAplicacao = new javax.swing.JScrollPane();
        tblAplicacao = new javax.swing.JTable();
        pnlUsuario = new javax.swing.JPanel();
        lblCodUsuarioUsuario = new javax.swing.JLabel();
        lblUsuarioUsuario = new javax.swing.JLabel();
        lblSenhaUsuario = new javax.swing.JLabel();
        lblNivelUsuario = new javax.swing.JLabel();
        lblAtivoUsuario = new javax.swing.JLabel();
        txtCodUsuarioUsuario = new javax.swing.JTextField();
        txtUsuarioUsuario = new javax.swing.JTextField();
        txtSenhaUsuario = new javax.swing.JTextField();
        txtNivelUsuario = new javax.swing.JTextField();
        txtAtivoUsuario = new javax.swing.JTextField();
        btnIncluirUsuario = new javax.swing.JButton();
        btnAlterarUsuario = new javax.swing.JButton();
        btnExcluirUsuario = new javax.swing.JButton();
        btnSalvarUsuario = new javax.swing.JButton();
        btnGravarUsuario = new javax.swing.JButton();
        btnCancelarUsuario = new javax.swing.JButton();
        pnlClassificacaoAplicacaoUsuario = new javax.swing.JPanel();
        slpClassificacaoUsuario = new javax.swing.JScrollPane();
        tblClassificacaoUsuario = new javax.swing.JTable();
        slpClassificacaoAplicacaoUsuario = new javax.swing.JScrollPane();
        tblClassificacaoAplicacaoUsuario = new javax.swing.JTable();
        pnlUsuarioRelacao = new javax.swing.JPanel();
        slpUsuario = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        slpRelacao = new javax.swing.JScrollPane();
        tblRelacao = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        pnlClassificacao.setBackground(new java.awt.Color(3, 188, 123));

        lblCodClassificacaoClassificacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblCodClassificacaoClassificacao.setText("CodClassificação :");

        lblClassificacaoClassificacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblClassificacaoClassificacao.setText("Classificação :");

        btnIncluirClassificacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnIncluirClassificacao.setText("Incluir");
        btnIncluirClassificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirClassificacaoActionPerformed(evt);
            }
        });

        btnAlterarClassificacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnAlterarClassificacao.setText("Alterar");
        btnAlterarClassificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarClassificacaoActionPerformed(evt);
            }
        });

        btnExcluirClassificacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnExcluirClassificacao.setText("Excluir");
        btnExcluirClassificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirClassificacaoActionPerformed(evt);
            }
        });

        btnGravarClassificacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnGravarClassificacao.setText("Gravar");
        btnGravarClassificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarClassificacaoActionPerformed(evt);
            }
        });

        btnCancelarClassificacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnCancelarClassificacao.setText("Cancelar");
        btnCancelarClassificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarClassificacaoActionPerformed(evt);
            }
        });

        tblClassificacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Classificação", "Nome da Classificação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClassificacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClassificacaoMouseClicked(evt);
            }
        });
        tblClassificacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblClassificacaoKeyReleased(evt);
            }
        });
        slpClassificacao.setViewportView(tblClassificacao);

        javax.swing.GroupLayout pnlClassificacaoLayout = new javax.swing.GroupLayout(pnlClassificacao);
        pnlClassificacao.setLayout(pnlClassificacaoLayout);
        pnlClassificacaoLayout.setHorizontalGroup(
            pnlClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClassificacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slpClassificacao)
                    .addGroup(pnlClassificacaoLayout.createSequentialGroup()
                        .addGroup(pnlClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlClassificacaoLayout.createSequentialGroup()
                                .addComponent(lblCodClassificacaoClassificacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodClassificacaoClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(lblClassificacaoClassificacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtClassificacaoClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlClassificacaoLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(btnIncluirClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAlterarClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluirClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGravarClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelarClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 529, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlClassificacaoLayout.setVerticalGroup(
            pnlClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClassificacaoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodClassificacaoClassificacao)
                    .addComponent(txtCodClassificacaoClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClassificacaoClassificacao)
                    .addComponent(txtClassificacaoClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(slpClassificacao, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIncluirClassificacao)
                    .addComponent(btnAlterarClassificacao)
                    .addComponent(btnExcluirClassificacao)
                    .addComponent(btnGravarClassificacao)
                    .addComponent(btnCancelarClassificacao))
                .addContainerGap())
        );

        tabGerenciadorSenha.addTab("Classificação", pnlClassificacao);

        pnlAplicacao.setBackground(new java.awt.Color(3, 188, 123));

        lblCodAplicacaoAplicacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblCodAplicacaoAplicacao.setForeground(new java.awt.Color(51, 51, 51));
        lblCodAplicacaoAplicacao.setText("CodAplicação :");

        lblNomeAplicacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblNomeAplicacao.setText("Nome :");

        lblComentarioAplicacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblComentarioAplicacao.setText("Comentário :");

        lblNivelAplicacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblNivelAplicacao.setText("Nível :");

        lblCodClassificacaoAplicacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblCodClassificacaoAplicacao.setText("CodClassificação :");

        btnIncluirAplicacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnIncluirAplicacao.setText("Incluir");
        btnIncluirAplicacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirAplicacaoActionPerformed(evt);
            }
        });

        btnAlterarAplicacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnAlterarAplicacao.setText("Alterar");
        btnAlterarAplicacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarAplicacaoActionPerformed(evt);
            }
        });

        btnExcluirAplicacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnExcluirAplicacao.setText("Excluir");
        btnExcluirAplicacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirAplicacaoActionPerformed(evt);
            }
        });

        btnGravarAplicacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnGravarAplicacao.setText("Gravar");
        btnGravarAplicacao.setName("btnGravarAplica"); // NOI18N
        btnGravarAplicacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarAplicacaoActionPerformed(evt);
            }
        });

        btnCancelarAplicacao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnCancelarAplicacao.setText("Cancelar");
        btnCancelarAplicacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarAplicacaoActionPerformed(evt);
            }
        });

        tblAplicacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aplicação", "Nome da Aplicação", "Nivel", "Classificação", "Nome da Classificação", "Comentário"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAplicacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAplicacaoMouseClicked(evt);
            }
        });
        tblAplicacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblAplicacaoKeyReleased(evt);
            }
        });
        slpAplicacao.setViewportView(tblAplicacao);

        javax.swing.GroupLayout pnlAplicacaoLayout = new javax.swing.GroupLayout(pnlAplicacao);
        pnlAplicacao.setLayout(pnlAplicacaoLayout);
        pnlAplicacaoLayout.setHorizontalGroup(
            pnlAplicacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAplicacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAplicacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAplicacaoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnIncluirAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterarAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGravarAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(589, Short.MAX_VALUE))
                    .addGroup(pnlAplicacaoLayout.createSequentialGroup()
                        .addGroup(pnlAplicacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAplicacaoLayout.createSequentialGroup()
                                .addGroup(pnlAplicacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblCodClassificacaoAplicacao)
                                    .addComponent(lblCodAplicacaoAplicacao)
                                    .addComponent(lblComentarioAplicacao))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlAplicacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlAplicacaoLayout.createSequentialGroup()
                                        .addComponent(txtCodClassificacaoAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtAplicacaoClassificacao))
                                    .addGroup(pnlAplicacaoLayout.createSequentialGroup()
                                        .addComponent(txtCodAplicacaoAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13)
                                        .addComponent(lblNomeAplicacao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNomeAplicacao))
                                    .addComponent(txtComentarioAplicacao))
                                .addGap(17, 17, 17)
                                .addComponent(lblNivelAplicacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNivelAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(slpAplicacao))
                        .addContainerGap())))
        );
        pnlAplicacaoLayout.setVerticalGroup(
            pnlAplicacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAplicacaoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlAplicacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodAplicacaoAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeAplicacao)
                    .addComponent(lblNivelAplicacao)
                    .addComponent(txtNivelAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodAplicacaoAplicacao))
                .addGap(18, 18, 18)
                .addGroup(pnlAplicacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodClassificacaoAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodClassificacaoAplicacao)
                    .addComponent(txtAplicacaoClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(pnlAplicacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtComentarioAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblComentarioAplicacao))
                .addGap(18, 18, 18)
                .addComponent(slpAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(pnlAplicacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIncluirAplicacao)
                    .addComponent(btnAlterarAplicacao)
                    .addComponent(btnExcluirAplicacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGravarAplicacao)
                    .addComponent(btnCancelarAplicacao))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        tabGerenciadorSenha.addTab("Aplicação", pnlAplicacao);

        pnlUsuario.setBackground(new java.awt.Color(3, 188, 123));

        lblCodUsuarioUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblCodUsuarioUsuario.setText("Cod Usuário :");

        lblUsuarioUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblUsuarioUsuario.setText("Usuário :");

        lblSenhaUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblSenhaUsuario.setText("Senha :");

        lblNivelUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblNivelUsuario.setText("Nivel :");

        lblAtivoUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblAtivoUsuario.setText("Ativo :");

        btnIncluirUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnIncluirUsuario.setText("Incluir");
        btnIncluirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirUsuarioActionPerformed(evt);
            }
        });

        btnAlterarUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnAlterarUsuario.setText("Alterar");
        btnAlterarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarUsuarioActionPerformed(evt);
            }
        });

        btnExcluirUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnExcluirUsuario.setText("Excluir");
        btnExcluirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirUsuarioActionPerformed(evt);
            }
        });

        btnSalvarUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnSalvarUsuario.setText("Salvar");
        btnSalvarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarUsuarioActionPerformed(evt);
            }
        });

        btnGravarUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnGravarUsuario.setText("Gravar");
        btnGravarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarUsuarioActionPerformed(evt);
            }
        });

        btnCancelarUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnCancelarUsuario.setText("Cancelar");
        btnCancelarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarUsuarioActionPerformed(evt);
            }
        });

        tblClassificacaoUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Classificação", "Nome da Classificação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClassificacaoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClassificacaoUsuarioMouseClicked(evt);
            }
        });
        tblClassificacaoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblClassificacaoUsuarioKeyReleased(evt);
            }
        });
        slpClassificacaoUsuario.setViewportView(tblClassificacaoUsuario);

        tblClassificacaoAplicacaoUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aplicação", "Nome da Aplicação", "Incluir", "Alterar", "Excluir", "Consultar", "Imprimir"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClassificacaoAplicacaoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClassificacaoAplicacaoUsuarioMouseClicked(evt);
            }
        });
        slpClassificacaoAplicacaoUsuario.setViewportView(tblClassificacaoAplicacaoUsuario);
        if (tblClassificacaoAplicacaoUsuario.getColumnModel().getColumnCount() > 0) {
            tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(0).setResizable(false);
            tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(1).setResizable(false);
            tblClassificacaoAplicacaoUsuario.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout pnlClassificacaoAplicacaoUsuarioLayout = new javax.swing.GroupLayout(pnlClassificacaoAplicacaoUsuario);
        pnlClassificacaoAplicacaoUsuario.setLayout(pnlClassificacaoAplicacaoUsuarioLayout);
        pnlClassificacaoAplicacaoUsuarioLayout.setHorizontalGroup(
            pnlClassificacaoAplicacaoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClassificacaoAplicacaoUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slpClassificacaoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slpClassificacaoAplicacaoUsuario)
                .addContainerGap())
        );
        pnlClassificacaoAplicacaoUsuarioLayout.setVerticalGroup(
            pnlClassificacaoAplicacaoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClassificacaoAplicacaoUsuarioLayout.createSequentialGroup()
                .addGroup(pnlClassificacaoAplicacaoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(slpClassificacaoAplicacaoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(slpClassificacaoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Usuário", "Nome do Usuário", "Senha", "Nivel", "Ativo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuarioMouseClicked(evt);
            }
        });
        tblUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblUsuarioKeyReleased(evt);
            }
        });
        slpUsuario.setViewportView(tblUsuario);
        if (tblUsuario.getColumnModel().getColumnCount() > 0) {
            tblUsuario.getColumnModel().getColumn(0).setResizable(false);
        }

        tblRelacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Classificação", "Nome da Classificação", "Aplicação", "Nome da Aplicacação", "Incluir", "Alterar", "Excluir", "Consultar", "Imprimir"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        slpRelacao.setViewportView(tblRelacao);

        javax.swing.GroupLayout pnlUsuarioRelacaoLayout = new javax.swing.GroupLayout(pnlUsuarioRelacao);
        pnlUsuarioRelacao.setLayout(pnlUsuarioRelacaoLayout);
        pnlUsuarioRelacaoLayout.setHorizontalGroup(
            pnlUsuarioRelacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarioRelacaoLayout.createSequentialGroup()
                .addGroup(pnlUsuarioRelacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slpRelacao)
                    .addComponent(slpUsuario))
                .addContainerGap())
        );
        pnlUsuarioRelacaoLayout.setVerticalGroup(
            pnlUsuarioRelacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarioRelacaoLayout.createSequentialGroup()
                .addComponent(slpUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slpRelacao, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlUsuarioLayout = new javax.swing.GroupLayout(pnlUsuario);
        pnlUsuario.setLayout(pnlUsuarioLayout);
        pnlUsuarioLayout.setHorizontalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlClassificacaoAplicacaoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodUsuarioUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodUsuarioUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(lblUsuarioUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuarioUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(lblSenhaUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addComponent(lblNivelUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNivelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(lblAtivoUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAtivoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlUsuarioRelacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnIncluirUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluirUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGravarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlUsuarioLayout.setVerticalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodUsuarioUsuario)
                    .addComponent(txtCodUsuarioUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuarioUsuario)
                    .addComponent(txtUsuarioUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenhaUsuario)
                    .addComponent(txtSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNivelUsuario)
                    .addComponent(txtNivelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAtivoUsuario)
                    .addComponent(txtAtivoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlUsuarioRelacao, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlClassificacaoAplicacaoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIncluirUsuario)
                    .addComponent(btnAlterarUsuario)
                    .addComponent(btnExcluirUsuario)
                    .addComponent(btnGravarUsuario)
                    .addComponent(btnCancelarUsuario)
                    .addComponent(btnSalvarUsuario))
                .addGap(22, 22, 22))
        );

        tabGerenciadorSenha.addTab("Usuário", pnlUsuario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabGerenciadorSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 1290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabGerenciadorSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Abrir conexão com o banco sql server
        abrirConexao();
        
        // Teste gradiente 
//        Login login = new Login();
//        Color corInicial = new Color(3,188,123);
//        Color corFinal = new Color(0,169,157);   
//        TestPanel panel = new TestPanel();
//        TestPanel panel = (TestPanel) pnlClassificacao;
//        login.setContentPane(panel);
//        login.setTitle("Login");
//        login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        login.setLocationRelativeTo(null);
//        login.setVisible(true);
//     
          
        // Instancia das classes DAO 
        AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();
        ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        // Variável de inicialização
        int Usuario;
        
        // Atualiza as tabelas na tela
        aplicacaoDAO.atualizarTabela(connection, tblAplicacao);
        classificacaoDAO.atualizarTabela(connection, tblClassificacao);  
        usuarioDAO.atualizarTabela(connection, tblUsuario);   
        classificacaoDAO.atualizarTabela(connection, tblClassificacaoUsuario); 
        
        // Busca ultimo CodUsuario e preenche campo na tela
        Usuario = usuarioDAO.buscarUltimoRegistro(connection);   
        txtCodUsuarioUsuario.setText("" + Usuario + "");
        
        // Deixa os campos ocultos
        txtAplicacaoClassificacao.setEnabled(false);
        txtCodUsuarioUsuario.setEnabled(false);

        // Seta tabela para modo editável
        tblAplicacao.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        // Formata as colunas das tabelas
        formatarTabelaAplicacao();
        formatarTabelaClassificacao();
        formatarTabelaUsuario();
        formatarTabelaRelacao();
        formatarTabelaClassificacaoUsuario();
        formatarTabelaClassificacaoAplicacaoUsuario();
        
        // Visualização dos botões e do panel na tela
        btnGravarAplicacao.setVisible(false);
        btnCancelarAplicacao.setVisible(false);
        btnGravarClassificacao.setVisible(false);
        btnCancelarClassificacao.setVisible(false);
        btnSalvarUsuario.setVisible(false);
        btnGravarUsuario.setVisible(false);
        btnCancelarUsuario.setVisible(false);  
        pnlClassificacaoAplicacaoUsuario.setVisible(false);
       
        // Instancia do objeto
        MaxLengthTextDocument maxLength = new MaxLengthTextDocument();
        
        // Limitador de caracteres
        maxLength.setMaxChars(30);//Limitamos para 50 caracteres
        
        // Limita a entrada máxima de caracteres
        txtNomeAplicacao.setDocument(maxLength);
        txtComentarioAplicacao.setDocument(maxLength);
        txtNivelAplicacao.setDocument(maxLength);
        txtCodClassificacaoAplicacao.setDocument(maxLength);
        txtCodClassificacaoAplicacao.setDocument(maxLength);
        txtCodClassificacaoClassificacao.setDocument(maxLength);
        txtCodClassificacaoAplicacao.setDocument(maxLength);     
        txtCodUsuarioUsuario.setDocument(maxLength);
        txtUsuarioUsuario.setDocument(maxLength);
        txtSenhaUsuario.setDocument(maxLength);
        txtCodUsuarioUsuario.setDocument(maxLength); 
         
        // Fechar conexão com o banco sql server
        //fecharConexao();
    }//GEN-LAST:event_formWindowOpened

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // Fecha aplicativo
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void tblClassificacaoAplicacaoUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClassificacaoAplicacaoUsuarioMouseClicked
        // Pega linha da tabela
        String CodUsuario =  txtCodUsuarioUsuario.getText();
        int linhaClassificacaoAplicacaoUsuario = tblClassificacaoAplicacaoUsuario.getSelectedRow();
        // Cria modelo da tabela 
        DefaultTableModel modeloClassificacaoAplicacaoUsuario = (DefaultTableModel)tblClassificacaoAplicacaoUsuario.getModel();
         
        if(CodUsuario != null || CodUsuario != "")
        {        
            String CodAplicacao = modeloClassificacaoAplicacaoUsuario.getValueAt(linhaClassificacaoAplicacaoUsuario, 0).toString();
            String Where = "where CodUsuario = " + CodUsuario + " AND " + " CodAplicacao = '"+ CodAplicacao + "' ";

             // Instância da classe DAO
             RelacaoDAO relacaoDAO = new RelacaoDAO();
             
             // Pegas as informações das colunas da respectiva linha selecionada
             String Incluir = modeloClassificacaoAplicacaoUsuario.getValueAt(linhaClassificacaoAplicacaoUsuario, 2).toString();
             if(Incluir == "true"){Incluir = "S";} else {Incluir = "N";};
             String Alterar = modeloClassificacaoAplicacaoUsuario.getValueAt(linhaClassificacaoAplicacaoUsuario, 3).toString();
             if(Alterar == "true"){Alterar = "S";} else {Alterar = "N";};
             String Excluir = modeloClassificacaoAplicacaoUsuario.getValueAt(linhaClassificacaoAplicacaoUsuario, 4).toString();
             if(Excluir == "true"){Excluir = "S";} else {Excluir = "N";};
             String Consultar = modeloClassificacaoAplicacaoUsuario.getValueAt(linhaClassificacaoAplicacaoUsuario, 5).toString();
             if(Consultar == "true"){Consultar = "S";} else {Consultar = "N";};
             String Imprimir = modeloClassificacaoAplicacaoUsuario.getValueAt(linhaClassificacaoAplicacaoUsuario, 6).toString();
             if(Imprimir == "true"){Imprimir = "S";} else {Imprimir = "N";};
             String Confirma = "N";
             
             // Realiza chamada DAO para atualizar e selecionar tabela relação
             relacaoDAO.atualizarTabela(connection, Where, new Relacao(Integer.parseInt(CodUsuario), CodAplicacao, "", Incluir, Alterar, 
             Excluir, Consultar, Imprimir, "", Confirma));
             relacaoDAO.selecionarRelacao(connection , tblRelacao, CodUsuario);
             
             // Deixa tabela visivel
             tblRelacao.setVisible(true);
        }
    }//GEN-LAST:event_tblClassificacaoAplicacaoUsuarioMouseClicked

    private void btnCancelarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarUsuarioActionPerformed
            // Verifica qual é o fluxo de codigo do programa
            if(AlterarClicado == true || GravarClicado == true)
            {
                // Abre a janela de confirmação
                Object[] opcoes = {"Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, "Deseja cancelar esse Permissionamento?", "Cancelar Permissionamento", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, opcoes, opcoes[0]);

                // Realiza exclusão caso opção selecionada for igual a sim
                if(opcao == 0)
                {
                    // Instância da classe usuário
                     RelacaoDAO relacaoDAO = new RelacaoDAO();

                    // Carrega objeto e realiza chamada DAO
                    int CodUsuario = Integer.parseInt(txtCodUsuarioUsuario.getText());
                    relacaoDAO.excluirRelacao(connection,  new Relacao(CodUsuario, "", "", "", "", "", "", "", "", ""));
                    String CodigoUsuario = txtCodUsuarioUsuario.getText();
                    relacaoDAO.selecionarRelacao(connection , tblRelacao, CodigoUsuario);
                    btnSalvarUsuario.setVisible(false);
                }
                else
                {
                    return;
                }
            }
        
            // Visualização dos botões e do panel na tela
            btnIncluirUsuario.setVisible(true);
            btnAlterarUsuario.setVisible(true);
            btnExcluirUsuario.setVisible(true);
            btnGravarUsuario.setVisible(false);
            btnCancelarUsuario.setVisible(false);
            pnlClassificacaoAplicacaoUsuario.setVisible(false);
            pnlUsuarioRelacao.setVisible(true);

            // Limpas os campos da tela
            txtCodUsuarioUsuario.setText("");
            txtUsuarioUsuario.setText("");
            txtSenhaUsuario.setText("");
            txtNivelUsuario.setText("");
            txtAtivoUsuario.setText("");
            
            // Variável de inicialização
            int Usuario;    

            // Instancia da classe 
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // Busca ultimo CodUsuario e preenche campo na tela
            Usuario = usuarioDAO.buscarUltimoRegistro(connection); 
            txtCodUsuarioUsuario.setText("" + Usuario + "");

            // Atualiza tabela usuário
            usuarioDAO.atualizarTabela(connection, tblUsuario);   

            // Variável usada para direcionar fluxo do código
            IncluirClicado = false;
            AlterarClicado = false;                                   
    }//GEN-LAST:event_btnCancelarUsuarioActionPerformed

    private void btnGravarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarUsuarioActionPerformed
        // Abrir conexão com o banco sql server
        abrirConexao();
    
        // Consistência dos campos da tela
        if(consistirCamposUsuario())
        {  
            // Modo de inclusão
            if(IncluirClicado == true)
            {                      
                // Instância da classe aplicação
                UsuarioDAO usuarioDAO = new UsuarioDAO(); 
                ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();           
                
                // Carrega objeto e realiza chamada DAO para consistencia da chave         
                int CodUsuario = Integer.parseInt(txtCodUsuarioUsuario.getText());
                if(!usuarioDAO.consistirChave(connection, new Usuario(CodUsuario, "", "", "", "")))
                {            
                    // Carrega objeto e realiza chamada DAO para inclusão do usuário
                    String Usuario = txtUsuarioUsuario.getText();
                    String Senha = txtSenhaUsuario.getText();
                    String Nivel = txtNivelUsuario.getText();
                    String Ativo = txtAtivoUsuario.getText();  
                    String Where = "WHERE CodUsuario = " + CodUsuario + " ";
                    usuarioDAO.incluirUsuario(connection, new Usuario(CodUsuario, Usuario, Senha, Nivel, Ativo), tblUsuario, Where);
                    
                    // Visualização do painel e da tabela na tela
                    pnlClassificacaoAplicacaoUsuario.setVisible(true);    
                    pnlUsuarioRelacao.setVisible(true);  
                    tblRelacao.setVisible(false);
                    
                    // Atualiza tabela classificação
                    classificacaoDAO.atualizarTabela(connection, tblClassificacaoUsuario);
                    
                    // Visualização dos botões na tela
                    btnSalvarUsuario.setVisible(true);
                    btnGravarUsuario.setVisible(false);
                }
                else
                {
                    // Abre a janela de aviso
                    JOptionPane.showMessageDialog(null, "Mensagem", "Titulo da Janela", JOptionPane.ERROR_MESSAGE);
                }
                
                // Variável usada para direcionar fluxo do código
                GravarClicado = true;  
            }
            
            // Modo de alteração
            if(AlterarClicado == true)
            {
                AlterarClicado = false;
          
                // Instância da classe aplicação
                UsuarioDAO usuarioDAO = new UsuarioDAO(); 
                
                // Carrega objeto e realiza chamada DAO para alteração
                int CodUsuario = Integer.parseInt(txtCodUsuarioUsuario.getText());
                String Usuario = txtUsuarioUsuario.getText();
                String Senha = txtSenhaUsuario.getText();
                String Nivel = txtNivelUsuario.getText();
                String Ativo = txtAtivoUsuario.getText();            
                usuarioDAO.alterarUsuario(connection, new Usuario(CodUsuario, Usuario, Senha, Nivel, Ativo), tblUsuario);
                
                RelacaoDAO relacaoDAO = new RelacaoDAO();
                String CodigoUsuario =  txtCodUsuarioUsuario.getText();
                relacaoDAO.confirmarRelacao(connection, CodigoUsuario);
                
                // Visualização dos botões e do painel na tela
                btnIncluirUsuario.setVisible(true);
                btnAlterarUsuario.setVisible(true);
                btnExcluirUsuario.setVisible(true);
                btnGravarUsuario.setVisible(false);
                btnCancelarUsuario.setVisible(false);
                pnlClassificacaoAplicacaoUsuario.setVisible(false);
            }   
        }
         
        // Fechar conexão com o banco sql server
        //fecharConexao();
    }//GEN-LAST:event_btnGravarUsuarioActionPerformed

    private void btnExcluirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirUsuarioActionPerformed
        // Abrir conexão com o banco sql server
        abrirConexao();
        
        // Abre a janela de confirmação
        Object[] opcoes = {"Sim", "Não"};
        int opcao = JOptionPane.showOptionDialog(null, "Deseja excluir esse CodUsuário?", "Excluir Usuário", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
        null, opcoes, opcoes[0]);
        
        // Realiza exclusão caso opção selecionada for igual a sim
        if(opcao == 0)
        {
            // Instância da classe usuário
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // Carrega objeto e realiza chamada DAO
            int CodUsuario = Integer.parseInt(txtCodUsuarioUsuario.getText());
            usuarioDAO.excluirUsuario(connection, new Usuario(CodUsuario, "", "", "", ""), tblUsuario);
        }
        
        // Fechar conexão com o banco sql server
        //fecharConexao();
    }//GEN-LAST:event_btnExcluirUsuarioActionPerformed

    private void btnAlterarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarUsuarioActionPerformed
        // Visualização dos botões e panel na tela
        btnIncluirUsuario.setVisible(false);
        btnAlterarUsuario.setVisible(false);
        btnExcluirUsuario.setVisible(false);
        btnSalvarUsuario.setVisible(false);
        btnGravarUsuario.setVisible(true);
        btnCancelarUsuario.setVisible(true);
        pnlClassificacaoAplicacaoUsuario.setVisible(true);
             
        // Variável usada para direcionar fluxo do código
         AlterarClicado = true;     
        
        // Da o foco no campo da tela
        txtUsuarioUsuario.grabFocus();
        
        // Da o foco na primeira linha do Grid
        tblClassificacaoAplicacaoUsuario.clearSelection();
        tblClassificacaoAplicacaoUsuario.changeSelection(tblClassificacaoAplicacaoUsuario.getRowCount() - 2, 0, true, false);
        
        tblClassificacaoAplicacaoUsuario.setVisible(false);
    }//GEN-LAST:event_btnAlterarUsuarioActionPerformed

    private void btnIncluirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirUsuarioActionPerformed
        // Visualização dos botões e panel na tela
        btnIncluirUsuario.setVisible(false);
        btnAlterarUsuario.setVisible(false);
        btnExcluirUsuario.setVisible(false);
        btnSalvarUsuario.setVisible(false);
        btnGravarUsuario.setVisible(true);
        btnCancelarUsuario.setVisible(true);
        pnlClassificacaoAplicacaoUsuario.setVisible(false);
        pnlUsuarioRelacao.setVisible(false);
        
        // Instancia das classes DAO 
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        // Variável de inicialização
        int Usuario;    
        
        // Busca ultimo CodUsuario e preenche campo na tela
        Usuario = usuarioDAO.buscarUltimoRegistro(connection);   
        Usuario = Usuario + 1;
        
        // Variável usada para direcionar fluxo do código
        IncluirClicado = true;
        
        // Limpas os campos da tela
        txtCodUsuarioUsuario.setText("" + Usuario + "");
        txtUsuarioUsuario.setText("");
        txtSenhaUsuario.setText("");
        txtNivelUsuario.setText("");
        txtAtivoUsuario.setText("");
         
        // Da o foco no campo da tela
        txtUsuarioUsuario.requestFocus();
        
        // Da o foco na primeira linha do Grid
        tblClassificacaoAplicacaoUsuario.clearSelection();
        tblClassificacaoAplicacaoUsuario.changeSelection(tblClassificacaoAplicacaoUsuario.getRowCount() - 27, 0, true, false);
    }//GEN-LAST:event_btnIncluirUsuarioActionPerformed

    private void tblAplicacaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblAplicacaoKeyReleased
        // Pega o valor da linha selecionada e cria o modelo de tabela
        int linha = tblAplicacao.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel)tblAplicacao.getModel();
        
        // Pega as informações da tabela e joga para os campos na tela
        txtCodAplicacaoAplicacao.setText(modelo.getValueAt(linha, 0).toString());
        txtNomeAplicacao.setText(modelo.getValueAt(linha, 1).toString());
        txtNivelAplicacao.setText(modelo.getValueAt(linha, 2).toString());
        txtCodClassificacaoAplicacao.setText(modelo.getValueAt(linha, 3).toString());
        txtAplicacaoClassificacao.setText(modelo.getValueAt(linha, 4).toString());
        txtComentarioAplicacao.setText(modelo.getValueAt(linha, 5).toString());
    }//GEN-LAST:event_tblAplicacaoKeyReleased

    private void tblAplicacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAplicacaoMouseClicked
        // Pega o valor da linha selecionada e cria o modelo de tabela
        int linha = tblAplicacao.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel)tblAplicacao.getModel();
        
        // Pega as informações da tabela e joga para os campos na tela
        txtCodAplicacaoAplicacao.setText(modelo.getValueAt(linha, 0).toString());
        txtNomeAplicacao.setText(modelo.getValueAt(linha, 1).toString());
        txtNivelAplicacao.setText(modelo.getValueAt(linha, 2).toString());
        txtCodClassificacaoAplicacao.setText(modelo.getValueAt(linha, 3).toString());
        txtAplicacaoClassificacao.setText(modelo.getValueAt(linha, 4).toString());
        txtComentarioAplicacao.setText(modelo.getValueAt(linha, 5).toString());
    }//GEN-LAST:event_tblAplicacaoMouseClicked

    private void btnCancelarAplicacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarAplicacaoActionPerformed
        // Visualização dos botões e campo na tela
        btnIncluirAplicacao.setVisible(true);
        btnAlterarAplicacao.setVisible(true);
        btnExcluirAplicacao.setVisible(true);
        btnGravarAplicacao.setVisible(false);
        btnCancelarAplicacao.setVisible(false);
        txtCodAplicacaoAplicacao.setEnabled(true);
        
        // Variável usada para direcionar fluxo do código
        IncluirClicado = false;
        AlterarClicado = false;
    }//GEN-LAST:event_btnCancelarAplicacaoActionPerformed

    private void btnGravarAplicacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarAplicacaoActionPerformed
        // Abrir conexão com o banco sql server
        abrirConexao();

         // Consistência dos campos da tela
        if(consistirCamposAplicacao())
        {
            // Modo de inclusão
            if(IncluirClicado == true)
            {
                // Instância da classe aplicação
                AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();
                ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();
                
                // Carrega objeto e realiza chamada DAO para as consistencias das chaves
                String CodAplicacao = txtCodAplicacaoAplicacao.getText();
                String CodClassificacao = txtCodClassificacaoAplicacao.getText();
                if(!aplicacaoDAO.consistirChave(connection, new Aplicacao(CodAplicacao, "", "", "", "", "")))
                {
                     if(classificacaoDAO.consistirChave(connection, new Classificacao(CodClassificacao, "", "")))
                     {
                        // Carrega objeto e realiza chamada DAO para inclusão
                        String Nome = txtNomeAplicacao.getText();
                        String Comentario = txtComentarioAplicacao.getText();
                        String Nivel = txtNivelAplicacao.getText();
                        aplicacaoDAO.incluirAplicacao(connection, new Aplicacao(CodAplicacao, Nome, Comentario, Nivel, CodClassificacao, ""), tblAplicacao);
                        
                        // Limpas os campos da tela
                        txtCodAplicacaoAplicacao.setText("");
                        txtNomeAplicacao.setText("");
                        txtComentarioAplicacao.setText("");
                        txtNivelAplicacao.setText("");
                        txtCodClassificacaoAplicacao.setText("");
                        
                        // Da o foco no campo da tela
                        txtCodAplicacaoAplicacao.requestFocus();
                     }
                     else
                     {
                        // Abre a janela de aviso
                        JOptionPane.showMessageDialog(null, "Código de Classificação não existente!", "Titulo da Janela", JOptionPane.ERROR_MESSAGE);
                     }
                }
                else
                {
                    // Abre a janela de aviso
                    JOptionPane.showMessageDialog(null, "Código de Aplicação já existente!", "Titulo da Janela", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            // Modo de alteração
            if(AlterarClicado == true)
            {
                AlterarClicado = false;

                // Instância da classe aplicação
                AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();
                ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();
                
                // Carrega objeto e realiza chamada DAO para consistencia da chave
                String CodClassificacao = txtCodClassificacaoAplicacao.getText();
                if(classificacaoDAO.consistirChave(connection, new Classificacao(CodClassificacao, "", "")))
                {                                           
                    // Carrega objeto e realiza chamada DAO para alteração
                    String CodAplicacao = txtCodAplicacaoAplicacao.getText();
                    String Nome = txtNomeAplicacao.getText();
                    String Comentario = txtComentarioAplicacao.getText();
                    String Nivel = txtNivelAplicacao.getText();
                    aplicacaoDAO.alterarAplicacao(connection, new Aplicacao(CodAplicacao, Nome, Comentario, Nivel, CodClassificacao, ""), tblAplicacao);

                    // Visualização dos botões e do campo na tela
                    btnIncluirAplicacao.setVisible(true);
                    btnAlterarAplicacao.setVisible(true);
                    btnExcluirAplicacao.setVisible(true);
                    btnGravarAplicacao.setVisible(false);
                    btnCancelarAplicacao.setVisible(false);
                    txtCodAplicacaoAplicacao.setEnabled(true); 
                    
                    // Variável usada para direcionar fluxo do código
                    IncluirClicado = false;
                    AlterarClicado = false;
                }
                else
                {
                    // Abre a janela de aviso
                     JOptionPane.showMessageDialog(null, "Código de Classificação não existente!", "Titulo da Janela", JOptionPane.ERROR_MESSAGE);
                     AlterarClicado = true;
                }
            }
        }
        
        // Fechar conexão com o banco sql server
        //fecharConexao();
    }//GEN-LAST:event_btnGravarAplicacaoActionPerformed

    private void btnExcluirAplicacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirAplicacaoActionPerformed
        // Abrir conexão com o banco sql server
        abrirConexao();
        
        // Abre a janela de confirmação
        Object[] opcoes = {"Sim", "Não"};
        int opcao = JOptionPane.showOptionDialog(null, "Deseja excluir esse CodAplicação?", "Excluir Usuário", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
        null, opcoes, opcoes[0]);
          
        // Realiza exclusão caso opção selecionada for igual a sim
        if(opcao == 0)
        {
            // Instância da classe aplicação
            AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();
        
            // Carrega objeto e realiza chamada DAO
            String CodAplicacao = txtCodAplicacaoAplicacao.getText();
            aplicacaoDAO.excluirAplicacao(connection, new Aplicacao(CodAplicacao, "", "", "", "", ""), tblAplicacao);
        }
        
        // Fechar conexão com o banco sql server
        //fecharConexao();
    }//GEN-LAST:event_btnExcluirAplicacaoActionPerformed

    private void btnAlterarAplicacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarAplicacaoActionPerformed
        // Visualização dos botõese do campo na tela
        btnIncluirAplicacao.setVisible(false);
        btnAlterarAplicacao.setVisible(false);
        btnExcluirAplicacao.setVisible(false);
        btnGravarAplicacao.setVisible(true);
        btnCancelarAplicacao.setVisible(true);
        txtCodAplicacaoAplicacao.setEnabled(false);
        
        // Variável usada para direcionar fluxo do código
        AlterarClicado = true;
        
        // Da o foco no campo da tela
        txtNomeAplicacao.grabFocus();
        
        // Da o foco na primeira linha do Grid
        tblAplicacao.clearSelection();
        tblAplicacao.changeSelection(tblAplicacao.getRowCount() - 27, 0, true, false);
    }//GEN-LAST:event_btnAlterarAplicacaoActionPerformed

    private void btnIncluirAplicacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirAplicacaoActionPerformed
        // Visualização dos botões na tela
        btnIncluirAplicacao.setVisible(false);
        btnAlterarAplicacao.setVisible(false);
        btnExcluirAplicacao.setVisible(false);
        btnGravarAplicacao.setVisible(true);
        btnCancelarAplicacao.setVisible(true);
        
        // Variável usada para direcionar fluxo do código
        IncluirClicado = true;
        
        // Limpas os campos da tela
        txtCodAplicacaoAplicacao.setText("");
        txtNomeAplicacao.setText("");
        txtComentarioAplicacao.setText("");
        txtNivelAplicacao.setText("");
        txtCodClassificacaoAplicacao.setText("");
        
        // Da o foco no campo da tela
        txtCodAplicacaoAplicacao.requestFocus();
        
        // Da o foco na primeira linha do Grid
        tblAplicacao.clearSelection();
        tblAplicacao.changeSelection(tblAplicacao.getRowCount() - 28, 0, true, false);
    }//GEN-LAST:event_btnIncluirAplicacaoActionPerformed

    private void tblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarioMouseClicked
        // Pega o valor da linha selecionada e cria o modelo de tabela
        int linha = tblUsuario.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel)tblUsuario.getModel();
        
        // Pega as informações da tabela e joga para os campos na tela
        txtCodUsuarioUsuario.setText(modelo.getValueAt(linha, 0).toString());
        txtUsuarioUsuario.setText(modelo.getValueAt(linha, 1).toString()); 
        txtSenhaUsuario.setText(modelo.getValueAt(linha, 2).toString());
        txtNivelUsuario.setText(modelo.getValueAt(linha, 3).toString()); 
        txtAtivoUsuario.setText(modelo.getValueAt(linha, 4).toString());
        
         // Instancia da classe 
         RelacaoDAO relacaoDAO = new RelacaoDAO();
         relacaoDAO.selecionarRelacao(connection , tblRelacao, modelo.getValueAt(linha, 0).toString());
         
         // Verifica se o painel esta visivel
         if(pnlClassificacaoAplicacaoUsuario.isVisible() == true)
         {
            // Instancia da classe 
            ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();

            // Pega o valor da linha selecionada e cria o modelo de tabela
            int linha1 = tblUsuario.getSelectedRow();
            int linha2 = tblClassificacaoUsuario.getSelectedRow();
            DefaultTableModel modelo1 = (DefaultTableModel)tblUsuario.getModel();
            DefaultTableModel modelo2 = (DefaultTableModel)tblClassificacaoUsuario.getModel();

            // Seleciona tabela classificação
            classificacaoDAO.selecionarClassificacao(connection , tblClassificacaoAplicacaoUsuario, Integer.parseInt(modelo1.getValueAt(linha1, 0).toString()), modelo2.getValueAt(linha2, 0).toString());
         }
    }//GEN-LAST:event_tblUsuarioMouseClicked

    private void tblUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblUsuarioKeyReleased
        // Pega o valor da linha selecionada e cria o modelo de tabela
        int linha = tblUsuario.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel)tblUsuario.getModel();
        
        // Pega as informações da tabela e joga para os campos na tela
        txtCodUsuarioUsuario.setText(modelo.getValueAt(linha, 0).toString());
        txtUsuarioUsuario.setText(modelo.getValueAt(linha, 1).toString());
        txtSenhaUsuario.setText(modelo.getValueAt(linha, 2).toString());
        txtNivelUsuario.setText(modelo.getValueAt(linha, 3).toString()); 
        txtAtivoUsuario.setText(modelo.getValueAt(linha, 4).toString());
        
         // Instâncias da classe DAO
         RelacaoDAO relacaoDAO = new RelacaoDAO();
         ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();
         
        // Realiza seleção da relação
        relacaoDAO.selecionarRelacao(connection , tblRelacao, modelo.getValueAt(linha, 0).toString());
         
         // Verifica se o painel esta visivel
         if(pnlClassificacaoAplicacaoUsuario.isVisible() == true)
         {
            // Pega o valor da linha selecionada e cria o modelo de tabela
            int linha1 = tblUsuario.getSelectedRow();
            int linha2 = tblClassificacaoUsuario.getSelectedRow();
            DefaultTableModel modelo1 = (DefaultTableModel)tblUsuario.getModel();
            DefaultTableModel modelo2 = (DefaultTableModel)tblClassificacaoUsuario.getModel();

            // Seleciona tabela classificação
            classificacaoDAO.selecionarClassificacao(connection , tblClassificacaoAplicacaoUsuario, Integer.parseInt(modelo1.getValueAt(linha1, 0).toString()), modelo2.getValueAt(linha2, 0).toString());
         }
    }//GEN-LAST:event_tblUsuarioKeyReleased

    private void tblClassificacaoUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClassificacaoUsuarioMouseClicked
        // Visualização da tabela na tela
        tblClassificacaoAplicacaoUsuario.setVisible(true);    
        
        // Instância da classe DAO
        ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();
        
        // Inicializa Variáveis e cria o modelo de tabela
        int CodUsuario; 
        int CodClassificacao;
        DefaultTableModel modelo1 = (DefaultTableModel)tblUsuario.getModel();
        DefaultTableModel modelo2 = (DefaultTableModel)tblClassificacaoUsuario.getModel();
        
        // Pega linha da tabela
        if(AlterarClicado == true)
        {
            // Pega o valor da linha selecionada e realiza chamada DAO de seleção
            CodUsuario =  tblUsuario.getSelectedRow();
            CodClassificacao = tblClassificacaoUsuario.getSelectedRow();
            classificacaoDAO.selecionarClassificacao(connection , tblClassificacaoAplicacaoUsuario, Integer.parseInt(modelo1.getValueAt(CodUsuario, 0).toString()), modelo2.getValueAt(CodClassificacao, 0).toString());
        }
        else
        {
            // Pega o valor da linha selecionada e realiza chamada DAO de seleção
            CodUsuario =  Integer.parseInt(txtCodUsuarioUsuario.getText());
            CodClassificacao = tblClassificacaoUsuario.getSelectedRow();
            classificacaoDAO.selecionarClassificacao(connection , tblClassificacaoAplicacaoUsuario, CodUsuario, modelo2.getValueAt(CodClassificacao, 0).toString());
        }          
    }//GEN-LAST:event_tblClassificacaoUsuarioMouseClicked

    private void tblClassificacaoUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblClassificacaoUsuarioKeyReleased
        // Visualização da tabela na tela
        tblClassificacaoAplicacaoUsuario.setVisible(true);
        
        // Instância da classe DAO
        ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();
        
        // Inicializa Variáveis e cria o modelo de tabela
        int CodUsuario; 
        int CodClassificacao;
        DefaultTableModel modelo1 = (DefaultTableModel)tblUsuario.getModel();
        DefaultTableModel modelo2 = (DefaultTableModel)tblClassificacaoUsuario.getModel();
              
        // Pega linha da tabela
        if(AlterarClicado == true)
        {
            // Pega o valor da linha selecionada e realiza chamada DAO de seleção
            CodUsuario =  tblUsuario.getSelectedRow();
            CodClassificacao = tblClassificacaoUsuario.getSelectedRow();
            classificacaoDAO.selecionarClassificacao(connection , tblClassificacaoAplicacaoUsuario, Integer.parseInt(modelo1.getValueAt(CodUsuario, 0).toString()), modelo2.getValueAt(CodClassificacao, 0).toString());
        }
        else
        {
            // Pega o valor da linha selecionada e realiza chamada DAO de seleção
            CodUsuario =  Integer.parseInt(txtCodUsuarioUsuario.getText());
            CodClassificacao = tblClassificacaoUsuario.getSelectedRow();
            classificacaoDAO.selecionarClassificacao(connection , tblClassificacaoAplicacaoUsuario, CodUsuario, modelo2.getValueAt(CodClassificacao, 0).toString());
        }          
    }//GEN-LAST:event_tblClassificacaoUsuarioKeyReleased

    private void tblClassificacaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblClassificacaoKeyReleased
        // Pega o valor da linha selecionada e cria o modelo de tabela
        int linha = tblClassificacao.getSelectedRow();  
        DefaultTableModel modelo = (DefaultTableModel)tblClassificacao.getModel();
        
        // Pega as informações da tabela e joga para os campos na tela
        txtCodClassificacaoClassificacao.setText(modelo.getValueAt(linha, 0).toString());
        txtClassificacaoClassificacao.setText(modelo.getValueAt(linha, 1).toString());
    }//GEN-LAST:event_tblClassificacaoKeyReleased

    private void tblClassificacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClassificacaoMouseClicked
        // Pega o valor da linha selecionada e cria o modelo de tabela
        int linha = tblClassificacao.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel)tblClassificacao.getModel();
        
        // Pega as informações da tabela e joga para os campos na tela
        txtCodClassificacaoClassificacao.setText(modelo.getValueAt(linha, 0).toString());
        txtClassificacaoClassificacao.setText(modelo.getValueAt(linha, 1).toString());
    }//GEN-LAST:event_tblClassificacaoMouseClicked

    private void btnCancelarClassificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarClassificacaoActionPerformed
        // Visualização dos botões e do campo na tela
        btnIncluirClassificacao.setVisible(true);
        btnAlterarClassificacao.setVisible(true);
        btnExcluirClassificacao.setVisible(true);
        btnGravarClassificacao.setVisible(false);
        btnCancelarClassificacao.setVisible(false);
        txtCodClassificacaoClassificacao.setEnabled(true);
        
        // Variável usada para direcionar fluxo do código
        IncluirClicado = false;
        AlterarClicado = false;
    }//GEN-LAST:event_btnCancelarClassificacaoActionPerformed

    private void btnGravarClassificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarClassificacaoActionPerformed
        // Abrir conexão com o banco sql server
        abrirConexao();

        // Consistência dos campos da tela
        if(consistirCamposClassificacao())
        {      
            // Modo de inclusão
            if(IncluirClicado == true)
            {
                // Instância da classe aplicação
                ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();

                // Carrega objeto e realiza chamada DAO para consistencia da chave
                String CodClassificacao = txtCodClassificacaoClassificacao.getText();
                if(!classificacaoDAO.consistirChave(connection, new Classificacao(CodClassificacao, "", "")))
                {
                   // Carrega objeto e realiza chamada DAO para inclusão
                   String Classificacao = txtClassificacaoClassificacao.getText();
                   classificacaoDAO.incluirClassificacao(connection, new Classificacao(CodClassificacao, Classificacao, ""), tblClassificacao);
                }
                else
                {
                   // Abre a janela de aviso
                   JOptionPane.showMessageDialog(null, "Código de Classificação já existente!", "Titulo da Janela", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            // Modo de alteração
            if(AlterarClicado == true)
            {
                AlterarClicado = false;

                // Instância da classe aplicação
                ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();

                // Carrega objeto e realiza chamada DAO para alteração
                String CodClassificacao = txtCodClassificacaoClassificacao.getText();
                String Classificacao = txtClassificacaoClassificacao.getText();
                classificacaoDAO.alterarClassificacao(connection, new Classificacao(CodClassificacao, Classificacao, ""), tblClassificacao);
                
                // Visualização dos botões e do campo na tela
                btnIncluirClassificacao.setVisible(true);
                btnAlterarClassificacao.setVisible(true);
                btnExcluirClassificacao.setVisible(true);
                btnGravarClassificacao.setVisible(false);
                btnCancelarClassificacao.setVisible(false);
                txtCodClassificacaoClassificacao.setEnabled(true);
                 
                // Variável usada para direcionar fluxo do código
                IncluirClicado = false;
                AlterarClicado = false;
            }
        }
        
        // Fechar conexão com o banco sql server
        //fecharConexao();
    }//GEN-LAST:event_btnGravarClassificacaoActionPerformed

    private void btnExcluirClassificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirClassificacaoActionPerformed
        // Abrir conexão com o banco sql server
        abrirConexao();

        // Abre a janela de confirmação
        Object[] opcoes = {"Sim", "Não"};
        int opcao = JOptionPane.showOptionDialog(null, "Deseja excluir esse CodClassificação?", "Excluir Classificação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
        null, opcoes, opcoes[0]);
    
        // Realiza exclusão caso opção selecionada for igual a sim
        if(opcao == 0)
        {
            // Instância da classe classificação
            ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();

            // Carrega objeto e realiza chamada DAO
            String CodClassificacao = txtCodClassificacaoClassificacao.getText();
            classificacaoDAO.excluirClassificacao(connection, new Classificacao(CodClassificacao, "", ""), tblClassificacao);
        }
        
        // Fechar conexão com o banco sql server
        //fecharConexao();
    }//GEN-LAST:event_btnExcluirClassificacaoActionPerformed

    private void btnAlterarClassificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarClassificacaoActionPerformed
        // Visualização dos botões e do campo na tela
        btnIncluirClassificacao.setVisible(false);
        btnAlterarClassificacao.setVisible(false);
        btnExcluirClassificacao.setVisible(false);
        btnGravarClassificacao.setVisible(true);
        btnCancelarClassificacao.setVisible(true);
        txtCodClassificacaoClassificacao.setEnabled(false);
        
        // Variável usada para direcionar fluxo do código
        AlterarClicado = true;

        // Da o foco no campo da tela
        txtClassificacaoClassificacao.grabFocus();

        // Da o foco na primeira linha do Grid
        tblClassificacao.clearSelection();
        tblClassificacao.changeSelection(tblClassificacao.getRowCount() - 24, 0, true, false);
    }//GEN-LAST:event_btnAlterarClassificacaoActionPerformed

    private void btnIncluirClassificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirClassificacaoActionPerformed
        // Visualização dos botões na tela
        btnIncluirClassificacao.setVisible(false);
        btnAlterarClassificacao.setVisible(false);
        btnExcluirClassificacao.setVisible(false);
        btnGravarClassificacao.setVisible(true);
        btnCancelarClassificacao.setVisible(true);

        // Variável usada para direcionar fluxo do código
        IncluirClicado = true;

        // Limpas os campos da tela
        txtCodClassificacaoClassificacao.setText("");
        txtClassificacaoClassificacao.setText("");

        // Da o foco no campo da tela
        txtCodClassificacaoClassificacao.requestFocus();

        // Da o foco na primeira linha do Grid
        tblClassificacao.clearSelection();
        tblClassificacao.changeSelection(tblClassificacao.getRowCount() - 24, 0, true, false);
    }//GEN-LAST:event_btnIncluirClassificacaoActionPerformed

    private void btnSalvarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarUsuarioActionPerformed
        // Visualização dos botões e do painel na tela
        btnIncluirUsuario.setVisible(true);
        btnAlterarUsuario.setVisible(true);
        btnExcluirUsuario.setVisible(true);
        btnSalvarUsuario.setVisible(false);
        btnGravarUsuario.setVisible(false);
        btnCancelarUsuario.setVisible(false);
        pnlClassificacaoAplicacaoUsuario.setVisible(false);
        pnlUsuarioRelacao.setVisible(true);
        
        RelacaoDAO relacaoDAO = new RelacaoDAO();
        String CodUsuario =  txtCodUsuarioUsuario.getText();
        relacaoDAO.confirmarRelacao(connection, CodUsuario);
        
      
        String Where = "where CodUsuario = " + CodUsuario + " ";
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int Usuario;    
        Usuario = usuarioDAO.buscarUltimoRegistro(connection); 
        txtCodUsuarioUsuario.setText("" + Usuario + "");
        usuarioDAO.atualizarTabela(connection, tblUsuario);       
    //    relacaoDAO.atualizarTabela(connection, Where, new Relacao(Integer.parseInt(CodUsuario), "", "", "", "", "", "", "", "", ""));
            
        // Variável usada para direcionar fluxo do código
        IncluirClicado = false;
        AlterarClicado = false;
    }//GEN-LAST:event_btnSalvarUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Principal principal = new Principal();
                // Personalizado
                // JFrame frame = new JFrame();
//              Color corInicial = new Color(3,188,123);
//              Color corFinal = new Color(0,169,157);
//              JGradientPanel panel = new JGradientPanel(corInicial, corFinal);   
//              principal.setContentPane(panel);
                principal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                principal.setTitle("Gerenciamento de Senha");
                principal.setLocationRelativeTo(null);
                principal.setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarAplicacao;
    private javax.swing.JButton btnAlterarClassificacao;
    private javax.swing.JButton btnAlterarUsuario;
    private javax.swing.JButton btnCancelarAplicacao;
    private javax.swing.JButton btnCancelarClassificacao;
    private javax.swing.JButton btnCancelarUsuario;
    private javax.swing.JButton btnExcluirAplicacao;
    private javax.swing.JButton btnExcluirClassificacao;
    private javax.swing.JButton btnExcluirUsuario;
    private javax.swing.JButton btnGravarAplicacao;
    private javax.swing.JButton btnGravarClassificacao;
    private javax.swing.JButton btnGravarUsuario;
    private javax.swing.JButton btnIncluirAplicacao;
    private javax.swing.JButton btnIncluirClassificacao;
    private javax.swing.JButton btnIncluirUsuario;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvarUsuario;
    private javax.swing.JLabel lblAtivoUsuario;
    private javax.swing.JLabel lblClassificacaoClassificacao;
    private javax.swing.JLabel lblCodAplicacaoAplicacao;
    private javax.swing.JLabel lblCodClassificacaoAplicacao;
    private javax.swing.JLabel lblCodClassificacaoClassificacao;
    private javax.swing.JLabel lblCodUsuarioUsuario;
    private javax.swing.JLabel lblComentarioAplicacao;
    private javax.swing.JLabel lblNivelAplicacao;
    private javax.swing.JLabel lblNivelUsuario;
    private javax.swing.JLabel lblNomeAplicacao;
    private javax.swing.JLabel lblSenhaUsuario;
    private javax.swing.JLabel lblUsuarioUsuario;
    private javax.swing.JPanel pnlAplicacao;
    private javax.swing.JPanel pnlClassificacao;
    private javax.swing.JPanel pnlClassificacaoAplicacaoUsuario;
    private javax.swing.JPanel pnlUsuario;
    private javax.swing.JPanel pnlUsuarioRelacao;
    private javax.swing.JScrollPane slpAplicacao;
    private javax.swing.JScrollPane slpClassificacao;
    private javax.swing.JScrollPane slpClassificacaoAplicacaoUsuario;
    private javax.swing.JScrollPane slpClassificacaoUsuario;
    private javax.swing.JScrollPane slpRelacao;
    private javax.swing.JScrollPane slpUsuario;
    private javax.swing.JTabbedPane tabGerenciadorSenha;
    private javax.swing.JTable tblAplicacao;
    private javax.swing.JTable tblClassificacao;
    private javax.swing.JTable tblClassificacaoAplicacaoUsuario;
    private javax.swing.JTable tblClassificacaoUsuario;
    private javax.swing.JTable tblRelacao;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txtAplicacaoClassificacao;
    private javax.swing.JTextField txtAtivoUsuario;
    private javax.swing.JTextField txtClassificacaoClassificacao;
    private javax.swing.JTextField txtCodAplicacaoAplicacao;
    private javax.swing.JTextField txtCodClassificacaoAplicacao;
    private javax.swing.JTextField txtCodClassificacaoClassificacao;
    private javax.swing.JTextField txtCodUsuarioUsuario;
    private javax.swing.JTextField txtComentarioAplicacao;
    private javax.swing.JTextField txtNivelAplicacao;
    private javax.swing.JTextField txtNivelUsuario;
    private javax.swing.JTextField txtNomeAplicacao;
    private javax.swing.JTextField txtSenhaUsuario;
    private javax.swing.JTextField txtUsuarioUsuario;
    // End of variables declaration//GEN-END:variables
}
