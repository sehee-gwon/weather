# Weather Forecast Project


## 소개
- 이 프로젝트는 대한민국의 기상 예보를 보여주는 서비스 입니다.
- 원하는 지역을 선택하면 그 지역의 날씨 정보를 나타내줍니다.
- 이 서비스는 회원가입 및 로그인을 진행한 사용자에게 제공됩니다.


## 개발 환경
- [![Windows](https://img.shields.io/badge/Windows10-0078D6?style=flat-square&logo=Windows&logoColor=white)]()
- [![Chrome](https://img.shields.io/badge/Chrome-4285F4?style=flat-square&logo=Chrome&logoColor=white)]()
- [![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-000000?style=flat-square&logo=IntelliJ%20IDEA&logoColor=white)]()
- [![SpringBoot](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=SpringBoot&logoColor=white)]() ver. 2.5.5
- [![JAVA](https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white)]() ver. 11
- [![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=white)]() ver. 6.8
- [![Vue.js](https://img.shields.io/badge/Vue.js-4FC08D?style=flat-square&logo=Vue.js&logoColor=white)]() [![JS](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=black)]()
- [![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=HTML5&logoColor=white)]() [![css](https://img.shields.io/badge/CSS-1572B6?style=flat-square&logo=CSS3&logoColor=white)]()
- [![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white)]()


## 기능
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


## 설치 & 실행
- 설치
```
$ git clone git@github.com:loltaeja/weather.git
$ git clone https://github.com/loltaeja/weather.git
```
- Gradle 의존성 주입
```
// Spring Boot
compile('org.springframework.boot:spring-boot-starter-web')

// Lombok
compile('org.projectlombok:lombok')
annotationProcessor('org.projectlombok:lombok')

// Mybatis
compile('org.mybatis.spring.boot:mybatis-spring-boot-starter:1.3.2')
compile('org.springframework.boot:spring-boot-starter-jdbc')
compile('mysql:mysql-connector-java')

// Spring Boot Test
testCompile('org.springframework.boot:spring-boot-starter-test')

// Devtools
runtimeOnly('org.springframework.boot:spring-boot-devtools')

// BeanValidation - BindingResult
implementation('org.springframework.boot:spring-boot-starter-validation')

// Spring Security
implementation ('org.springframework.boot:spring-boot-starter-security')
implementation ('org.springframework.boot:spring-boot-starter-web')
testImplementation ('org.springframework.boot:spring-boot-starter-test')
testImplementation ('org.springframework.security:spring-security-test')

// JWT
compile group: 'io.jsonwebtoken', name: 'jjwt-api', version: '0.11.2'
runtime group: 'io.jsonwebtoken', name: 'jjwt-impl', version: '0.11.2'
runtime group: 'io.jsonwebtoken', name: 'jjwt-jackson', version: '0.11.2'

// DB Log
implementation ('org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16')
 ```
- 실행
 ```
 $ ./gradlew build
 ```
   
