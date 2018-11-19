package com.tgen.spike.cbio.cbiospike;

import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;

import static org.junit.Assert.*;

public class CBIOServiceTest {

  private final CBIOService cbioService = new CBIOService();
  private String result = null;

  @Test
  public void getTypesOfCancer() {
    result = cbioService.getTypesOfCancer();
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void getNetwork() {
    result = cbioService.getNetwork("EGFR+PTEN");
    System.out.println(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
  }

  @Test
  public void getCancerStudies() {
    result = cbioService.getCancerStudies();
    System.out.println(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
  }

  @Test
  public void getClinicalData() {
    result = cbioService.getClinicalData("gbm_tcga_all");
    System.out.println(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
  }

  @Test
  public void getGeneticProfiles() {
    result = cbioService.getGeneticProfiles("gbm_tcga");
    System.out.println(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
  }

  @Test
  public void getCaseLists() {
    result = cbioService.getCaseLists("gbm_tcga");
    System.out.println(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
  }

  @Test
  public void getProfileData() {
    result = cbioService.getProfileData("gbm_tcga_all", "gbm_tcga_mutations", "EGFR+PTEN");
    System.out.println(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
  }

  @Test
  public void getMutationData() {
    result = cbioService.getMutationData("gbm_tcga_mutations", "gbm_tcga_all", "EGFR+PTEN");
    System.out.println(result);
    Assert.assertFalse(result.contains("Error:"));
    Assert.assertNotNull(result);
  }
}