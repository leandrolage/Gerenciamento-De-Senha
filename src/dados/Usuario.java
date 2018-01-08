// Pacote que armazena classe Usuário
package dados;

public class Usuario {

    // Variáveis de inicialização
    private int CodUsuario;
    private String Usuario;
    private String Senha;
    private String Nivel;
    private String Ativo;

    // Método Construtor da classe Usuário
    public Usuario(int CodUsuario, String Usuario, String Senha, String Nivel, String Ativo) {
        this.CodUsuario = CodUsuario;
        this.Usuario = Usuario;
        this.Senha = Senha;
        this.Nivel = Nivel;
        this.Ativo = Ativo;
    }
    
    // Getters and Setters da classe Usuário
    public int getCodUsuario() {
        return CodUsuario;
    }

    public void setCodUsuario(int CodUsuario) {
        this.CodUsuario = CodUsuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String Nivel) {
        this.Nivel = Nivel;
    }

    public String getAtivo() {
        return Ativo;
    }

    public void setAtivo(String Ativo) {
        this.Ativo = Ativo;
    } 
}
