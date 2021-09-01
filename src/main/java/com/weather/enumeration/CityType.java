package com.weather.enumeration;

public enum CityType {
    SEOUL("Seoul", "서울시"),
    INCHEON("Incheon", "인천시"),
    BUSAN("Busan", "부산시"),
    DAEJEON("Daejeon", "대전시"),
    GWANGJU("Gwangju", "광주시"),
    DAEGU("Daegu", "대구시"),
    ULSAN("Ulsan", "울산시"),
    JEJU("Jeju City", "제주시"),
    SUWON("Suwon-si", "수원시"),
    GOYANG("Goyang-si", "고양시"),
    YONGIN("Yongin", "용인시"),
    ANYANG("Anyang-si", "안양시"),
    SEONGNAM("Seongnam-si", "성남시"),
    BUCHEON("Bucheon-si", "부천시"),
    GIMPO("Gimpo-si", "김포시"),
    YEOJU("Yeoju", "여주시"),
    ICHEON("Icheon-si", "이천시"),
    ANSAN("Ansan-si", "안산시"),
    HWASEONG("Hwaseong-si", "화성시"),
    OSAN("Osan", "오산시"),
    PYEONGTAEK("Pyeongtaek-si", "평택시"),
    GANGNEUNG("Gangneung", "강릉시"),
    CHUNCHEON("Chuncheon", "춘천시"),
    TONGHAE("Tonghae", "동해시"),
    SOKCHO("Sokcho", "속초시"),
    JEONJU("Jeonju", "전주시"),
    SUNCHEON("Suncheon", "순천시"),
    MOKPO("Mokpo", "목포시"),
    IKSAN("Iksan", "익산시"),
    JINJU("Jinju", "진주시"),
    GYEONGJU("Gyeongju", "경주시"),
    POHANG("Pohang", "포항시"),
    MASAN("Masan", "마산시"),
    JECHEON("Jecheon", "제천시"),
    CHUNGJU("Chungju", "청주시"),
    CHEONAN("Cheonan", "천안시"),
    NONSAN("Nonsan", "논산시"),
    SEOSAN("Seosan City", "서산시"),
    GONGJU("Gongju", "공주시"),
    GUMI("Gumi", "구미시"),
    SANGJU("Sangju", "상주시"),
    ANDONG("Andong", "안동시");

    private String code;
    private String title;

    CityType(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
