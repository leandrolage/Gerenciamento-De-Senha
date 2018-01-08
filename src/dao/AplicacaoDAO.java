// Pacote que armazena classe AplicacaoDAO
package dao;

// Bibliotecas usadas
import dados.Aplicacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AplicacaoDAO {
    
    // Variáveis que utilizam instrução sql
    PreparedStatement p;
    ResultSet rs;
    
    // Métodos CRUD 
    public void incluirAplicacao(Connection connection, Aplicacao v, JTable tabela)
    {
        String sql = "INSERT INTO Aplicacao VALUES(?, ?, ?, ?, ?, ?, ?, ?)";   
        Date data = new Date();
        //pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);  
        
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, v.getCodAplicacao());
            p.setString(2, v.getNome());
            p.setString(3, v.getComentario());
            p.setString(4, v.getNivel());
            p.setString(5, v.getCodClassificacao());
            p.setDate(6, new java.sql.Date(data.getTime()));
            p.setDate(7, new java.sql.Date(data.getTime()));
            p.setString(8, v.getUsuario());
            p.executeUpdate();
            sql = "SELECT Aplicacao.CodAplicacao, Aplicacao.Nome, Aplicacao.Nivel, Aplicacao.CodClassificacao, Classificacao.Classificacao, Aplicacao.Comentario\n" +
                  "FROM Aplicacao LEFT OUTER JOIN\n" +
                  "Classificacao ON Aplicacao.CodClassificacao = Classificacao.CodClassificacao\n" +
                   "ORDER BY Aplicacao.CodAplicacao ASC";
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("CodAplicacao"), rs.getString("Nome"), rs.getString("Nivel"), rs.getString("CodClassificacao"), 
                rs.getString("Classificacao"), rs.getString("Comentario")});
            }
            modelo.fireTableDataChanged();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
    
    public void alterarAplicacao(Connection connection, Aplicacao v, JTable tabela)
    {
        String sql = "UPDATE Aplicacao SET Nome = ?, Comentario = ?, Nivel = ?, CodClassificacao = ?, Inclusao = ?, Alteracao = ?, Usuario = ? WHERE CodAplicacao = ?";
        Date data = new Date();
        //pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);  
        
        try {
            p = connection.prepareStatement(sql);  
            p.setString(1, v.getNome());
            p.setString(2, v.getComentario());
            p.setString(3, v.getNivel());
            p.setString(4, v.getCodClassificacao());
            p.setDate(5, new java.sql.Date(data.getTime()));
            p.setDate(6, new java.sql.Date(data.getTime()));
            p.setString(7, v.getUsuario());
            p.setString(8, v.getCodAplicacao());
            p.executeUpdate();
            sql = "SELECT Aplicacao.CodAplicacao, Aplicacao.Nome, Aplicacao.Nivel, Aplicacao.CodClassificacao, Classificacao.Classificacao, Aplicacao.Comentario\n" +
                  "FROM Aplicacao LEFT OUTER JOIN\n" +
                  "Classificacao ON Aplicacao.CodClassificacao = Classificacao.CodClassificacao\n" +
                  "ORDER BY Aplicacao.CodClassificacao ASC";
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("CodAplicacao"), rs.getString("Nome"), rs.getString("Nivel"), rs.getString("CodClassificacao"), 
                rs.getString("Classificacao"), rs.getString("Comentario")});
            }
            modelo.fireTableDataChanged();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
    
    public void excluirAplicacao(Connection connection, Aplicacao v, JTable tabela)
    {
        String sql = "DELETE FROM Aplicacao WHERE CodAplicacao = ?";
         //pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);   
        
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, v.getCodAplicacao());
            p.executeUpdate();
            sql = "SELECT Aplicacao.CodAplicacao, Aplicacao.Nome, Aplicacao.Nivel, Aplicacao.CodClassificacao, Classificacao.Classificacao, Aplicacao.Comentario\n" +
                  "FROM Aplicacao LEFT OUTER JOIN\n" +
                  "Classificacao ON Aplicacao.CodClassificacao = Classificacao.CodClassificacao\n" +
                  "ORDER BY Aplicacao.CodClassificacao ASC";
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("CodAplicacao"), rs.getString("Nome"), rs.getString("Nivel"), rs.getString("CodClassificacao"), 
                rs.getString("Classificacao"), rs.getString("Comentario")});
            }
            modelo.fireTableDataChanged();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
     
    public void atualizarTabela(Connection connection, JTable tabela)
    {
        String sql = "SELECT Aplicacao.CodAplicacao, Aplicacao.Nome, Aplicacao.Nivel, Aplicacao.CodClassificacao, Classificacao.Classificacao, Aplicacao.Comentario\n" +
                     "FROM Aplicacao LEFT OUTER JOIN\n" +
                     "Classificacao ON Aplicacao.CodClassificacao = Classificacao.CodClassificacao\n" +
                     "ORDER BY Aplicacao.CodClassificacao ASC";     
        //pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);        
        
        try {
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("CodAplicacao"), rs.getString("Nome"), rs.getString("Nivel"), rs.getString("CodClassificacao"), 
                rs.getString("Classificacao"), rs.getString("Comentario")});
            }
            modelo.fireTableDataChanged();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
    
    public void selecionarAplicacao(Connection connection, JTable tabela)
    {
        String sql = "SELECT Aplicacao.Nome FROM Classificacao LEFT OUTER JOIN Aplicacao ON Classificacao.CodClassificacao = Aplicacao.CodClassificacao";     
        //pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);        
        
        try {
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("Nome")});
            }
            modelo.fireTableDataChanged();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
    
    public boolean consistirChave(Connection connection, Aplicacao v)
    {
        boolean resposta = false;
        String sql = "SELECT * FROM Aplicacao WHERE CodAplicacao = ?";
                 
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, v.getCodAplicacao());
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
