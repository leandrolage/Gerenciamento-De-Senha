// Pacote que armazena classe UsuárioDAO
package dao;

// Bibliotecas usadas
import dados.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UsuarioDAO {
    
    // Variáveis que utilizam instrução sql
    PreparedStatement p;
    ResultSet rs;
    
    // Métodos CRUD 
    public void incluirUsuario(Connection connection, Usuario v, JTable tabela, String Where)
    {
        String sql = "INSERT INTO Usuario VALUES(?, ?, ?, ?, ?, ?, ?)";
        Date data = new Date();
        // Pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);   
        
        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, v.getCodUsuario());
            p.setString(2, v.getUsuario());
            p.setString(3, v.getSenha());
            p.setString(4, v.getNivel());
            p.setString(5, v.getAtivo());
            p.setDate(6, new java.sql.Date(data.getTime()));
            p.setDate(7, new java.sql.Date(data.getTime()));
            p.executeUpdate();
            sql = "SELECT * FROM Usuario " + Where + " ORDER BY CodUsuario ASC";    
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("CodUsuario"), rs.getString("Usuario"), rs.getString("Senha"), rs.getString("Nivel"), rs.getString("Ativo")});
            }
            modelo.fireTableDataChanged();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
    
    public void alterarUsuario(Connection connection, Usuario v, JTable tabela)
    {
        String sql = "UPDATE Usuario SET Usuario = ?, Senha = ?, Nivel = ?, Ativo = ?, Inclusao = ?, Alteracao = ? WHERE CodUsuario = ?";
        Date data = new Date();
        // Pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);   
        
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, v.getUsuario());
            p.setString(2, v.getSenha());
            p.setString(3, v.getNivel());
            p.setString(4, v.getAtivo());
            p.setDate(5, new java.sql.Date(data.getTime()));
            p.setDate(6, new java.sql.Date(data.getTime()));
             p.setInt(7, v.getCodUsuario());
            p.executeUpdate();
            sql = "SELECT * FROM Usuario ORDER BY CodUsuario ASC";
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("CodUsuario"), rs.getString("Usuario"), rs.getString("Senha"), rs.getString("Nivel"), rs.getString("Ativo")});
            }
            modelo.fireTableDataChanged();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
    }
    
    public void excluirUsuario(Connection connection, Usuario v, JTable tabela)
    {
        String sql = "DELETE FROM Usuario WHERE CodUsuario = ?";
        // Pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();    
        modelo.setNumRows(0);
        
        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, v.getCodUsuario());
            p.executeUpdate();
            sql = "SELECT * FROM Usuario ORDER BY CodUsuario ASC";
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("CodUsuario"), rs.getString("Usuario"), rs.getString("Senha"), rs.getString("Nivel"), rs.getString("Ativo")});
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
        String sql = "SELECT * FROM Usuario ORDER BY CodUsuario ASC";       
        // Pega o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);        
        
        try {
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                modelo.addRow(new String[]{rs.getString("CodUsuario"), rs.getString("Usuario"), rs.getString("Senha"), rs.getString("Nivel"), rs.getString("Ativo")});
            }
            modelo.fireTableDataChanged();
        }
        catch(SQLException e)
        {
            e.printStackTrace();   
        }
    }   
    
    public int buscarUltimoRegistro(Connection connection)
    {
        int usuario = 0;
        String sql = "SELECT MAX(CodUsuario) AS CodUsuario FROM Usuario";              
        
        try {
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();
            if(rs.next())
            {
                usuario = Integer.parseInt(rs.getString("CodUsuario"));
                return usuario;
            }
            else
            {
                usuario = 1;
                return usuario;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();   
        }
        return usuario;
    }   
    
    public boolean consistirChave(Connection connection, Usuario v)
    {
        boolean resposta = false;
        String sql = "SELECT * FROM Usuario WHERE CodUsuario = ?";
                 
        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, v.getCodUsuario());
            rs = p.executeQuery();
            resposta =  rs.next();
        }
        catch(SQLException e)
        {
             e.printStackTrace();   
        }
        return resposta;
    }
    
    public boolean verificarUsuario(Connection connection, Usuario v) 
    {
        boolean resposta = false;
        String sql = "SELECT * FROM Usuario WHERE Usuario = ? AND Senha = ?";
        
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, v.getUsuario());
            p.setString(2, v.getSenha());
            rs = p.executeQuery();
            resposta =  rs.next();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        return resposta;
    } 
}
