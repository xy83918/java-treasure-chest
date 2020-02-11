package cc.adaoz.empty;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author albert on 1/28/20
 */
@Slf4j
public class IsEmptyTest {

    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap<>(16);

        log.info("{}",map.isEmpty());
        map.put("1", "1");

        log.info("{}",map.isEmpty());
    }

}
