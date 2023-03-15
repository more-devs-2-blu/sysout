package devs2blu.sysout.nfse.dtos;

import devs2blu.sysout.nfse.enums.BorrowerType;
import devs2blu.sysout.nfse.enums.InvoiceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Data
public class NfseDto {

	// Provider information


	private String providerEconomicRegistration;

	@NotBlank
	private String providerCnpjOrCpf;

	// Invoice information

	@NotNull
	private int series;

	// Borrower information

	@NotNull
	private BorrowerType borrowerType;

	@NotBlank
	private String borrower;

	@NotBlank
	private String borrowerCnpjOrCpf;

	private String borrowerEconomicRegistration;

	@NotBlank
	private String borrowerCity;

	@NotBlank
	private String borrowerCep;

	@NotBlank
	private String borrowerCountry;

	@NotBlank
	private String borrowerStreet;

	@NotBlank
	private String borrowerDistrict;

	// Invoice Items

	@NotBlank
	private String placeOfProvision;

	@NotBlank
	private String serviceList;

	@NotBlank
	private String placeOfIncidence;

	@NotBlank
	private String taxSituation;

	@NotNull
	private double timboRate;

	@NotNull
	private double serviceValue;

	@NotNull
	private double discountUnconditional;

	@NotNull
	private double dedutionAmount;

	@NotNull
	private double calculationBase;

	@NotNull
	private double ISSQN;

	@NotNull
	private double ISSRF;

	@NotBlank
	private String description;

	// Federal taxes

	@NotNull
	private double incomeTax;

	@NotNull
	private double pis;

	@NotNull
	private double cofins;

	// Values

	@NotNull
	private double amount;

	@NotNull
	private double unconditionalDiscount;

	@NotNull
	private double deduction;

	@NotNull
	private double totalIssqn;

	@NotNull
	private double totalIssrf;

	@NotNull
	private double totalFederalTaxes;

	@NotNull
	private double conditionalDescount;

	@NotNull
	private double netValue;

	// Invoice status


	private InvoiceStatus invoiceStatus;

}
