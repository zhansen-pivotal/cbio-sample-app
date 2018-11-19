package com.tgen.spike.cbio.cbiospike;

import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

public class CBIOService {

  private final RestTemplate restTemplate = new RestTemplate();
  private final String BASE_CBIO_URL = "http://www.cbioportal.org/webservice.do?cmd=";
  private String uri;

  //No Params
  public String getTypesOfCancer() {
    String CANCER_TYPE_METHOD = "getTypesOfCancer";
    uri = BASE_CBIO_URL + CANCER_TYPE_METHOD;
    return getFromCBioPortal(uri);
  }

  //No Param
  public String getNetwork(String geneList) {
    String NETWORK_METHOD = "getNetwork";
    uri = BASE_CBIO_URL + NETWORK_METHOD;
    return getFromCBioPortal(appendGeneList(uri, geneList));
  }

  //No Params
  public String getCancerStudies() {
    String CANCER_STUDIES_METHOD = "getCancerStudies";
    uri = BASE_CBIO_URL + CANCER_STUDIES_METHOD;
    return getFromCBioPortal(uri);
  }

  public String getClinicalData(@NotNull String caseSetId) {
    String CLINICAL_DATA_METHOD = "getClinicalData";
    uri = BASE_CBIO_URL + CLINICAL_DATA_METHOD;
    return getFromCBioPortal(appendCaseSetId(uri, caseSetId));
  }

  public String getGeneticProfiles(@NotNull String cancerStudyId) {
    String GENETIC_PROFILE_METHOD = "getGeneticProfiles";
    uri = BASE_CBIO_URL + GENETIC_PROFILE_METHOD;
    return getFromCBioPortal(appendCancerStudyCase(uri, cancerStudyId));
  }


  public String getCaseLists(@NotNull String cancerStudyId) {
    String CASE_LIST_METHOD = "getCaseLists";
    uri = BASE_CBIO_URL + CASE_LIST_METHOD;
    return getFromCBioPortal(appendCancerStudyCase(uri, cancerStudyId));
  }

  public String getProfileData(@NotNull String caseSetId, @NotNull String geneticProfileId,
                               @NotNull String geneList) {
    String PROFILE_DATA_METHOD = "getProfileData";
    uri = BASE_CBIO_URL + PROFILE_DATA_METHOD;
    return getFromCBioPortalWithParams(uri, caseSetId, geneticProfileId, geneList);
  }

  public String getMutationData(@NotNull String geneticProfileId, String caseSetId,
                                @NotNull String geneList) {
    String MUTATION_DATA_METHOD = "getMutationData";
    uri = BASE_CBIO_URL + MUTATION_DATA_METHOD;
    return getFromCBioPortalWithParams(uri, caseSetId, geneticProfileId, geneList);
  }

  private String getFromCBioPortal(String uri) {
    return restTemplate.getForObject(uri, String.class);
  }

  private String getFromCBioPortalWithParams(String uri, String caseSetId, String geneticProfileId,
                                             String geneList) {
    uri = appendCaseSetId(uri, caseSetId);
    uri = appendGeneticProfile(uri, geneticProfileId);
    uri = appendGeneList(uri, geneList);
    System.out.println("DEBUG: getFromCBioPortalWithParams URI=" + uri);
    return getFromCBioPortal(uri);
  }

  private String appendCaseSetId(String uri, String caseSetId) {
    if (caseSetId != null) {
      uri = uri + "&case_set_id=" + caseSetId;
    }
    return uri;
  }

  private String appendCancerStudyCase(String uri, @NotNull String cancerStudyId) {
    if (cancerStudyId != null) {
      uri = uri + "&cancer_study_id=" + cancerStudyId;
    }
    return uri;
  }

  private String appendGeneList(String uri, String geneList) {
    if (geneList != null) {
      uri = uri + "&gene_list=" + geneList;
    }
    return uri;
  }

  private String appendGeneticProfile(String uri, String geneticProfileId) {
    if (geneticProfileId != null) {
      uri = uri + "&genetic_profile_id=" + geneticProfileId;
    }
    return uri;
  }

}
