package com.group.commute.domain.overtime;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class HolidayManager {
    private static final Set<LocalDate> holidays = new HashSet<>();

    // 정적 초기화 블록 (클래스 로드 시 한 번만 초기화)
    static {
        holidays.add(LocalDate.of(2024, 1, 1)); // 신정

        holidays.add(LocalDate.of(2024, 2, 9)); // 설날
        holidays.add(LocalDate.of(2024, 2, 10)); // 설날
        holidays.add(LocalDate.of(2024, 2, 11)); // 설날
        holidays.add(LocalDate.of(2024, 2, 12)); // 설날(대체공휴일)

        holidays.add(LocalDate.of(2024, 3, 1)); // 삼일절

        holidays.add(LocalDate.of(2024, 4, 10)); // 제22대 국회의원 선거

        holidays.add(LocalDate.of(2024, 5, 1)); // 근로자의 날
        holidays.add(LocalDate.of(2024, 5, 5)); // 어린이날
        holidays.add(LocalDate.of(2024, 5, 6)); // 어린이날(대체공휴일)
        holidays.add(LocalDate.of(2024, 5, 15)); // 석가탄신일

        holidays.add(LocalDate.of(2024, 6, 6)); // 현충일

        holidays.add(LocalDate.of(2024, 8, 15)); // 광복절

        holidays.add(LocalDate.of(2024, 9, 16)); // 추석
        holidays.add(LocalDate.of(2024, 9, 17)); // 추석
        holidays.add(LocalDate.of(2024, 9, 18)); // 추석

        holidays.add(LocalDate.of(2024, 10, 3)); // 개천절
        holidays.add(LocalDate.of(2024, 10, 9)); // 한글날

        holidays.add(LocalDate.of(2024, 12, 25)); // 크리스마스
    }

    public static boolean isHoliday(LocalDate date){
        return holidays.contains(date);
    }
}
