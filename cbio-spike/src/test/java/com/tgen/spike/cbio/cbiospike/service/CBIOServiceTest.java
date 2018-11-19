package com.tgen.spike.cbio.cbiospike.service;

import com.tgen.spike.cbio.cbiospike.parsers.CSVParser;
import com.tgen.spike.cbio.cbiospike.service.CBIOService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class CBIOServiceTest {

  private final CBIOService cbioService = new CBIOService();
  private String result = null;

  @Test
  public void getTypesOfCancer() throws JSONException {
    result = cbioService.getTypesOfCancer();
    JSONObject json = getTestJsonObject(result);
    System.out.println(result);
    Assert.assertNotNull(result);
    Assert.assertEquals("aa", json.get("type_of_cancer_id"));
  }

  @Test
  @Ignore //ignoring test as it takes a long time to return
  public void getNetwork() {
    result = cbioService.getNetwork("EGFR+PTEN");
    System.out.println(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
  }

  @Test
  public void getCancerStudies() throws JSONException {
    result = cbioService.getCancerStudies();
    JSONObject json = getTestJsonObject(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
    Assert.assertEquals("paac_jhu_2014", json.get("cancer_study_id"));
  }

  @Test
  public void getClinicalData() throws JSONException {
    result = cbioService.getClinicalData("gbm_tcga_all");
    JSONObject json = getTestJsonObject(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
    Assert.assertEquals("Glioma", json.get("CANCER_TYPE"));
    Assert.assertEquals("918246a7-4ae5-463d-b889-011e0ee45efb", json.get("PATHOLOGY_REPORT_UUID"));
  }

  @Test
  public void getGeneticProfiles() throws JSONException {
    result = cbioService.getGeneticProfiles("gbm_tcga");
    JSONObject json = getTestJsonObject(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
    Assert.assertEquals("1209", json.get("cancer_study_id"));
    Assert.assertEquals("Protein expression (RPPA)", json.get("genetic_profile_name"));
  }

  @Test
  public void getCaseLists() throws JSONException {
    result = cbioService.getCaseLists("gbm_tcga");
    JSONObject json = getTestJsonObject(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
    Assert.assertEquals("1209", json.get("cancer_study_id"));
    Assert.assertEquals("All tumor samples that have mRNA, CNA and sequencing data (136 samples)", json.get("case_list_description"));
  }

  @Test //parsing a wide column seems to have issues.
  public void getProfileData() {
    result = cbioService.getProfileData("gbm_tcga_all", "gbm_tcga_mutations", "EGFR+PTEN");
    System.out.println(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
  }

  @Test //tsv parser has issues with web links
  public void getMutationData() {
    result = cbioService.getMutationData("gbm_tcga_mutations", "gbm_tcga_all", "EGFR+PTEN");
    System.out.println(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
  }

  private JSONObject getTestJsonObject(String result) {
    CSVParser csvParser = new CSVParser();
    result = csvParser.convertToJSON(result);
    JSONObject json = null;
    try {
      JSONArray jsonArray = new JSONArray(result);
      System.out.println(jsonArray);
      json = jsonArray.getJSONObject(0);
      System.out.println(json.toString(2));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return json;
  }
}
