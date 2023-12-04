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
        categories.put(artificialIntelligenceRadioButton, Arrays.asList("cs", "cs.AI", "Artificial Intelligence"));
        categories.put(computationAndLanguageRadioButton, Arrays.asList("cs", "cs.CL", "Computation and Language"));
        categories.put(computationalComplexityRadioButton, Arrays.asList("cs", "cs.CC", "Computational Complexity"));
        categories.put(computationalEngineeringFinanceAndRadioButton, Arrays.asList("cs", "cs.CE", "Computational Engineering, Finance, and Science"));
        categories.put(computationalGeometryRadioButton, Arrays.asList("cs", "cs.CG", "Computational Geometry"));
        categories.put(computerScienceAndGameRadioButton, Arrays.asList("cs", "cs.GT", "Computer Science and Game Theory"));
        categories.put(computerVisionAndPatternRadioButton, Arrays.asList("cs", "cs.CV", "Computer Vision and Pattern Recognition"));
        categories.put(computersAndSocietyRadioButton, Arrays.asList("cs", "cs.CY", "Computers and Society"));
        categories.put(cryptographyAndSecurityRadioButton, Arrays.asList("cs", "cs.CR", "Cryptography and Security"));
        categories.put(dataStructuresAndAlgorithmsRadioButton, Arrays.asList("cs", "cs.DS", "Data Structures and Algorithms"));
        categories.put(databasesRadioButton, Arrays.asList("cs", "cs.DB", "Databases"));
        categories.put(digitalLibrariesRadioButton, Arrays.asList("cs", "cs.DL", "Digital Libraries"));
        categories.put(discreteMathematicsRadioButton, Arrays.asList("cs", "cs.DM", "Discrete Mathematics"));
        categories.put(distributedParallelAndClusterRadioButton, Arrays.asList("cs", "cs.DC", "Distributed, Parallel, and Cluster Computing"));
        categories.put(emergingTechnologiesRadioButton, Arrays.asList("cs", "cs.ET", "Emerging Technologies"));
        categories.put(formalLanguagesAndAutomataRadioButton, Arrays.asList("cs", "cs.FL", "Formal Languages and Automata Theory"));
        categories.put(generalLiteratureRadioButton, Arrays.asList("cs", "cs.GL", "General Literature"));
        categories.put(graphicsRadioButton, Arrays.asList("cs", "cs.GR", "Graphics"));
        categories.put(hardwareArchitectureRadioButton, Arrays.asList("cs", "cs.AR", "Hardware Architecture"));
        categories.put(humanComputerInteractionRadioButton, Arrays.asList("cs", "cs.HC", "Human-Computer Interaction"));
        categories.put(informationRetrievalRadioButton, Arrays.asList("cs", "cs.IR", "Information Retrieval"));
        categories.put(informationTheoryRadioButton, Arrays.asList("cs", "cs.IT", "Information Theory"));
        categories.put(logicInComputerScienceRadioButton, Arrays.asList("cs", "cs.LO", "Logic in Computer Science"));
        categories.put(machineLearningRadioButton, Arrays.asList("cs", "cs.LG", "Machine Learning"));
        categories.put(mathematicalSoftwareRadioButton, Arrays.asList("cs", "cs.MS", "Mathematical Software"));
        categories.put(multiagentSystemsRadioButton, Arrays.asList("cs", "cs.MA", "Multiagent Systems"));
        categories.put(multimediaRadioButton, Arrays.asList("cs", "cs.MM", "Multimedia"));
        categories.put(networkingAndInternetArchitectureRadioButton, Arrays.asList("cs", "cs.NI", "Networking and Internet Architecture"));
        categories.put(neuralAndEvolutionaryComputingRadioButton, Arrays.asList("cs", "cs.NE", "Neural and Evolutionary Computing"));
        categories.put(numericalAnalysisRadioButton, Arrays.asList("cs", "cs.NA", "Numerical Analysis"));
        categories.put(operatingSystemsRadioButton, Arrays.asList("cs", "cs.OS", "Operating Systems"));
        categories.put(otherComputerScienceRadioButton, Arrays.asList("cs", "cs.OH", "Other Computer Science"));
        categories.put(performanceRadioButton, Arrays.asList("cs", "cs.PF", "Performance"));
        categories.put(programmingLanguagesRadioButton, Arrays.asList("cs", "cs.PL", "Programming Languages"));
        categories.put(roboticsRadioButton, Arrays.asList("cs", "cs.RO", "Robotics"));
        categories.put(socialAndInformationNetworksRadioButton, Arrays.asList("cs", "cs.SI", "Social and Information Networks"));
        categories.put(softwareEngineeringRadioButton, Arrays.asList("cs", "cs.SE", "Software Engineering"));
        categories.put(soundRadioButton, Arrays.asList("cs", "cs.SD", "Sound"));
        categories.put(symbolicComputationRadioButton, Arrays.asList("cs", "cs.SC", "Symbolic Computation"));
        categories.put(systemsAndControlRadioButton, Arrays.asList("cs", "cs.SY", "Systems and Control"));



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
