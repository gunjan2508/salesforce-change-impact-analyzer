# Salesforce Change Impact Analyzer

A Spring Boot service that connects to Salesforce and identifies what breaks before you delete or modify a field — by querying Salesforce's dependency metadata.

## The Problem
Salesforce doesn't warn you before you delete or change a field that's referenced in a Layout, Apex class, or Flow. This tool answers: "What depends on this field?"

## How It Works
1. Authenticates with Salesforce using OAuth 2.0 Client Credentials Flow
2. Uses Salesforce's Tooling API (`MetadataComponentDependency` object) to fetch all metadata relationships in the org
3. Filters results by field name to show exactly what references it

## Tech Stack
- Java 24, Spring Boot 4
- Salesforce Tooling API
- OAuth 2.0 Client Credentials Flow via **External Client Apps** — Salesforce's replacement for Connected Apps (Connected Apps creation was disabled by default starting Spring '26)

## Endpoints
| Endpoint | Description |
|---|---|
| `GET /accounts` | Test endpoint — fetches sample Account records |
| `GET /dependencies/search?field=FieldName` | Returns all metadata components that reference the given field |

## Setup

1. **Create a Salesforce Developer Edition org** (free): https://developer.salesforce.com/signup

2. **Create an External Client App:**
    - Setup → External Client App Manager → New External Client App
    - Enable OAuth, add scope `api`
    - Enable Client Credentials Flow, set a Run As user

3. **Clone this repo and configure credentials:**
   git clone <your-repo-url>
   cd salesforce
   cp src/main/resources/application.properties.example src/main/resources/application.properties

   Fill in your Client ID, Client Secret, and My Domain URL in `application.properties`.

4. **Run:**
5. **Test:**
   http://localhost:8081/dependencies/search?field=YourFieldName


## Why This Matters
Spring '26 changed how Salesforce integrations authenticate — Connected Apps are being phased out in favor of External Client Apps. This project was built using the new pattern from scratch.

## Status
Early-stage / actively being built. Next planned addition: a simple frontend for non-technical users to search dependencies without hitting the API directly.