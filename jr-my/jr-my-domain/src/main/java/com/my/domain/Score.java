package com.my.domain;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1316:14
 */
public class Score {

    int score;
    int numbers;
    int averageScore;
    int fixedScore;

    public int getFixedScore() {
        return fixedScore;
    }

    public void setFixedScore(int fixedScore) {
        this.fixedScore = fixedScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }
}
