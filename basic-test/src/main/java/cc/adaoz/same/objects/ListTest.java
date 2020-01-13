package cc.adaoz.same.objects;

import com.google.common.collect.Lists;
import lombok.*;

import java.util.List;

/**
 * @author albert on 1/13/20
 */
public class ListTest {


    public static void main(String[] args) {


        List<ObjectTwoTest> list = Lists.newArrayList();

        ObjectTwoTest twoTest1 = new ObjectTwoTest();
        ObjectTwoTest twoTest2 = new ObjectTwoTest();
        twoTest1.setId(0);
        ObjectOneTest abc = new ObjectOneTest("abc", 1);
        twoTest1.setOneTest(abc);

        twoTest2.setId(1);
        twoTest2.setOneTest(abc);


        list.add(twoTest1);
        list.add(twoTest2);

        System.out.println(list);


        abc.setName("def");
        abc.setId(2);


        System.out.println(list);


    }


}


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
class ObjectOneTest {

    private String name;
    private Integer id;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
class ObjectTwoTest {

    private Integer id;
    private ObjectOneTest oneTest;

}
