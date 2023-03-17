package devs2blu.sysout.nfse.dtos;

import java.util.UUID;

import devs2blu.sysout.nfse.enums.StatusNfseEnum;
import devs2blu.sysout.nfse.enums.TipoTomadorEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "nfse")
public class NfseDto {
	// Informações da nota
	private UUID id;

	private UUID userId;

	private StatusNfseEnum statusNfse;

	@NotNull
	private double valorTotal;

	// Informação do prestador
	@NotBlank
	private String cpfCnpjPrestador;

	@NotBlank
	private String cidadePrestador;

	// Informação do tomador
	private TipoTomadorEnum tipoTomador;

	// Item da nota
	@NotNull
	private int codigoLocalPrestacaoServico;

	@NotNull
	private int codigoItemListaServico;

	@NotBlank
	private String descritivo;

	@NotNull
	private double aliquotaItemListaServico;

	@NotNull
	private int situacaoTributaria;

	@NotNull
	private double valorTributavel;

	@NotBlank
	private String tributaMunicipioPrestador;

	@NotBlank
	private String nomeRazaoSocial;

	@NotBlank
	private String dataDoFatoGerador;

	@NotBlank
	private String cpfCnpjTomador;

	@NotNull
	private double valorServico;
	@NotNull
	private double valorIssqn;
}