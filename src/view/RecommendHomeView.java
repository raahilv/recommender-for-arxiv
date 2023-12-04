package view;

import interface_adapters.RecommendHome.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RecommendHomeView extends JPanel implements PropertyChangeListener {
    private JTabbedPane tabbedPanel;
    private JPanel CS;
    private JRadioButton computationalComplexityRadioButton;
    private JRadioButton computersAndSocietyRadioButton;
    private JRadioButton hardwareArchitectureRadioButton;
    private JRadioButton databasesRadioButton;
    private JRadioButton computationAndLanguageRadioButton;
    private JRadioButton computationalGeometryRadioButton;
    private JRadioButton computationalEngineeringFinanceAndRadioButton;
    private JRadioButton computerVisionAndPatternRadioButton;
    private JRadioButton digitalLibrariesRadioButton;
    private JRadioButton discreteMathematicsRadioButton;
    private JRadioButton dataStructuresAndAlgorithmsRadioButton;
    private JRadioButton emergingTechnologiesRadioButton;
    private JRadioButton formalLanguagesAndAutomataRadioButton;
    private JRadioButton generalLiteratureRadioButton;
    private JRadioButton multiagentSystemsRadioButton;
    private JRadioButton performanceRadioButton;
    private JRadioButton cryptographyAndSecurityRadioButton;
    private JRadioButton graphicsRadioButton;
    private JRadioButton multimediaRadioButton;
    private JRadioButton programmingLanguagesRadioButton;
    private JRadioButton computerScienceAndGameRadioButton;
    private JRadioButton mathematicalSoftwareRadioButton;
    private JRadioButton roboticsRadioButton;
    private JRadioButton humanComputerInteractionRadioButton;
    private JRadioButton numericalAnalysisRadioButton;
    private JRadioButton symbolicComputationRadioButton;
    private JRadioButton informationRetrievalRadioButton;
    private JRadioButton neuralAndEvolutionaryComputingRadioButton;
    private JRadioButton soundRadioButton;
    private JRadioButton informationTheoryRadioButton;
    private JRadioButton networkingAndInternetArchitectureRadioButton;
    private JRadioButton softwareEngineeringRadioButton;
    private JRadioButton machineLearningRadioButton;
    private JRadioButton otherComputerScienceRadioButton;
    private JRadioButton socialAndInformationNetworksRadioButton;
    private JRadioButton logicInComputerScienceRadioButton;
    private JRadioButton operatingSystemsRadioButton;
    private JRadioButton systemsAndControlRadioButton;
    private JRadioButton artificialIntelligenceRadioButton;
    private JRadioButton distributedParallelAndClusterRadioButton;
    private JPanel Economics;
    private JPanel EEsSS;
    private JPanel Math;
    private JPanel QBiology;
    private JPanel QFinance;
    private JPanel Stat;
    private JPanel Phy;
    private JButton recommendButton;
    private JCheckBox useSavedPapersForCheckBox;
    private JCheckBox prioritizeSubCategoriesCheckBox;
    private JCheckBox useUserRatingsForCheckBox;
    private JButton savedPapersButton;
    private JPanel mainPanel;
    private JLabel userLabel;
    ArrayList<JRadioButton> radioButtonsList = new ArrayList<>();
    HashMap<JRadioButton, List<String>> categories = new HashMap<JRadioButton, List<String>>();

    private RecommendHomeController recommendHomeController;
    private RecommendHomeViewModel recommendHomeViewModel;
    public RecommendHomeView(RecommendHomeViewModel viewModel, RecommendHomeController controller){
        recommendHomeController = controller;
        this.recommendHomeViewModel = viewModel;
        this.recommendHomeViewModel.addPropertyChangeListener(this);
        userLabel.setText(viewModel.getState().getUsername());
        add(recommendButton);
        recommendButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(recommendButton)) {
                            recommendHomeController.execute(getPreferredCategories(), prioritizeSubCategoriesCheckBox.isSelected(),useUserRatingsForCheckBox.isSelected(),useSavedPapersForCheckBox.isSelected(),userLabel.getText());
                        }
                    }
                }
        );
        savedPapersButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(savedPapersButton)) {
                            recommendHomeController.SwitchToLibrary(userLabel.getText());
                        }
                    }
                }
        );
        categories.put(artificialIntelligenceRadioButton, Arrays.asList("cs", "cs.AI"));
        categories.put(computationAndLanguageRadioButton, Arrays.asList("cs", "cs.CL"));
        categories.put(computationalComplexityRadioButton, Arrays.asList("cs", "cs.CC"));
        categories.put(computationalEngineeringFinanceAndRadioButton, Arrays.asList("cs", "cs.CE"));
        categories.put(computationalGeometryRadioButton, Arrays.asList("cs", "cs.CG"));
        categories.put(computerScienceAndGameRadioButton, Arrays.asList("cs", "cs.GT"));
        categories.put(computerVisionAndPatternRadioButton, Arrays.asList("cs", "cs.CV"));
        categories.put(computersAndSocietyRadioButton, Arrays.asList("cs", "cs.CY"));
        categories.put(cryptographyAndSecurityRadioButton, Arrays.asList("cs", "cs.CR"));
        categories.put(dataStructuresAndAlgorithmsRadioButton, Arrays.asList("cs", "cs.DS"));
        categories.put(databasesRadioButton, Arrays.asList("cs", "cs.DB"));
        categories.put(digitalLibrariesRadioButton, Arrays.asList("cs", "cs.DL"));
        categories.put(discreteMathematicsRadioButton, Arrays.asList("cs", "cs.DM"));
        categories.put(distributedParallelAndClusterRadioButton, Arrays.asList("cs", "cs.DC"));
        categories.put(emergingTechnologiesRadioButton, Arrays.asList("cs", "cs.ET"));
        categories.put(formalLanguagesAndAutomataRadioButton, Arrays.asList("cs", "cs.FL"));
        categories.put(generalLiteratureRadioButton, Arrays.asList("cs", "cs.GL"));
        categories.put(graphicsRadioButton, Arrays.asList("cs", "cs.GR"));
        categories.put(hardwareArchitectureRadioButton, Arrays.asList("cs", "cs.AR"));
        categories.put(humanComputerInteractionRadioButton, Arrays.asList("cs", "cs.HC"));
        categories.put(informationRetrievalRadioButton, Arrays.asList("cs", "cs.IR"));
        categories.put(informationTheoryRadioButton, Arrays.asList("cs", "cs.IT"));
        categories.put(logicInComputerScienceRadioButton, Arrays.asList("cs", "cs.LO"));
        categories.put(machineLearningRadioButton, Arrays.asList("cs", "cs.LG"));
        categories.put(mathematicalSoftwareRadioButton, Arrays.asList("cs", "cs.MS"));
        categories.put(multiagentSystemsRadioButton, Arrays.asList("cs", "cs.MA"));
        categories.put(multimediaRadioButton, Arrays.asList("cs", "cs.MM"));
        categories.put(networkingAndInternetArchitectureRadioButton, Arrays.asList("cs", "cs.NI"));
        categories.put(neuralAndEvolutionaryComputingRadioButton, Arrays.asList("cs", "cs.NE"));
        categories.put(numericalAnalysisRadioButton, Arrays.asList("cs", "cs.NA"));
        categories.put(operatingSystemsRadioButton, Arrays.asList("cs", "cs.OS"));
        categories.put(otherComputerScienceRadioButton, Arrays.asList("cs", "cs.OH"));
        categories.put(performanceRadioButton, Arrays.asList("cs", "cs.PF"));
        categories.put(programmingLanguagesRadioButton, Arrays.asList("cs", "cs.PL"));
        categories.put(roboticsRadioButton, Arrays.asList("cs", "cs.RO"));
        categories.put(socialAndInformationNetworksRadioButton, Arrays.asList("cs", "cs.SI"));
        categories.put(softwareEngineeringRadioButton, Arrays.asList("cs", "cs.SE"));
        categories.put(soundRadioButton, Arrays.asList("cs", "cs.SD"));
        categories.put(symbolicComputationRadioButton, Arrays.asList("cs", "cs.SC"));
        categories.put(systemsAndControlRadioButton, Arrays.asList("cs", "cs.SY"));



        radioButtonsList.add(computationalComplexityRadioButton);
        radioButtonsList.add(computersAndSocietyRadioButton);
        radioButtonsList.add(hardwareArchitectureRadioButton);
        radioButtonsList.add(databasesRadioButton);
        radioButtonsList.add(computationAndLanguageRadioButton);
        radioButtonsList.add(computationalGeometryRadioButton);
        radioButtonsList.add(computationalEngineeringFinanceAndRadioButton);
        radioButtonsList.add(computerVisionAndPatternRadioButton);
        radioButtonsList.add(digitalLibrariesRadioButton);
        radioButtonsList.add(discreteMathematicsRadioButton);
        radioButtonsList.add(dataStructuresAndAlgorithmsRadioButton);
        radioButtonsList.add(emergingTechnologiesRadioButton);
        radioButtonsList.add(formalLanguagesAndAutomataRadioButton);
        radioButtonsList.add(generalLiteratureRadioButton);
        radioButtonsList.add(multiagentSystemsRadioButton);
        radioButtonsList.add(performanceRadioButton);
        radioButtonsList.add(cryptographyAndSecurityRadioButton);
        radioButtonsList.add(graphicsRadioButton);
        radioButtonsList.add(multimediaRadioButton);
        radioButtonsList.add(programmingLanguagesRadioButton);
        radioButtonsList.add(computerScienceAndGameRadioButton);
        radioButtonsList.add(mathematicalSoftwareRadioButton);
        radioButtonsList.add(roboticsRadioButton);
        radioButtonsList.add(humanComputerInteractionRadioButton);
        radioButtonsList.add(numericalAnalysisRadioButton);
        radioButtonsList.add(symbolicComputationRadioButton);
        radioButtonsList.add(informationRetrievalRadioButton);
        radioButtonsList.add(neuralAndEvolutionaryComputingRadioButton);
        radioButtonsList.add(soundRadioButton);
        radioButtonsList.add(informationTheoryRadioButton);
        radioButtonsList.add(networkingAndInternetArchitectureRadioButton);
        radioButtonsList.add(softwareEngineeringRadioButton);
        radioButtonsList.add(machineLearningRadioButton);
        radioButtonsList.add(otherComputerScienceRadioButton);
        radioButtonsList.add(socialAndInformationNetworksRadioButton);
        radioButtonsList.add(logicInComputerScienceRadioButton);
        radioButtonsList.add(operatingSystemsRadioButton);
        radioButtonsList.add(systemsAndControlRadioButton);
        radioButtonsList.add(artificialIntelligenceRadioButton);
        radioButtonsList.add(distributedParallelAndClusterRadioButton);
        for(JRadioButton button : radioButtonsList){
            add(button);
        }
    }
    public List<List<String>> getPreferredCategories(){
        /*
        Returns a list of preferred categories in the format of (category, sub_category, description)
         */
        List<List<String>> finalCategories = new ArrayList<>();
        for(JRadioButton radioButton : radioButtonsList){
            if(radioButton.isSelected()){
                finalCategories.add(categories.get(radioButton));
            }
        }
        return finalCategories;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RecommendHomeState state = (RecommendHomeState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(RecommendHomeState state) {
        userLabel.setText(state.getUsername());
    }
}
