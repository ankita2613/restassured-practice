# API Testing with Rest Assured: JSONPlaceholder

![API tests](https://github.com/ankita2613/restassured-practice/actions/workflows/tests.yml/badge.svg)

Automated API tests in Java for the practice API https://jsonplaceholder.typicode.com.
The same checks also exist as a Postman collection in my `api-testing-postman` repository.

## Tools
Java 25, Rest Assured, TestNG, Maven

## How to run
`mvn clean test`

## Tests
| Test | What is checked |
| getUserReturns200 | GET /users/1 returns 200, `id` is 1, `name` is present |
| missingUserReturns404 | GET /users/9999 returns 404 |
| createPostReturns201 | POST /posts returns 201, title echoed back, `id` created |
| postWithoutTitleShouldBeRejected (disabled) | A POST with no title should return 400 |

## Finding
JSONPlaceholder does not validate input. A POST with no `title` returned `201 Created` instead of `400`, so that test is disabled with a comment. A real API should reject it.
