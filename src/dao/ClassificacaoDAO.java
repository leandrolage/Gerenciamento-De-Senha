// Pacote que armazena classe ClassificacaoDAO
package dao;

// Bibliotecas usadas
import dados.Classificacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClassificacaoDAO {
    
    // Variáveis que utilizam instrução sql
    PreparedStatement p;
    ResultSet rs;
    
    // Métodos CRUD 
    public void incluirClassificacao(Connection connection, Classificacao v, JTable tabela)
    {
        String sql = "INSERT INTO Classificacao VALUES(?, ?, ?, ?, ?)";   
        Date data = new Date();
        // Pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);
        
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, v.getCodClassificacao());
            p.setString(2, v.getClassificacao());
            p.setDate(3, new java.sql.Date(data.getTime()));
            p.setDate(4, new java.sql.Date(data.getTime()));
            p.setString(5, v.getUsuario());
            p.executeUpdate();
            sql = "SELECT * FROM Classificacao ORDER BY CodClassificacao ASC";
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("CodClassificacao"), rs.getString("Classificacao")});
            }
            modelo.fireTableDataChanged();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
    
    public void alterarClassificacao(Connection connection, Classificacao v, JTable tabela)
    {
        String sql = "UPDATE Classificacao SET CodClassificacao = ?, Classificacao = ? , Inclusao = ? , Alteracao = ? , Usuario = ? WHERE CodClassificacao = ?";
        Date data = new Date();
        //pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);
        
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, v.getCodClassificacao());
            p.setString(2, v.getClassificacao());
            p.setDate(3, new java.sql.Date(data.getTime()));
            p.setDate(4, new java.sql.Date(data.getTime()));
            p.setString(5, v.getUsuario());
            p.setString(6, v.getCodClassificacao());
            p.executeUpdate();
            sql = "SELECT * FROM Classificacao ORDER BY CodClassificacao ASC";
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("CodClassificacao"), rs.getString("Classificacao")});
            }
            modelo.fireTableDataChanged();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
    
    public void excluirClassificacao(Connection connection, Classificacao v, JTable tabela)
    {
        String sql = "DELETE FROM Classificacao WHERE CodClassificacao = ?";
        //pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);
        
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, v.getCodClassificacao());
            p.executeUpdate();
            sql = "SELECT * FROM Classificacao ORDER BY CodClassificacao ASC";
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("CodClassificacao"), rs.getString("Classificacao")});
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
        String sql = "SELECT * FROM Classificacao ORDER BY CodClassificacao ASC";      
        //pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);        
        
        try {
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("CodClassificacao"), rs.getString("Classificacao")});
            }
            modelo.fireTableDataChanged();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
    
    public void selecionarClassificacao(Connection connection, JTable tabela, int CodUsuario, String CodClassificacao)
    {        
       boolean Incluir;
       boolean Alterar;
       boolean Excluir;
       boolean Consultar;
       boolean Imprimir;
       String sql = "SELECT DISTINCT Aplicacao.CodAplicacao, Aplicacao.Nome AS Aplicacao, \n" +
                    "\n" +
                    "IIf((SELECT IIf([Relacao].[Incluir] IS NULL,'N',[Relacao].[Incluir]) AS Incluir FROM Relacao\n" +
                    "WHERE (([Relacao].[CodAplicacao] = [Aplicacao].[CodAplicacao]) AND \n" +
                    "([Relacao].[CodUsuario] = " + CodUsuario + "))) IS NULL,'N',(SELECT IIf([Relacao].[Incluir] IS NULL,'N', [Relacao].[Incluir]) AS Incluir \n" +
                    "	FROM Relacao WHERE (([Relacao].[CodAplicacao] = [Aplicacao].[CodAplicacao]) AND ([Relacao].[CodUsuario] = " + CodUsuario + ")))) AS Incluir, \n" +
                    "\n" +
                    "IIf((SELECT IIf([Relacao].[Alterar] IS NULL,'N',[Relacao].[Alterar]) AS Alterar FROM Relacao \n" +
                    "WHERE (([Relacao].[CodAplicacao] = [Aplicacao].[CodAplicacao] ) AND ([Relacao].[CodUsuario] = " + CodUsuario + ")))IS NULL,'N',\n" +
                    "(SELECT IIf([Relacao].[Alterar] IS NULL,'N',[Relacao].[Alterar]) AS Alterar\n" +
                    "    FROM Relacao WHERE (([Relacao].[CodAplicacao] = [Aplicacao].[CodAplicacao]) AND ([Relacao].[CodUsuario] = " + CodUsuario + ")))) AS Alterar, \n" +
                    "\n" +
                    "IIf((SELECT IIf([Relacao].[Excluir] IS NULL,'N',[Relacao].[Excluir]) AS Excluir FROM Relacao\n" +
                    "WHERE (([Relacao].[CodAplicacao] = [Aplicacao].[CodAplicacao]) AND ([Relacao].[CodUsuario] = " + CodUsuario + ")))IS NULL,'N',\n" +
                    "(SELECT IIf([Relacao].[Excluir] IS NULL,'N',[Relacao].[Excluir]) AS Excluir \n" +
                    "    FROM Relacao WHERE (([Relacao].[CodAplicacao] = [Aplicacao].[CodAplicacao]) AND ([Relacao].[CodUsuario] = " + CodUsuario + ")))) AS Excluir,\n" +
                    "\n" +
                    "IIf((SELECT IIf([Relacao].[Consultar] IS NULL,'N',[Relacao].[Consultar]) AS Consultar FROM Relacao \n" +
                    "WHERE (([Relacao].[CodAplicacao] = [Aplicacao].[CodAplicacao]) AND ([Relacao].[CodUsuario] = " + CodUsuario + ")))IS NULL,'N', \n" +
                    "(SELECT IIf([Relacao].[Consultar] IS NULL,'N',[Relacao].[Consultar]) AS Consultar \n" +
                    "    FROM Relacao WHERE (([Relacao].[CodAplicacao] = [Aplicacao].[CodAplicacao]) AND ([Relacao].[CodUsuario] = " + CodUsuario + ")))) AS Consultar, \n" +
                    "\n" +
                    "IIf((SELECT IIf([Relacao].[Imprimir] IS NULL,'N',[Relacao].[Imprimir]) AS Imprimir FROM Relacao \n" +
                    "WHERE (([Relacao].[CodAplicacao] = [Aplicacao].[CodAplicacao]) AND ([Relacao].[CodUsuario] = " + CodUsuario + ")))IS NULL,'N', \n" +
                    "(SELECT IIf([Relacao].[Imprimir] IS NULL,'N',[Relacao].[Imprimir]) AS Imprimir\n" +
                    "    FROM Relacao WHERE (([Relacao].[CodAplicacao] = [Aplicacao].[CodAplicacao]) AND ([Relacao].[CodUsuario] = " + CodUsuario + ")))) AS Imprimir \n" +
                    "\n" +
                    "FROM Aplicacao LEFT JOIN Relacao ON Aplicacao.CodAplicacao = Relacao.CodAplicacao WHERE (((Relacao.CodAplicacao) IS NULL OR \n" +
                    "(Relacao.CodAplicacao) <> '') AND Aplicacao.CodClassificacao = '" + CodClassificacao + "')";     
        //pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);        
        
        try {
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                if(rs.getString("Incluir").equals("S")){Incluir = true;}else{Incluir = false;}
                if(rs.getString("Alterar").equals("S")){Alterar = true;}else{Alterar = false;}
                if(rs.getString("Excluir").equals("S")){Excluir = true;}else{Excluir = false;}
                if(rs.getString("Consultar").equals("S")){Consultar = true;}else{Consultar = false;}
                if(rs.getString("Imprimir").equals("S")){Imprimir = true;}else{Imprimir = false;}
                modelo.addRow(new Object[]{rs.getString("CodAplicacao"), rs.getString("Aplicacao"), Incluir, Alterar, Excluir, Consultar, Imprimir});
            }
            modelo.fireTableDataChanged();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
    
    public boolean consistirChave(Connection connection, Classificacao v)
    {
        boolean resposta = false;
        String sql = "SELECT * FROM Classificacao WHERE CodClassificacao = ?";
                 
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, v.getCodClassificacao());
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
