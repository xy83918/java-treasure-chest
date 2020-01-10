package cc.adaoz.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author albert on 1/10/20
 */
public class FileTest {

    public static final ConcurrentHashMap<String, AtomicLong> cache = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {


        File writeFile = new File("/Users/albert/albertCode/java-treasure-chest/basic-test/test/test1.txt");

        List<String> strings = FileUtils.readLines(writeFile, Charset.defaultCharset());

        Map<String, Long> collect =
                strings.stream().collect(groupingBy(Function.identity(), counting()));


        LinkedHashMap<String, Long> collect1 = collect.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> {
                            throw new IllegalStateException();
                        },
                        LinkedHashMap::new
                ));

        System.out.println(collect1);


    }
}
