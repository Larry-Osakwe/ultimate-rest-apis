# UserApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**listUsers**](UserApi.md#listUsers) | **GET** /users | Get users information

<a name="listUsers"></a>
# **listUsers**
> listUsers()

Get users information

Returns a list of users

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserApi;


UserApi apiInstance = new UserApi();
try {
    apiInstance.listUsers();
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#listUsers");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

