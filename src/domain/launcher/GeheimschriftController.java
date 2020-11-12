package domain.launcher;

import domain.model.DomainException;

public class GeheimschriftController implements GeheimschriftObserver {
    private GeheimschriftModel model;
    private GeheimschriftView view;

    public GeheimschriftController(GeheimschriftModel model) {
        if (model == null) throw new DomainException("GeheimschriftModel can't be null");
        this.model = model;
        model.addObserver(this);
    }

    public void setView(GeheimschriftView view) {
        if (view == null) throw new DomainException("GeheimschriftView can't be null");
        this.view = view;
    }

    public void updateEncryptHistory(String s) {
        model.updateEncryptHistory(s);
    }

    @Override
    public void update(String s) {
        view.setResult(s);
    }
}
