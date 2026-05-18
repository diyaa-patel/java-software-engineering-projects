import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** 
 * Tests for the Grades class
 * @author Dr. Sarah Heckman
 */
public class GradesTest {

	/**
	 * Test A student
	 */
	@Test
	public void testAStudent() {
		Grades studentA = new Grades();
		studentA.setGp1(107);
		studentA.setGp2(94);
		studentA.setGp3(110);
		studentA.setP1p1(101);
		studentA.setP1p2(142);
		studentA.setP2p1(103);
		studentA.setP2p2(145);
		studentA.setExam1(95);
		studentA.setExam2(97);
		studentA.setExam3(93);
		
		assertEquals(95.46, studentA.getFinalGrade(), 0.01);
		assertEquals("A", studentA.getFinalLetterGrade());
		assertEquals(95.96, studentA.getProjectMGR(), 0.01);
		assertEquals(95.81, studentA.getProjectGPMGR(), 0.01);
		assertEquals(94.89, studentA.getExamMGR(), 0.01);
	}
	
	/**
	 * Test B student
	 */
	@Test
	public void testBStudent() {
		Grades studentB = new Grades();
		studentB.setGp1(97);
		studentB.setGp2(84);
		studentB.setGp3(97);
		studentB.setP1p1(90);
		studentB.setP1p2(119);
		studentB.setP2p1(87);
		studentB.setP2p2(124);
		studentB.setExam1(85);
        studentB.setExam2(87);
        studentB.setExam3(83);

		assertEquals(83.56, studentB.getFinalGrade(), 0.01);
		assertEquals("B", studentB.getFinalLetterGrade());
		assertEquals(81.65, studentB.getProjectMGR(), 0.01);
		assertEquals(82.73, studentB.getProjectGPMGR(), 0.01);
		assertEquals(84.89, studentB.getExamMGR(), 0.01);
	}
	
	/**
	 * Test C student
	 */
	@Test
	public void testCStudent() {
		Grades studentC = new Grades();
		studentC.setGp1(77);
		studentC.setGp2(74);
		studentC.setGp3(87);
		studentC.setP1p1(75);
		studentC.setP1p2(78);
		studentC.setP2p1(77);
		studentC.setP2p2(74);
		studentC.setExam1(75);
        studentC.setExam2(77);
        studentC.setExam3(73);
	}
	
	/**
     * Test Exam MGR student
     */
    @Test
    public void testExamMGRStudent() {
        Grades studentMGR = new Grades();
        studentMGR.setGp1(107);
        studentMGR.setGp2(94);
        studentMGR.setGp3(110);
        studentMGR.setP1p1(101);
        studentMGR.setP1p2(142);
        studentMGR.setP2p1(103);
        studentMGR.setP2p2(145);
        studentMGR.setExam1(65);
        studentMGR.setExam2(53);
        studentMGR.setExam3(42);
        
        assertEquals(79.44, studentMGR.getFinalGrade(), 0.01);
        assertEquals("F", studentMGR.getFinalLetterGrade());
        assertEquals(95.96, studentMGR.getProjectMGR(), 0.01);
        assertEquals(95.80, studentMGR.getProjectGPMGR(), 0.01);
        assertEquals(52.74, studentMGR.getExamMGR(), 0.01);
    }
    
    /**
     * Test Project+GP MGR student 
     */
    @Test
    public void testProjectGPMGRStudent() {
        Grades studentMGR = new Grades();
        studentMGR.setGp1(0);
        studentMGR.setGp2(14);
        studentMGR.setGp3(50);
        studentMGR.setP1p1(101);
        studentMGR.setP1p2(115);
        studentMGR.setP2p1(103);
        studentMGR.setP2p2(105);
        studentMGR.setExam1(95);
        studentMGR.setExam2(97);
        studentMGR.setExam3(93);
        
        assertEquals(74.04, studentMGR.getFinalGrade(), 0.01);
        assertEquals("C-", studentMGR.getFinalLetterGrade());
        assertEquals(78.09, studentMGR.getProjectMGR(), 0.01);
        assertEquals(61.27, studentMGR.getProjectGPMGR(), 0.01);
        assertEquals(94.89, studentMGR.getExamMGR(), 0.01);
    }
    
    /**
     * Test Project MGR student
     */
    @Test
    public void testProjectMGRStudent() {
        Grades studentMGR = new Grades();
        studentMGR.setGp1(77);
        studentMGR.setGp2(93);
        studentMGR.setGp3(114);
        studentMGR.setP1p1(37);
        studentMGR.setP1p2(65);
        studentMGR.setP2p1(68);
        studentMGR.setP2p2(91);
        studentMGR.setExam1(95);
        studentMGR.setExam2(97);
        studentMGR.setExam3(93);
        
        assertEquals(76.02, studentMGR.getFinalGrade(), 0.01);
        assertEquals("F", studentMGR.getFinalLetterGrade());
        assertEquals(51.60, studentMGR.getProjectMGR(), 0.01);
        assertEquals(64.45, studentMGR.getProjectGPMGR(), 0.01);
        assertEquals(94.89, studentMGR.getExamMGR(), 0.01);
    }
	
}
