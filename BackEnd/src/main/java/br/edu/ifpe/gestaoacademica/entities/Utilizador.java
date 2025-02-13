package br.edu.ifpe.gestaoacademica.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ifpe.gestaoacademica.entities.enums.AcessLevel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "Utilizador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Utilizador implements UserDetails{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Lob  // Define o campo como um BLOB
    @Column(columnDefinition = "LONGBLOB")  // Para MySQL, pode ser BLOB, MEDIUMBLOB ou LONGBLOB
    private byte[] foto;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(nullable = false, unique = true, length = 45)
	private String login;
	
    @Column(nullable = false, length = 60)
	private String senha;
    
	private boolean ativo;
	
	@Column(name = "acess_level")
	@Enumerated(EnumType.STRING)
	private AcessLevel acessLevel;
	
	 @OneToOne(mappedBy = "utilizador")
	 private Usuario usuario;

	   
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public String getPassword() {
		return senha;
	}
	@Override
	public String getUsername() {
		return login;
	}
	public void inativar() {
		this.ativo= false;
	}
}
