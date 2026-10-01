package com.example.salesforce.model;

import java.util.List;

public class DependencyQueryResponse {
    private List<DependencyRecord> records;

    public List<DependencyRecord> getRecords() { return records; }
    public void setRecords(List<DependencyRecord> records) { this.records = records; }
}