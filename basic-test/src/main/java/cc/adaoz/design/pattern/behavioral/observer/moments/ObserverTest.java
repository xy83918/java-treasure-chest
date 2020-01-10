package cc.adaoz.design.pattern.behavioral.observer.moments;

import java.util.LinkedList;
import java.util.List;

class User2 implements Observable, Observer {

    private List<Observer> friends;
    private String name;

    public User2(String name) {
        this.name = name;
        this.friends = new LinkedList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        this.friends.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.friends.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        this.friends.forEach(friend -> {
            friend.update(this.name, message);
        });
    }

    @Override
    public void update(String name, String message) {
        System.out.println("【" + this.name + "】看到【" + name + "】发的朋友圈：" + message);
    }

    public void sendMessage(String message) {
        this.notifyObservers(message);
    }
}

public class ObserverTest {

    public static void main(String[] args) {
        User2 xiaoMing2 = new User2("小明");
        User2 xiaoHong2 = new User2("小红");
        User2 xiaoDong2 = new User2("小东");
        xiaoMing2.addObserver(xiaoHong2);
        xiaoMing2.addObserver(xiaoDong2);
        xiaoMing2.sendMessage("今天真开心");
        xiaoMing2.removeObserver(xiaoHong2);
        xiaoMing2.sendMessage("希望明天也像今天一样开心");

        xiaoHong2.addObserver(xiaoMing2);
        xiaoHong2.addObserver(xiaoDong2);
        xiaoHong2.sendMessage("今天和小明吵架了，屏蔽他的朋友圈");

        xiaoDong2.addObserver(xiaoMing2);
        xiaoDong2.addObserver(xiaoHong2);
        xiaoDong2.sendMessage("小明和小红吵架了，夹在中间好尴尬");
    }
}