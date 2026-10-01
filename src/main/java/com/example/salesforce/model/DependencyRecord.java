package com.example.salesforce.model;

public class DependencyRecord {
    private String MetadataComponentName;
    private String MetadataComponentType;
    private String RefMetadataComponentName;
    private String RefMetadataComponentType;

    public String getMetadataComponentName() { return MetadataComponentName; }
    public void setMetadataComponentName(String v) { this.MetadataComponentName = v; }
    public String getMetadataComponentType() { return MetadataComponentType; }
    public void setMetadataComponentType(String v) { this.MetadataComponentType = v; }
    public String getRefMetadataComponentName() { return RefMetadataComponentName; }
    public void setRefMetadataComponentName(String v) { this.RefMetadataComponentName = v; }
    public String getRefMetadataComponentType() { return RefMetadataComponentType; }
    public void setRefMetadataComponentType(String v) { this.RefMetadataComponentType = v; }
}