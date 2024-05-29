@allapi
Feature: Test All ReqRes APIS


Scenario: List_Users_TC01
Given url
When perform GET operation
Then verify status code
And validate response


Scenario: List_Single_Users_TC02
Given url
When perform GET operation
Then verify status code
And validate response

@api
Scenario: Create_Single_Users_TC03
Given url
And construct dynamic request
When perform POST operation
Then verify status code
And validate response


@api
Scenario: Update_Single_Users_TC04
Given url
And construct dynamic request
When perform PUT operation
Then verify status code
And validate response


@api
Scenario: Update_Single_Users_TC05
Given url
And construct dynamic request
When perform PATCH operation
Then verify status code
And validate response

@api
Scenario: Delete_Single_Users_TC06
Given url
When perform DELETE operation
Then verify status code


@api123
Scenario Outline: <TCName>
Given url
And construct dynamic request
When perform <Method> operation
Then verify status code
And validate response
Examples:
|TCName|Method|
|Create_Single_Users_TC03 | POST|
|Update_Single_Users_TC04|PUT|
|Update_Single_Users_TC05|PATCH|
|Create_Single_Users_TC07 | POST|
|Create_Single_Users_TC08 | POST|
|Update_Single_Users_TC09|PATCH|


