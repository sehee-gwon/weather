package com.weather.common.enumeration;

public enum CityType {
    SEOUL("Seoul", "서울시", 37.5683, 126.9778),
    INCHEON("Incheon", "인천시", 37.45, 126.4161),
    BUSAN("Busan", "부산시", 35.1028, 129.0403),
    DAEJEON("Daejeon", "대전시", 36.3333, 127.4167),
    GWANGJU("Gwangju", "광주시", 35.1547, 126.9156),
    DAEGU("Daegu", "대구시", 35.8, 128.55),
    ULSAN("Ulsan", "울산시", 35.5372, 129.3167),
    JEJU("Jeju City", "제주시", 33.5097, 126.5219),
    SUWON("Suwon-si", "수원시", 37.2911, 127.0089),
    GOYANG("Goyang-si", "고양시", 37.6564, 126.835),
    YONGIN("Yongin", "용인시", 37.2342, 127.2064),
    ANYANG("Anyang-si", "안양시", 37.3925, 126.9269),
    SEONGNAM("Seongnam-si", "성남시", 37.4386, 127.1378),
    BUCHEON("Bucheon-si", "부천시", 37.4989, 126.7831),
    GIMPO("Gimpo-si", "김포시", 37.6236, 126.7142),
    YEOJU("Yeoju", "여주시", 37.2958, 127.6339),
    ICHEON("Icheon-si", "이천시", 37.2792, 127.4425),
    ANSAN("Ansan-si", "안산시", 37.3236, 126.8219),
    HWASEONG("Hwaseong-si", "화성시", 37.2068, 126.8169),
    OSAN("Osan", "오산시", 37.1522, 127.0706),
    PYEONGTAEK("Pyeongtaek-si", "평택시", 36.9947, 127.0889),
    GANGNEUNG("Gangneung", "강릉시", 37.7556, 128.8961),
    CHUNCHEON("Chuncheon", "춘천시", 37.8747, 127.7342),
    TONGHAE("Tonghae", "동해시", 37.5439, 129.1069),
    SOKCHO("Sokcho", "속초시", 38.2083, 128.5911),
    JEONJU("Jeonju", "전주시", 35.8219, 127.1489),
    SUNCHEON("Suncheon", "순천시", 34.9481, 127.4895),
    MOKPO("Mokpo", "목포시", 34.7936, 126.3886),
    IKSAN("Iksan", "익산시", 35.9439, 126.9544),
    JINJU("Jinju", "진주시", 35.1928, 128.0847),
    GYEONGJU("Gyeongju", "경주시", 35.8428, 129.2117),
    POHANG("Pohang", "포항시", 36.0322, 129.365),
    MASAN("Masan", "마산시", 35.2081, 128.5725),
    JECHEON("Jecheon", "제천시", 37.1361, 128.2119),
    CHUNGJU("Chungju", "청주시", 36.9706, 127.9322),
    CHEONAN("Cheonan", "천안시", 36.8065, 127.1522),
    NONSAN("Nonsan", "논산시", 36.2039, 127.0847),
    SEOSAN("Seosan City", "서산시", 36.7817, 126.4522),
    GONGJU("Gongju", "공주시", 36.4556, 127.1247),
    GUMI("Gumi", "구미시", 36.1136, 128.336),
    SANGJU("Sangju", "상주시", 36.4153, 128.1606),
    ANDONG("Andong", "안동시", 36.5656, 128.725);

    private String code;
    private String title;
    private double lat;
    private double lon;

    CityType(String code, String title, double lat, double lon) {
        this.code = code;
        this.title = title;
        this.lat = lat;
        this.lon = lon;
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLon() {
        return this.lon;
    }
}
