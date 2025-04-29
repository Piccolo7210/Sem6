package io;

import math.ArithmeticOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FileIOTest {
    FileIO fileIO=null;
    @BeforeEach
    public void setup ()throws Exception{
        fileIO = new FileIO();
    }
    @Test
    void normalFile() {
        int[] expected = {3,9,0,2,10,9,3,8,0,3};
        int[] actual = fileIO.readFile("E:\\StudyElements\\Semester6\\SoftwareTesting\\unittesting\\unittesting\\src\\test\\resources\\grades_valid.txt");
        assertArrayEquals(expected, actual);
    }
    @Test
    void specialCharacterFile() {
        assertThrows(NumberFormatException.class, () -> fileIO.readFile("E:\\StudyElements\\Semester6\\SoftwareTesting\\unittesting\\unittesting\\src\\test\\resources\\specialCharacter.txt"));
    }
    @Test
    void normalAlphabetFile() {
        assertThrows(NumberFormatException.class, () -> fileIO.readFile("E:\\StudyElements\\Semester6\\SoftwareTesting\\unittesting\\unittesting\\src\\test\\resources\\normalAlphabet.txt"));
    }
    @Test
    void emptyFile(){
        assertThrows(IllegalArgumentException.class, () -> fileIO.readFile("E:\\StudyElements\\Semester6\\SoftwareTesting\\unittesting\\unittesting\\src\\test\\resources\\empty_file.txt"));
    }
    @Test
    void wrongPath(){
        assertThrows(IllegalArgumentException.class, () -> fileIO.readFile("E:\\StudyElements\\Semester6\\SoftwareTesting\\unittesting\\unittesting\\src\\test\\resources\\empy_file.txt"));
    }
    @Test
    void allNotIntegerFile() {
        assertThrows(NumberFormatException.class, () -> fileIO.readFile("E:\\StudyElements\\Semester6\\SoftwareTesting\\unittesting\\unittesting\\src\\test\\resources\\grades_invalid.txt"));
    }
}