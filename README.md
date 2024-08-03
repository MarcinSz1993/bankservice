## Bank Service

This is my first project. It is a simple backend application that performs CRUD (Create, Read, Update, Delete) operations for managing payments for different services.
I focused on basic functionality and ease of use.

The app can:

    Create clients.
    Create bank accounts.
    Handle payment processing.
    Store and manage payment information.

Feel free to explore the code and see how it works!

CREATING A NEW CLIENT
- REQUEST:
```json
{
    "name":"Jan",
    "surname":"Kowalski",
    "pesel":"62345678909"
}
```
- RESPONSE:
```json
{
    "id": 6,
    "name": "Jan",
    "surname": "Kowalski",
    "pesel": "62345678909"
}
```
- A field 'pesel' has validations: not empty and exactly 11 numbers.
----------------------------------------
CREATING NEW BANK ACCOUNT:
- REQUEST:
```json
{
    "pesel":"62345678909"
}
```
- RESPONSE:
```json
{
    "id": 8,
    "accountNumber": "430398232",
    "balance": 0.0,
    "client": {
        "id": 6,
        "name": "Jan",
        "surname": "Kowalski",
        "pesel": "62345678909"
    },
    "transactions": []
}
```
- Account number is generated automatically.
---------------------------------------
TRANSFER MONEY TO ACCOUNT:
- REQUEST:
```json
{
  "accountNumber":"430398232",
  "amount":1000,
  "transactionType":"DEPOSIT"
}
```
- RESPONSE:
```json
{
    "id": 38,
    "amount": 1000.0,
    "dateOfTransaction": "2024-08-03T12:55:42.7863792",
    "typeOfTransaction": "DEPOSIT"
}
```
-----------------------------------------
CHECKING BALANCE:
- REQUEST:
```http request
http://localhost:9090/accounts/balance?accountNumber=430398232
```
- RESPONSE:
```json
1000.0
```
---------------------------------------------
WITHDRAWAL MONEY:
- REQUEST:
```json
{
    "accountNumber":"430398232",
    "amount":800,
    "transactionType":"WITHDRAWAL"
}
```
- RESPONSE:
```json
{
    "id": 39,
    "amount": 800.0,
    "dateOfTransaction": "2024-08-03T13:00:02.5243364",
    "typeOfTransaction": "WITHDRAWAL"
}
```
- History of all transactions is stored in database.
------------------------------------------------------------

DELETING BANK ACCOUNT:
- REQUEST:
```http request
http://localhost:9090/accounts/?accountNumber=430398232&pesel=62345678909
```
RESPONSE:
```json
"Account with number 430398232 deleted."
```

The application handled some specific exceptions like:
ClientAlreadyExistsException
IncorrectPeselException
NotEnoughMoneyException
WrongAccountNumberException

Suppose we want to withdrawal more money than we have on our account:

REQUEST:
```json
{
    "accountNumber":"202271644",
    "amount":1000,
    "transactionType":"WITHDRAWAL"
}
```
RESPONSE:
```json
"You don't have enough money to do it!"
```