package cc.adaoz.design.pattern.behavioral.observer.moments;

import java.util.LinkedList;
import java.util.List;

class User implements Observable {

    private List<Observer> friends;
    private String name;

    public User(String name) {
        this.name = name;
        this.friends = new LinkedList<>();
    }

    public void sendMessage(String message) {
        this.notifyObservers(message);
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
}

class Friend implements Observer {

    private String name;

    public Friend(String name) {
        this.name = name;
    }
    @Override
    public void update(String name, String message) {
        System.out.println("?" + this.name + "????" + name + "???????" + message);
    }
}

public class ObserverTest1 {

    public static void main(String[] args) {
        User xiaoMing = new User("小明");
        Friend xiaoHong = new Friend("小红");
        Friend xiaoDong = new Friend("小东");
        xiaoMing.addObserver(xiaoHong);
        xiaoMing.addObserver(xiaoDong);
        xiaoMing.sendMessage("今天真开心");
        // 小红和小明闹别扭了，小红取消订阅小明的朋友圈
        xiaoMing.removeObserver(xiaoHong);
        xiaoMing.sendMessage("希望明天也像今天一样开心");
    }

}
