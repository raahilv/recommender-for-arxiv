package view;

import interface_adapters.save.SaveController;
import interface_adapters.save.SaveViewModel;
import interface_adapters.vote.VoteController;
import interface_adapters.vote.VoteViewModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class RecommendView extends JPanel implements ActionListener, PropertyChangeListener {
    private final SaveViewModel saveViewModel;
    private final VoteViewModel voteViewModel;
    private final SaveController saveController;
    private final VoteController voteController;


}
