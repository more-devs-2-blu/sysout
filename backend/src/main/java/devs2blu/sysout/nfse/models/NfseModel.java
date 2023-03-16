package devs2blu.sysout.nfse.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import devs2blu.sysout.nfse.enums.BorrowerType;
import devs2blu.sysout.nfse.enums.InvoiceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "nfses")
public class NfseModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(unique = true, updatable = false)
	private UUID id;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_user")
	private UserModel user;

	// Provider information
	private String providerEconomicRegistration;

	private String providerCnpjOrCpf;

	// Invoice information
	@Column(nullable = false)
	private int series;

	private char type;

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dateOfIssue;

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime TaxableEventDate;

	// Borrower information
	private BorrowerType borrowerType;

	private String borrower;

	private String borrowerCnpjOrCpf;

	private String borrowerEconomicRegistration;

	private String borrowerCity;

	private String borrowerCep;

	private String borrowerCountry;

	private String borrowerStreet;

	private String borrowerDistrict;

	// Invoice Items
	private String placeOfProvision;

	private String serviceList;

	private String placeOfIncidence;

	private String taxSituation;

	private double timboRate;

	private double serviceValue;

	private double discountUnconditional;

	private double dedutionAmount;

	private double calculationBase;

	private double ISSQN;

	private double ISSRF;

	private String description;

	// Federal taxes
	private double incomeTax;

	private double pis;

	private double cofins;

	// Values
	private double amount;

	private double unconditionalDiscount;

	private double deduction;

	private double calculationBasis;

	private double totalIssqn;

	private double totalIssrf;

	private double totalFederalTaxes;

	private double conditionalDiscount;

	private double netValue;

	// Invoice status
	private InvoiceStatus invoiceStatus;
}
