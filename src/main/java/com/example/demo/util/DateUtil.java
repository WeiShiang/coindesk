package com.example.demo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static DateUtil instance = null;
    public static DateUtil getInstance() {
        if (instance == null) {
            synchronized (DateUtil.class) {
                instance = new DateUtil();
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        String date = "2022-02-27T01:19:00+00:00";
//        System.out.println(dateFormat(date));
    }

    /**
     * ISO Date to yyyy/MM/dd HH:mm:ss
     *
     * @return
     */
    public String formatDate(String date) {
        String tmpDate = date.substring(0, date.indexOf("+"));
        LocalDateTime localDateTime = LocalDateTime.parse(tmpDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String formatDateTime = localDateTime.format(formatter);
        return formatDateTime;
    }


}
