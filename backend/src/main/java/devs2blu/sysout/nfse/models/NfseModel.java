package devs2blu.sysout.nfse.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import devs2blu.sysout.nfse.enums.TipoTomadorEnum;
import devs2blu.sysout.nfse.enums.StatusNfseEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "nfses")
public class NfseModel implements Serializable {
	// Informações da nota
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(unique = true, updatable = false)
	private UUID id;

	@Column(nullable = false)
	private UUID userId;


	private StatusNfseEnum statusNfse;

	@Column(nullable = false)
	private double valorTotal;

	// Informação do prestador
	@Column(nullable = false)
	private String cpfCnpjPrestador;

	@Column(nullable = false)
	private String cidadePrestador;

	// Informação do tomador
	@Column(nullable = false)
	private TipoTomadorEnum tipoTomador;

	// Item da nota
	@Column(nullable = false)
	private int codigoLocalPrestacaoServico;

	@Column(nullable = false)
	private int codigoItemListaServico;

	@Column(nullable = false)
	private String descritivo;

	@Column(nullable = false)
	private double aliquotaItemListaServico;

	@Column(nullable = false)
	private int situacaoTributaria;

	@Column(nullable = false)
	private double valorTributavel;

	@Column(nullable = false)
	private String tributaMunicipioPrestador;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyy-MM-dd" )
	private LocalDateTime dataDeEmissao;

	@Column(nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private String dataDoFatoGerador;

	@Column(nullable = false)
	private String nomeRazaoSocial;

	@Column(nullable = false)
	private String cpfCnpjTomador;

	@Column(nullable = false)
	private double valorServico;

	@Column(nullable = false)
	private double valorIssqn;
}