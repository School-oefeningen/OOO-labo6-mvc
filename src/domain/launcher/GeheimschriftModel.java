package domain.launcher;


import domain.model.DomainException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class GeheimschriftModel extends Observable {
    List<GeheimschriftObserver> observers = new ArrayList<>();

    public GeheimschriftModel() {}

    public void updateEncryptHistory(String s) {
        updateObservers(s);
    }

    public void addObserver(GeheimschriftObserver obs) {
        if (obs == null) throw new DomainException("Observer can't be null");
        observers.add(obs);
    }

    public void removeObserver(GeheimschriftObserver obs) {
        if (obs == null) throw new DomainException("Observer can't be null");
        observers.remove(obs);
    }

    private void updateObservers(String s) {
        for (GeheimschriftObserver obs: observers) obs.update(s);
    }
}
