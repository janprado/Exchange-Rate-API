# Exchange-Rate-API

### **Scope and objective**

API project to fetches, compare exchange rates and to conversion of currencies values.
All endpoints have caching for 30 min.

### **How to use**

Start by cloning the project in your IDE.

link = git clone https://github.com/janprado/Exchange-Rate-API.git

Run the project, it is located at com/janprado/exchangerateapi/ExchangeRateApiApplication.java

go to the browser and access the following address: http://localhost:8080/swagger-ui.html

### **Technologies and Framework**

Java, Maven, SpringBoot, Swagger and ehCache.

### **Available endpoints**

`GET - exchangeRateFromCurrencyAToB`
*Return exchange rate from currency A to currency B*

`GET - allExchangeRatesFromCurrency`
*Return all exchange rates from the selected currency*

`GET - valueConversionFromCurrencyAToB`
*Return the value of currency A converted to currency B*

`GET - valueConversionFromCurrencyAToCurrencyList`
*Return the value of currency A converted to currency List*

`GET - availableCurrencies`
*Return all available currencies and description*
