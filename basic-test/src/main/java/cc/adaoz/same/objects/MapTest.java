package cc.adaoz.same.objects;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author albert on 1/13/20
 */
public class MapTest {


    public static void main(String[] args) {


        Map<String, ObjectTwoTest> map = Maps.newConcurrentMap();

        ObjectTwoTest twoTest1 = new ObjectTwoTest();
        ObjectTwoTest twoTest2 = new ObjectTwoTest();
        twoTest1.setId(0);
        ObjectOneTest abc = new ObjectOneTest("abc", 1);
        twoTest1.setOneTest(abc);

        twoTest2.setId(1);
        twoTest2.setOneTest(abc);


        map.put("1", twoTest1);
        map.put("2", twoTest2);

        System.out.println(map);

        ObjectTwoTest objectTwoTest = map.get("1");
        objectTwoTest.setId(2);
        ObjectOneTest oneTest = objectTwoTest.getOneTest();

        oneTest.setId(3);
        oneTest.setName("def");


        System.out.println(map);


    }


}

