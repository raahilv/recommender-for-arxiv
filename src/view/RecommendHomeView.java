package view;

import entities.Category;
import interface_adapters.RecommendHome.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class RecommendHomeView {
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
    ArrayList<JRadioButton> radioButtonsList;
    HashMap<JRadioButton, Category> categories = new HashMap<JRadioButton, Category>();

    public RecommendHomeView(RecommendHomeController controller){
        recommendButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(recommendButton)) {
                            controller.execute(getPreferredCategories());
                        }
                    }
                }
        );

        categories.put(artificialIntelligenceRadioButton, new Category("cs", "cs.AI", "Artificial Intelligence"));
        categories.put(computationAndLanguageRadioButton, new Category("cs", "cs.CL", "Computation and Language"));
        categories.put(computationalComplexityRadioButton, new Category("cs", "cs.CC", "Computational Complexity"));
        categories.put(computationalEngineeringFinanceAndRadioButton, new Category("cs", "cs.CE", "Computational Engineering, Finance, and Science"));
        categories.put(computationalGeometryRadioButton, new Category("cs", "cs.CG", "Computational Geometry"));
        categories.put(computerScienceAndGameRadioButton, new Category("cs", "cs.GT", "Computer Science and Game Theory"));
        categories.put(computerVisionAndPatternRadioButton, new Category("cs", "cs.CV", "Computer Vision and Pattern Recognition"));
        categories.put(computersAndSocietyRadioButton, new Category("cs", "cs.CY", "Computers and Society"));
        categories.put(cryptographyAndSecurityRadioButton, new Category("cs", "cs.CR", "Cryptography and Security"));
        categories.put(dataStructuresAndAlgorithmsRadioButton, new Category("cs", "cs.DS", "Data Structures and Algorithms"));
        categories.put(databasesRadioButton, new Category("cs", "cs.DB", "Databases"));
        categories.put(digitalLibrariesRadioButton, new Category("cs", "cs.DL", "Digital Libraries"));
        categories.put(discreteMathematicsRadioButton, new Category("cs", "cs.DM", "Discrete Mathematics"));
        categories.put(distributedParallelAndClusterRadioButton, new Category("cs", "cs.DC", "Distributed, Parallel, and Cluster Computing"));
        categories.put(emergingTechnologiesRadioButton, new Category("cs", "cs.ET", "Emerging Technologies"));
        categories.put(formalLanguagesAndAutomataRadioButton, new Category("cs", "cs.FL", "Formal Languages and Automata Theory"));
        categories.put(generalLiteratureRadioButton, new Category("cs", "cs.GL", "General Literature"));
        categories.put(graphicsRadioButton, new Category("cs", "cs.GR", "Graphics"));
        categories.put(hardwareArchitectureRadioButton, new Category("cs", "cs.AR", "Hardware Architecture"));
        categories.put(humanComputerInteractionRadioButton, new Category("cs", "cs.HC", "Human-Computer Interaction"));
        categories.put(informationRetrievalRadioButton, new Category("cs", "cs.IR", "Information Retrieval"));
        categories.put(informationTheoryRadioButton, new Category("cs", "cs.IT", "Information Theory"));
        categories.put(logicInComputerScienceRadioButton, new Category("cs", "cs.LO", "Logic in Computer Science"));
        categories.put(machineLearningRadioButton, new Category("cs", "cs.LG", "Machine Learning"));
        categories.put(mathematicalSoftwareRadioButton, new Category("cs", "cs.MS", "Mathematical Software"));
        categories.put(multiagentSystemsRadioButton, new Category("cs", "cs.MA", "Multiagent Systems"));
        categories.put(multimediaRadioButton, new Category("cs", "cs.MM", "Multimedia"));
        categories.put(networkingAndInternetArchitectureRadioButton, new Category("cs", "cs.NI", "Networking and Internet Architecture"));
        categories.put(neuralAndEvolutionaryComputingRadioButton, new Category("cs", "cs.NE", "Neural and Evolutionary Computing"));
        categories.put(numericalAnalysisRadioButton, new Category("cs", "cs.NA", "Numerical Analysis"));
        categories.put(operatingSystemsRadioButton, new Category("cs", "cs.OS", "Operating Systems"));
        categories.put(otherComputerScienceRadioButton, new Category("cs", "cs.OH", "Other Computer Science"));
        categories.put(performanceRadioButton, new Category("cs", "cs.PF", "Performance"));
        categories.put(programmingLanguagesRadioButton, new Category("cs", "cs.PL", "Programming Languages"));
        categories.put(roboticsRadioButton, new Category("cs", "cs.RO", "Robotics"));
        categories.put(socialAndInformationNetworksRadioButton, new Category("cs", "cs.SI", "Social and Information Networks"));
        categories.put(softwareEngineeringRadioButton, new Category("cs", "cs.SE", "Software Engineering"));
        categories.put(soundRadioButton, new Category("cs", "cs.SD", "Sound"));
        categories.put(symbolicComputationRadioButton, new Category("cs", "cs.SC", "Symbolic Computation"));
        categories.put(systemsAndControlRadioButton, new Category("cs", "cs.SY", "Systems and Control"));


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
    }
    ArrayList<Category> getPreferredCategories(){
        ArrayList<Category> finalCategories = new ArrayList<Category>();
        for(JRadioButton radioButton : radioButtonsList){
            if(radioButton.isSelected()){
                finalCategories.add(categories.get(radioButton));
            }
        }
        return finalCategories;
    }
}
