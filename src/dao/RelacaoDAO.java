// Pacote que armazena classe RelacaoDAO
package dao;

// Bibliotecas usadas
import dados.Relacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RelacaoDAO {
    
    // Variáveis que utilizam instrução sql
    PreparedStatement p;
    ResultSet rs;
    
    // Métodos CRUD 
    public void incluirRelacao(Connection connection, Relacao v)
    {
        String sql = "INSERT INTO Relacao VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Date data = new Date();
            
        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, v.getCodUsuario());
            p.setString(2, v.getCodAplicacao());
            p.setString(3, v.getSenha());
            p.setString(4, v.getIncluir());
            p.setString(5, v.getAlterar());
            p.setString(6, v.getExcluir());
            p.setString(7, v.getConsultar());
            p.setString(8, v.getImprimir());
            p.setDate(9, new java.sql.Date(data.getTime()));
            p.setDate(10, new java.sql.Date(data.getTime()));
            p.setString(11, v.getUsuario());
            p.setString(12, v.getConfirma());
            p.executeUpdate();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
    
    public void alterarRelacao(Connection connection, Relacao v)
    {
        String sql = "UPDATE Relacao SET Senha = ?, Incluir = ?, Alterar = ?, Excluir = ?, Consultar = ?, Imprimir = ?, Inclusao = ?, Alteracao = ?, Usuario = ?, Confirma = ?"
                   + " WHERE CodUsuario = ? AND CodAplicacao = ?";
        Date data = new Date();
         
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, v.getSenha());
            p.setString(2, v.getIncluir());
            p.setString(3, v.getAlterar());
            p.setString(4, v.getExcluir());
            p.setString(5, v.getConsultar());
            p.setString(6, v.getImprimir());
            p.setDate(7, new java.sql.Date(data.getTime()));
            p.setDate(8, new java.sql.Date(data.getTime()));
            p.setString(9, v.getUsuario());
            p.setString(10, v.getConfirma());
            p.setInt(11, v.getCodUsuario());
            p.setString(12, v.getCodAplicacao());      
            p.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();   
        }
    }
    
    public void excluirRelacao(Connection connection, Relacao v)
    {
        String sql = "DELETE FROM Relacao WHERE CodUsuario = ? AND Confirma = 'N'";
        
        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, v.getCodUsuario());
            p.executeUpdate();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
    
    public void confirmarRelacao(Connection connection, String CodUsuario)
    {
        String sql = "UPDATE Relacao SET Confirma = 'S' WHERE CodUsuario = ? AND Confirma = 'N'";
        
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, CodUsuario);
            p.executeUpdate();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
        
    public void atualizarTabela(Connection connection, String where, Relacao v)
    {
        String sql = "SELECT * FROM Relacao " + where + "";            
        
        try {
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            if(rs.next())
            {
                alterarRelacao(connection, v);
            }
            else
            {
                incluirRelacao(connection, v);
            }
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }  
    
    public DefaultTableModel selecionarRelacao(Connection connection, JTable tabela, String CodUsuario)
    {
        String sql = "SELECT Classificacao.CodClassificacao, Classificacao.Classificacao, Relacao.CodAplicacao, Aplicacao.Nome, Relacao.Incluir, Relacao.Alterar, Relacao.Excluir, Relacao.Consultar, Relacao.Imprimir\n" +
                     "FROM Classificacao RIGHT OUTER JOIN\n" +
                     " Aplicacao ON Classificacao.CodClassificacao = Aplicacao.CodClassificacao RIGHT OUTER JOIN\n" +
                     "Relacao ON Aplicacao.CodAplicacao = Relacao.CodAplicacao WHERE (Relacao.CodUsuario = " + CodUsuario + ") ORDER BY CodClassificacao ASC";     
        // Pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);        
        
        try {
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("CodClassificacao"), rs.getString("Classificacao"), rs.getString("CodAplicacao"), rs.getString("Nome"), rs.getString("Incluir"), rs.getString("Alterar"), rs.getString("Excluir"), 
                rs.getString("Consultar"), rs.getString("Imprimir")});
            }
            modelo.fireTableDataChanged();
            return modelo;
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
        return modelo;
    }
    
    public boolean consistirChave(Connection connection, Relacao v)
    {
       boolean resposta = false;
       String sql = "SELECT * FROM Relacao WHERE CodUsuario = ? AND CodAplicacao ?";
                 
        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, v.getCodUsuario());
            p.setString(2, v.getCodAplicacao());
            rs = p.executeQuery();
            resposta =  rs.next();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
        return resposta;
    }
}
