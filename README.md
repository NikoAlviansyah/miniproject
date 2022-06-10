# miniproject

Mini project tentang pembuatan "backend untuk banking system menggunakan spring boot" sederhana.
Flow pembuatan datanya dari tabel User, dari data user yang telah dibuat dapat melakukan transaksi berupa deposit, withdraw, dan transfer.
Dengan menggunakan CRUD untuk pengetesan aplikasi ini menggunakan postman.
Dan untuk databasenya saya sertakan di dalam project ini yang bernama Griffin.bak.



# MiniProject



<!--- If we have only one group/collection, then no need for the "ungrouped" heading -->


## Variables

| Key | Value | Type |
| --- | ------|-------------|
| base_url | http://localhost:8081 | string |



## Endpoints

* [Admin](#admin)
    1. [Get All Admin](#1-get-all-admin)
    1. [Get All Admin By Name](#2-get-all-admin-by-name)
    1. [Get All Customer By Name Copy](#3-get-all-customer-by-name-copy)
    1. [Create Admin](#4-create-admin)
    1. [Update Admin](#5-update-admin)
    1. [Delete Admin](#6-delete-admin)
* [Customer](#customer)
    1. [Get All Customer](#1-get-all-customer)
    1. [Get All Customer By Name](#2-get-all-customer-by-name)
    1. [Get All Customer By City](#3-get-all-customer-by-city)
    1. [Get Customer By Account Number](#4-get-customer-by-account-number)
    1. [Create Customer](#5-create-customer)
    1. [Update Customer](#6-update-customer)
    1. [Delete Customer](#7-delete-customer)
* [Deposit](#deposit)
    1. [Get All Deposit History](#1-get-all-deposit-history)
    1. [Get Deposit History By User Id](#2-get-deposit-history-by-user-id)
    1. [Create Deposit](#3-create-deposit)
* [Withdraw](#withdraw)
    1. [Get All Withdraw History](#1-get-all-withdraw-history)
    1. [Get Withdraw History By User Id](#2-get-withdraw-history-by-user-id)
    1. [Create Withdraw](#3-create-withdraw)
* [Transfer](#transfer)
    1. [Get All Transfer History](#1-get-all-transfer-history)
    1. [Get Transfer History By Sender User Id](#2-get-transfer-history-by-sender-user-id)
    1. [Get Transfer History By Recipient User Id](#3-get-transfer-history-by-recipient-user-id)
    1. [Create Transfer](#4-create-transfer)

--------



## Admin



### 1. Get All Admin



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/admin
```



### 2. Get All Admin By Name



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/admin/name/A
```



### 3. Get All Customer By Name Copy



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/customer/city/Jakarta
```



### 4. Create Admin



***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{base_url}}/admin
```



***Body:***

```js        
{
    "firstName" : "Cynthia",
    "lastName" : "Wijaya",
    "birthDate" : "1995-03-21",
    "gender" : "FEMALE",
    "phone" : "088775569348",
    "email" : "cynthia.wijaya@gmail.com",
    "address" : "Jl. Pangeran Tubagus Angke",
    "subDistrict" : "Bendungan Hilir",
    "district" : "Tanah Abang",
    "city" : "Jakarta Pusat",
    "province" : "Jakarta",
    "zipCode" : "102210"
}
```



### 5. Update Admin



***Endpoint:***

```bash
Method: PUT
Type: RAW
URL: {{base_url}}/admin
```



***Body:***

```js        
{
    "userId" : "A2",
    "phone" : "088775569348",
    "email" : "cynthia.wijaya@gmail.com",
    "address" : "Jalan Penjernihan II",
    "subDistrict" : "Bendungan Hilir",
    "district" : "Tanah Abang",
    "city" : "Jakarta Pusat",
    "province" : "Jakarta",
    "zipCode" : "10210"
}
```



### 6. Delete Admin



***Endpoint:***

```bash
Method: DELETE
Type: 
URL: {{base_url}}/admin/A1
```



## Customer



### 1. Get All Customer



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/customer
```



### 2. Get All Customer By Name



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/customer/name/A
```



### 3. Get All Customer By City



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/customer/city/Jakarta
```



### 4. Get Customer By Account Number



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/customer/account-number/2022060001
```



### 5. Create Customer



***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{base_url}}/customer
```



***Body:***

```js        
{
    "firstName" : "Toni",
    "lastName" : "Tanoto",
    "birthDate" : "1998-11-01",
    "gender" : "MALE",
    "phone" : "081378659008",
    "email" : "toni.tanoto@gmail.com",
    "address" : "Jl. Bedeng 6",
    "subDistrict" : "Hajinema",
    "district" : "Natar",
    "city" : "Lampung Selatan",
    "province" : "Lampung",
    "zipCode" : "35362"
}
```



### 6. Update Customer



***Endpoint:***

```bash
Method: PUT
Type: RAW
URL: {{base_url}}/customer
```



***Body:***

```js        
{
    "userId" : "C2",
    "phone" : "081298653431",
    "email" : "grace.widjaja@gmail.com",
    "address" : "Jalan Jambu",
    "subDistrict" : "Menteng",
    "district" : "Menteng",
    "city" : "Jakarta Pusat",
    "province" : "Jakarta",
    "zipCode" : "10310"
}
```



### 7. Delete Customer



***Endpoint:***

```bash
Method: DELETE
Type: 
URL: {{base_url}}/customer/C1
```



## Deposit



### 1. Get All Deposit History



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/deposit
```



### 2. Get Deposit History By User Id



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/deposit/C1
```



### 3. Create Deposit



***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{base_url}}/deposit
```



***Body:***

```js        
{
    "fund" : "100000",
    "accountNumber" : "2022060002"
}
```



## Withdraw



### 1. Get All Withdraw History



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/withdraw
```



### 2. Get Withdraw History By User Id



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/withdraw/C1
```



### 3. Create Withdraw



***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{base_url}}/withdraw
```



***Body:***

```js        
{
    "fund" : "100000",
    "accountNumber" : "2022060002"
}
```



## Transfer



### 1. Get All Transfer History



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/transfer
```



### 2. Get Transfer History By Sender User Id



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/transfer/sender/C1
```



### 3. Get Transfer History By Recipient User Id



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{base_url}}/transfer/recipient/C1
```



### 4. Create Transfer



***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{base_url}}/transfer
```



***Body:***

```js        
{
    "fund" : "100000",
    "senderAccountNumber" : "2022060002",
    "recipientAccountNumber" : "2022060003"
}
```



---
[Back to top](#miniproject)
