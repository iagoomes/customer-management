```markdown
# Customer Management Service
Swagger API Documentation: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## API Endpoints

### POST /v1/customers

Creates a new customer.

**Endpoint:**

```bash
curl -X POST --location "http://localhost:8080/v1/customers" \
    -H "Content-Type: application/json" \
    -d '{
          "fullName": "Jhon Doe",
          "countryOfBirth": "Brazil",
          "cpf": "724.564.650-32",
          "passport": "ABDE123456",
          "dateOfBirth": "2023-01-01T00:00:00",
          "addressInCountryOfBirth": "Rua ABC, 123",
          "phone": "1234567890",
          "email": "john.doe@example.com"
        }'
```

### GET /v1/customers/{id}

Returns the customer details based on the customer ID provided.

**Endpoint:**

```bash
curl -X GET --location "http://localhost:8080/v1/customers/1"
```

### GET /v1/customers

Returns all the available customers.

**Endpoint:**

```bash
curl -X GET --location "http://localhost:8080/v1/customers"
```

### PUT /v1/customers/{id}

Updates the details of the customer based on the customer ID provided.

**Endpoint:**

```bash
curl -X PUT --location "http://localhost:8080/v1/customers/1" \
    -H "Content-Type: application/json" \
    -d '{
          "fullName": "Carlos",
          "countryOfBirth": "Brazil",
          "cpf": "123.456.789-09",
          "passport": "ABCD123456",
          "dateOfBirth": "2023-01-01T00:00:00",
          "addressInCountryOfBirth": "Rua ABC, 123",
          "phone": "1234567890",
          "email": "john.doe@example.com"
        }'
```

### DELETE /v1/customers/{id}

Deletes the customer based on the customer ID provided.

**Endpoint:**

```bash
curl -X DELETE --location "http://localhost:8080/v1/customers/1"
```
