# Weather Forecast Project

## 프로젝트 소개
 - 이 프로젝트는 대한민국의 기상 예보를 보여주는 서비스 입니다.
 - 원하는 지역을 선택하면 그 지역의 날씨 정보를 나타내줍니다.
 - 이 서비스는 회원가입 및 로그인을 진행한 사용자에게 제공됩니다.

## 개발환경
 - OS: Windows 10
 - Browser: Chrome
 - IDE: Intellij IDEA
 - SpringBoot 2.5.5
 - Java11
 - Gradle 6.8
 - Vue.js, JavaScript
 - HTML 5, CSS 3
 - MySQL

## 프로젝트 실행 방법
 - 본 프로젝트는 Spring Boot 기반의 웹 프로젝트로 내장된 Tomcat Server를 사용하여 웹 프로그램이 실행됩니다.
 - 본 프로젝트는 @SpringBootApplication 어노테이션을 사용하므로, 우측 상단의 Debug 버튼으로 실행시킵니다.
 <br>![bootRun](https://user-images.githubusercontent.com/86970934/139812694-d5a302cc-84df-436a-8e0d-5ef4bfd9376c.PNG)
 - 실행하면 Tomcat Server 8080으로 실행된 것을 확인할 수 있습니다.
  


## 프로젝트 기능
 - Weather: 대한민국의 40개의 지역의 날씨 예보 제공
   <br>![weather](https://user-images.githubusercontent.com/86970934/139816149-a7fe1871-c755-4e43-8474-b477cc85cdfb.png)
   - 지역을 선택하면, 해당 지역의 날씨 정보를 확인 할 수 있는 기능
     - 현재 날씨 정보: 기상, 현재 기온, 습도, 풍속
     - 내일부터 5일뒤 날씨 정보: 기상, 최고 기온, 최저 기온
 - Login: 회원 로그인
   - Email, password를 통한 로그인 기능
     - Spring Security 사용하여 보안 강화
     - JWT 방식으로 사용자 인증 기능 강화
 - Signup: 회원 가입
   - Name, Email, Password를 통한 회원 가입 기능
     - MyBatis를 통해 DB와 연동
