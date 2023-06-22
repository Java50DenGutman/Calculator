package telran.exceptions.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.exceptions.BallBrokenFloor;

class ExceptionsTest {

	@Test
	void testException()  {
		int res = 0;
			try {
				res = itThrowsCheckedException(10000);
				
				System.out.println("everything ok");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
				res = 100;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				res = 200;
			}
			assertEquals(100, res);
			
	}
	private int itThrowsCheckedException(int number) throws Exception  {
		if(number < 0) {
			throw new Exception("just test checked exception");
		}
		if (number > 1000) {
			throw new RuntimeException("number cannot be greater than 1000");
		}
		return number * 2;
		
	}
	@Test
	void ballBrokenFloorTest() {
		BallBrokenFloor bbf = new BallBrokenFloor(200);
		assertEquals(bbf.getFloor(),getMinFloor(bbf));
	}
	private int getMinFloor(BallBrokenFloor bbf) {
	    int left = 1; // минимальный этаж, на котором мяч не разбивается
	    int right = bbf.nFloors; // максимальный этаж, на котором мяч разбивается
	    while (left < right) {
	        int mid = (left + right) / 2; // средний этаж
	        try {
	            bbf.broken(mid); // проверяем, разбивается ли мяч на этом этаже
	            left = mid + 1; // мяч не разбивается, продолжаем поиск в верхней половине диапазона
	        } catch (Exception e) {
	            right = mid; // мяч разбивается, продолжаем поиск в нижней половине диапазона
	        }
	    }
	    return left; // возвращаем найденный этаж
	}

}
