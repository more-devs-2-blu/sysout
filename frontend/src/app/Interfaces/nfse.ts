export interface Nfse {
  id: string,
  userId: string,
  statusNfse: string | null,
  valorTotal: number,
  cpfCnpjPrestador: string,
  cidadePrestador: string,
  dataDeEmissao: string,
  dataDoFatoGerador: string,
  registroEconomicoPrestador?: string | null,
  serie: number,
  tipo: string,
  nomeRazaoSocial: string,
  tipoTomador: number,
  cpfCnpjTomador: string,
  registroEconomicoTomador?: string | null,
  cidade: string,
  cepTomador: string,
  pais: string,
  logradouro: string,
  bairro: string,
  codigoLocalPrestacaoServico: number,
  codigoItemListaServico: number,
  localDeIncidencia: string,
  situacaoTributaria: string,
  aliquotaItemListaServico: number,
  valorServico: number,
  valorDeducao: number,
  baseDeCalculo: number,
  issqn: number,
  issrf: number,
  descritivo: string,
  ir: number,
  pis: number,
  cofins: number,
  descontoIncondicional?: number | null,
  deducao: number,
  valorIssqn: number,
  valorIssrf: number,
  valorTotalFederal: number,
  descontoCondicional: number,
  tributaMunicipioPrestador: string,
  valorTributavel: number
}
