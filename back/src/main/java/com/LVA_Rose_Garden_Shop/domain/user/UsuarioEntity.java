package com.LVA_Rose_Garden_Shop.domain.user;

import com.LVA_Rose_Garden_Shop.domain.user.valueobject.EmailUsuario;
import com.LVA_Rose_Garden_Shop.domain.user.valueobject.NomeCompletoUsuario;
import com.LVA_Rose_Garden_Shop.domain.user.valueobject.SenhaCriptografada;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Usuario")
@Table(name = "usuario")
public class UsuarioEntity {

    private static final String PAIS_PADRAO = "Brasil";
    private static final String NOME_GOOGLE_PADRAO = "Usuario Google";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    private String cpf;
    private String cnpj;
    private String endereco;
    private String numero;
    private String bairro;

    @Column(name = "ponto_referencia")
    private String pontoReferencia;

    private String complemento;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    private String celular;
    private String telefone;

    @Column(unique = true, nullable = false, length = 190)
    private String email;

    @Column(nullable = false)
    private String password;

    public static UsuarioEntity criarCadastroLocal(UsuarioForm form, String senhaCriptografada) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.aplicarCadastro(form, senhaCriptografada);
        return usuario;
    }

    public static UsuarioEntity criarCadastroGoogle(String email, String nomeCompleto, String senhaCriptografada) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.cpf = "";
        usuario.cnpj = "";
        usuario.endereco = "";
        usuario.numero = "";
        usuario.bairro = "";
        usuario.pontoReferencia = "";
        usuario.complemento = "";
        usuario.cidade = "";
        usuario.estado = "";
        usuario.cep = "";
        usuario.celular = "";
        usuario.telefone = "";
        usuario.tornarGoogle(email, nomeCompleto, senhaCriptografada);
        return usuario;
    }

    public static UsuarioEntity referencia(Long id) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.id = id;
        return usuario;
    }

    public void atualizarCadastro(UsuarioForm form, String senhaCriptografada) {
        aplicarCadastro(form, senhaCriptografada);
    }

    public void redefinirSenha(String senhaCriptografada) {
        this.password = senhaCriptografada;
    }

    public void atribuirId(Long id) {
        this.id = id;
    }

    public void atualizarDadosPessoais(String nomeCompleto,
                                       String cpf,
                                       String cnpj,
                                       String endereco,
                                       String numero,
                                       String bairro,
                                       String pontoReferencia,
                                       String complemento,
                                       String cidade,
                                       String estado,
                                       String pais,
                                       String cep,
                                       String celular,
                                       String telefone,
                                       String email) {
        this.nomeCompleto = NomeCompletoUsuario.of(limpar(nomeCompleto, this.nomeCompleto)).valor();
        this.cpf = limpar(cpf, "");
        this.cnpj = limpar(cnpj, "");
        this.endereco = limpar(endereco, "");
        this.numero = limpar(numero, "");
        this.bairro = limpar(bairro, "");
        this.pontoReferencia = limpar(pontoReferencia, "");
        this.complemento = limpar(complemento, "");
        this.cidade = limpar(cidade, "");
        this.estado = limpar(estado, "");
        this.pais = limpar(pais, PAIS_PADRAO);
        this.cep = limpar(cep, "");
        this.celular = limpar(celular, "");
        this.telefone = limpar(telefone, "");
        this.email = EmailUsuario.of(email).valor();
    }

    public boolean ehCadastroGoogle() {
        return limpar(cpf, "").isBlank()
                && limpar(cnpj, "").isBlank()
                && limpar(endereco, "").isBlank()
                && limpar(numero, "").isBlank()
                && limpar(bairro, "").isBlank()
                && !limpar(password, "").isBlank();
    }

    public boolean possuiCadastroCompleto() {
        return !limpar(nomeCompleto, "").isBlank()
                && !limpar(email, "").isBlank()
                && !limpar(password, "").isBlank();
    }

    public String nomeExibicao() {
        return limpar(nomeCompleto, NOME_GOOGLE_PADRAO);
    }

    public void tornarGoogle(String email, String nomeCompleto, String senhaCriptografada) {
        this.nomeCompleto = NomeCompletoUsuario.of(limpar(nomeCompleto, NOME_GOOGLE_PADRAO)).valor();
        this.email = EmailUsuario.of(email).valor();
        this.cpf = "";
        this.cnpj = "";
        this.endereco = "";
        this.numero = "";
        this.bairro = "";
        this.pontoReferencia = "";
        this.complemento = "";
        this.cidade = "";
        this.estado = "";
        this.pais = PAIS_PADRAO;
        this.cep = "";
        this.celular = "";
        this.telefone = "";
        this.password = SenhaCriptografada.of(senhaCriptografada).valor();
    }

    private void aplicarCadastro(UsuarioForm form, String senhaCriptografada) {
        this.nomeCompleto = NomeCompletoUsuario.of(limpar(form.getNomeCompleto(), "")).valor();
        this.cpf = limpar(form.getCpf(), "");
        this.cnpj = limpar(form.getCnpj(), "");
        this.endereco = limpar(form.getEndereco(), "");
        this.numero = limpar(form.getNumero(), "");
        this.bairro = limpar(form.getBairro(), "");
        this.pontoReferencia = limpar(form.getPontoReferencia(), "");
        this.complemento = limpar(form.getComplemento(), "");
        this.cidade = limpar(form.getCidade(), "");
        this.estado = limpar(form.getEstado(), "");
        this.pais = limpar(form.getPais(), PAIS_PADRAO);
        this.cep = limpar(form.getCep(), "");
        this.celular = limpar(form.getCelular(), "");
        this.telefone = limpar(form.getTelefone(), "");
        this.email = EmailUsuario.of(form.getEmail()).valor();
        this.password = SenhaCriptografada.of(senhaCriptografada).valor();
    }

    private static String limpar(String valor, String valorPadrao) {
        if (valor == null) {
            return valorPadrao;
        }

        String valorNormalizado = valor.trim();
        return valorNormalizado.isEmpty() ? valorPadrao : valorNormalizado;
    }

}
