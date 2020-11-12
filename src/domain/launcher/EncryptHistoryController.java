package domain.launcher;

import domain.model.DomainException;

public class EncryptHistoryController implements GeheimschriftObserver {
    private EncryptHistoryView view;

    public EncryptHistoryController(GeheimschriftModel model) {
        if (model == null) throw new DomainException("GeheimschriftModel can't be null");
        model.addObserver(this);
    }

    public void setView(EncryptHistoryView view) {
        if (view == null) throw new DomainException("EncryptHistoryView can't be null");
        this.view = view;
    }

    @Override
    public void update(String s) {
        view.updateDisplay(s);
    }
}
