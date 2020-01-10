package cc.adaoz.design.pattern.behavioral.observer.moments;

interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}