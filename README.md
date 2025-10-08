# 🏢 Simple Insurance API

**Simple Insurance API** is a RESTful backend service built with **Spring Boot**, designed for managing insurance quotes and providers.  
It follows **clean layered architecture** principles and leverages modern Spring Boot practices such as **MapStruct**, **JPA/Hibernate**, and **global exception handling**.

---

## 🚀 Overview

This API provides endpoints for managing **insurance quotes** and **providers**.  
Each quote contains essential information such as the name, coverage type, price, and its associated provider.  
The API also supports retrieving the **best (lowest-priced) quote** based on available data.

---

## ⚙️ Key Features

- Full CRUD operations for **Quotes** and **Providers**
- Fetching the **best (lowest-price) quote**
- **MapStruct** for automatic DTO ↔ Entity conversion
- **Auditing fields** for creation and modification timestamps
- **Global exception handling** for consistent error responses
- **Custom response wrapper** (`ApiResponse`) for all API outputs
- **Jakarta Validation** for input validation
- **Separation of layers**: Controller, Service, Repository, Mapper, DTO
- **Clean, maintainable, and extensible architecture**

---

com.example.simpleinsurance
┣ api
┃ ┣ controller → REST controllers and endpoint management
┃ ┣ dto → Data Transfer Objects for API requests/responses
┃ ┗ mapper → MapStruct mappers for DTO ↔ Entity conversion
┣ model → JPA entities and repositories (DAO layer)
┣ service → Business logic and service layer implementations
┣ exception → Custom exceptions and global exception handler
┗ SimpleInsuranceApplication.java

