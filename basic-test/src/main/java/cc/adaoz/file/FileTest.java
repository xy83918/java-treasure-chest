package cc.adaoz.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author albert on 1/10/20
 */
public class FileTest {

    public static final ConcurrentHashMap<String, AtomicLong> cache = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {


        File read = new File("/Users/albert/Downloads/playerCommand");

        List<String> strings = FileUtils.readLines(read, Charset.defaultCharset());

//        Map<String, Long> collect =
//                strings.stream().collect(groupingBy(Function.identity(), counting()));
//
//
//        LinkedHashMap<String, Long> collect1 = collect.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (v1, v2) -> {
//                            throw new IllegalStateException();
//                        },
//                        LinkedHashMap::new
//                ));
//
//        System.out.println(collect1);

        File writeFile = new File("/Users/albert/albertCode/java-treasure-chest/basic-test/test/test2.txt");

        for (int i = 0; i < 1000; i++) {
            FileUtils.writeLines(writeFile, strings, true);
        }


    }
}
