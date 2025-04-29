package math;

import io.FileIO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayOperationsTest {
    FileIO fileIO = new FileIO();
    MyMath myMath = new MyMath();
    ArrayOperations arrayOperations = new ArrayOperations();

    @Test
    void findPrimesInFile() {
        int[] expected = {3,2,5};
        int[] actual = arrayOperations.findPrimesInFile(fileIO,"E:\\StudyElements\\Semester6\\SoftwareTesting\\unittesting\\unittesting\\src\\test\\resources\\array_operation.txt",myMath);
        assertArrayEquals(expected,actual);
    }

}