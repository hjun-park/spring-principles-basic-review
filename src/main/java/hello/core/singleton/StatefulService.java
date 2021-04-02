package hello.core.singleton;

public class StatefulService {

    /*
        싱글톤 방식 주의점
            1) 싱글톤 객체는 stateful 하게 설계 X => stateless 하게 설계
            2) 특정 클라이언트에 의존적인 필드 있으면 X
            3) 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 X
            4) 되도록이면 읽기만 가능해야 한다.
            5) 그래서 필드(전역변수) 대신에 공유되지 않는 ( 지역변수, 파라미터, ThreadLocal ) 등을 사용해야 한다.
     */

    private int price;  // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제점 ( StatefulServiceTest 참고 )
    }

    public int getPrice() {
        return price;
    }
}
