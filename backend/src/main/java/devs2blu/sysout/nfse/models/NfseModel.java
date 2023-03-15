package devs2blu.sysout.nfse.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import devs2blu.sysout.nfse.enums.BorrowerType;
import devs2blu.sysout.nfse.enums.InvoiceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "nfses")
public class NfseModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(unique = true, updatable = false)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private UserModel user;

	// Provider information
	@Column
	private String providerEconomicRegistration;

	@Column
	private String providerCnpjOrCpf;

	// Invoice information
	@Column(nullable = false)
	private int series;

	@Column
	private char type;

	@Column
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dateOfIssue;

	@Column
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime TaxableEventDate;

	// Borrower information
	@Column
	@Enumerated(EnumType.STRING)
	private BorrowerType borrowerType;

	@Column
	private String borrower;

	@Column
	private String borrowerCnpjOrCpf;

	@Column
	private String borrowerEconomicRegistration;

	@Column
	private String borrowerCity;

	@Column
	private String borrowerCep;

	@Column
	private String borrowerCountry;

	@Column
	private String borrowerStreet;

	@Column
	private String borrowerDistrict;

	// Invoice Items
	@Column
	private String placeOfProvision;

	@Column
	private String serviceList;

	@Column
	private String placeOfIncidence;

	@Column
	private String taxSituation;

	@Column
	private double timboRate;

	@Column
	private double serviceValue;

	@Column
	private double discountUnconditional;

	@Column
	private double dedutionAmount;

	@Column
	private double calculationBase;

	@Column
	private double ISSQN;

	@Column
	private double ISSRF;

	@Column
	private String description;

	// Federal taxes
	@Column
	private double incomeTax;

	@Column
	private double pis;

	@Column
	private double cofins;

	// Values
	@Column
	private double amount;

	@Column
	private double unconditionalDiscount;

	@Column
	private double deduction;

	@Column
	private double calculationBasis;

	@Column
	private double totalIssqn;

	@Column
	private double totalIssrf;

	@Column
	private double totalFederalTaxes;

	@Column
	private double conditionalDiscount;

	@Column
	private double netValue;

	// Invoice status
	@Enumerated(EnumType.STRING)
	private InvoiceStatus invoiceStatus;
}
